package com.example.volleydemo;

import com.example.volleydemo.R.layout;
import com.example.volleydemo.utils.ImageURLs;
import com.example.volleydemo.utils.PhotoWallAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class PhotoWall extends Activity {

	private GridView gridView;
	private PhotoWallAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(layout.activity_gridview);
		gridView = (GridView)findViewById(R.id.gridView);
		adapter = new PhotoWallAdapter(this, gridView);
		gridView.setAdapter(adapter);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		adapter.cancelAllTasks();
	}
	
	
	
}
