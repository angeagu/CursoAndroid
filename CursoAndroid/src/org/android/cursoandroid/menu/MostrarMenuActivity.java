package org.android.cursoandroid.menu;

import org.android.cursoandroid.R;
import org.android.cursoandroid.R.layout;
import org.android.cursoandroid.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MostrarMenuActivity extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu); 
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Alternativa 1
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    
}
