package com.example.lbsdemo;

import java.io.*;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper{

	//The Android's default system path of your application database.
	private static String DB_PATH = "";
	private static String DB_NAME = "locaa.db";
	//private static final int DB_VERSION = 1;
	private SQLiteDatabase myDatabase;
	private final Context context;
	 /**
	  * Constructor
	  * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
	  * @param context
	  */
	public DataBaseHelper(Context context) {
		super(context,DB_NAME,null,1);
		this.context = context;
		DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
	}
	 /**
	  * Creates an empty database on the system and rewrites it with your own database.
	  * */
	public void createDatabase() throws IOException{
		boolean dbExists = checkDatabase();
		if(dbExists){
			
		}
		else{
			 //By calling this method and empty database will be created into the default system path
			//of your application so we are gonna be able to overwrite that database with our database.
			this.getReadableDatabase();
			try{
				copyDatabase();
			}
			catch(Exception e){
				System.out.println("after copy");
			}
		}
	}
	 /**
	  * Check if the database already exist to avoid re-copying the file each time you open the application.
	  * @return true if it exists, false if it doesn't
	  */
	private boolean checkDatabase(){
		SQLiteDatabase ch = null;
		try{
			String myPath = DB_PATH + DB_NAME;
			ch = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
		}
		catch(SQLiteException e){
			System.out.println("in check");
			//database does't exist yet.
		}
		if(ch!=null){
			ch.close();
		}
		return ch!=null?true:false;
	}
	 /**
	  * Copies your database from your local assets-folder to the just created empty database in the
	  * system folder, from where it can be accessed and handled.
	  * This is done by transfering bytestream.
	  * */
	public void copyDatabase() throws IOException{
		try{
		//Open your local db as the input stream
		InputStream myInput = context.getAssets().open(DB_NAME);
		// Path to the just created empty db
		String outFile = DB_PATH + DB_NAME;
		//Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFile);
		//transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}
		myOutput.flush();
		myOutput.close();
		myInput.close();
		}
		catch(Exception e){
			System.out.println("in copy");
		}
	}
	public void openDataBase() throws SQLException
	{
		String myPath = DB_PATH+DB_NAME;
		myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
	}
	public String saveLoc(String address,String date,String time) {
		String str = null;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c;
		try{
			db.execSQL("insert into gps values('"+address+"','"+date+"','"+time+"');");
		}
		catch(Exception e){
			System.out.println("SaveLoc function");
		}
		return str;
	}
	public ArrayList<String> getLoc(String address) {
		ArrayList <String> results = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c;
		try{
			c = db.rawQuery("select * from gps", null);
			if(c==null){
				Log.v("cursor", "success");
				return null;
			}
			c.moveToFirst();
			while(c!=null)
			{
				if(c.getString(0).toLowerCase().contains(address))
				{
					String strResult = c.getString(0)+"\n"+c.getString(1)+"\n"+c.getString(2)+"\n";
					results.add(strResult);
				}
				c.move(1);
			}	
			c.close();
		}
		catch(Exception e){
			System.out.println("in getname");
		}
		return results;
	}
	public ArrayList<String> getLocByDate(String date) {
		ArrayList <String> results = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c;
		try{
			c = db.rawQuery("select * from gps", null);
			if(c==null){
				Log.v("cursor", "success");
				return null;
			}
			c.moveToFirst();
			while(c!=null)
			{	// Check if the date matches the specified date
				if(c.getString(1).equals(date))
				{
					String strResult = c.getString(0)+"\n"+c.getString(1)+"\n"+c.getString(2)+"\n";
					results.add(strResult);
				}
				c.move(1);
			}	
			c.close();
		}
		catch(Exception e){
			System.out.println("in getname");
		}
		return results;
	}
	@Override
	public synchronized void close(){
		if(myDatabase!=null){
			myDatabase.close();
		}
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
