package com.example.volleydemo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volleydemo.app.AppController;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SimpleVolley extends Activity {

	private ProgressDialog dialog;
	private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_volley);
		imageView = (ImageView)findViewById(R.id.imageView1);
		
		dialog = new ProgressDialog(this);
		dialog.setMessage("Loading...");
		Button stringRequestButton = (Button)findViewById(R.id.button1);
		stringRequestButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				volleyStringRequest();
			}
		});
		
		Button jsonObjectRequestButton = (Button)findViewById(R.id.button2);
		jsonObjectRequestButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				volleyJsonOjectRequest();
			}
		});
		
		Button jsonPostRequestButton = (Button)findViewById(R.id.button3);
		jsonPostRequestButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				volleyJsonPostRequest();
			}
		});
		Button ImageRequestButton = (Button)findViewById(R.id.button4);
		ImageRequestButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				volleyImageRequest();
			}
		});
	}
	
	private void volleyStringRequest() {
		String tag_string = "string_req";
		String url = "http://www.baidu.com/";
		dialog.show();
		StringRequest request = new StringRequest(Method.GET, url,
				new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
				Log.i("yuan", response);
				dialog.hide();
			}
			
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
				Log.i("yuan", error.toString());
				dialog.hide();
			}
			
		});
		AppController.getInstance().addToRequestQueue(request, tag_string);
	}


	private void volleyJsonOjectRequest() {
		String tag_json_obj = "json_obj_req";
		String url = "http://api.androidhive.info/volley/person_object.json";
		dialog.show();
		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
						Log.i("yuan", response.toString());
						dialog.hide();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
						Log.i("yuan", error.toString());
						dialog.hide();
					}
					
				});
		AppController.getInstance().addToRequestQueue(req, tag_json_obj);
	}
	

	private void volleyJsonPostRequest() {
		String tag_json_post = "json_post_req";
		String url = "http://api.androidhive.info/volley/person_object.json";
		dialog.show();
		JsonObjectRequest req = new JsonObjectRequest(Method.POST, url, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
						Log.i("yuan", response.toString());
						dialog.hide();
					}
			
				}, new Response.ErrorListener() {
		
					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
						Log.i("yuan", error.toString());
						dialog.hide();
					}
				}) {
					 protected Map<String, String> getParams() {
						// 显示服务器出错ServerError，应该是该域名的作者写错了
						Map<String, String> params = new HashMap<String, String>();
						params.put("name", "yuanwj");
						params.put("email", "yuanwj1993@gmail.com");
						params.put("password", "123");
						return params;
					 }
				};
		AppController.getInstance().addToRequestQueue(req, tag_json_post);
	}
	
	private void volleyImageRequest() {
		String tag_image = "image_tag";
		String url = "http://i.imgur.com/7spzG.png";
		dialog.show();
		ImageRequest req = new ImageRequest(url,
				new Response.Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap bm) {
						// TODO Auto-generated method stub
						imageView.setImageBitmap(bm);
						dialog.hide();
					}
			
				}, 0, 0, null,
				new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						imageView.setImageResource(R.drawable.ic_launcher);
						dialog.hide();
					}
				});
		AppController.getInstance().addToRequestQueue(req, tag_image);
	}
}
