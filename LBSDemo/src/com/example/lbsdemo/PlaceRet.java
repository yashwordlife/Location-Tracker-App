package com.example.lbsdemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PlaceRet extends Activity {
	EditText et;
	String place;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place_ret);
		Button submitBtn = (Button) findViewById(R.id.submit);
		submitBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(PlaceRet.this,PlaceResult.class);
				et=(EditText)findViewById(R.id.editText1);
				place = et.getText().toString();
				i.putExtra("ePlace", place);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.place_ret, menu);
		return true;
	}

}
