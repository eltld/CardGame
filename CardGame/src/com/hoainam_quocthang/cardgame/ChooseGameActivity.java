package com.hoainam_quocthang.cardgame;

import com.hoainam_quocthang.adapters.MyPagerAdapter;
import com.hoainam_quocthang.gameutil.GameUtil;
import com.hoainam_quocthang.gameutil.GameUtil.CallBackAnimationEnd;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class ChooseGameActivity extends Activity
	implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_game);
		
		int[] pages = {R.layout.layout_act_choose_game_bao_cao,
				R.layout.layout_act_choose_game_tien_len,
				R.layout.layout_act_choose_game_xi_lat};
		MyPagerAdapter pagerAdapter = new MyPagerAdapter(this, pages);
		ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager_choose_game);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setCurrentItem(1);
	}

	@Override
	public void onClick(View view) {
		ImageButton btn = (ImageButton)view;
		final String game;
		
		switch(view.getId()){
		case R.id.btn_choose_game_bai_cao:
			game = "game_bai_cao";
			break;
		case R.id.btn_choose_game_tien_len:
			game = "game_tien_len";
			break;
		case R.id.btn_choose_game_xi_lat:
			game = "game_xi_lat";
			break;
			default:
				game = null;
		}
		
		btn.startAnimation(GameUtil.createAnimation(this, R.anim.animate_btn_menu, new CallBackAnimationEnd() {
			
			@Override
			public void animationEnded() {
				playGamePressHandler(game);
			}
		}));
		
	}

	protected void playGamePressHandler(String game) {
		Intent intent = new Intent(ChooseGameActivity.this, MainActivity.class);
		intent.putExtra("com.hoainam_quocthang.cardgame.GAME_NAME", game);
		startActivity(intent);
	}
	
}
