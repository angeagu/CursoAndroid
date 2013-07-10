package org.android.cursoandroid.facturas;

import org.android.cursoandroid.R;
import org.android.cursoandroid.R.id;
import org.android.cursoandroid.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NuevaFacturaActivity extends Activity {

		@Override
	    public void onCreate(Bundle savedInstanceState) {     
			try {
				//Mostramos el layout con el formulario con las facturas.
				super.onCreate(savedInstanceState);
	        	setContentView(R.layout.nuevafactura);
	        	
	        	//Obtenemos el botón, para controlar que cuando se pulse,
	        	//se guarde la factura.
	        	
	        	Button botonGuardar = (Button)this.findViewById(R.id.botonGuardar);
	        	botonGuardar.setOnClickListener(new OnClickListener() {
	        		public void onClick(View v) {     
	        			guardarFactura();
	        		}        		
	        	});
	        	
	        	
			}
			catch (Exception ex) {
				
			}
		
		}
	
		private void guardarFactura() {
			//Al pulsar en el botón procedemos a guardar la factura.
			//Obtenemos los campos y los guardamos en la base de datos
			//Obtenemos los campos del formulario:
        	EditText editTextConcepto = (EditText) this.findViewById(R.id.EditTextConcepto);
        	EditText editTextFecha = (EditText) this.findViewById(R.id.EditTextFecha);
        	EditText editTextImporte = (EditText) this.findViewById(R.id.EditTextImporte);
			
        	String concepto = editTextConcepto.getText().toString();
        	String fecha = editTextFecha.getText().toString();
        	Double importe = Double.valueOf(editTextImporte.getText().toString());
        	
        	//Obtenemos el acceso a la BBDD
        	FacturasSQLiteHelper fact = new FacturasSQLiteHelper(this);
			SQLiteDatabase db = fact.getWritableDatabase();
			String insert = "INSERT INTO FACTURAS(concepto,fecha,importe) VALUES ('"+concepto+"','"+fecha+"','"+importe+"')";
			db.execSQL(insert);
			
			//Pasamos el control de nuevo a MuestraFacturasActivity
			Intent i = new Intent(NuevaFacturaActivity.this,MuestraFacturasActivity.class);
			startActivity(i);
			
		}
}
