package org.android.cursoandroid.mapas;

import org.android.cursoandroid.R;
import org.android.cursoandroid.utils.VentanaAlerta;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends android.support.v4.app.FragmentActivity {
    /** Called when the activity is first created. */
	
	//Obtenemos una referencia a un mapa.
    GoogleMap mapa;
    boolean locationReady = false;
    LocationManager locationManager;
    LocationListener listener;
    float zoom=16; //Nivel de zoom en el mapa.
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
      
        
        //Obtenemos la posición inicial a través del GPS.
        
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        //Obtenemos el provider del GPS.
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        	//Si el GPS está encendido...	
        	LocationProvider provider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
        		
        	listener = new LocationListener() {
        		public void onLocationChanged(Location location) {
       			
        			if (location!=null) {
        				double latitud = location.getLatitude();
        				double longitud = location.getLongitude();
        				mostrarMensaje("Latitud: " + latitud + " Longitud: " + longitud);
        				actualizarPosicionMapa(latitud,longitud,zoom);
        			}
        		}

        		public void onProviderDisabled(String provider) {
        			// TODO Auto-generated method stub
        			mostrarMensaje("GPS desactivado.");	
        		}

        		public void onProviderEnabled(String provider) {
        			// TODO Auto-generated method stub
        			mostrarMensaje("GPS activado.");
        		}

        		public void onStatusChanged(String provider, int status, Bundle extras) {
        			// TODO Auto-generated method stub
        			switch (status) {
        				case 0: mostrarMensaje("GPS FUERA DE SERVICIO"); break; 
        				case 1: mostrarMensaje("GPS TEMPORALMENTE NO DISPONIBLE"); break;
        				case 2: mostrarMensaje("GPS DISPONIBLE"); break;
        			}
        		}
        		
        	};
        	
        	//Nos suscribimos a la actualización de datos del provider.
        	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
        			1000, 0, listener);
        	
        }
        else {
        	//Si el GPS no está encendido...
        	VentanaAlerta.mostrarAlerta(this, "El GPS no está activado.");
        }
      
        
        
     }
    
    private void mostrarMensaje(String mensaje) {
		VentanaAlerta.mostrarAlerta(this, mensaje);
	}
    
    private void actualizarPosicionMapa(double latitud,double longitud, float zoom) {
    	
    	/* Obtenemos un objeto CameraUpdate, los movimientos en el mapa
         * serán a través de un objeto Camera.
         * Con el método .newLatLngZoom establecemos la latitud y longitud
         * y el zoom deseado.
         */
    	
    	setContentView(R.layout.mapsactivity);
    	
    	//Obtenemos una referencia a un mapa.
        mapa = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapa_prueba)).getMap();
        
        /*Podemos establecer el tipo de mapa: */
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //mapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        
    	
    	if (!locationReady) {
    		CameraUpdate cameraUpdate =
        	    CameraUpdateFactory.newLatLngZoom(new LatLng(latitud,longitud),zoom);
        	 
    		//Actualizamos la posición en el mapa.
        	mapa.moveCamera(cameraUpdate);
        	//Añadimos un marcador en el mapa para la posición actual.
        	mapa.addMarker(new MarkerOptions()
            .position(new LatLng(latitud,longitud))
            .title("PosicionActual."));
        	locationReady=true;
        	
        	//Podemos añadir un listener al marcador, por si se pulsa.
        	mapa.setOnMarkerClickListener(new OnMarkerClickListener() {
        	    public boolean onMarkerClick(Marker marker) {
        	        Toast.makeText(
        	            MapsActivity.this,
        	            " Coordenadas:\n" +
        	            " Latitud: " + marker.getPosition().latitude + "\n" +
        	            " Longitud: " + marker.getPosition().longitude + "\n",
        	            Toast.LENGTH_LONG).show();
        	        
        	        return false;
        	    }
        	});
        	
        	//Una vez seteada la posición, paramos la actualización de coordenadas
			locationManager.removeUpdates(listener);
    	}

    }
    
}