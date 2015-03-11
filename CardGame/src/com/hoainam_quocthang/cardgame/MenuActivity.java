package com.hoainam_quocthang.cardgame;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class MenuActivity extends Activity 
	implements OnClickListener, AnimationListener {
	
	private ImageButton _btn_new_game;
	private ImageButton _btn_continue;
	private ImageButton _btn_stat;
	private ImageButton _btn_setting;
	private ImageButton _btn_about_us;
	private ImageButton _btn_help;
	
	private ImageButton _button;
	private Intent _intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		retrieveButtons();
		setListener();
	}

	private void retrieveButtons() {
		_btn_new_game = (ImageButton)findViewById(R.id.btn_menu_new_game);
		_btn_continue = (ImageButton)findViewById(R.id.btn_menu_continue);
		_btn_stat = (ImageButton)findViewById(R.id.btn_menu_stat);
		_btn_setting = (ImageButton)findViewById(R.id.btn_menu_setting);
		_btn_about_us = (ImageButton)findViewById(R.id.btn_menu_about_us);
		_btn_help = (ImageButton)findViewById(R.id.btn_menu_help);
	}

	private void setListener() {
		_btn_new_game.setOnClickListener(this);
		_btn_continue.setOnClickListener(this);
		_btn_stat.setOnClickListener(this);
		_btn_setting.setOnClickListener(this);
		_btn_about_us.setOnClickListener(this);
		_btn_help.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		pressButtonHandler(view);
	}
	
	private void pressButtonHandler(View view) {
		_button = (ImageButton)view;
		switch(_button.getId()){
		case R.id.btn_menu_new_game:
			_button.setImageResource(R.drawable.menu_new_game_pressed);
			break;
		case R.id.btn_menu_continue:
			_button.setImageResource(R.drawable.menu_continue_pressed);
			break;
		case R.id.btn_menu_stat:
			_button.setImageResource(R.drawable.menu_stat_pressed);
			break;
		case R.id.btn_menu_setting:
			_button.setImageResource(R.drawable.menu_setting_pressed);
			break;
		case R.id.btn_menu_help:
			_button.setImageResource(R.drawable.menu_help_pressed);
			break;
		case R.id.btn_menu_about_us:
			_button.setImageResource(R.drawable.menu_about_us_pressed);
			break;
		}
		
		Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), 
				R.anim.animate_btn_menu);
		_button.startAnimation(animation);
		animation.setAnimationListener(this);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		releaseButtonHandler(_button);
	}

	@Override
	public void onAnimationRepeat(Animation animation) {}

	@Override
	public void onAnimationStart(Animation animation) {}
	
	private void releaseButtonHandler(View view) {
		ImageButton button = (ImageButton)view;
		switch(button.getId()){
		case R.id.btn_menu_new_game:
			button.setImageResource(R.drawable.menu_new_game);
			_intent = new Intent(MenuActivity.this, ChooseGameActivity.class);
			break;
		case R.id.btn_menu_continue:
			button.setImageResource(R.drawable.menu_continue);
			_intent = new Intent(MenuActivity.this, ChooseGameActivity.class);
			break;
		case R.id.btn_menu_stat:
			button.setImageResource(R.drawable.menu_stat);
			_intent = new Intent(MenuActivity.this, StatisticActivity.class);
			break;
		case R.id.btn_menu_setting:
			button.setImageResource(R.drawable.menu_setting);
			_intent = new Intent(MenuActivity.this, SettingActivity.class);
			break;
		case R.id.btn_menu_help:
			button.setImageResource(R.drawable.menu_help);
			_intent = new Intent(MenuActivity.this, HelpActivity.class);
			break;
		case R.id.btn_menu_about_us:
			button.setImageResource(R.drawable.menu_about_us);
			_intent = new Intent(MenuActivity.this, AboutUsActivity.class);
			break;
		}
		startActivity(_intent);
	}
}
