package org.android.cursoandroid.saludo;

import org.android.cursoandroid.R;
import org.android.cursoandroid.R.id;
import org.android.cursoandroid.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SaludoActivity extends Activity {

	EditText editText;
    Button boton;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
    	
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.saludo);
        	
        	editText = (EditText)this.findViewById(R.id.EditTextSaludo);
        	
        	boton = (Button)this.findViewById(R.id.boton_aceptar);
        	boton.setOnClickListener(new OnClickListener() {
        		public void onClick(View v) {
        			String nombre = SaludoActivity.this.editText.getText().toString();
        			//SaludoActivity.this.setResult(0, new Intent(nombre));
        			//SaludoActivity.this.finish();
        			
        			Log.i("SaludoActivity", "Nombre Introducido: " + nombre);
        			
        			Intent i = new Intent(SaludoActivity.this,MostrarSaludoActivity.class);
        			i.putExtra("nombre", nombre);
        			startActivityForResult(i, 1);
        			
        		}
        		
        	});
        
        }
        catch (Exception e) {
        	Log.e("HolaMundoActivity", "Excepcion: " + e.toString());
        }
    }
	
}
