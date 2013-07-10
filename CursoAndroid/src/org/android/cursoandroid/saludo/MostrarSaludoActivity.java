package org.android.cursoandroid.saludo;

import org.android.cursoandroid.R;
import org.android.cursoandroid.R.id;
import org.android.cursoandroid.R.layout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MostrarSaludoActivity extends Activity {
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.mostrarsaludo);
	        
	        String nombre = "";
	        String saludo = "BUENOS DIAS ";
	        TextView textView;
	        
	        Bundle extras = getIntent().getExtras();
			if(extras!=null){
			   nombre = extras.getString("nombre");
			   nombre = nombre.toUpperCase();
			}
			
			saludo = saludo + nombre;
			textView = (TextView)this.findViewById(R.id.TextViewMostrarSaludo);
			textView.setText(saludo);
			textView.setTextColor(Color.RED);
			textView.setTextSize(24);
	        
	    }

}
