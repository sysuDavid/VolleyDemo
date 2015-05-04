package com.example.volleydemo;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.volleydemo.R.layout;
import com.example.volleydemo.app.AppController;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NetworkImageLoader extends Activity {

	private NetworkImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(layout.activity_image_loader);
		imageView = (NetworkImageView)findViewById(R.id.network_imageview);
		Button btn = (Button)findViewById(R.id.button5);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imageRequest();
			}
		});
	}
	
	private void imageRequest() {
		// TODO Auto-generated method stub
		String url = "http://i.imgur.com/7spzG.png";
		imageView.setDefaultImageResId(R.drawable.ic_launcher);
		imageView.setErrorImageResId(R.drawable.ic_launcher);
		ImageLoader imageLoader = AppController.getInstance().getImageLoader();
		imageView.setImageUrl(url, imageLoader);
	}

}
