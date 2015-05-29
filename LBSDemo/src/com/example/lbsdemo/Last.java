package com.example.lbsdemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Last extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_last);
		Intent i =getIntent();
		String x = i.getStringExtra("li");
		TextView tv = (TextView) findViewById(R.id.textView1);
		String res[] = x.split("\n");
		tv.setText("You were at "+res[0]+"\nOn Date :"+res[1]+"\nAt time :"+res[2]);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.last, menu);
		return true;
	}

}
