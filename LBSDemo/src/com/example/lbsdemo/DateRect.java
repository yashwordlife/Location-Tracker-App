package com.example.lbsdemo;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DateRect extends Activity {

	private DatePicker datePicker;
	private Calendar calendar;
	private int year, month, day;
	private TextView dateView;
	String date;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_rect);
		 calendar = Calendar.getInstance();
	      year = calendar.get(Calendar.YEAR);
	      month = calendar.get(Calendar.MONTH);
	      day = calendar.get(Calendar.DAY_OF_MONTH);
	      Button submitBtn = (Button) findViewById(R.id.button2);
	      submitBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(DateRect.this,DateFinalResult.class);
					String resDate = dateView.getText().toString();
					i.putExtra("eDate", resDate);
					startActivity(i);
				}
			});
	}
	@SuppressWarnings("deprecation")
	 public void setDate(View view) {
	      showDialog(999);
	   }

	   @Override
	   protected Dialog onCreateDialog(int id) {
	   // TODO Auto-generated method stub
	      if (id == 999) {
	         return new DatePickerDialog(this, myDateListener, year, month, day);
	       }
	      return null;
	   }

	   private DatePickerDialog.OnDateSetListener myDateListener
	   = new DatePickerDialog.OnDateSetListener() {

	   @Override
	   public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
	      // TODO Auto-generated method stub
	      // arg1 = year
	      // arg2 = month
	      // arg3 = day
	      showDate(arg1, arg2+1, arg3);
	   }
	   };

	   private void showDate(int year, int month, int day) {
		   dateView = (TextView) findViewById(R.id.textView1);
	      dateView.setText(new StringBuilder().append(day).append("-")
	      .append(month).append("-").append(year));
	   }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.date_rect, menu);
		return true;
	}

}
