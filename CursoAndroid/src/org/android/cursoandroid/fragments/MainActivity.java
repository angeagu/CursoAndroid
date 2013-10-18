package org.android.cursoandroid.fragments;

import org.android.cursoandroid.R;
import org.android.cursoandroid.fragments.MenuFragment.MenuListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends Activity implements MenuListener {
	
	/* Definimos la actividad principal, que hará uso de 3 layouts
	 * distintos, en los que se utilizarán los Fragments que
	 * acabamos de definir.
	 */
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        
        MenuFragment menuFragment = (MenuFragment)
        		this.getFragmentManager().findFragmentById(R.id.MenuFragment);
        menuFragment.setListener(this);
    }

	public void onMenuItemSelected(String etiqueta) {
		// TODO Auto-generated method stub
		ExplanationFragment explanationFragment = 
				(ExplanationFragment)this.getFragmentManager().findFragmentById(R.id.ExplanationFragment);
		
		if (explanationFragment!=null) {
			//Está presente en la vista el fragmento de detalle.
			String descripcion = "";
			if (etiqueta.equalsIgnoreCase("Hola Mundo")) {descripcion="Primera aplicación básica Hola Mundo en Android";}
	        else if (etiqueta.equalsIgnoreCase("Formulario")) {descripcion="Aplicación en la que se realiza un envio de datos de formularios a través de la comunicación entre actividades.";}
	        else if (etiqueta.equalsIgnoreCase("Ficheros")) {descripcion="Demo del API de Ficheros de Android Nativo. Tanto memoria externa como interna.";}
	        else if (etiqueta.equalsIgnoreCase("GPS")) {descripcion="Aplicación Demo del API de GPS de Android Nativo. Obtiene las coordenadas GPS, calcula distancia, velocidad media y tiempo y muestra un mapa con la ruta.";}
	        else if (etiqueta.equalsIgnoreCase("Mapas")) {descripcion="Aplicación que muestra la localización actual a través del API de Google Maps v2.";}
	        else if (etiqueta.equalsIgnoreCase("Facturas")) {descripcion="Aplicación Demo de 'Hoja de Cálculo' que permite crear, almacenar y modificar facturas. Muestra listados por mes y año. Almacenamiento con SQLite.";}
	        
			explanationFragment.mostrarExplicacion(descripcion);
		}
		else {
			/* Si no está presente el fragmento de detalle, es porque
			 * no está en la vista, así que tenemos que lanzar un nuevo
			 * Intent para mostrar el detalle; con la actividad
			 * DetalleActivity
			 */
			Intent i = new Intent(this,ExplanationActivity.class);
			i.putExtra("menu_item", etiqueta);
			startActivity(i);
		}
		
	}

}
