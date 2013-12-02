package com.glassthetic.dribbble.demo;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.glassthetic.dribbble.api.Shot;

public class ShotsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shots_list);
		Intent intent = getIntent();
		final List<Shot> shots = intent.getParcelableArrayListExtra("shots");
		final ListView listView = (ListView) findViewById(R.id.list);
		final ShotListArrayAdapter adapter = new ShotListArrayAdapter(this, R.layout.shots_list_item, shots);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
				Intent intent = new Intent();
				Shot shot = shots.get(position);
				intent.setClass(ShotsActivity.this, ShotActivity.class);
				intent.putExtra("shot", shot);
				startActivity(intent);
			}
		});
	}
	
	private class ShotListArrayAdapter extends ArrayAdapter<Shot> {
		private final Context context;
		private List<Shot> objects;
		
		public ShotListArrayAdapter(Context context, int textViewResourceId, List<Shot> objects) {
			super(context, textViewResourceId, objects);
			this.context = context;
			this.objects = objects;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.shots_list_item, parent, false);
			
//			ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
			TextView titleTextView = (TextView) rowView.findViewById(R.id.title);
			TextView playerNameTextView = (TextView) rowView.findViewById(R.id.player_name);
			
			Shot shot = objects.get(position);
			titleTextView.setText(shot.title);
			playerNameTextView.setText(shot.player.name);
			
			return rowView;
		}
	}
}
