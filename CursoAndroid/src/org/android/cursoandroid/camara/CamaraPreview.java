package org.android.cursoandroid.camara;

import org.android.cursoandroid.R;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CamaraPreview extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder surfaceHolder;
    private Camera camara;
	boolean myPreviewRunning = false;
	
    //Constructor que obtiene el contexto y la cámara.
    public CamaraPreview(Context context, Camera camera) {
        
    	super(context);
        this.camara = camera;
         
        this.surfaceHolder = this.getHolder();
        this.surfaceHolder.addCallback(this); // we get notified when underlying surface is created and destroyed
        this.surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); //this is a deprecated method, is not requierd after 3.0
        
    }
    
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		// Llamado cuando el surface sufre algun cambio, por ejemplo de tamaño o
		// de formato.
		try {
			
			if(myPreviewRunning){
				camara.stopPreview();
				myPreviewRunning = false;
			}
			
			Camera.Parameters p = camara.getParameters();
			p.setPreviewSize(width,height);
			camara.setParameters(p);
			camara.setPreviewDisplay(holder);
			camara.startPreview();
			myPreviewRunning = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		// Llamado cuando el Surface es creado por primera vez.
		
		//Abrimos la vista de la cámara.
		if (camara == null) {
			try {
				camara = Camera.open();
				camara.setPreviewDisplay(holder);
	            // TODO test how much setPreviewCallbackWithBuffer is faster
	            camara.setPreviewCallback(null);
			}
			catch (Exception ex) {
				camara.release();
	            camara = null;
				ex.printStackTrace();
			}
		}
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		// Llamado después de quitar el surface de la vista del usuario y antes
		// de destruir el surface, en la mayoría de las veces para liberar memoria.
		
		//Liberamos recursos
		camara.stopPreview();
		camara.setPreviewCallback(null);
		myPreviewRunning = false;
		camara.release();
		camara = null;
	}

}
