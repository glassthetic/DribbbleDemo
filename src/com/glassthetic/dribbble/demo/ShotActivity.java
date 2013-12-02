package com.glassthetic.dribbble.demo;

import com.glassthetic.dribbble.api.Shot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShotActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shot);
		Intent intent = getIntent();
		Shot shot = intent.getParcelableExtra("shot");
		TextView titleTextView = (TextView) findViewById(R.id.title);
		titleTextView.setText(shot.title);
	}
}
