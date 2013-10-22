package org.android.cursoandroid.camara;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.android.cursoandroid.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class CamaraActivity extends Activity  {

	//La interfaz SurfaceHolder.Callback nos permitira manejar los cambios ocurridos
	//en el SurfaceView incluido en el archivo vistapreviacamara.xml
	
	private Camera camara;
	private CamaraPreview camaraPreview;
	byte[] tempdata;
	private int tiempoEspera;
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
			setContentView(R.layout.botondisparo);
			
			//Inicializamos la variable camara.
			camara = getCameraInstance();
			camaraPreview = new CamaraPreview(this,camara);
			//Establecemos la orientación de la camara, ya que sino
			//aparece en horizontal, debido a un bug.
			camara.setDisplayOrientation(90);
			
			FrameLayout frameLayoutPreview = 
					(FrameLayout)this.findViewById(R.id.camera_preview);
			frameLayoutPreview.addView(camaraPreview);
			
			final SeekBar seekbar = (SeekBar)this.findViewById(R.id.seekBar1);
			final TextView textViewSeekbar = (TextView)this.findViewById(R.id.TextViewSeekBar);
			
			
			//Obtenemos el botón de la foto y le asignamos un listener,
			//lo que hará será tomar la foto.
			botonFoto = (Button) findViewById(R.id.botonFoto);
			botonFoto.setOnClickListener(new OnClickListener() {
				
				ShutterCallback shutterCallback = new ShutterCallback() {
					public void onShutter() {
						}
				};
				
				PictureCallback imagenJPG = new PictureCallback() {
					public void onPictureTaken(byte[] data, Camera myCamera) {
						// TODO Auto-generated method stub
						if(data != null){
							
							//Obtenemos la ruta al directorio de fotos del teléfono.
							File pictureFile = PictureFile.getPictureFile();
			                if (pictureFile == null){
			                    return;
			                }
			 
			                //Guardamos la imagen a través de un FileOutputStream.
			                try {
			                    FileOutputStream fos = new FileOutputStream(pictureFile);
			                    fos.write(data);
			                    fos.close();
			                    Toast.makeText(CamaraActivity.this.getApplicationContext(), "Imagen Guardada en la ruta: " + pictureFile.getAbsolutePath(), Toast.LENGTH_SHORT);
			                } catch (FileNotFoundException ex) {
			                	Toast.makeText(CamaraActivity.this.getApplicationContext(), "Error al Guardar la imagen." + ex.toString(), Toast.LENGTH_SHORT);
			                	Log.e("CamaraActivity", ex.toString());
			                } catch (IOException ex) {
			                	Toast.makeText(CamaraActivity.this.getApplicationContext(), "Error al Guardar la imagen." + ex.toString(), Toast.LENGTH_SHORT);
			                	Log.e("CamaraActivity", ex.toString());
			                }
						}
					}

				};
				
				public void onClick(View v) {
					/* Invocaremos el método takePicture de la cámara, para ello
					 tendremos que crear tres elementos:
					 1-ShutterCallback: Para definir efectos tras la foto.
					 2-PictureCallback: Para tratamiento de fotos con formato raw
					 3-PictureCallback: Almacena la foto en sí, comprimida. */
					
					/* Para la versión básica, el ShutterCallback será null, y el
					 * PictureCallback también.
					 */
					
					//Tomamos la foto, tras la espera.
					try {
						tiempoEspera = seekbar.getProgress();
						Thread.sleep(tiempoEspera*1000);
					}
					catch (Exception ex) {
						
					}
					camara.takePicture(null, null,imagenJPG);
					
				}
			});
			
			
			
			
			seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					// TODO Auto-generated method stub
					
					textViewSeekbar.setText("Tiempo de Espera: "+progress);
				}

				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}

				public void onStopTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
		}
		catch (Exception ex) {
			Log.e("CamaraActivity", ex.toString());
		}
	}

	
	
	private Camera getCameraInstance() {
        Camera camera = null;
 
        try {
            camera = Camera.open();
        } catch (Exception e) {
            // cannot get camera or does not exist
        }
        return camera;
    }
	
}
