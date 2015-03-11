package com.hoainam_quocthang.cardgame;

import java.util.ArrayList;

import com.hoainam_quocthang.adapters.MyListViewAdapter;
import com.hoainam_quocthang.gameutil.DataListviewEndAct;

import android.os.Bundle;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;

public class EndGameActivity extends Activity {

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end_game);
		
		ListView listView = (ListView)findViewById(R.id.list_view_end_activity);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("history");
		ArrayList<DataListviewEndAct> listHistory = 
				(ArrayList<DataListviewEndAct>)bundle.getSerializable("history");
		
		MyListViewAdapter adapter = new MyListViewAdapter(this, 
				R.layout.item_list_view_end_act, listHistory);
		listView.setAdapter(adapter);
	}

}
