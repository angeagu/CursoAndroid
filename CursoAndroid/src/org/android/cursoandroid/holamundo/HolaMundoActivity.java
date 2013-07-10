package org.android.cursoandroid.holamundo;

import org.android.cursoandroid.R;
import org.android.cursoandroid.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HolaMundoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holamundo);
    }
}