package com.hoainam_quocthang.games.gametienlenhelper;

import java.util.ArrayList;

import com.hoainam_quocthang.cardgame.R;
import com.hoainam_quocthang.deck.Card;
import com.hoainam_quocthang.deck.Deck;
import com.hoainam_quocthang.games.GameTienLen;
import com.hoainam_quocthang.gameutil.GameUtil.IFlipCardEnd;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GameTienLenUIHelper {

	public static void layoutTable(GameTienLen game) {
		DisplayMetrics dimensionScr = new DisplayMetrics();
    	game._context.getWindowManager().getDefaultDisplay().getMetrics( dimensionScr );
    	
    	ImageView imageViewBegin = game._listCenterCard.get(0);
    	ImageView imageViewEnd = game._listCenterCard.get(game._listCenterCard.size() - 1);
    	
    	int imageViewBeginPos[] = new int[2];
        int imageViewEndPos[] = new int[2];
        imageViewBegin.getLocationOnScreen( imageViewBeginPos );
        imageViewEnd.getLocationOnScreen( imageViewEndPos );
        
    	int xCoor = (dimensionScr.widthPixels - 
    			(imageViewEndPos[0] - imageViewBeginPos[0] + imageViewBegin.getWidth())) / 2;
    	int yCoor = dimensionScr.heightPixels / 2 - imageViewBegin.getHeight() / 2;
    	
		RelativeLayout.LayoutParams layoutParams =
				(RelativeLayout.LayoutParams)imageViewBegin.getLayoutParams();
		layoutParams.setMargins(xCoor, 
				yCoor, layoutParams.rightMargin, layoutParams.bottomMargin);
		imageViewBegin.setLayoutParams(layoutParams);
	}
	
	public static void hitCardsToTable(ArrayList<Card> cards, ArrayList<ImageView> cardsImage, 
			boolean isAI, GameTienLen game) {
		GameTienLenUIHelper.resetTable(game._listCenterCard);
		int offset = game._listCenterCard.size() / 2 - cards.size() / 2;
		for(int i = 0; i < cards.size(); i++){
			if(i == cards.size() - 1){
				hitCardToTable(game._listCenterCard.get(i + offset), 
						cardsImage.get(i), cards.get(i), true, isAI, game);
			}
			else{
				hitCardToTable(game._listCenterCard.get(i + offset), 
						cardsImage.get(i), cards.get(i), false, isAI, game);
			}
		}
	}

	private static void hitCardToTable(ImageView viewToMove, View viewToCollapse, 
			Card card, boolean theLastOne, boolean isAI, final GameTienLen game){
        int srcPos[] = new int[2];
        int desPos[] = new int[2];
        viewToMove.getLocationOnScreen( desPos );
        viewToCollapse.getLocationOnScreen( srcPos );
        
        viewToMove.setImageResource(Deck.getIdImage(card));
        
        TranslateAnimation anim = new TranslateAnimation( srcPos[0] - desPos[0], 0 , 
        		srcPos[1] - desPos[1], 0 );
        if(isAI){
        	anim.setStartOffset(0);
        }
        anim.setDuration(1000);
        anim.setFillAfter( true );
        if(theLastOne){
        	anim.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {}
				
				@Override
				public void onAnimationRepeat(Animation animation) {}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					game.aiHitCard();
				}
			});
        }
        viewToMove.startAnimation(anim);
    }
	
	public static void removeCardFromLinearLayout(ArrayList<ImageView> cardsImage, 
			LinearLayout container){
		for (ImageView imageView : cardsImage) {
			imageView.setVisibility(View.GONE);
		}
		maintainLinearLayout(container, container.getOrientation());
	}
	
	private static void maintainLinearLayout(LinearLayout container, int orientation) {
		ImageView image = null;
		for(int i = 0; i < container.getChildCount(); i++){
			image = (ImageView)container.getChildAt(i);
			if(image.getVisibility() == View.VISIBLE){
				break;
			}
		}
		if(image == null){
			return;
		}
		LinearLayout.LayoutParams lg = (LinearLayout.LayoutParams)image.getLayoutParams();
		if(orientation == LinearLayout.HORIZONTAL){
			lg.leftMargin = 0;
		}
		else{
			lg.topMargin = 0;
		}
		lg.setMargins(lg.leftMargin, lg.topMargin, lg.rightMargin, lg.bottomMargin);
		image.setLayoutParams(lg);
	}
	
	public static ArrayList<ImageView> getRaisedCards(LinearLayout container) {
		ArrayList<ImageView> raisedCards = new ArrayList<ImageView>();
		for(int i = 0; i < container.getChildCount(); i++){
			ImageView image = (ImageView)container.getChildAt(i);
			if(image.getVisibility() == View.VISIBLE && ((String)image.getTag()).equals("up")){
				raisedCards.add(image);
			}
		}
		return raisedCards;
	}
	
	public static void resetTable(ArrayList<ImageView> images) {
		for (ImageView image : images) {
			image.setImageResource(0);
		}
	}
	
	public static void dealCard(GameTienLen game) {
		dealCardEachPlayer(game, game._linear_layout_bottom, R.anim.deal_card_down_to_up, false);
		dealCardEachPlayer(game, game._linear_layout_top, R.anim.deal_card_up_to_down, false);
		dealCardEachPlayer(game, game._linear_layout_left, R.anim.deal_card_down_to_up, false);
		dealCardEachPlayer(game, game._linear_layout_right, R.anim.deal_card_down_to_up, true);
	}
	
	private static void dealCardEachPlayer(final GameTienLen game, LinearLayout container, 
			int animation, boolean theLast) {
		
		for(int i = 0; i < container.getChildCount(); i++){
			Animation _animation = AnimationUtils.loadAnimation(game._context, 
					animation);
			_animation.setStartOffset(i*300);
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
	
	private static void flipCardBegin(final GameTienLen game) {
		IFlipCardEnd handler = new IFlipCardEnd() {
			
			@Override
			public void flipCardEnd() {
				controlBtn(game, 1);
			}
		};
		
		game.flipCard3D(game._linear_layout_bottom, game._numCardEachPlayer, 0, handler);
	}
	
	public static void controlBtn(GameTienLen game, int n){
		switch(n){
		case 0:
			game._btn_prevent.setVisibility(View.GONE);
			game._btn_yield.setVisibility(View.GONE);
			break;
		case 1:
			game._btn_prevent.setVisibility(View.VISIBLE);
			break;
		case 2:
			game._btn_prevent.setVisibility(View.VISIBLE);
			game._btn_yield.setVisibility(View.VISIBLE);
			break;
		}
	}
	
	public static void refreshRaisedCards(GameTienLen game){
		for(int i = 0; i < game._linear_layout_bottom.getChildCount(); i++){
			ImageView card = (ImageView)game._linear_layout_bottom.getChildAt(i);
			LinearLayout.LayoutParams lg = (LinearLayout.LayoutParams)card.
    				getLayoutParams();
			lg.bottomMargin = 0;
    		lg.setMargins(lg.leftMargin, lg.topMargin, lg.rightMargin, lg.bottomMargin);
    		card.setLayoutParams(lg);
    		card.setTag("down");
		}
	}
	
	public static void refreshMarginCards(LinearLayout container, Context context){
		int orientation = container.getOrientation();
		
		for(int i = 1; i < container.getChildCount(); i++){
			ImageView card = (ImageView)container.getChildAt(i);
			LinearLayout.LayoutParams lg = (LinearLayout.LayoutParams)card.
					getLayoutParams();
			if(i == 0){
				if(orientation == LinearLayout.HORIZONTAL){
					lg.leftMargin = 0;
				}
				else{
					lg.topMargin = 0;
				}
			}
			else{
				if(orientation == LinearLayout.HORIZONTAL){
					lg.leftMargin = (int)context.getResources().
							getDimension(R.dimen.game_tien_len_margin_card_horizontal);
				}
				else{
					lg.topMargin = (int)context.getResources().
							getDimension(R.dimen.game_tien_len_margin_card_vertical);
				}
			}
			
			lg.setMargins(lg.leftMargin, lg.topMargin, lg.rightMargin, lg.bottomMargin);
			card.setLayoutParams(lg);
		}
	}
}
