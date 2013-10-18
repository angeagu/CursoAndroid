package org.android.cursoandroid.fragments;

import org.android.cursoandroid.R;
import org.android.cursoandroid.fragments.MenuFragment.MenuListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends Activity implements MenuListener {
	
	/* Definimos la actividad principal, que har� uso de 3 layouts
	 * distintos, en los que se utilizar�n los Fragments que
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
			//Est� presente en la vista el fragmento de detalle.
			String descripcion = "";
			if (etiqueta.equalsIgnoreCase("Hola Mundo")) {descripcion="Primera aplicaci�n b�sica Hola Mundo en Android";}
	        else if (etiqueta.equalsIgnoreCase("Formulario")) {descripcion="Aplicaci�n en la que se realiza un envio de datos de formularios a trav�s de la comunicaci�n entre actividades.";}
	        else if (etiqueta.equalsIgnoreCase("Ficheros")) {descripcion="Demo del API de Ficheros de Android Nativo. Tanto memoria externa como interna.";}
	        else if (etiqueta.equalsIgnoreCase("GPS")) {descripcion="Aplicaci�n Demo del API de GPS de Android Nativo. Obtiene las coordenadas GPS, calcula distancia, velocidad media y tiempo y muestra un mapa con la ruta.";}
	        else if (etiqueta.equalsIgnoreCase("Mapas")) {descripcion="Aplicaci�n que muestra la localizaci�n actual a trav�s del API de Google Maps v2.";}
	        else if (etiqueta.equalsIgnoreCase("Facturas")) {descripcion="Aplicaci�n Demo de 'Hoja de C�lculo' que permite crear, almacenar y modificar facturas. Muestra listados por mes y a�o. Almacenamiento con SQLite.";}
	        
			explanationFragment.mostrarExplicacion(descripcion);
		}
		else {
			/* Si no est� presente el fragmento de detalle, es porque
			 * no est� en la vista, as� que tenemos que lanzar un nuevo
			 * Intent para mostrar el detalle; con la actividad
			 * DetalleActivity
			 */
			Intent i = new Intent(this,ExplanationActivity.class);
			i.putExtra("menu_item", etiqueta);
			startActivity(i);
		}
		
	}

}
