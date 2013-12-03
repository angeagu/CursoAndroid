package org.android.cursoandroid.ficheros;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.android.cursoandroid.R;
import org.android.cursoandroid.R.id;
import org.android.cursoandroid.R.layout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class FicheroMemInternaActivity extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.holamundo);
        
        TextView textView = (TextView)this.findViewById(R.id.textViewHola);
        
        try {
        		//Primero Realizamos la escritura del fichero en memoria interna.
        		OutputStreamWriter foutsw=
        			new OutputStreamWriter(
        					openFileOutput("ficheroTest_mem_int.txt", Context.MODE_WORLD_WRITEABLE));
        		foutsw.write("Hola Mundo!!!");
        		foutsw.close();
        		
        		//Posteriormente procedemos a leer el fichero desde la memoria interna
        		BufferedReader fin =
        			new BufferedReader( new
        			InputStreamReader(openFileInput("ficheroTest_mem_int.txt")));
        			String texto = fin.readLine();
        			//Ponemos en reverso el texto, para comprobar que lo hemos leido bien.
        			texto = texto + " Leido desde la memoria interna del teléfono.";
        			fin.close();
        			textView.setText(texto);
        		
        	}
        	catch (Exception ex) {
        		Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        	}
        
    }
	
}
