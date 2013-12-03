package org.android.cursoandroid.listener;

import org.android.cursoandroid.camara.CamaraActivity;
import org.android.cursoandroid.facturas.MuestraFacturasActivity;
import org.android.cursoandroid.ficheros.FicheroMemInternaActivity;
import org.android.cursoandroid.ficheros.VisorFicherosActivity;
import org.android.cursoandroid.formulario.MostrarFormularioActivity;
import org.android.cursoandroid.gps.TestLocationActivity;
import org.android.cursoandroid.holamundo.HolaMundoActivity;
import org.android.cursoandroid.mapas.MapsActivity;
import org.android.cursoandroid.saludo.SaludoActivity;
import org.android.cursoandroid.xml.XMLParserActivity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;


public class TextViewOnClickListener implements OnClickListener {

	private static TextViewOnClickListener listener;
	
	private enum TextViews {
		TextViewHolaMundo,
		TextViewMostrarSaludo,
		TextViewProcesarFormulario,
		TextViewDemoAppFacturas,
		TextViewVisorFicheros,
		TextViewProcesadoXML,
		TextViewFicheroMemInterna,
		TextViewTestLocationGPS,
		TextViewDemoMapas,
		TextViewCamara,
		TextViewFragments
	}
	
	public static TextViewOnClickListener getInstance() {
		
		if (listener==null)
			listener = new TextViewOnClickListener();
		
		return listener;
	}
	
    public void onClick(View v) {
        // TODO Auto-generated method stub
    	//Intent que invocará a la actividad que se ha pulsado en el menu
    	Intent i;
    	
    	//Recogemos el nombre del textView que se ha pulsado.
    	String tag = ((String)v.getTag());
    	TextViews textView = TextViews.valueOf(tag);
    	
    	switch (textView) {
    		case TextViewHolaMundo: i = new Intent(v.getContext(),HolaMundoActivity.class); break;
    		case TextViewMostrarSaludo: i = new Intent(v.getContext(),SaludoActivity.class); break;
    		case TextViewProcesarFormulario: i = new Intent(v.getContext(),MostrarFormularioActivity.class); break;
    		case TextViewDemoAppFacturas: i = new Intent(v.getContext(),MuestraFacturasActivity.class); break;
    		case TextViewVisorFicheros: i = new Intent(v.getContext(),VisorFicherosActivity.class); break;
    		case TextViewProcesadoXML: i = new Intent(v.getContext(),XMLParserActivity.class); break;
    		case TextViewFicheroMemInterna: i = new Intent(v.getContext(),FicheroMemInternaActivity.class); break;
    		case TextViewTestLocationGPS: i = new Intent(v.getContext(),TestLocationActivity.class); break;
    		case TextViewDemoMapas: i = new Intent(v.getContext(),MapsActivity.class); break;
    		case TextViewCamara: i = new Intent(v.getContext(),CamaraActivity.class); break;
    		case TextViewFragments: i = new Intent(v.getContext(),org.android.cursoandroid.fragments.MainActivity.class); break;
    		default : i = new Intent(v.getContext(),org.android.cursoandroid.main.MainActivity.class); break;
    	}
		v.getContext().startActivity(i);
   		
    }
	
    
}

