package com.hoainam_quocthang.games.gamexilathelper;

import com.hoainam_quocthang.cardgame.R;
import com.hoainam_quocthang.deck.Card;
import com.hoainam_quocthang.deck.Deck;
import com.hoainam_quocthang.games.GameXiLat;
import com.hoainam_quocthang.gameutil.GameUtil;
import com.hoainam_quocthang.gameutil.GameUtil.CallBackAnimationEnd;
import com.hoainam_quocthang.gameutil.Turn;

public class BotXiLat {

	public static void botHandler(final GameXiLat game) {
		game._btn_bot.startAnimation(GameUtil.createAnimation(game._context, R.anim.animate_btn_menu, 
				new CallBackAnimationEnd() {
			
			@Override
			public void animationEnded() {
				botHandlerCallBack(game);
			}
		}));
	}
	
	protected static void botHandlerCallBack(GameXiLat game) {
		XiLatUIHelper.hideBot(game);
		Card card = Deck.DealOneCard(game._allCards);
		game.addCard(card, 0, game._linear_layout_bottom);
	}

	public static void kobotHandler(final GameXiLat game) {
		game._btn_ko_bot.startAnimation(GameUtil.createAnimation(game._context, R.anim.animate_btn_menu, 
				new CallBackAnimationEnd() {
			
			@Override
			public void animationEnded() {
				kobotHandlerCallBack(game);
			}
		}));
	}

	protected static void kobotHandlerCallBack(GameXiLat game) {
		XiLatUIHelper.hideBot(game);
		Turn.suspendPlayer(0);
		game.hitCard();
	}
}
