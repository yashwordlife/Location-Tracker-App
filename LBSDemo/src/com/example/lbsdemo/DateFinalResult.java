package com.example.lbsdemo;

import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DateFinalResult extends ListActivity
	{
	DataBaseHelper db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		String res=i.getStringExtra("eDate");
		db = new DataBaseHelper(getApplicationContext());
        try{
        	db.createDatabase();
        }
        catch(IOException e){
        	e.printStackTrace();
        }
        ArrayList<String> results = db.getLocByDate(res);
        if(results.size()==0)
        {
        	results.add("Sorry. No place visited on this date");
        }
        
        

			    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			        android.R.layout.simple_list_item_1, results);
			    setListAdapter(adapter);

			    ListView lv = getListView();
			    lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			    lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
						// TODO Auto-generated method stub
		                 	String tha=parent.getItemAtPosition(position).toString();
		                 	Intent i = new Intent(DateFinalResult.this,Last.class);
		                 	i.putExtra("li",tha);
		                 	startActivity(i);
					}});
						}
		                      
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.date_final_result, menu);
		return true;
	}

			    }
