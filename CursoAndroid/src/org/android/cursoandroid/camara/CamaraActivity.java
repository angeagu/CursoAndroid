package org.android.cursoandroid.camara;

import org.android.cursoandroid.R;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class CamaraActivity extends Activity implements SurfaceHolder.Callback {

	//La interfaz SurfaceHolder.Callback nos permitira manejar los cambios ocurridos
	//en el SurfaceView incluido en el archivo vistapreviacamara.xml
	
	private LayoutInflater inflater = null;
	Camera camara;
	byte[] tempdata;
	boolean myPreviewRunning = false;
	private SurfaceHolder surfaceHolder;
	private SurfaceView surfaceView;
	Button botonFoto;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			
			super.onCreate(savedInstanceState);
			
			getWindow().setFormat(PixelFormat.TRANSLUCENT);
			//Obtenemos la ventana sin título
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			//Ponemos la ventana en modo PANTALLA COMPLETA.
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN);
			
			//Cargamos la vista previa de la cámara.
			setContentView(R.layout.vistapreviacamara);
			
			//Inicializamos las variables SurfaceView y SurfaceHolder
			
			//Indicamos que el surfaceView será el surface definido en vistaprevia.xml
			surfaceView = (SurfaceView) findViewById(R.id.surface);
			//Con el surfaceView obtenermos el surfaceHolder
			surfaceHolder = surfaceView.getHolder();
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			
			//Con LayoutInflater pondremos un xml sobre otro, en este caso pondremos
			//el boton de disparo sobre la vista previa de la cámara.
			inflater = LayoutInflater.from(this);
			View overView = inflater.inflate(R.layout.botondisparo,null);
			this.addContentView(overView, new LayoutParams(LayoutParams.FILL_PARENT));
			
			
			
			//Obtenemos el botón de la foto y le asignamos un listener,
			//lo que hará será tomar la foto.
			botonFoto = (Button) findViewById(R.id.botonFoto);
			botonFoto.setOnClickListener(new OnClickListener() {
				
				ShutterCallback shutterCallback = new ShutterCallback() {
					public void onShutter() {
						}
				};
				
				PictureCallback pictureCallback = new PictureCallback() {
					public void onPictureTaken(byte[] data, Camera myCamera) {
						// TODO Auto-generated method stub
					}
				};
				
				PictureCallback imagenJPG = new PictureCallback() {
					public void onPictureTaken(byte[] data, Camera myCamera) {
						// TODO Auto-generated method stub
						if(data != null){
							tempdata = data;
							//Llama a done() para almacenar la imagen.
							done();
						}
					}

				};
				
				public void onClick(View v) {
					/* Invocaremos el método takePicture de la cámara, para ello
					 tendremos que crear tres elementos:
					 1-ShutterCallback: Para definir efectos tras la foto.
					 2-PictureCallback: Para tratamiento de fotos con formato raw
					 3-PictureCallback: Almacena la foto en sí, comprimida. */
					
					//Tomamos la foto.
					try {
						Thread.sleep(5000);
					}
					catch (Exception ex) {
						
					}
					camara.takePicture(shutterCallback, pictureCallback,imagenJPG);
					
				}
			});
			
		}
		catch (Exception ex) {
			Log.e("CamaraActivity", ex.toString());
		}
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

	//El método done es el que realmente realiza la foto y la almacena.
	private void done() {
		
		//La clase BitmapFactory se utiliza para decodificar el array de bytes
		//en un objeto de tipo Bitmap.
		Bitmap bm = BitmapFactory.decodeByteArray(tempdata,0, tempdata.length);
		String url = Images.Media.insertImage(getContentResolver(), bm, null, null);
		bm.recycle();
		
		Bundle bundle = new Bundle();
			if(url != null){
				//Devolvemos una url con la ruta de la imagen.
				bundle.putString("url",url);
				Intent mIntent = new Intent();
				mIntent.putExtras(bundle);
				setResult(RESULT_OK, mIntent);
			}
			else{
				Toast.makeText(this, "La imagen no se ha podido guardar", Toast.LENGTH_SHORT);
	
			}
		finish();
	}
	
}
