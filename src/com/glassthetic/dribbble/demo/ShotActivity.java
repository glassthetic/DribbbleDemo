package com.glassthetic.dribbble.demo;

import java.util.List;

import com.glassthetic.dribbble.api.Comment;
import com.glassthetic.dribbble.api.ErrorListener;
import com.glassthetic.dribbble.api.Listener;
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
		final TextView titleTextView = (TextView) findViewById(R.id.title);
		titleTextView.setText(shot.title);
		
		shot.getComments(new Listener<List<Comment>>() {

			@Override
			public void onResponse(List<Comment> comments) {
				// TODO Auto-generated method stub
				titleTextView.setText(comments.get(0).body);
			}
			
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(Exception exception) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
}
