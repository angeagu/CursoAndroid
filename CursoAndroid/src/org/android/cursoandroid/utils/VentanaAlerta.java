package org.android.cursoandroid.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class VentanaAlerta {

	public static void mostrarAlerta(Context ctx, String mensaje) {
		Toast toast = Toast.makeText(ctx,mensaje, Toast.LENGTH_SHORT); 
		toast.show();
	}
	
	public static void mostrarDialogo (Context ctx, String mensaje) {
	
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

		builder.setMessage(mensaje)
			.setTitle("Informe")
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
               dialog.cancel();
			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
		
	}
}
