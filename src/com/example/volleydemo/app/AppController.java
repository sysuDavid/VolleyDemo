package com.example.volleydemo.app;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.volleydemo.utils.LruBitmapCache;

import android.app.Application;
import android.text.TextUtils;

public class AppController extends Application {
	public static final String TAG = Application.class.getSimpleName();
	
	private RequestQueue mQueue;
	private ImageLoader mImageLoader;
	private static AppController mInstance;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mInstance = this;
	}
	
	public static synchronized AppController getInstance() {
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mQueue == null) {
			mQueue = Volley.newRequestQueue(getApplicationContext());
		}
		return mQueue;
	}
	
	public ImageLoader getImageLoader() {
		getRequestQueue();
		if (mImageLoader == null) {
			mImageLoader = new ImageLoader(this.mQueue, new LruBitmapCache());
		}
		return mImageLoader;
	}
	
	public <T> void addToRequestQueue(Request<T> req, String tag) {
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}
	
	public <T> void addToRequestQueue(Request<T> req) {
		getRequestQueue().add(req);
	}
	
	public void cancelPendingRequests(Object tag) {
		if (mQueue != null) {
			mQueue.cancelAll(tag);
		}
	}
}
