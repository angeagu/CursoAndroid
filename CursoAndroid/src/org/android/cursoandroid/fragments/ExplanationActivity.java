package org.android.cursoandroid.fragments;

import org.android.cursoandroid.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ExplanationActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.fragment_explanation);
        
        TextView textViewExplicacion = (TextView)
        		this.findViewById(R.id.textViewExplicacion);
        String item = (String)getIntent().getExtras().get("menu_item");
        String descripcion = "";
        
        if (item.equalsIgnoreCase("Hola Mundo")) {descripcion="Primera aplicación básica Hola Mundo en Android";}
        else if (item.equalsIgnoreCase("Formulario")) {descripcion="Aplicación en la que se realiza un envio de datos de formularios a través de la comunicación entre actividades.";}
        else if (item.equalsIgnoreCase("Ficheros")) {descripcion="Demo del API de Ficheros de Android Nativo. Tanto memoria externa como interna.";}
        else if (item.equalsIgnoreCase("GPS")) {descripcion="Aplicación Demo del API de GPS de Android Nativo. Obtiene las coordenadas GPS, calcula distancia, velocidad media y tiempo y muestra un mapa con la ruta.";}
        else if (item.equalsIgnoreCase("Mapas")) {descripcion="Aplicación que muestra la localización actual a través del API de Google Maps v2.";}
        else if (item.equalsIgnoreCase("Facturas")) {descripcion="Aplicación Demo de 'Hoja de Cálculo' que permite crear, almacenar y modificar facturas. Muestra listados por mes y año. Almacenamiento con SQLite.";}
        
        textViewExplicacion.setText(descripcion);
        
    }
	
}
