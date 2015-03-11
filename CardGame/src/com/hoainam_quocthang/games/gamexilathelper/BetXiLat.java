package com.hoainam_quocthang.games.gamexilathelper;

import android.view.View;

import com.hoainam_quocthang.cardgame.R;
import com.hoainam_quocthang.games.GameXiLat;
import com.hoainam_quocthang.games.gamebaicaohelper.BetBaiCao;
import com.hoainam_quocthang.gameutil.GameUtil;
import com.hoainam_quocthang.gameutil.GameUtil.CallBackAnimationEnd;

public class BetXiLat {

	public static void betHandler(final GameXiLat game) {
		game._btn_bet.startAnimation(GameUtil.createAnimation(game._context, R.anim.animate_btn_menu, 
				new CallBackAnimationEnd() {
			
			@Override
			public void animationEnded() {
				betHandlerCallBack(game);
			}
		}));
	}

	protected static void betHandlerCallBack(GameXiLat game) {
		GameUtil.enableImageButton(game._btn_unbet);
		GameUtil.enableImageButton(game._btn_deal);
		
		game._totalBet += game._currentBet;
		game._text_betted_chips.setVisibility(View.VISIBLE);
		StringBuilder str = new StringBuilder("Bạn đã cược ");
		str.append(game._totalBet);
		str.append("$");
		game._text_betted_chips.setText(str.toString());
		
		game._image_view_betted_chips.setVisibility(View.VISIBLE);
		game._image_view_betted_chips.setImageResource(game._chosenChip);
	}

	public static void unbetHandler(final GameXiLat game) {
		game._btn_unbet.startAnimation(GameUtil.createAnimation(game._context, R.anim.animate_btn_menu, 
				new CallBackAnimationEnd() {
			
			@Override
			public void animationEnded() {
				unbetHandlerCallBack(game);
			}
		}));
	}

	protected static void unbetHandlerCallBack(GameXiLat game) {
		game._totalBet = 0;
		GameUtil.disableImageButton(game._btn_deal);
		GameUtil.disableImageButton(game._btn_unbet);
		game._image_view_betted_chips.setVisibility(View.INVISIBLE);
		game._text_betted_chips.setVisibility(View.INVISIBLE);
	}
	
	public static void clearBetUI(GameXiLat game) {
		game._chip_5.setVisibility(View.GONE);
		game._chip_10.setVisibility(View.GONE);
		game._chip_25.setVisibility(View.GONE);
		game._chip_50.setVisibility(View.GONE);
		game._chip_100.setVisibility(View.GONE);
		game._chip_500.setVisibility(View.GONE);
		
		game._btn_bet.setVisibility(View.GONE);
		game._btn_unbet.setVisibility(View.GONE);
		game._btn_deal.setVisibility(View.GONE);
		
		game._image_view_betted_chips.setVisibility(View.GONE);
		game._text_betted_chips.setVisibility(View.GONE);
	}
	
	public static void showBetUI(GameXiLat game) {
		game._chip_5.setVisibility(View.VISIBLE);
		game._chip_10.setVisibility(View.VISIBLE);
		game._chip_25.setVisibility(View.VISIBLE);
		game._chip_50.setVisibility(View.VISIBLE);
		game._chip_100.setVisibility(View.VISIBLE);
		game._chip_500.setVisibility(View.VISIBLE);
		
		game._chip_5.setImageResource(R.drawable.chip_5);
		game._chip_10.setImageResource(R.drawable.chip_10);
		game._chip_25.setImageResource(R.drawable.chip_25);
		game._chip_50.setImageResource(R.drawable.chip_50);
		game._chip_100.setImageResource(R.drawable.chip_100);
		game._chip_500.setImageResource(R.drawable.chip_500);
		
		game._text_bet_guide.setVisibility(View.VISIBLE);
	}
	
	public static void refreshBet(GameXiLat game){
		game._moneys[0] = BetBaiCao.randomBetMoney();
		game._moneys[1] = BetBaiCao.randomBetMoney();
		
		setVisibilityMoney(game, View.INVISIBLE);
	}
	
	public static void setVisibilityMoney(GameXiLat game, int visibility){
		
		game._img_set_money_left.setVisibility(visibility);
		game._img_set_money_right.setVisibility(visibility);
		game._img_set_money_bottom.setVisibility(visibility);
		
		game._text_view_set_money_left.setVisibility(visibility);
		game._text_view_set_money_right.setVisibility(visibility);
		game._text_view_set_money_bottom.setVisibility(visibility);
	}
	
	public static void betDoneHandler(int[] moneys, GameXiLat game){
		
		setVisibilityMoney(game, View.VISIBLE);
		
		game._text_view_set_money_left.setText(moneys[0] + " $");
		game._text_view_set_money_right.setText(moneys[1] + " $");
		game._text_view_set_money_bottom.setText(moneys[2] + " $");
	}
}
