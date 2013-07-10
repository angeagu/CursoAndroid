package org.android.cursoandroid.xml;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.android.cursoandroid.R;
import org.android.cursoandroid.R.id;
import org.android.cursoandroid.R.layout;
import org.android.cursoandroid.R.raw;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class XMLParserActivity extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	try {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.listadoequipos);
        
        //Obtenemos primeramente el LinearLayout:
        LinearLayout layoutEquipos = (LinearLayout)this.findViewById(R.id.layoutEquipos);
        
        int id = R.raw.class.getDeclaredField("equipos").getInt(this);
        InputStream inputStreamFile =  getResources().openRawResource(id);
        
        //Podemos utilizar el Parser SAX
        EquipoParserSAX parser = new EquipoParserSAX(inputStreamFile);
        //O también el Parser DOM. El resto de la activity será exactamente
        //igual independientemente del parser que utilicemos
        //EquipoParserDOM parser = new EquipoParserDOM(inputStreamFile);
        
        List<Equipo> equipos = parser.parse();
        Iterator<Equipo> it = equipos.iterator();
        while (it.hasNext()) {
        	Equipo equipo = it.next();
        	String nombre = equipo.getNombre();
        	String presidente = equipo.getPresidente();
        	String jugador1 = equipo.getJugador1().trim();
        	String jugador2 = equipo.getJugador2().trim();
        	String jugador3 = equipo.getJugador3().trim();
        	
        	TextView tViewEquipo = new TextView(this);
            tViewEquipo.setText("Equipo: " + nombre);
            //Añadimos el elemento al layout.
            layoutEquipos.addView(tViewEquipo);
            
            TextView tViewPresidente = new TextView(this);
            tViewPresidente.setText("Presidente: " + presidente);
            layoutEquipos.addView(tViewPresidente);
            
            TextView tViewJugadores = new TextView(this);
            tViewJugadores.setText("Jugadores: ");
            layoutEquipos.addView(tViewJugadores);
            
            ListView listViewJugadores = new ListView(this);
            listViewJugadores.setBackgroundColor(Color.WHITE);
	        
	        String[] jugadores = new String[3];
	        jugadores[0] = jugador1;
	        jugadores[1] = jugador2;
	        jugadores[2] = jugador3;
	        
	        ArrayAdapter<String> adaptadorJugadores =
	            new ArrayAdapter<String>(this,
	            		android.R.layout.simple_spinner_item, jugadores);
	     
	        listViewJugadores.setAdapter(adaptadorJugadores);
	        
	        layoutEquipos.addView(listViewJugadores);
	        
        

        }
        
        layoutEquipos.refreshDrawableState();
        
    	}
    	catch (Exception ex) {
    		ex.printStackTrace();
    	}
        
    }
	
}
