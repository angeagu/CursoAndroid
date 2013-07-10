package org.android.cursoandroid.widget;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class MiWidget extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context,AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		/*
		//Método de actualización del widget
		//Iteramos la lista de widgets en ejecución
		for (int i = 0; i < appWidgetIds.length; i++)
		{
		//ID del widget actual
		int widgetId = appWidgetIds[i];
		//Actualizamos el widget actual
		actualizarWidget(context, appWidgetManager, widgetId);
		}
		*/
	}
	
	/*
	public static void actualizarWidget(Context context,
				AppWidgetManager appWidgetManager, int widgetId) {
			
			//Recuperamos el mensaje personalizado para el widget actual
			SharedPreferences prefs =
			context.getSharedPreferences("WidgetPrefs",Context.MODE_PRIVATE);
			String mensaje = prefs.getString("msg_" + widgetId, "Hora actual:");
			
			//Obtenemos la lista de controles del widget actual
			RemoteViews controles = new RemoteViews(context.getPackageName(),
								R.layout.pantallawidget);
			
			//Actualizamos el mensaje en el control del widget
			controles.setTextViewText(R.id.textMensaje, mensaje);
			
			//Obtenemos la hora actual
			Calendar calendario = new GregorianCalendar();
			String hora = calendario.getTime().toLocaleString();
			
			//Actualizamos la hora en el control del widget
			controles.setTextViewText(R.id.textViewHora, hora);
			//Notificamos al manager de la actualización del widget actual
			appWidgetManager.updateAppWidget(widgetId, controles);
			
			//
			Intent intent = new Intent("org.android.HolaMundo.ACTUALIZAR_WIDGET");
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context, widgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			controles.setOnClickPendingIntent(R.id.BtnActualizar,pendingIntent);
	}
	*/
	
}
