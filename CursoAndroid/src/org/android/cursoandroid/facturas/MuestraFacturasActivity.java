package org.android.cursoandroid.facturas;

import org.android.cursoandroid.R;
import org.android.cursoandroid.R.id;
import org.android.cursoandroid.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MuestraFacturasActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {     
		try {
			
			//Lo primero de todo, obtenemos el Layout sobre el que vamos
			//a trabajar.
			super.onCreate(savedInstanceState);
        	setContentView(R.layout.facturas);
			
        	//Obtenemos el elemento tabla sobre el que iremos añadiendo facturas.
        	TableLayout tableLayout = (TableLayout)this.findViewById(R.id.tablaFacturas);
        	
			FacturasSQLiteHelper fact = new FacturasSQLiteHelper(this);
			SQLiteDatabase db = fact.getWritableDatabase();
			Cursor c = db.rawQuery("SELECT concepto,fecha,importe FROM FACTURAS",null);
			
			//Nos aseguramos de que existe al menos un registro
			if (c.moveToFirst()) {
			//Recorremos el cursor hasta que no haya más registros
				do {
					String concepto = c.getString(0);
					String fecha = c.getString(1);
					String importe = String.valueOf(c.getDouble(2));
			
					//Creamos una nueva fila para la tabla de facturas
					TableRow tableRow = new TableRow(this);
					
					//Vamos creando los TextViews con la informacion y lo añadimos
					//al TableRow creado antes.
					TextView tViewConcepto = new TextView(this);
					tViewConcepto.setText(concepto);
					tableRow.addView(tViewConcepto);
					
					TextView tViewFecha = new TextView(this);
					tViewFecha.setText(fecha);
					tableRow.addView(tViewFecha);
					
					TextView tViewImporte = new TextView(this);
					tViewImporte.setText(importe);
					tableRow.addView(tViewImporte);
					
					//Finalmente añadimos la fila a la tabla.
					tableLayout.addView(tableRow);
					
				} while(c.moveToNext());
			}

			//Obtenemos el botón de nueva factura y cuando se pulse, redirigimos
			//a la Activity que mostrara el Formulario
			Button botonNuevaFactura = (Button)this.findViewById(R.id.botonNuevaFactura);
        	botonNuevaFactura.setOnClickListener(new OnClickListener() {
        		public void onClick(View v) {     
        			//Realizamos un Intent para pasar a la activity que mostrara
        			//el formulario para añadir la nueva factura.
        			Intent i = new Intent(MuestraFacturasActivity.this,NuevaFacturaActivity.class);
        			startActivity(i);
        		}        		
        	});
			
		}

		catch (Exception ex) {
			ex.printStackTrace();
			}
	}
	
}
	

