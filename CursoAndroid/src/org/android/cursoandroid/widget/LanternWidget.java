package org.android.cursoandroid.widget;

import org.android.cursoandroid.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.widget.RemoteViews;

public class LanternWidget extends AppWidgetProvider {

	private static Camera cam;
	public static boolean flashEncendido = false;
	
	@Override
    public void onUpdate(Context context,
                 AppWidgetManager appWidgetManager,
                 int[] appWidgetIds) {

		VentanaAlerta.mostrarAlerta(context,"METODO UPDATE DEL WIDGET");
		
		/* Realizamos una llamada al método de configuración del widget (asigna
		 * los intents a los botones y demás configuración. 
		 */
	    this.loadWidgetConfiguration(context);
	    
		VentanaAlerta.mostrarAlerta(context,"SALIENDO DEL METODO UPDATE DEL WIDGET");
    }
	
	@Override
    public void onEnabled(Context context) {
		/* Obtenemos la lista de controles (vistas, botones, textviews, etc)
		 * del widget actual 
		 */
		
		VentanaAlerta.mostrarAlerta(context,"METODO ONENABLED DEL WIDGET");
		
		int[] appWidgetIds = AppWidgetManager.getInstance(context)
		.getAppWidgetIds(new ComponentName(context, LanternWidget.class));
		
		this.loadWidgetConfiguration(context);
		
		VentanaAlerta.mostrarAlerta(context,"SALIENDO DEL METODO ONENABLED DEL WIDGET");
	
	}
	
	@Override
    public void onReceive(Context context,Intent intent) {
		
		super.onReceive(context, intent);
		
		int[] appWidgetIds = AppWidgetManager.getInstance(context)
				.getAppWidgetIds(new ComponentName(context, LanternWidget.class));				
		
		
		VentanaAlerta.mostrarAlerta(context, "ONRECEIVE con: " + intent.getAction());
        /* Definimos en el widget el método onReceive, que nos servirá
         * para recibir los intents y realizar el filtrado de los mismos.
         */
		if (intent.getAction().equals("com.example.lanternwidget.intents.TOGGLE_LINTERNA")) {
			VentanaAlerta.mostrarAlerta(context, "evento recibido: " + intent.getAction());
			toggleLinterna(context);
		}
		
		this.loadWidgetConfiguration(context);
    }
	
	public void toggleLinterna(Context context) {
		
		VentanaAlerta.mostrarAlerta(context, "TOGGLE LINTERNA");
		if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
			if (cam == null) {
				cam = Camera.open();
			}
			if (flashEncendido == false ) {
				Parameters p = cam.getParameters();
				p.setFlashMode(Parameters.FLASH_MODE_TORCH);
				cam.setParameters(p);
				cam.startPreview();
				flashEncendido = true;
			}
			else if (flashEncendido == true) {
				Parameters p = cam.getParameters();
				p.setFlashMode(Parameters.FLASH_MODE_OFF);
				cam.setParameters(p);
				cam.stopPreview();
				//cam.release();
				flashEncendido = false;
			}
			
		}
		else {
			//No hay flash.
			VentanaAlerta.mostrarAlerta(context, "La cámara de su teléfono móvil no dispone de FLASH.");
			
		}
		
		
	}
	
	
	private void loadWidgetConfiguration(Context context) {
		
		int[] appWidgetIds = AppWidgetManager.getInstance(context)
				.getAppWidgetIds(new ComponentName(context, LanternWidget.class));
		
		RemoteViews controles =
	            new RemoteViews(context.getPackageName(), R.layout.main_widget);
		if (flashEncendido)
			controles.setTextViewText(R.id.botonEncender, "Apagar Linterna");
		else 
			controles.setTextViewText(R.id.botonEncender, "Encender Linterna");
		
		//Iteramos la lista de widgets en ejecución para obtener el ID.
		int widgetId = 0;
	    for (int i = 0; i < appWidgetIds.length; i++) {
	        //ID del widget actual
	        widgetId = appWidgetIds[i];
	    }
		
		//Construimos un intent para la accion de encender la linterna
		Intent intentToggle = new Intent("com.example.lanternwidget.intents.TOGGLE_LINTERNA");
		intentToggle.putExtra(
		         AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
		
		//Creamos un pendingIntent a partir del metodo getBroadcast.
		PendingIntent pendingIntentToggle =
				PendingIntent.getBroadcast(context, widgetId,
						intentToggle, PendingIntent.FLAG_UPDATE_CURRENT);
			 
		//Asignamos el pendingIntent al botón de encendido de la linterna.
		controles.setOnClickPendingIntent(R.id.botonEncender, pendingIntentToggle);
							
		//Notificamos al manager de la actualización del widget actual
		AppWidgetManager.getInstance(context).updateAppWidget(widgetId, controles);
	}

}
