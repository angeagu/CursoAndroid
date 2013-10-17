package org.android.cursoandroid.gps;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.android.cursoandroid.R;
import org.android.cursoandroid.mapas.MostrarRutaActivity;
import org.android.cursoandroid.utils.VentanaAlerta;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

public class TestLocationActivity extends Activity {
	    /** Called when the activity is first created. */
	    
	    private ArrayList mapaCoordenadas = new ArrayList();
	    private Date fechaInicio;
	    private Date fechaFin;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.location);
	        
	        TextView textViewLocation = (TextView)this.findViewById(R.id.textViewLocation);
	        textViewLocation.setTextSize(18);
	        textViewLocation.setTextColor(Color.RED);
	        textViewLocation.setBackgroundColor(Color.BLACK);
	        
	        final LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
	        //Obtenemos el provider del GPS.
	        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
	            
	        	//Si el GPS está encendido...	
	        	LocationProvider provider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
	        	String name = provider.getName();
	        	int accuracy = provider.getAccuracy();
	        	int powerRequirement = provider.getPowerRequirement();
	        
	        	//Ojo, con el método getAllProviders obtenemos todos los providers,
	        	//por ejemplo el GPS, el Network, etc.
	        
	        	//Con la clase Criteria podríamos definir criterios para elegir el
	        	//mejor provider con ciertas características:
	        
	        	/*
	        	 * Este objeto Criteria indica que queremos un Provider con precisión
	        	 * alta y que pueda proporcionar la altitud.
	        	 * 
	        	Criteria req = new Criteria();
	        	req.setAccuracy(Criteria.ACCURACY_FINE);
	        	req.setAltitudeRequired(true);
	        	locationManager.getBestProvider(criteria, false);
	        	 */
	        	final LocationListener listener = new LocationListener() {
	        		public void onLocationChanged(Location location) {
	        			// TODO Auto-generated method stub
	        			if (fechaInicio==null)
	        				fechaInicio = new Date();
	        			
	        			if (location!=null) {
	        				
	        				float precision = location.getAccuracy();
	        				double altitud = location.getAltitude();
	        				double latitud = location.getLatitude();
	        				double longitud = location.getLongitude();
	        				double velocidad = location.getSpeed();
	        				velocidad = velocidad * (3600/1000);
	        				long tiempo = location.getTime();
	        				Date time = new Date(tiempo);
	        				
	        				actualizarInterfaz(precision,altitud,latitud,longitud,velocidad,time);
	        			}
	        		}

	        		public void onProviderDisabled(String provider) {
	        			// TODO Auto-generated method stub
	        			mostrarMensaje("GPS desactivado.");	
	        		}

	        		public void onProviderEnabled(String provider) {
	        			// TODO Auto-generated method stub
	        			mostrarMensaje("GPS sactivado.");
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
	        	
	        	//Definimos el listener del Botón Obtener Datos GPS
	        	Button botonObtenerDatos = (Button) this.findViewById(R.id.botonObtenerDatosGps);
	        	botonObtenerDatos.setOnClickListener(new OnClickListener() {
	        		public void onClick(View v) {		       			
	        			/*
	        			Intent i = new Intent(TestLocationActivity.this,TestLocationActivity.class);
	        			//startActivityForResult(i, 1);
	        			startActivity(i);
	        			*/
	        			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
	    	        			1000, 0, listener);
	        			v.setEnabled(false);
	        			Button botonDetener = (Button) findViewById(R.id.botonDetener);
	        			botonDetener.setEnabled(true);
	        			mapaCoordenadas.clear();
	        		}
	        	});
	        	
	        	//Definimos el listener del Botón Detener
	        	Button botonDetener = (Button) this.findViewById(R.id.botonDetener);
	        	botonDetener.setOnClickListener(new OnClickListener() {
	        		public void onClick(View v) {   			
	        			//Detenemos la obtención de datos del GPS.
	        			locationManager.removeUpdates(listener);
	        			v.setEnabled(false);
	        			Button botonObtenerDatos = (Button) findViewById(R.id.botonObtenerDatosGps);
	        			botonObtenerDatos.setEnabled(true);
	        			fechaFin = new Date();
	        		}
	        	});
	        	
	        	//Definimos el listener del Botón Calcular Distancia
	        	Button botonCalcularDistancia = (Button) this.findViewById(R.id.botonCalcularDistancia);
	        	botonCalcularDistancia.setOnClickListener(new OnClickListener() {
	        		public void onClick(View v) {   			
	        			//Detenemos la obtención de datos del GPS.
	        			locationManager.removeUpdates(listener);
	        			calcularInforme(mapaCoordenadas);
	        		}
	        	});
	        	
	        	//Definimos el listener del Botón Detener
	        	Button botonVerRuta = (Button) this.findViewById(R.id.botonVerRuta);
	        	botonVerRuta.setOnClickListener(new OnClickListener() {
	        		public void onClick(View v) {   			
	        			Intent i = new Intent(TestLocationActivity.this,MostrarRutaActivity.class);
	        			Bundle bundle = new Bundle();
	        			i.putExtra("mapa_coordenadas", mapaCoordenadas);
	        			startActivity(i);
	        		}
	        	});
	        	
	        	
	        }
	        else {
	        	//Si el GPS no está encendido...
	        	VentanaAlerta.mostrarAlerta(this, "El GPS no está activado.");
	        }
	        
	        
	    }
	  
	private void actualizarInterfaz (float precision,double altitud,
			double latitud,double longitud,double velocidad,Date time) {
		
		
		EditText etLatitud = (EditText) this.findViewById(R.id.EditTextLatitud);
		etLatitud.setText(Double.toString(latitud));
		EditText etLongitud = (EditText) this.findViewById(R.id.EditTextLongitud);
		etLongitud.setText(Double.toString(longitud));
		mapaCoordenadas.add(new LatLng(latitud,longitud));
		
		EditText etVelocidad = (EditText) this.findViewById(R.id.EditTextVelocidad);
		etVelocidad.setText(Double.toString(velocidad));
		EditText etTiempo = (EditText) this.findViewById(R.id.EditTextTiempo);
		etTiempo.setText((time.toGMTString()));
		EditText etPrecision = (EditText) this.findViewById(R.id.EditTextPrecision);
		etPrecision.setText(Float.toString(precision));
		EditText etAltitud = (EditText) this.findViewById(R.id.EditTextAltitud);
		etAltitud.setText(Double.toString(altitud));
		
	}
	
	private void mostrarMensaje(String mensaje) {
		VentanaAlerta.mostrarAlerta(this, mensaje);
	}
	
	private void calcularInforme(ArrayList mapaCoordenadas) {
		double distancia = 0;
		double latitud1;
		double longitud1;
		double latitud2;
		double longitud2;
		
		//Primero el tiempo empleado.
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(fechaInicio);
		c2.setTime(fechaFin);
		long segundosTotales = cantidadTotalSegundos(c1,c2);
		long horas = segundosTotales/3600;  
        long minutos = (segundosTotales-(3600*horas))/60;  
        long segundos = segundosTotales-((horas*3600)+(minutos*60)); 
		
		if (mapaCoordenadas.isEmpty()) {
			VentanaAlerta.mostrarAlerta(this, "No hay coordenadas GPS disponibles.");
		}
		else {
			Iterator it = mapaCoordenadas.iterator();
			while (it.hasNext()) {
				//Primera coordenada
				LatLng latlong = (LatLng)it.next();
				latitud1 = latlong.latitude;
				longitud1 = latlong.longitude;
				//Segunda coordenada
				if (it.hasNext()) {
					//Si no hay siguiente es que no hay coordenada.
					LatLng latlong2 = (LatLng)it.next();
					latitud2 = latlong2.latitude;
					longitud2 = latlong2.longitude;
					distancia = distancia + distancia(latitud1,longitud1,latitud2,longitud2);
				}
			}
		}
		
		//Construimos el mensaje de informe.
		DecimalFormat df = new DecimalFormat("0.000");
		String mensaje = "Tiempo: " + horas + " horas " + minutos + " minutos " +
		segundos + " segundos " + System.getProperty("line.separator");
		mensaje+="Distancia (km): " + df.format(distancia) + System.getProperty("line.separator");
		
		//Calculamos la velocidad media en kilometros hora.
		double metrossegundo = distancia * 1000 / segundosTotales;
		double kilometroshora = metrossegundo * (3600/1000);
		mensaje+="Velocidad Media (km/h): " + df.format(kilometroshora) + System.getProperty("line.separator");
		
		//VentanaAlerta.mostrarAlerta(this,mensaje);
		VentanaAlerta.mostrarDialogo(this, mensaje);
	}
	
	private double distancia(double La1, double Lo1, double La2, double Lo2) {
        double Distancia = 0, Distancia1 = 0;
        double r = 6378;

        //Punto inicial
        La1 = La1* Math.PI / 180;
        Lo1 = Lo1 * Math.PI / 180;

        // Punto final
        La2 = La2 * Math.PI / 180;
        Lo2 = Lo2 * Math.PI / 180;        
        //Distancia = 6378*Math.acos(Math.sin(La1)*Math.sin(La2)+ Math.cos(La1)* Math.cos(La2)*Math.cos(Lo1 - Lo2));
        Distancia1 = Math.pow(Math.sin((Lo2 - Lo1) / 2), 2);
        Distancia1 = Math.cos(La1) * Math.cos(La2) * Distancia1;
        Distancia1 = Math.pow(Math.sin((La2 - La1) / 2), 2) + Distancia1;
        Distancia1 = Math.sqrt(Distancia1);
        Distancia1 = 2 * r * Math.asin(Distancia1);
        return Distancia1;
  }
	
	public long cantidadTotalSegundos(Calendar fechaInicial ,Calendar fechaFinal) {  
			long totalSegundos=0; 
		
			totalSegundos=((fechaFinal.getTimeInMillis()-fechaInicial.getTimeInMillis())/1000); 
			return totalSegundos;
		
	}
	    
}
