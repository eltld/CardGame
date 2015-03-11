package com.hoainam_quocthang.games.gamexilathelper;

import com.hoainam_quocthang.cardgame.R;
import com.hoainam_quocthang.deck.Deck;
import com.hoainam_quocthang.games.GameXiLat;
import com.hoainam_quocthang.games.gamexilathelper.XiLatLogicHelper.CallBackDealCardAnimationEnd;
import com.hoainam_quocthang.gameutil.GameUtil;
import com.hoainam_quocthang.gameutil.GameUtil.CallBackAnimationEnd;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class XiLatUIHelper {

	public static LinearLayout identifyContainerByPlayer(int player, GameXiLat game) {
		switch(player){
		case 0:
			return game._linear_layout_bottom;
		case 1:
			return game._linear_layout_top;
		case 2:
			return game._linear_layout_left;
		case 3:
			return game._linear_layout_right;
		}
		return null;
	}
	
	public static void resetChips(GameXiLat game){
		game._chip_5.setImageResource(R.drawable.chip_5);
		game._chip_10.setImageResource(R.drawable.chip_10);
		game._chip_25.setImageResource(R.drawable.chip_25);
		game._chip_50.setImageResource(R.drawable.chip_50);
		game._chip_100.setImageResource(R.drawable.chip_100);
		game._chip_500.setImageResource(R.drawable.chip_500);
	}
	
	public static void focusChip(ImageButton chip, GameXiLat game){
		switch(chip.getId()){
		case R.id.chip_5_xi_lat:
			chip.setImageResource(R.drawable.chip_5_focus);
			game._currentBet = 5;
			game._chosenChip = R.drawable.chip_5_tilt;
			break;
		case R.id.chip_10_xi_lat:
			chip.setImageResource(R.drawable.chip_10_focus);
			game._currentBet = 10;
			game._chosenChip = R.drawable.chip_10_tilt;
			break;
		case R.id.chip_25_xi_lat:
			chip.setImageResource(R.drawable.chip_25_focus);
			game._currentBet = 25;
			game._chosenChip = R.drawable.chip_25_tilt;
			break;
		case R.id.chip_50_xi_lat:
			chip.setImageResource(R.drawable.chip_50_focus);
			game._currentBet = 50;
			game._chosenChip = R.drawable.chip_50_tilt;
			break;
		case R.id.chip_100_xi_lat:
			chip.setImageResource(R.drawable.chip_100_focus);
			game._currentBet = 100;
			game._chosenChip = R.drawable.chip_100_tilt;
			break;
		case R.id.chip_500_xi_lat:
			chip.setImageResource(R.drawable.chip_500_focus);
			game._currentBet = 500;
			game._chosenChip = R.drawable.chip_500_tilt;
			break;
		}
	}
	
	public static void chipPressedHandler(GameXiLat game, View view) {
		prepareToBet(game);
		resetChips(game);
		focusChip((ImageButton)view, game);
	}

	private static void prepareToBet(GameXiLat game) {
		if(game._btn_bet.getVisibility() == View.VISIBLE){
			return;
		}
		
		game._text_bet_guide.setVisibility(View.INVISIBLE);
		game._image_view_hand.clearAnimation();
		game._image_view_hand.setVisibility(View.INVISIBLE);
		
		game._btn_bet.setVisibility(View.VISIBLE);
		game._btn_unbet.setVisibility(View.VISIBLE);
		game._btn_deal.setVisibility(View.VISIBLE);
		
		GameUtil.disableImageButton(game._btn_deal);
		GameUtil.disableImageButton(game._btn_unbet);
	}
	
	public static void showToBot(GameXiLat game){
		game._btn_bot.setVisibility(View.VISIBLE);
		game._btn_ko_bot.setVisibility(View.VISIBLE);
		game._text_ask_bot.setVisibility(View.VISIBLE);
	}
	
	public static void hideBot(GameXiLat game){
		game._btn_bot.setVisibility(View.INVISIBLE);
		game._btn_ko_bot.setVisibility(View.INVISIBLE);
		game._text_ask_bot.setVisibility(View.INVISIBLE);
	}
	
	public static void dealCardHandler(final GameXiLat game) {
		game._btn_deal.startAnimation(GameUtil.createAnimation(game._context, R.anim.animate_btn_menu, 
				new CallBackAnimationEnd() {
			
			@Override
			public void animationEnded() {
				/*flipCard(_linear_layout_bottom, 0);
				flipCard(_linear_layout_top, 1);
				flipCard(_linear_layout_left, 2);
				flipCard(_linear_layout_right, 3);*/
				game._moneys[2] = game._totalBet;
				BetXiLat.betDoneHandler(game._moneys, game);
				
				BetXiLat.clearBetUI(game);
				dealCardBeginGame(game._listCardsToDeal.get(0), game);
			}
		}));
	}
	
	public static void dealCardBeginGame(final ImageView card, final GameXiLat game) {
		
        game._image_view_card_to_deal.startAnimation(XiLatLogicHelper.createDealCardAnimation(
        		game._image_view_card_to_deal, card, new CallBackDealCardAnimationEnd() {
			
			@Override
			public void dealCardAnimationEnded() {
				dealCardBeginGameCallBack(card, game);
			}
		}, 1000));
	}
	
	protected static void dealCardBeginGameCallBack(ImageView card, final GameXiLat game) {
		card.setVisibility(View.VISIBLE);
		int index = game._listCardsToDeal.indexOf(card);
		if(index%4 == 1){
			card.setImageResource(Deck.getIdImage(XiLatLogicHelper.getCardBaseOnImage(index, game)));
		}
		else{
			card.setImageResource(R.drawable.card_back);
		}
		
		if(index + 1 < game._listCardsToDeal.size()){
			dealCardBeginGame(game._listCardsToDeal.get(index + 1), game);
		}
		else{
			game._image_view_card_to_deal.startAnimation(XiLatLogicHelper.createDealCardAnimation(
	        		card, card, new CallBackDealCardAnimationEnd() {
				
				@SuppressWarnings("deprecation")
				@Override
				public void dealCardAnimationEnded() {
					game._image_view_card_to_deal.setAlpha(0);
				}
			}, 0));
			XiLatUIHelper.showToBot(game);
		}
	}
	
	public static void refreshDeck(GameXiLat game){
		refreshDeckUI(game);
		XiLatLogicHelper.refreshDeckLogic(game);
	}

	private static void refreshDeckUI(GameXiLat game) {
		refreshLinearLayout(game._linear_layout_bottom);
		refreshLinearLayout(game._linear_layout_top);
		refreshLinearLayout(game._linear_layout_left);
		refreshLinearLayout(game._linear_layout_right);
	}

	private static void refreshLinearLayout(LinearLayout container) {
		for(int i = 0; i < container.getChildCount(); i++){
			ImageView card = (ImageView)container.getChildAt(i);
			if(i < 2){
				card.setVisibility(View.INVISIBLE);
			}
			else{
				card.setVisibility(View.GONE);
			}
		}
	}
}
