package org.android.cursoandroid.fragments;

import org.android.cursoandroid.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ExplanationFragment extends Fragment {

	/* En el caso de los Fragments, normalmente los métodos que
	 * sobreescribiremos son onCreateView() y onActivityCreated.
	 */
	
	@Override
	public View onCreateView(LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

		/* onCreateView es el equivalente del onCreate de las
		 * actividades "normales"
		 * Aquí asignaremos al fragment un determinado layout
		 * 
		 */
		
		return inflater.inflate(R.layout.fragment_explanation, container);
		
	}

	@Override
	public void onActivityCreated(Bundle state) {
		/* Se ejecutará cuando la actividad contenedora del fragment
		 * esté completamente creada.
		 */
		super.onActivityCreated(state);
	}
	
	public void mostrarExplicacion(String etiqueta) {
		TextView textViewExplicacion = 
				(TextView) this.getView().findViewById(R.id.textViewExplicacion);
		textViewExplicacion.setText(etiqueta);
		
	}
	
}
