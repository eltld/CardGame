package com.hoainam_quocthang.games.gamexilathelper;

import java.util.ArrayList;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.hoainam_quocthang.cardgame.R;
import com.hoainam_quocthang.deck.Card;
import com.hoainam_quocthang.games.GameXiLat;
import com.hoainam_quocthang.gameutil.GameUtil;
import com.hoainam_quocthang.gameutil.GameUtil.CallBackAnimationEnd;
import com.hoainam_quocthang.gameutil.GameUtil.IFlipCardEnd;

public class XiLatLogicHelper {

	/*
	public static void resetTag(GameXiLat game){
		game._chip_5.setTag("unchosen");
		game._chip_10.setTag("unchosen");
		game._chip_25.setTag("unchosen");
		game._chip_50.setTag("unchosen");
		game._chip_100.setTag("unchosen");
		game._chip_500.setTag("unchosen");
	}*/
	
	public static Card getCardBaseOnImage(int index, GameXiLat game){
		int player = index % 4;
		int cardInPlayer = index / 4;
		switch(player){
		case 0:
			player = 2;
			break;
		case 1:
			player = 0;
			break;
		case 2:
			player = 3;
			break;
		case 3:
			player = 1;
			break;
		}
		return game._allCards.get(player).get(cardInPlayer);
	}
	
	public static Animation createDealCardAnimation(ImageView src, ImageView des, 
			final CallBackDealCardAnimationEnd callBack, long duration){
		
		int srcPos[] = new int[2];
        int desPos[] = new int[2];
        
        src.getLocationOnScreen( srcPos );
        des.getLocationOnScreen( desPos );
        
        TranslateAnimation anim = new TranslateAnimation(0, desPos[0] - srcPos[0], 
        		0, desPos[1] - srcPos[1]);
        anim.setDuration(duration);
        anim.setFillAfter( true );
        anim.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {}
			
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				callBack.dealCardAnimationEnded();
			}
		});
        
        return anim;
	}
	
	public static interface CallBackDealCardAnimationEnd{
		void dealCardAnimationEnded();
	}
	
	public static void getAndShowScore(GameXiLat game){
		ScoreXilat score = GetScoreXilat.calculateScore(game._allCards.get(0));
		score.setScoreImage(game._img_score_1_4, game._img_score_2_4, game._img_word_4);
		
		score = GetScoreXilat.calculateScore(game._allCards.get(1));
		score.setScoreImage(game._img_score_1_2, game._img_score_2_2, game._img_word_2);
		
		score = GetScoreXilat.calculateScore(game._allCards.get(2));
		score.setScoreImage(game._img_score_1_1, game._img_score_2_1, game._img_word_1);
		
		score = GetScoreXilat.calculateScore(game._allCards.get(3));
		score.setScoreImage(game._img_score_1_3, game._img_score_2_3, game._img_word_3);
	}
	
	public static void flipCardHandler(final GameXiLat game){
		game._btn_flip_card.startAnimation(GameUtil.createAnimation(game._context, R.anim.animate_btn_menu, 
				new CallBackAnimationEnd() {
			
			@Override
			public void animationEnded() {
				btnFlipClickHandlerCallBack(game);
			}
		}));
	}

	protected static void btnFlipClickHandlerCallBack(GameXiLat game) {
		String tag = (String)game._btn_flip_card.getTag();
		
		if(tag.equals("flip")){
			flipCards(game);
		}
		if(tag.equals("end")){
			game.refreshGame();
		}
	}
	
	public static void refredhBtn(GameXiLat game){
		game._btn_flip_card.setTag("flip");
		game._btn_flip_card.setImageResource(R.drawable.button_flip_game_bai_cao);
		game._btn_flip_card.setVisibility(View.INVISIBLE);
	}

	private static void flipCards(final GameXiLat game) {
		IFlipCardEnd handler = new IFlipCardEnd() {
			
			@Override
			public void flipCardEnd() {
				game._btn_flip_card.setTag("end");
				game._btn_flip_card.setImageResource(R.drawable.button_end_ses_bai_cao);
				getAndShowScore(game);
			}
		};
		
		game.flipCard3D(game._linear_layout_top, GetScoreXilat.getRealNumCards(game._allCards.get(1)), 1, 
				new IFlipCardEnd() {
					
					@Override
					public void flipCardEnd() {}
				});
		game.flipCard3D(game._linear_layout_left, GetScoreXilat.getRealNumCards(game._allCards.get(2)), 2, 
				new IFlipCardEnd() {
					
					@Override
					public void flipCardEnd() {}
				});
		game.flipCard3D(game._linear_layout_right, GetScoreXilat.getRealNumCards(game._allCards.get(3)), 3, 
				handler);
	}
	
	public static void refreshDeckLogic(GameXiLat game){
		refreshList(game._allCards.get(0));
		refreshList(game._allCards.get(1));
		refreshList(game._allCards.get(2));
		refreshList(game._allCards.get(3));
	}

	private static void refreshList(ArrayList<Card> list) {
		for(int i = 2; i < list.size(); i++){
			Card card = list.get(i);
			card.emptyCard();
		}
	}
}
