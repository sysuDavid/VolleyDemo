package com.example.volleydemo.utils;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.example.volleydemo.R;
import com.example.volleydemo.app.AppController;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class PhotoWallAdapter extends BaseAdapter implements OnScrollListener {

	private final int WIDTH = 200, HEIGHT = 200;
	private final static String[] URLs = ImageURLs.imageThumbUrls;
	
	private Context mContext;
	private GridView mGridView;
	private ImageLoader mImageLoader;
	
	public PhotoWallAdapter(Context context, GridView gridView) {
		// TODO Auto-generated constructor stub
		mContext = context;
		mGridView = gridView;
		mImageLoader = AppController.getInstance().getImageLoader();
		mGridView.setOnScrollListener(this);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return URLs.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return URLs[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		if (convertView == null) {
			view = LayoutInflater.from(mContext).inflate(R.layout.photo_item, null);
		} else {
			view = convertView;
		}
		String imageUrl = URLs[position];
		ImageView imageView = (ImageView)view.findViewById(R.id.photo);
		imageView.setTag(imageUrl);
		return view;
	}

	private int mFirstVisibleItem;
	private int mVisibleItemCount;
	private boolean isFirstEnter = true;
	private List<ImageContainer> icList = new ArrayList<ImageContainer>();
	
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		if (scrollState == SCROLL_STATE_IDLE) {
			loadBitmap(mFirstVisibleItem, mVisibleItemCount);
		} else {
			for (ImageContainer ic : icList) {
				ic.cancelRequest();
				Log.i("yuan", ">>> cancel loading " + ic.getRequestUrl());
			}
			icList.clear();
		}
	}
	

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		mFirstVisibleItem = firstVisibleItem;
		mVisibleItemCount = visibleItemCount;
		if (isFirstEnter && visibleItemCount > 0) {
			loadBitmap(firstVisibleItem, visibleItemCount);
			isFirstEnter = false;
		}
	}

	private void loadBitmap(int firstVisibleItem, int visibleItemCount) {
		// TODO Auto-generated method stub
		try {
			for (int i = mFirstVisibleItem;
					i < mFirstVisibleItem + mVisibleItemCount; i++) {
				final String url = URLs[i];
				final ImageView imageView =
						(ImageView) mGridView.findViewWithTag(url);
				ImageContainer ic = mImageLoader.get(url,
						new ImageListener() {
							
							public void onErrorResponse(VolleyError error) {
								// TODO Auto-generated method stub
								Log.i("yuan", "error: " + error.toString());
								imageView.setImageResource(R.drawable.empty);
							}
							
							public void onResponse(ImageContainer response, boolean isImmediate) {
								// TODO Auto-generated method stub
								String imageUrl = response.getRequestUrl();
								if (imageView != null) {
									Bitmap bmp = response.getBitmap();
									if (bmp != null) {
										imageView.setImageBitmap(bmp);
										Log.i("yuan", ">>>load image finished " + imageUrl);
									} else {
										imageView.setImageResource(R.drawable.empty);
									}
								}
							}
						},
						WIDTH, HEIGHT);
				Log.i("yuan", i + "loading image " + url);
				icList.add(ic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cancelAllTasks() {
		// TODO Auto-generated method stub
		for (ImageContainer ic : icList) {
			ic.cancelRequest();
			Log.i("yuan", ">>> cancel loading " + ic.getRequestUrl());
		}
		icList.clear();
	}

}
