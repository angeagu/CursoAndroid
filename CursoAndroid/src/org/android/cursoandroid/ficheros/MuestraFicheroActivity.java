package org.android.cursoandroid.ficheros;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.android.cursoandroid.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MuestraFicheroActivity extends Activity {
	
	TextView tView;
	String nombreFichero = "/res/raw/prueba.txt";
	StringBuffer sBuffer = new StringBuffer();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
    	
        	super.onCreate(savedInstanceState);
        	
        			Log.d("FicherosActivity","Entrando en FicherosActivity...");
        			
        			/*
        			java.io.File fich = new java.io.File("./data");
        			if (fich.isDirectory()) {
        				Log.d("listaFicheros", "Es directorio!");
        				String[] array = fich.list();
        				for (int i=0;i<array.length;i++) {
        					String nombreFichero = (String) array[i];
        					sBuffer.append(nombreFichero);
        					sBuffer.append("\n");
        				}
        				
        			}
        			else {
        				Log.d("listaFicheros", "No es un directorio");
        			}
        			*/
        			
        			
        			
        			
        			Bundle extras = getIntent().getExtras();
        			if(extras!=null){
        			   nombreFichero = extras.getString("nombreFichero");
        			}
        			
        			Log.d("FicherosActivity", "Nombre Fichero Recibido: " + nombreFichero);
        			int id = R.raw.class.getDeclaredField(nombreFichero).getInt(this);
        			Log.d("FicherosActivity", "Valor de ID: " + id);
        			InputStream inputFile =  getResources().openRawResource(id);
        			
        			InputStreamReader isr = new InputStreamReader(inputFile);
        			BufferedReader breader = new BufferedReader(isr);
        			
        			//sBuffer.append("\u00A9");
        			String linea = breader.readLine();
        			while (linea !=null) {
        				sBuffer.append(linea).append("\n");
        				linea = breader.readLine();
        			}
        			
        			  
        			Intent resultIntent = new Intent();
        			Log.d("FicherosActivity","Devolviendo resultado: " + sBuffer.toString());
        			resultIntent.putExtra("texto", sBuffer.toString());
        			setResult(Activity.RESULT_OK,resultIntent);
        			finish();                	
                	
        
        }
        catch (Exception e) {
        	Log.e("FicherosActivity", "Excepcion: " + e.toString());
        	Log.e("FicherosActivity", "Excepcion: " + e.getMessage());
        	e.printStackTrace();
        }
    }

}
