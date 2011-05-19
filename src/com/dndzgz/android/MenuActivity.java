package com.dndzgz.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;

public class MenuActivity extends Activity implements OnClickListener {

	private ImageView btnAutobuses;
	private ImageView btnBizis;
	private ImageView btnTranvia;
	private ImageView btnRecarga;
	private ImageView btnFarmacia;
	private ImageView btnGasolinera;
	private ImageView btnTaxi;
	private ImageView btnParking;
	private ImageView btnWifis;
	private ImageView btnFavoritos;
	private ImageView btnBuscar;
	private EditText busStop;
	private static final String TAG = "DndZgzAndroid";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setHomeAction(new NullAction());
		actionBar.setTitle(R.string.dndzgz);
		
		btnAutobuses = (ImageView) findViewById(R.id.bus);
		btnAutobuses.setOnClickListener(this);

		btnBizis = (ImageView) findViewById(R.id.bizi);
		btnBizis.setOnClickListener(this);

		btnTranvia = (ImageView) findViewById(R.id.tranvia);
		btnTranvia.setOnClickListener(this);

		btnRecarga = (ImageView) findViewById(R.id.recarga);
		btnRecarga.setOnClickListener(this);

		btnFarmacia = (ImageView) findViewById(R.id.farmacia);
		btnFarmacia.setOnClickListener(this);

		btnGasolinera = (ImageView) findViewById(R.id.gas);
		btnGasolinera.setOnClickListener(this);

		btnTaxi = (ImageView) findViewById(R.id.taxi);
		btnTaxi.setOnClickListener(this);

		btnParking = (ImageView) findViewById(R.id.parking);
		btnParking.setOnClickListener(this);

		btnWifis = (ImageView) findViewById(R.id.wifi);
		btnWifis.setOnClickListener(this);

		btnFavoritos = (ImageView) findViewById(R.id.fav);
		btnFavoritos.setOnClickListener(this);
		
		btnBuscar = (ImageView) findViewById(R.id.search);
		btnBuscar.setOnClickListener(this);
		
		busStop = (EditText) findViewById(R.id.txtParada);
	}

	@Override
	public void onClick(View v) {
		if (v == btnAutobuses) {
			Log.w(TAG, "Boton Autobus"); // warning
			 Intent autobusesIntent = new Intent(MenuActivity.this,
			 BusesActivity.class);
			 autobusesIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
			 MenuActivity.this.startActivity(autobusesIntent);
		} else if (v == btnBizis) {
			Log.w(TAG, "Boton Bicis"); // warning
			// Intent biziIntent = new Intent(MenuActivity.this,
			// BiziActivity.class);
			// biziIntent.putExtra("listadoBizis", listadoBizis);
			// biziIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// MenuActivity.this.startActivity(biziIntent);
		} else if (v == btnTranvia) {
			Log.w(TAG, "Boton Tranvia"); // warning
			// Intent wifisIntent = new Intent(MenuActivity.this,
			// RouteActivity.class);
			// wifisIntent.putExtra("listadoWifis", listadoWifis);
			// wifisIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// MenuActivity.this.startActivity(wifisIntent);
		}else if (v == btnRecarga) {
			Log.w(TAG, "Boton Recarga"); // warning
			
		}else if (v == btnFarmacia) {
			Log.w(TAG, "Boton Farmacia"); // warning
			
		}else if (v == btnGasolinera) {
			Log.w(TAG, "Boton Gasolinera"); // warning
			
		}else if (v == btnTaxi) {
			Log.w(TAG, "Boton Taxi"); // warning
			
		}else if (v == btnParking) {
			Log.w(TAG, "Boton Parking"); // warning
			
		}else if (v == btnWifis) {
			Log.w(TAG, "Boton Wifi"); // warning
			
		}else if (v == btnFavoritos) {
			Log.w(TAG, "Boton Favoritos"); // warning
			
		}else if (v == btnBuscar) {
			Log.w(TAG, "Boton Buscar"); // warning
			String textoFiltro = busStop.getText().toString();
			Intent autobusesIntent = new Intent(MenuActivity.this,
			BusesActivity.class);
			autobusesIntent.putExtra("filtro", textoFiltro);
			autobusesIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
			MenuActivity.this.startActivity(autobusesIntent);
			
		}
	}

	private class NullAction implements Action {

		@Override
		public int getDrawable() {
			return R.drawable.ic_logo_dndzgz;
		}

		@Override
		public void performAction(View view) {

		}

	}

}
