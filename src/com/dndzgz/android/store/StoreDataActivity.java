package com.dndzgz.android.store;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dndzgz.android.DndZgzApplication;
import com.dndzgz.android.MenuActivity;
import com.dndzgz.android.R;
import com.dndzgz.android.favorites.FavoritesActivity;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

public class StoreDataActivity extends Activity {

	private ProgressDialog m_ProgressDialog = null;
	private Runnable runnableStore;
	private JSONObject storeJson;
	private String storeInfo;
	private LinearLayout contenedor;
//	private JSONArray items;
	private DndZgzApplication dndzgzApp;
	private static final String TAG = "DndZgzAndroid";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dndzgzApp =  ((DndZgzApplication)this.getApplication());
		if (getIntent().hasExtra("object")) {
			storeInfo = getIntent().getStringExtra("object");			
		}
		try {
			storeJson = new JSONObject(storeInfo);
			storeJson.put("type", "store");
			dndzgzApp.setLastObject(storeJson);
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		
		// ACTION BAR
		setContentView(R.layout.data_info);
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
		Intent homeIntent = new Intent(this, MenuActivity.class);
		homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		actionBar.setHomeAction(new IntentAction(this, homeIntent,
				R.drawable.ic_title_home_default));
		///////////////////////
		String uri = "";
		try {
			uri = "google.navigation:q=" + storeJson.getString("lat") + "," + storeJson.getString("lon");			
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		Intent RouteIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));		
		final Action routeAction = new IntentAction(this, RouteIntent, R.drawable.ic_action_bar_navigation);
		actionBar.addAction(routeAction);
		
		Intent FavIntent = new Intent(StoreDataActivity.this, FavoritesActivity.class);
		FavIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		FavIntent.putExtra("action", "add");
		final Action FavAction = new IntentAction(this, FavIntent, R.drawable.ic_action_bar_star);	
		actionBar.addAction(FavAction);	
		actionBar.setTitle(R.string.establecimiento);
		// ////////////////
		contenedor = (LinearLayout) findViewById(R.id.contenedor);
		
		runnableStore = new Runnable() {
			@Override
			public void run() {
				getInfoStore();
			}
		};

		Thread thread = new Thread(null, runnableStore, "ObtenerInfoStore");
		thread.start();

		m_ProgressDialog = ProgressDialog.show(StoreDataActivity.this,
				getText(R.string.espere), getText(R.string.obteniendo_datos), true);

	}

	private void getInfoStore() {
		
		runOnUiThread(returnRes);
	}

	private Runnable returnRes = new Runnable() {

		@Override
		public void run() {
			m_ProgressDialog.dismiss();
			
			LinearLayout contentGeneral = new LinearLayout(
					getApplicationContext());
			contentGeneral
					.setLayoutParams(new LinearLayout.LayoutParams(
							LayoutParams.FILL_PARENT,
							LayoutParams.WRAP_CONTENT));
			contentGeneral.setPadding(0, 10, 0, 10);
			ImageView icono = new ImageView(getApplicationContext());
			icono.setImageDrawable(getResources().getDrawable(
					R.drawable.recarga));
			icono
					.setLayoutParams(new LinearLayout.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.FILL_PARENT));
			icono.setPadding(10, 0, 15, 0);
			LinearLayout contentData = new LinearLayout(
					getApplicationContext());
			contentData.setOrientation(LinearLayout.VERTICAL);
			contentData
					.setLayoutParams(new LinearLayout.LayoutParams(
							LayoutParams.FILL_PARENT,
							LayoutParams.WRAP_CONTENT));
			TextView titulo = new TextView(getApplicationContext());
			try {
				titulo.setText(storeJson.getString("title"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			titulo
					.setLayoutParams(new LinearLayout.LayoutParams(
							LayoutParams.FILL_PARENT,
							LayoutParams.WRAP_CONTENT));
			titulo.setTextSize(TypedValue.COMPLEX_UNIT_PX, 18);
			TextView subtitulo = new TextView(getApplicationContext());
			try {
				subtitulo.setText(storeJson.getString("subtitle"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			subtitulo
					.setLayoutParams(new LinearLayout.LayoutParams(
							LayoutParams.FILL_PARENT,
							LayoutParams.WRAP_CONTENT));
			subtitulo.setTextSize(TypedValue.COMPLEX_UNIT_PX, 18);
			contentData.addView(titulo);
			contentData.addView(subtitulo);
			contentGeneral.addView(icono);
			contentGeneral.addView(contentData);
			contenedor.addView(contentGeneral);
		}
	};		
}
