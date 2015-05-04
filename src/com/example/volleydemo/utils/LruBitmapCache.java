package com.example.volleydemo.utils;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

public class LruBitmapCache extends LruCache<String, Bitmap>
	implements ImageCache {

	public static int getDefaultLruCacheSize() {
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		
		final int cacheSize = maxMemory / 8;
		Log.i("yuan", "cacheSize: "+cacheSize+"KB");
		return cacheSize;
	}
	public LruBitmapCache() {
		this(getDefaultLruCacheSize());
	}
	
	public LruBitmapCache(int size) {
		super(size);
	}
	
	@Override
	protected int sizeOf(String key, Bitmap value) {
		// TODO Auto-generated method stub
		Log.i("yaun", "LruCache sizeof: " + value.getByteCount() / 1024);
		return value.getByteCount() / 1024;
	}
	@Override
	public Bitmap getBitmap(String url) {
		// TODO Auto-generated method stub
		return get(url);
	}
	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		// TODO Auto-generated method stub
		put(url, bitmap);
	}

}
