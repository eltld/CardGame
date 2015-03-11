package com.hoainam_quocthang.games.gamebaicaohelper;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hoainam_quocthang.cardgame.R;
import com.hoainam_quocthang.deck.Deck;
import com.hoainam_quocthang.games.GameBaiCao;
import com.hoainam_quocthang.gameutil.GameUtil;
import com.hoainam_quocthang.gameutil.GameUtil.IFlipCardEnd;

public class BaiCaoUIHelper {

	public static void dealCard(GameBaiCao game) {
		dealCardEachPlayer(game, game._LinearLayout_bottom, R.anim.deal_card_down_to_up, false);
		dealCardEachPlayer(game, game._LinearLayout_top, R.anim.deal_card_up_to_down, false);
		dealCardEachPlayer(game, game._LinearLayout_left, R.anim.deal_card_down_to_up, false);
		dealCardEachPlayer(game, game._LinearLayout_right, R.anim.deal_card_down_to_up, true);
	}
	
	private static void dealCardEachPlayer(final GameBaiCao game, LinearLayout container, 
			int animation, boolean theLast) {
		
		for(int i = 0; i < container.getChildCount(); i++){
			Animation _animation = AnimationUtils.loadAnimation(game._context, 
					animation);
			_animation.setStartOffset(i*1000);
			if(theLast && i == container.getChildCount() - 1){
				_animation.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {}
					
					@Override
					public void onAnimationRepeat(Animation animation) {}
					
					@Override
					public void onAnimationEnd(Animation animation) {
						flipCardBegin(game);
					}
				});
			}
        	container.getChildAt(i).startAnimation(_animation);
        }
	}
	
	private static void flipCardBegin(final GameBaiCao game) {
		IFlipCardEnd handler = new IFlipCardEnd() {
			
			@Override
			public void flipCardEnd() {
				GameUtil.enableImageButton(game._btn_flipCard);
			}
		};
		
		game.flipCard3D(game._LinearLayout_bottom, 3, 0, handler);
		game.flipCard3D(game._LinearLayout_top, 2, 1, handler);
		game.flipCard3D(game._LinearLayout_left, 2, 2, handler);
		game.flipCard3D(game._LinearLayout_right, 2, 3, handler);
	}
	
	private static void getAndShowScore(GameBaiCao game) {
		int i = ScoreBaiCao.getScore(game._allCards.get(0));
		showScore(game._img_score_4, game._img_nut_4, game._img_3_tien_4, i);
		game._scores[3] = i;
		
		i = ScoreBaiCao.getScore(game._allCards.get(1));
		showScore(game._img_score_2, game._img_nut_2, game._img_3_tien_2, i);
		game._scores[1] = i;
		
		i = ScoreBaiCao.getScore(game._allCards.get(2));
		showScore(game._img_score_1, game._img_nut_1, game._img_3_tien_1, i);
		game._scores[0] = i;
		
		i = ScoreBaiCao.getScore(game._allCards.get(3));
		showScore(game._img_score_3, game._img_nut_3, game._img_3_tien_3, i);
		game._scores[2] = i;
		
		game._btn_flipCard.setImageResource(R.drawable.button_end_ses_bai_cao);
		game._btn_flipCard.setTag("end");
	}
	
	private static void showScore(ImageView scoreImg, ImageView nut, ImageView baTien, int score) {
		if(score == 10){
			baTien.setVisibility(View.VISIBLE);
		}
		else{
			nut.setVisibility(View.VISIBLE);
			scoreImg.setVisibility(View.VISIBLE);
			scoreImg.setImageResource(ScoreBaiCao.getImageOfScore(score));
		}
	}

	public static void flipCardEnd(final GameBaiCao game) {
		flipACard(game, game._LinearLayout_top, 1, new IFlipCardEnd() {
			
			@Override
			public void flipCardEnd() {}
		});
		
		flipACard(game, game._LinearLayout_left, 2, new IFlipCardEnd() {
			
			@Override
			public void flipCardEnd() {}
		});
		
		flipACard(game, game._LinearLayout_right, 3, new IFlipCardEnd() {
			
			@Override
			public void flipCardEnd() {
				getAndShowScore(game);
			}
		});
	}

	private static void flipACard(GameBaiCao game, LinearLayout container, 
			int player, IFlipCardEnd handler) {
		
		ImageView img = (ImageView)container.getChildAt(game._numCardEachPlayer - 1);
		int imgId = Deck.getIdImage(game._allCards.get(player).get(game._numCardEachPlayer - 1));
		GameUtil.flipView(img, imgId, game._context, handler);
	}
	
	public static void refreshBtn(GameBaiCao game){
		game._btn_flipCard.setImageResource(R.drawable.button_bet_game_bai_cao);
		game._btn_flipCard.setTag("bet");
		GameUtil.disableImageButton(game._btn_flipCard);
	}
}
