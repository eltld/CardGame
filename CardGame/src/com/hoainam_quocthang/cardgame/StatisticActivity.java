package com.hoainam_quocthang.cardgame;

import com.hoainam_quocthang.gameutil.ConfigManager;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;

public class StatisticActivity extends Activity {

	private TextView _tv_money;
	private TextView _tv_so_van;
	private TextView _tv_so_van_thang;
	private TextView _tv_so_van_thua;
	
	private ConfigManager _manager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistic);
		
		_manager = ConfigManager.getInstance(getApplicationContext());
				
		retrieveViews();
		setTextForView();
	}

	private void retrieveViews() {
		_tv_money = (TextView)findViewById(R.id.text_view_2_stat_scr);
		_tv_so_van = (TextView)findViewById(R.id.text_view_3_stat_scr);
		_tv_so_van_thang = (TextView)findViewById(R.id.text_view_4_stat_scr);
		_tv_so_van_thua = (TextView)findViewById(R.id.text_view_5_stat_scr);
	}
	
	private void setTextForView() {
		String str;
		
		str = String.format("Số tiền đang có: %s $", _manager.getMoney());
		_tv_money.setText(str);
		
		str = String.format("Số ván đã chơi: %s ván", _manager.getSoVan());
		_tv_so_van.setText(str);
		
		str = String.format("Số ván đã thắng: %s ván", _manager.getSoVanThang());
		_tv_so_van_thang.setText(str);
		
		str = String.format("Số ván đã thua: %s ván", _manager.getSoVanThua());
		_tv_so_van_thua.setText(str);
	}
}
