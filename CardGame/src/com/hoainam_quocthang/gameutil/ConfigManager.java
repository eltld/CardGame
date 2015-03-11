package com.hoainam_quocthang.gameutil;

import android.content.Context;
import android.content.SharedPreferences;

public class ConfigManager {

	private static ConfigManager instance = null;
	
	public static ConfigManager getInstance(Context context){
		if(instance == null){
			instance = new ConfigManager(context);
		}
		return instance;
	}
	
	private Context _context;
	private SharedPreferences _preferences = null;
	private SharedPreferences.Editor _editor = null;
	
	private ConfigManager(Context context){
		_context = context;
		_preferences = _context.getSharedPreferences("game_data", Context.MODE_PRIVATE);
		_editor = _preferences.edit();
		
		boolean first = _preferences.getBoolean("is_first", true);
		if(first){
			_editor.putBoolean("is_first", false);
			_editor.putInt("money", 1000000);
			_editor.putInt("so_van", 0);
			_editor.putInt("so_van_thang", 0);
			_editor.putInt("so_van_thua", 0);
			_editor.commit();
		}
	}
	
	public int getMoney(){
		return _preferences.getInt("money", 0);
	}
	
	public void saveMoney(int money){
		_editor.putInt("money", money);
		_editor.commit();
	}
	
	public int getSoVan(){
		return _preferences.getInt("so_van", 0);
	}
	
	public void saveSoVan(int soVan){
		_editor.putInt("so_van", soVan);
		_editor.commit();
	}
	
	public int getSoVanThang(){
		return _preferences.getInt("so_van_thang", 0);
	}
	
	public void saveSoVanThang(int soVanThang){
		_editor.putInt("so_van_thang", soVanThang);
		_editor.commit();
	}
	
	public int getSoVanThua(){
		return _preferences.getInt("so_van_thua", 0);
	}
	
	public void saveSoVanThua(int soVanThua){
		_editor.putInt("so_van_thua", soVanThua);
		_editor.commit();
	}
}
