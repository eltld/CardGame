package com.hoainam_quocthang.games.gamebaicaohelper;

import java.util.Random;

import android.view.View;

import com.hoainam_quocthang.cardgame.R;
import com.hoainam_quocthang.games.GameBaiCao;

public class BetBaiCao {

	public static void refreshBet(GameBaiCao game){
		game._moneys[0] = BetBaiCao.randomBetMoney();
		game._moneys[1] = BetBaiCao.randomBetMoney();
		game._moneys[2] = BetBaiCao.randomBetMoney();
		
		setVisibilityMoney(game, View.INVISIBLE);
	}
	
	public static void setVisibilityMoney(GameBaiCao game, int visibility){
		
		game._img_set_money_1.setVisibility(visibility);
		game._img_set_money_2.setVisibility(visibility);
		game._img_set_money_3.setVisibility(visibility);
		game._img_set_money_4.setVisibility(visibility);
		
		game._text_view_set_money_1.setVisibility(visibility);
		game._text_view_set_money_2.setVisibility(visibility);
		game._text_view_set_money_3.setVisibility(visibility);
		game._text_view_set_money_4.setVisibility(visibility);
	}
	
	public static void betDoneHandler(int[] moneys, GameBaiCao game){
		game._btn_flipCard.setImageResource(R.drawable.button_flip_game_bai_cao);
		game._btn_flipCard.setTag("flip");
		
		setVisibilityMoney(game, View.VISIBLE);
		
		game._text_view_set_money_1.setText(moneys[0] + " $");
		game._text_view_set_money_2.setText(moneys[1] + " $");
		game._text_view_set_money_3.setText(moneys[2] + " $");
		game._text_view_set_money_4.setText(moneys[3] + " $");
	}
	
	public static int randomBetMoney(){
		Random random = new Random();
		int i = random.nextInt(200) + 1;
		return 5 * i;
	}
}
