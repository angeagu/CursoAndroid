package org.android.cursoandroid.facturas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FacturasSQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "FACTURAS";
    private static final String TABLE_CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                "CONCEPTO" + " TEXT, " +
                "IMPORTE" + " REAL, " +
                "FECHA" + " TEXT);";

    protected FacturasSQLiteHelper(Context context) {
        super(context, "BBDDFACTURAS", null, DATABASE_VERSION);
    }
    

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL("INSERT INTO FACTURAS (CONCEPTO,IMPORTE,FECHA) VALUES ('Gas ','123.45 ','27-07-2012 ')"); 
		db.execSQL("INSERT INTO FACTURAS (CONCEPTO,IMPORTE,FECHA) VALUES ('Alquiler ','98.87 ','27-05-2012 ')");
		db.execSQL("INSERT INTO FACTURAS (CONCEPTO,IMPORTE,FECHA) VALUES ('IBI ','123.45 ','27-06-2012 ')"); 
		db.execSQL("INSERT INTO FACTURAS (CONCEPTO,IMPORTE,FECHA) VALUES ('Agua ','98.87 ','27-07-2012 ')");
		db.execSQL("INSERT INTO FACTURAS (CONCEPTO,IMPORTE,FECHA) VALUES ('Dinero Cajero ','123.45 ','27-05-2012 ')"); 
		db.execSQL("INSERT INTO FACTURAS (CONCEPTO,IMPORTE,FECHA) VALUES ('Comisiones ','98.87 ','27-06-2012 ')");
		db.execSQL("INSERT INTO FACTURAS (CONCEPTO,IMPORTE,FECHA) VALUES ('Telefono ','123.45 ','27-07-2012 ')"); 
		db.execSQL("INSERT INTO FACTURAS (CONCEPTO,IMPORTE,FECHA) VALUES ('Iberdrola ','98.87 ','27-05-2012 ')");
		db.execSQL("INSERT INTO FACTURAS (CONCEPTO,IMPORTE,FECHA) VALUES ('Compra Super ','123.45 ','27-06-2012 ')"); 
		db.execSQL("INSERT INTO FACTURAS (CONCEPTO,IMPORTE,FECHA) VALUES ('Taller Coche ','98.87 ','27-06-2012 ')");
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		//Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
 
        //Se crea la nueva versión de la tabla
        db.execSQL(TABLE_CREATE);
        
	}
}