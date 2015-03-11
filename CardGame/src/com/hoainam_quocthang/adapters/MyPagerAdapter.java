package com.hoainam_quocthang.adapters;

import com.hoainam_quocthang.cardgame.ChooseGameActivity;
import com.hoainam_quocthang.cardgame.R;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyPagerAdapter extends PagerAdapter{
	
	private int[] _pages;
	private ChooseGameActivity _context;
	
	public MyPagerAdapter(ChooseGameActivity context, int[] pages){
		_pages = pages;
		_context = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _pages.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		// TODO Auto-generated method stub
		return view == (View)obj;
	}

	@Override
	public Object instantiateItem(View container, int position) {
		LayoutInflater inflater = (LayoutInflater)container.getContext().
				getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		int resId = 0;
		if(position >= 0 && position < _pages.length){
			resId = _pages[position];
		}
		
		View view = inflater.inflate(resId, null);
		setView(view, resId);
		
		((ViewPager)container).addView(view, 0);
		return view;
	}

	private void setView(View view, int resId) {
		TextView textView = null;
		ImageButton btn = null;
		String str = null;
		
		switch(resId){
		case R.layout.layout_act_choose_game_bao_cao:
			textView = (TextView)view.findViewById(R.id.text_view_choose_game_bai_cao);
			btn = (ImageButton)view.findViewById(R.id.btn_choose_game_bai_cao);
			str = _context.getResources().getString(R.string.help_bai_cao);
			break;
		case R.layout.layout_act_choose_game_tien_len:
			textView = (TextView)view.findViewById(R.id.text_view_choose_game_tien_len);
			btn = (ImageButton)view.findViewById(R.id.btn_choose_game_tien_len);
			str = _context.getResources().getString(R.string.help_tien_len);
			break;
		case R.layout.layout_act_choose_game_xi_lat:
			textView = (TextView)view.findViewById(R.id.text_view_choose_game_xi_lat);
			btn = (ImageButton)view.findViewById(R.id.btn_choose_game_xi_lat);
			str = _context.getResources().getString(R.string.help_xi_lat);
			break;
		}
		
		textView.setText(Html.fromHtml(str));
		btn.setOnClickListener(_context);
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager)container).removeView((View)object);
	}

	@Override
	public Parcelable saveState() {
		// TODO Auto-generated method stub
		return null;
	}

}
