package com.hoainam_quocthang.adapters;

import java.util.ArrayList;

import com.hoainam_quocthang.cardgame.R;
import com.hoainam_quocthang.gameutil.DataListviewEndAct;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyListViewAdapter extends ArrayAdapter<DataListviewEndAct>{

	private Activity _context = null;
    private ArrayList<DataListviewEndAct> _arr = null;
    private int _layoutId;
    
	public MyListViewAdapter(Activity context, int layoutId, ArrayList<DataListviewEndAct> objects) {
		super(context, layoutId, objects);
		
		_context = context;
        _arr = objects;
        _layoutId = layoutId;
	}
	
	public View getView(int position, View convertView, 
            ViewGroup parent){
		
		if(convertView == null){
			LayoutInflater inflater = _context.getLayoutInflater();
            convertView = inflater.inflate(_layoutId, null);
		}
		
		if(_arr.size() > 0 && position >= 0){
			TextView textView1 = (TextView)convertView.
                    findViewById(R.id.text_view_1_item_lv_end_act);
			TextView textView2 = (TextView)convertView.
                    findViewById(R.id.text_view_2_item_lv_end_act);
			TextView textView3 = (TextView)convertView.
                    findViewById(R.id.text_view_3_item_lv_end_act);
			TextView textView4 = (TextView)convertView.
                    findViewById(R.id.text_view_4_item_lv_end_act);
			
			DataListviewEndAct data = _arr.get(position);
			
			textView1.setText(data.text1);
			textView2.setText(data.text2);
			textView3.setText(data.text3);
			textView4.setText(data.text4);
			
			setColor(textView1, textView1.getText().toString());
			setColor(textView2, textView2.getText().toString());
			setColor(textView3, textView3.getText().toString());
			setColor(textView4, textView4.getText().toString());
		}
		return convertView;
	}

	private void setColor(TextView textView, String text) {
		
		if(text.equals("Nhất")){
			textView.setTextColor(_context.getResources().getColor(R.color.red));
			return;
		}
		if(text.equals("Tư")){
			textView.setTextColor(_context.getResources().getColor(R.color.green));
			return;
		}
		textView.setTextColor(_context.getResources().getColor(R.color.text_view_guide_play_game));
		
	}
	
}
