package com.glassthetic.dribbble.demo;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.glassthetic.dribbble.api.DribbbleAPI;
import com.glassthetic.dribbble.api.Response;
import com.glassthetic.dribbble.api.Shot;


public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final DribbbleAPI api = new DribbbleAPI(this.getApplicationContext());
		
		final MainActivity mainActivity = this;
		
		api.getPopularShots(new Response.Listener<List<Shot>>() {

			@Override
			public void onResponse(List<Shot> shots) {			
				final ListView list = (ListView) findViewById(R.id.list);
				final ShotListArrayAdapter adapter = new ShotListArrayAdapter(mainActivity, R.layout.list_item, shots);
				list.setAdapter(adapter);
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(Exception error) {
				// TODO Auto-generated method stub
				error.printStackTrace();
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
			View rowView = inflater.inflate(R.layout.list_item, parent, false);
			
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
