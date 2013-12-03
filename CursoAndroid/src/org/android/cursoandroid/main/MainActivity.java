package org.android.cursoandroid.main;

import org.android.cursoandroid.R;
import org.android.cursoandroid.listener.TextViewOnClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextViewOnClickListener listener = TextViewOnClickListener.getInstance();
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		TextView textViewHolaMundo = (TextView) findViewById(R.id.TextViewHolaMundo);
		textViewHolaMundo.setTextAppearance(this, R.style.MenuStyle);
		textViewHolaMundo.setTag(new String("TextViewHolaMundo"));
		textViewHolaMundo.setOnClickListener(listener);
		
		TextView textViewCamara = (TextView) findViewById(R.id.TextViewCamara);
		textViewCamara.setTextAppearance(this, R.style.MenuStyle);
		textViewCamara.setTag(new String("TextViewCamara"));
		textViewCamara.setOnClickListener(listener);
		
		TextView textViewDemoAppFacturas = (TextView) findViewById(R.id.TextViewDemoAppFacturas);
		textViewDemoAppFacturas.setTextAppearance(this, R.style.MenuStyle);
		textViewDemoAppFacturas.setTag(new String("TextViewDemoAppFacturas"));
		textViewDemoAppFacturas.setOnClickListener(listener);
		
		TextView textViewDemoMapas = (TextView) findViewById(R.id.TextViewDemoMapas);
		textViewDemoMapas.setTextAppearance(this, R.style.MenuStyle);
		textViewDemoMapas.setTag(new String("TextViewDemoMapas"));
		textViewDemoMapas.setOnClickListener(listener);
		
		TextView textViewFragments = (TextView) findViewById(R.id.TextViewFragments);
		textViewFragments.setTextAppearance(this, R.style.MenuStyle);
		textViewFragments.setTag(new String("TextViewFragments"));
		textViewFragments.setOnClickListener(listener);
		
		TextView textViewMostrarSaludo = (TextView) findViewById(R.id.TextViewMostrarSaludo);
		textViewMostrarSaludo.setTextAppearance(this, R.style.MenuStyle);
		textViewMostrarSaludo.setTag(new String("TextViewMostrarSaludo"));
		textViewMostrarSaludo.setOnClickListener(listener);
		
		TextView textViewProcesadoXML = (TextView) findViewById(R.id.TextViewProcesadoXML);
		textViewProcesadoXML.setTextAppearance(this, R.style.MenuStyle);
		textViewProcesadoXML.setTag(new String("TextViewProcesadoXML"));
		textViewProcesadoXML.setOnClickListener(listener);
		
		TextView textViewProcesarFormulario = (TextView) findViewById(R.id.TextViewProcesarFormulario);
		textViewProcesarFormulario.setTextAppearance(this, R.style.MenuStyle);
		textViewProcesarFormulario.setTag(new String("TextViewProcesarFormulario"));
		textViewProcesarFormulario.setOnClickListener(listener);
		
		TextView textViewTestLocationGPS = (TextView) findViewById(R.id.TextViewTestLocationGPS);
		textViewTestLocationGPS.setTextAppearance(this, R.style.MenuStyle);
		textViewTestLocationGPS.setTag(new String("TextViewTestLocationGPS"));
		textViewTestLocationGPS.setOnClickListener(listener);
		
		TextView textViewVisorFicheros = (TextView) findViewById(R.id.TextViewVisorFicheros);
		textViewVisorFicheros.setTextAppearance(this, R.style.MenuStyle);
		textViewVisorFicheros.setTag(new String("TextViewVisorFicheros"));
		textViewVisorFicheros.setOnClickListener(listener);
		
		TextView textViewFicheroMemInterna = (TextView) findViewById(R.id.TextViewFicheroMemInterna);
		textViewFicheroMemInterna.setTextAppearance(this, R.style.MenuStyle);
		textViewFicheroMemInterna.setTag(new String("TextViewFicheroMemInterna"));
		textViewFicheroMemInterna.setOnClickListener(listener);
		
		
	}
    
}
