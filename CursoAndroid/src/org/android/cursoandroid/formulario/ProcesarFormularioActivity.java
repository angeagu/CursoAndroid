package org.android.cursoandroid.formulario;

import org.android.cursoandroid.R;
import org.android.cursoandroid.R.id;
import org.android.cursoandroid.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class ProcesarFormularioActivity extends Activity {
	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.datosformulario);
        
        String equipo = "";
        String competicion = "";
        String mostrarJugadores = "";
        
        Bundle extras = getIntent().getExtras();
		if(extras!=null){
		   equipo = extras.getString("equipo");
		   competicion = extras.getString("competicion");
		   mostrarJugadores = extras.getString("mostrarJugadores");
		}
		
        setContentView(R.layout.datosformulario);
        TextView textViewMostrarJugadores = (TextView) this.findViewById(R.id.mostrarJugadoresValue);
        textViewMostrarJugadores.setText(mostrarJugadores);
        
        TextView textViewEquipo = (TextView) this.findViewById(R.id.equipoValue);
        textViewEquipo.setText(equipo);
        
        TextView textViewCompeticion = (TextView) this.findViewById(R.id.competicionValue);
        textViewCompeticion.setText(competicion);
        
        
        
        
        
	 }
}
