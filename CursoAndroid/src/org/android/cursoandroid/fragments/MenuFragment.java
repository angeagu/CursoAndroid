package org.android.cursoandroid.fragments;

import org.android.cursoandroid.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuFragment extends Fragment {

	private MenuListener listener;
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
		
		return inflater.inflate(R.layout.fragment_menu, container);
		
	}

	@Override
	public void onActivityCreated(Bundle state) {
		/* Se ejecutará cuando la actividad contenedora del fragment
		 * esté completamente creada.
		 */
		super.onActivityCreated(state);
		final ListView listViewMenu = (ListView) this.getView().findViewById(R.id.ListViewMenu);
		
		//Definimos el adapter para el ListView.
		String[] menu = new String[6];
		menu[0] = new String("Hola Mundo");
		menu[1] = new String("Formulario");
		menu[2] = new String("Ficheros");
		menu[3] = new String("Facturas");
		menu[4] = new String("GPS");
		menu[5] = new String("Mapas");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getView().getContext(), android.R.layout.simple_list_item_1, menu);
		
		listViewMenu.setAdapter(adapter);
		
		//Asignamos el evento de click sobre un elemento de la lista.
		listViewMenu.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener!=null) {
                    listener.onMenuItemSelected((String)listViewMenu.getAdapter().getItem(pos));

                }
            }
        });
		
	}
	
	public interface MenuListener {
		void onMenuItemSelected(String etiqueta);
	}

	public MenuListener getListener() {
		return listener;
	}

	public void setListener(MenuListener listener) {
		this.listener = listener;
	}
	
	

}
