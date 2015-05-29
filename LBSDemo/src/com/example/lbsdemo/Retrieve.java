package com.example.lbsdemo;

import java.util.ArrayList;

import com.example.lbsdemo.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils.StringSplitter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Retrieve extends Activity {
	int opt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retrieve);
		ArrayList list = new ArrayList();
		list.add("Retrieve Location By Date");
		list.add("Retrieve Location By Place");
		Spinner chSpinner = (Spinner) findViewById(id.choiceSpinner);
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
		ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		chSpinner.setAdapter(ad);
		final Button submit = (Button) findViewById(R.id.submit);
		chSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if(arg2==0)
				{
					opt = 1;
				}
				else
				{
					opt = 2;
				}
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(opt == 1)
				{
					Intent i = new Intent(Retrieve.this,DateRect.class);
					startActivity(i);	
				}
				else
				{

					Intent i = new Intent(Retrieve.this, PlaceRet.class);
					startActivity(i);	
				}				
			}
		});
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.retrieve, menu);
		return true;
	}

}
