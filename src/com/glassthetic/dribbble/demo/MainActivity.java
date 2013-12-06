package com.glassthetic.dribbble.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.glassthetic.dribbble.api.DribbbleAPI;
import com.glassthetic.dribbble.api.ErrorListener;
import com.glassthetic.dribbble.api.Listener;
import com.glassthetic.dribbble.api.Shot;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DribbbleAPI.setContext(this.getApplicationContext());
		
		Shot.getPopular(new Listener<List<Shot>>() {

			@Override
			public void onResponse(final List<Shot> shots) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, ShotsActivity.class);
				intent.putParcelableArrayListExtra("shots", (ArrayList<? extends Parcelable>) new ArrayList<Shot>(shots));
				startActivity(intent);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(Exception error) {
				// TODO Auto-generated method stub
				error.printStackTrace();
			}
		});
	}
}
