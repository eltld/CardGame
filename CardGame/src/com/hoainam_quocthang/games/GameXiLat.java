package com.hoainam_quocthang.games;

import java.util.ArrayList;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoainam_quocthang.cardgame.MainActivity;
import com.hoainam_quocthang.cardgame.R;
import com.hoainam_quocthang.deck.Card;
import com.hoainam_quocthang.deck.Deck;
import com.hoainam_quocthang.games.gamexilathelper.AIXilat;
import com.hoainam_quocthang.games.gamexilathelper.BetXiLat;
import com.hoainam_quocthang.games.gamexilathelper.BotXiLat;
import com.hoainam_quocthang.games.gamexilathelper.GetScoreXilat;
import com.hoainam_quocthang.games.gamexilathelper.ScoreXilat;
import com.hoainam_quocthang.games.gamexilathelper.XiLatLogicHelper;
import com.hoainam_quocthang.games.gamexilathelper.XiLatLogicHelper.CallBackDealCardAnimationEnd;
import com.hoainam_quocthang.games.gamexilathelper.XiLatUIHelper;
import com.hoainam_quocthang.gameutil.Turn;

public class GameXiLat extends GameCard
	implements OnClickListener{

	public LinearLayout _linear_layout_top;
	public LinearLayout _linear_layout_bottom;
	public LinearLayout _linear_layout_left;
	public LinearLayout _linear_layout_right;
	
	public ImageButton _chip_5;
	public ImageButton _chip_10;
	public ImageButton _chip_25;
	public ImageButton _chip_50;
	public ImageButton _chip_100;
	public ImageButton _chip_500;
	public ImageButton _btn_bet;
	public ImageButton _btn_unbet;
	public ImageButton _btn_deal;
	public ImageButton _btn_bot;
	public ImageButton _btn_ko_bot;
	public ImageButton _btn_flip_card;
	
	public ImageView _image_view_hand;
	public TextView _text_bet_guide;
	public ImageView _image_view_betted_chips;
	public TextView _text_betted_chips;
	public ImageView _image_view_card_to_deal;
	public TextView _text_ask_bot;
	
	public ImageView _img_set_money_left;
	public ImageView _img_set_money_right;
	public ImageView _img_set_money_bottom;
	
	public TextView _text_view_set_money_left;
	public TextView _text_view_set_money_right;
	public TextView _text_view_set_money_bottom;
	
	public ImageView _img_score_1_1;
	public ImageView _img_score_1_2;
	public ImageView _img_score_1_3;
	public ImageView _img_score_1_4;
	
	public ImageView _img_score_2_1;
	public ImageView _img_score_2_2;
	public ImageView _img_score_2_3;
	public ImageView _img_score_2_4;
	
	public ImageView _img_word_1;
	public ImageView _img_word_2;
	public ImageView _img_word_3;
	public ImageView _img_word_4;
	
    private int _currentTurn = 0;
    public int _currentBet;
    public int _totalBet;
    public int _chosenChip;
    
    public ArrayList<ImageView> _listCardsToDeal = new ArrayList<ImageView>();
    public int[] _moneys = new int[3];
    
    public GameXiLat(MainActivity context){
    	
        this._numOfPlayers = 4;
        this._numCardEachPlayer = 5;
        this._idLayout = R.layout.layout_act_main_bai_xi_lat;

        initializeBaseAttributes(context);
        retrieveViews();
        setUpListCards();
        setListener();
    }

	private void retrieveViews() {
		
		_linear_layout_top = (LinearLayout)_view.findViewById(R.id.linear_layout_top_bai_xi_lat);
		_linear_layout_bottom = (LinearLayout)_view.findViewById(R.id.linear_layout_bottom_bai_xi_lat);
		_linear_layout_left = (LinearLayout)_view.findViewById(R.id.linear_layout_left_bai_xi_lat);
		_linear_layout_right = (LinearLayout)_view.findViewById(R.id.linear_layout_right_bai_xi_lat);
		
		_chip_5 = (ImageButton)_view.findViewById(R.id.chip_5_xi_lat);
		_chip_10 = (ImageButton)_view.findViewById(R.id.chip_10_xi_lat);
		_chip_25 = (ImageButton)_view.findViewById(R.id.chip_25_xi_lat);
		_chip_50 = (ImageButton)_view.findViewById(R.id.chip_50_xi_lat);
		_chip_100 = (ImageButton)_view.findViewById(R.id.chip_100_xi_lat);
		_chip_500 = (ImageButton)_view.findViewById(R.id.chip_500_xi_lat);
		_btn_bet = (ImageButton)_view.findViewById(R.id.btn_bet_xi_lat);
		_btn_unbet = (ImageButton)_view.findViewById(R.id.btn_unbet_xi_lat);
		_btn_deal = (ImageButton)_view.findViewById(R.id.btn_deal_xi_lat);
		_btn_bot = (ImageButton)_view.findViewById(R.id.button_bot_xi_lat);
		_btn_ko_bot = (ImageButton)_view.findViewById(R.id.button_ko_bot_xi_lat);
		_btn_flip_card = (ImageButton)_view.findViewById(R.id.btn_flip_card_xilat);
		
		_image_view_hand = (ImageView)_view.findViewById(R.id.image_view_hand_xilat);
		_text_bet_guide = (TextView)_view.findViewById(R.id.text_view_xi_lat_bet_guide);
		_image_view_betted_chips = (ImageView)_view.findViewById(R.id.image_view_xilat_betted);
		_text_betted_chips = (TextView)_view.findViewById(R.id.text_view_xi_lat_betted);
		_image_view_card_to_deal = (ImageView)_view.findViewById(R.id.image_view_card_to_deal_xilat);
		_text_ask_bot = (TextView)_view.findViewById(R.id.text_view_xi_lat_ask_bot);
		
		_img_set_money_left = (ImageView)_view.findViewById(R.id.img_set_money_left_xi_lat);
		_img_set_money_right = (ImageView)_view.findViewById(R.id.img_set_money_right_xi_lat);
		_img_set_money_bottom = (ImageView)_view.findViewById(R.id.img_set_money_bottom_xi_lat);
		
		_text_view_set_money_left = (TextView)_view.findViewById(R.id.text_view_set_money_left_xi_lat);
		_text_view_set_money_right = (TextView)_view.findViewById(R.id.text_view_set_money_right_xi_lat);
		_text_view_set_money_bottom = (TextView)_view.findViewById(R.id.text_view_set_money_bottom_xi_lat);
		
		_img_score_1_1 = (ImageView)_view.findViewById(R.id.img_score_1_xi_lat_1);
		_img_score_1_2 = (ImageView)_view.findViewById(R.id.img_score_1_xi_lat_2);
		_img_score_1_3 = (ImageView)_view.findViewById(R.id.img_score_1_xi_lat_3);
		_img_score_1_4 = (ImageView)_view.findViewById(R.id.img_score_1_xi_lat_4);
		
		_img_score_2_1 = (ImageView)_view.findViewById(R.id.img_score_2_xi_lat_1);
		_img_score_2_2 = (ImageView)_view.findViewById(R.id.img_score_2_xi_lat_2);
		_img_score_2_3 = (ImageView)_view.findViewById(R.id.img_score_2_xi_lat_3);
		_img_score_2_4 = (ImageView)_view.findViewById(R.id.img_score_2_xi_lat_4);
		
		_img_word_1 = (ImageView)_view.findViewById(R.id.img_word_xi_lat_1);
		_img_word_2 = (ImageView)_view.findViewById(R.id.img_word_xi_lat_2);
		_img_word_3 = (ImageView)_view.findViewById(R.id.img_word_xi_lat_3);
		_img_word_4 = (ImageView)_view.findViewById(R.id.img_word_xi_lat_4);
	}
    
	private void setUpListCards() {
		_listCardsToDeal.add((ImageView)_linear_layout_left.getChildAt(0));
		_listCardsToDeal.add((ImageView)_linear_layout_bottom.getChildAt(0));
		_listCardsToDeal.add((ImageView)_linear_layout_right.getChildAt(0));
		_listCardsToDeal.add((ImageView)_linear_layout_top.getChildAt(0));
		_listCardsToDeal.add((ImageView)_linear_layout_left.getChildAt(1));
		_listCardsToDeal.add((ImageView)_linear_layout_bottom.getChildAt(1));
		_listCardsToDeal.add((ImageView)_linear_layout_right.getChildAt(1));
		_listCardsToDeal.add((ImageView)_linear_layout_top.getChildAt(1));
	}
	
    private void setListener() {
    	
    	_chip_5.setOnClickListener(this);
    	_chip_10.setOnClickListener(this);
    	_chip_25.setOnClickListener(this);
    	_chip_50.setOnClickListener(this);
    	_chip_100.setOnClickListener(this);
    	_chip_500.setOnClickListener(this);
    	_btn_bet.setOnClickListener(this);
    	_btn_unbet.setOnClickListener(this);
    	_btn_deal.setOnClickListener(this);
    	_btn_bot.setOnClickListener(this);
    	_btn_ko_bot.setOnClickListener(this);
    	_btn_flip_card.setOnClickListener(this);
	}
    
    public void hitCard() {
		_currentTurn = Turn.nextTurn(_currentTurn);
		if(_currentTurn == -1){
			//XiLatLogicHelper.getAndShowScore(this);
			_btn_flip_card.setVisibility(View.VISIBLE);
			return;
		}
		//Turn.updateTurnUI(_text_view_turn, _currentTurn);
		if(_currentTurn == 0){
			XiLatUIHelper.showToBot(this);
			return;
		}
		
		if(AIXilat.isAddCard(_allCards.get(_currentTurn))){
			Card card = Deck.DealOneCard(_allCards);
			addCard(card, _currentTurn, XiLatUIHelper.identifyContainerByPlayer(_currentTurn, this));
		}
		else{
			Turn.suspendPlayer(_currentTurn);
			hitCard();
		}
		
	}
    
    public void addCard(Card card, int whichPlayer, LinearLayout container){
    	int index = addCardLogic(card, whichPlayer);
    	addCardVisual(card, container, index, whichPlayer);
    }
    
    private int addCardLogic(Card card, int whichPlayer) {
    	int realNumCards = GetScoreXilat.getRealNumCards(_allCards.get(whichPlayer));
    	
    	_allCards.get(whichPlayer).get(realNumCards).setRank(card.getRank());
    	_allCards.get(whichPlayer).get(realNumCards).setSuit(card.getSuit());
    	if(realNumCards == _numCardEachPlayer - 1){
    		Turn.suspendPlayer(whichPlayer);
    	}
    	return realNumCards;
	}

	@SuppressWarnings("deprecation")
	private void addCardVisual(final Card card, LinearLayout container, int index, final int whichPlayer) {
		final ImageView image = (ImageView)container.getChildAt(index);
		_image_view_card_to_deal.setAlpha(255);
		_image_view_card_to_deal.startAnimation(XiLatLogicHelper.createDealCardAnimation(
				_image_view_card_to_deal, image, new CallBackDealCardAnimationEnd() {
			
			@Override
			public void dealCardAnimationEnded() {
				addCardVisualCallBack(image, card, whichPlayer);
			}
		}, 1000));
	}
	
	protected void addCardVisualCallBack(ImageView cardImage, Card card, int whichPlayer) {
		if(whichPlayer == 0){
			cardImage.setImageResource(Deck.getIdImage(card));
		}
		else{
			cardImage.setImageResource(R.drawable.card_back);
		}
		
		cardImage.setVisibility(View.VISIBLE);
		
		_image_view_card_to_deal.startAnimation(XiLatLogicHelper.createDealCardAnimation(
				cardImage, cardImage, new CallBackDealCardAnimationEnd() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void dealCardAnimationEnded() {
				_image_view_card_to_deal.setAlpha(0);
			}
		}, 0));
		
		hitCard();
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.btn_bet_xi_lat:
			BetXiLat.betHandler(this);
			break;
		case R.id.btn_unbet_xi_lat:
			BetXiLat.unbetHandler(this);
			break;
		case R.id.btn_deal_xi_lat:
			XiLatUIHelper.dealCardHandler(this);
			break;
		case R.id.button_bot_xi_lat:
			BotXiLat.botHandler(this);
			break;
		case R.id.button_ko_bot_xi_lat:
			BotXiLat.kobotHandler(this);
			break;
		case R.id.btn_flip_card_xilat:
			XiLatLogicHelper.flipCardHandler(this);
			break;
		default:
			XiLatUIHelper.chipPressedHandler(this, view);
			break;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void refreshGame() {
		Deck.RefreshDeck(_allCards, 2);
		_currentTurn = 0;
		_totalBet = 0;
		_image_view_card_to_deal.setVisibility(View.INVISIBLE);
		_image_view_card_to_deal.setAlpha(255);
		
		Turn.releaseAllPlayer();
		XiLatLogicHelper.refredhBtn(this);
		BetXiLat.refreshBet(this);
		ScoreXilat.hideScore(this);
		XiLatUIHelper.refreshDeck(this);
		BetXiLat.showBetUI(this);
		
		Animation animationHand = AnimationUtils.loadAnimation(_context, 
    			R.anim.animate_hand);
		_image_view_hand.startAnimation(animationHand);
	}

	@Override
	public void onActivityResultHandler(int requestCode, int resultCode, Intent data) {}

	@Override
	public void beginGame() {
		refreshGame();
	}
}
