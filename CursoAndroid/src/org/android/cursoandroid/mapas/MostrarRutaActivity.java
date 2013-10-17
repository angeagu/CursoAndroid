package org.android.cursoandroid.mapas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.android.cursoandroid.R;
import org.android.cursoandroid.utils.VentanaAlerta;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class MostrarRutaActivity extends android.support.v4.app.FragmentActivity {

	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 
		 Bundle extras = getIntent().getExtras();
		 int zoom = 16;
		 
		 if (extras!=null) {
		 
			 ArrayList mapaCoordenadas = (ArrayList)extras.getSerializable("mapa_coordenadas");

			 setContentView(R.layout.ruta);
	    	
	    	//Obtenemos una referencia a un mapa.
			 GoogleMap mapa = ((SupportMapFragment) getSupportFragmentManager()
	                .findFragmentById(R.id.mapa_ruta)).getMap();
	        
			 /*Podemos establecer el tipo de mapa: */
			 mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			 
			 PolylineOptions lineas = new PolylineOptions();
			 Iterator it = mapaCoordenadas.iterator();
			 boolean primeraCoordenada = true;
			 while (it.hasNext()) {
				 LatLng latlong = (LatLng)it.next();
				 if (primeraCoordenada) {
					 CameraUpdate cameraUpdate =
			        	    CameraUpdateFactory.newLatLngZoom(latlong,zoom);
			        	 
			    		//Actualizamos la posición en el mapa.
			        	mapa.moveCamera(cameraUpdate);
			 	 }
				 lineas.add(latlong);
			 }
	 
			 lineas.width(8);
			 lineas.color(Color.RED);
	 
			 mapa.addPolyline(lineas);
			 
		 }
		 else {
			 VentanaAlerta.mostrarDialogo(this, "No hay coordenadas GPS de ruta");
		 }

	 }
}
