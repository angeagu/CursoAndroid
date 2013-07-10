package org.android.cursoandroid.formulario;

import org.android.cursoandroid.R;
import org.android.cursoandroid.R.id;
import org.android.cursoandroid.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MostrarFormularioActivity extends Activity {

		 public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.formulario);		 
		        
		        		        
		        //Obtenemos el LinearLayout
		        ListView listView = (ListView)this.findViewById(R.id.listViewJugadores);
		        
		        final String[] jugadores =
		        new String[]{"Messi","Cristiano Ronaldo","Xavi","Casillas","Iniesta","Falcao","Courtois"};
		        ArrayAdapter<String> adaptadorJugadores =
		            new ArrayAdapter<String>(this,
		                android.R.layout.simple_spinner_item, jugadores);
		     
		        listView.setAdapter(adaptadorJugadores);
		        
		        //Obtenemos el Spinner
		        Spinner competicion = (Spinner)findViewById(R.id.spinnerCompeticion);
		         
		        //Creamos las opciones para el selector. Habrá tres, Liga, Champions y Copa
		        final String[] competiciones =
			        new String[]{"Liga","Champions League","Copa"};
			    ArrayAdapter<String> adaptadorCompeticiones =
			            new ArrayAdapter<String>(this,
			                android.R.layout.simple_spinner_item, competiciones);

			    adaptadorCompeticiones.setDropDownViewResource(
		                android.R.layout.simple_spinner_dropdown_item);
		        competicion.setAdapter(adaptadorCompeticiones);
		        
		        
		        Button boton = (Button)this.findViewById(R.id.boton_enviar);
		    	boton.setOnClickListener(new OnClickListener() {
		    		public void onClick(View v) {
		    			
		    			String mostrarJugadores;
		    	        CheckBox checkBox = (CheckBox)MostrarFormularioActivity.this.findViewById(R.id.checkBoxFormulario);
		    	        if (checkBox.isChecked()) {
		    	        	mostrarJugadores = "SI";
		    	        } else {
		    	        	mostrarJugadores = "NO";
		    	        }
		    	        
		    	        String equipo = "";
		    	        RadioGroup radioGroupEquipos = (RadioGroup)MostrarFormularioActivity.this.findViewById(R.id.radioGroupEquipos);
		    	        int idRadioButton = radioGroupEquipos.getCheckedRadioButtonId();
		    	        RadioButton radioButton = (RadioButton) findViewById(idRadioButton);
		    	        equipo = radioButton.getText().toString();
		    	        /*
		    	        if (idEquipo == 0) {
		    	        	equipo = "Real Madrid";
		    	        }
		    	        else if (idEquipo == 1) {
		    	        	equipo = "Barcelona"; 
		    	        }
		    	        else if (idEquipo == 2) {
		    	        	equipo = "Atlético Madrid";
		    	        }
		    	        */
		    	        
		    	        String competicion;
		    	        Spinner spinnerCompeticion = (Spinner)MostrarFormularioActivity.this.findViewById(R.id.spinnerCompeticion);
		    	        competicion = String.valueOf(spinnerCompeticion.getSelectedItem());
		    	      
		    			Intent i = new Intent(MostrarFormularioActivity.this,ProcesarFormularioActivity.class);
		    			i.putExtra("mostrarJugadores", mostrarJugadores);
		    			i.putExtra("equipo",equipo);
		    			i.putExtra("competicion",competicion);
		    			
		    			startActivityForResult(i, 1);
		    			
		    		}
		    		
		    	});
		        
		 }
		 
}
