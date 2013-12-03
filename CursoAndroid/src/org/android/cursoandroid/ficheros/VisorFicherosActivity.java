package org.android.cursoandroid.ficheros;

import org.android.cursoandroid.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VisorFicherosActivity extends Activity {
    /** Called when the activity is first created. */
	
	
	public String resultado = "";
	public TextView tView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) { 
        try {
        	Log.d("HolaMundoActivity", "Iniciando...");
        	super.onCreate(savedInstanceState);
        	Log.d("HolaMundoActivity", "Creando Vista Principal...");
        	setContentView(R.layout.visorficheros);
        	/*
        	Log.d("HolaMundoActivity", "Vista Principal creada Correctamente");
        	Button botonEntrada = (Button) this.findViewById(R.id.boton_entrada);
        	botonEntrada.setOnClickListener(new OnClickListener(){
        		public void onClick(View v) {
        			Intent i = new Intent(HolaMundoActivity.this,AskNameActivity.class);
        			String nombre = i.getDataString();
        			setContentView(R.layout.showname);
        			startActivityForResult(i, 1);
        			
        		}
        	});
        	
        	Button botonFicheros = (Button) this.findViewById(R.id.boton_ficheros);
        	botonFicheros.setOnClickListener(new OnClickListener() {
        		public void onClick(View v) {
        			
        			Intent i = new Intent(HolaMundoActivity.this,FicherosActivity.class);
        			startActivityForResult(i, 1);
        			
        		}
        	});
        	*/
        	
        	LinearLayout pantallaPrincipal = (LinearLayout)this.findViewById(R.id.LinearLayoutMain);
        	int numFicheros = R.raw.class.getDeclaredFields().length;
        	for (int i=0; i<numFicheros; i++) {
        		Button botonFich = new Button(this.getBaseContext());
        		botonFich.setId(i);
        		final String nombreFichero = R.raw.class.getDeclaredFields()[i].getName();
        		String textoBoton = "" + nombreFichero;
        		Log.d("HolaMundoActivity", textoBoton);
        		botonFich.setText(textoBoton);
        		botonFich.setOnClickListener(new OnClickListener() {
            		public void onClick(View v) {
            			
            			Intent i = new Intent(VisorFicherosActivity.this,MuestraFicheroActivity.class);
            			i.putExtra("nombreFichero", nombreFichero);
            			startActivityForResult(i, 1);
            			
            		}
            	});
        		
        		pantallaPrincipal.addView(botonFich);
        	}
        	
        	pantallaPrincipal.refreshDrawableState();
			
        	
        }
        
        catch (Exception e) {
        	Log.e("HolaMundoActivity", "Excepcion: " + e.toString());
        	Log.e("HolaMundoActivity", "Mensaje: " + e.getMessage());
        	e.printStackTrace();
        }
    }
    
    @Override 
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	/* Place out code to react on Activity-Result here. */ 
    	super.onActivityResult(requestCode, resultCode, data); 
    	switch(requestCode) { 
	    	case (1) : { 
	    		if (resultCode == Activity.RESULT_OK) { 
	    			resultado = data.getStringExtra("texto");
	    			Log.d("Resultado DEVUELTO: " , resultado);
	    			setContentView(R.layout.contenido_fichero);
	    			tView = (TextView)findViewById(R.id.TextViewFicheros);
	    			tView.setText("Resultado: " + resultado);
                	tView.refreshDrawableState();
                	//Typeface font= Typeface.createFromAsset(getAssets(), "ARIALN.TTF");
                	//tView.setTypeface(font);
                	
                	//Definimos el boton Volver
        			Button botonVolver = (Button) this.findViewById(R.id.boton_volver);
                	botonVolver.setOnClickListener(new OnClickListener(){
                		public void onClick(View v) {                			
                			Intent i = new Intent(VisorFicherosActivity.this,VisorFicherosActivity.class);
                			startActivityForResult(i, 1);
                		}
                	});
                	botonVolver.refreshDrawableState();
        			
	    		}
            break; 
	    	} 
    	} 
    }
    
    
}