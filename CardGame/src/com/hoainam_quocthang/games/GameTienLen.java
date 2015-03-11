package com.hoainam_quocthang.games;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hoainam_quocthang.cardgame.EndGameActivity;
import com.hoainam_quocthang.cardgame.MainActivity;
import com.hoainam_quocthang.cardgame.R;
import com.hoainam_quocthang.deck.Card;
import com.hoainam_quocthang.deck.Deck;
import com.hoainam_quocthang.games.gametienlenhelper.Combines;
import com.hoainam_quocthang.games.gametienlenhelper.EncapsulateCombines;
import com.hoainam_quocthang.games.gametienlenhelper.GameTienLenLogicHelper;
import com.hoainam_quocthang.games.gametienlenhelper.GameTienLenUIHelper;
import com.hoainam_quocthang.games.gametienlenhelper.LoadData;
import com.hoainam_quocthang.gameutil.DataListviewEndAct;
import com.hoainam_quocthang.gameutil.GameUtil;
import com.hoainam_quocthang.gameutil.Turn;
import com.hoainam_quocthang.gameutil.GameUtil.CallBackAnimationEnd;

public class GameTienLen extends GameCard 
	implements OnClickListener, OnGlobalLayoutListener{
	
	private EncapsulateCombines _encapsulateCombines = new EncapsulateCombines();
	
	public ImageButton _btn_prevent;
	public ImageButton _btn_yield;
	public LinearLayout _linear_layout_top;
	public LinearLayout _linear_layout_bottom;
	public LinearLayout _linear_layout_left;
	public LinearLayout _linear_layout_right;
	
	private int _currentTurn;
	private int _hostPlayer;
	private int _numPlayerFinished;
	private boolean _isEndGame;
	private ArrayList<Card> _cardsToPrevent;
	public ArrayList<DataListviewEndAct> _history = new ArrayList<DataListviewEndAct>();
	public ArrayList<ImageView> _listCenterCard;
	public int[] _rank;
	
	public GameTienLen(MainActivity context){
		
        this._numOfPlayers = 4;
        this._numCardEachPlayer = 13;
        this._idLayout = R.layout.layout_act_main_bai_tien_len;

        initializeBaseAttributes(context);
        _listCenterCard = LoadData.getCenterCard(_view);
        _rank = new int[_numOfPlayers];
        retrieveViews();
        setListener();
    }
	
	private void retrieveViews() {
		_btn_prevent = (ImageButton)_view.findViewById(R.id.btn_chan_tien_len);
		_btn_yield = (ImageButton)_view.findViewById(R.id.btn_thoi_tien_len);
		
		_linear_layout_top = (LinearLayout)_view.findViewById(R.id.linear_layout_top_bai_tien_len);
		_linear_layout_bottom = (LinearLayout)_view.
				findViewById(R.id.linear_layout_bottom_bai_tien_len);
		_linear_layout_left = (LinearLayout)_view.findViewById(R.id.linear_layout_left_bai_tien_len);
		_linear_layout_right = (LinearLayout)_view.findViewById(R.id.linear_layout_right_bai_tien_len);
	}
	
	private void setListener() {
		_btn_prevent.setOnClickListener(this);
		_btn_yield.setOnClickListener(this);
		_listCenterCard.get(0).getViewTreeObserver().addOnGlobalLayoutListener(this);
	}
	
	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.btn_chan_tien_len:
			_btn_prevent.startAnimation(GameUtil.createAnimation(_context, R.anim.animate_btn_menu, 
					new CallBackAnimationEnd() {
				
				@Override
				public void animationEnded() {
					humanHitCard();
				}
			}));
			break;
		case R.id.btn_thoi_tien_len:
			_btn_prevent.startAnimation(GameUtil.createAnimation(_context, R.anim.animate_btn_menu, 
					new CallBackAnimationEnd() {
				
				@Override
				public void animationEnded() {
					GameTienLenUIHelper.controlBtn(getGameTienLen(), 0);
					Turn.suspendPlayer(0);
					aiHitCard();
				}
			}));
			break;
		}
		
	}
	
	protected GameTienLen getGameTienLen() {
		return this;
	}

	@Override
	public void onGlobalLayout() {
		GameTienLenUIHelper.layoutTable(this);
	}
	
	public void aiHitCard(){
		if(_isEndGame){
			return;
		}
		_currentTurn = Turn.nextTurn(_currentTurn);
		if(_currentTurn == -1){
			Turn.releaseAllPlayer();
			_currentTurn = _hostPlayer;
			_hostPlayer = Turn.nextTurn(_currentTurn);
			aiHitCard();
			return;
		}
		//Turn.updateTurnUI(_text_view, _currentTurn);
		if(_currentTurn == 0){
			if(_hostPlayer == 0){
				GameTienLenUIHelper.controlBtn(this, 1);
			}
			else{
				GameTienLenUIHelper.controlBtn(this, 2);
			}
			return;
		}
		if(_currentTurn == _hostPlayer){
			Turn.releaseAllPlayer();
			ArrayList<Card> cards = yieldCards(null, _currentTurn);
			_cardsToPrevent = cards;
			ArrayList<ImageView> cardsImage = 
					GameTienLenLogicHelper.getImageFromCorresponseCards(this, cards, _currentTurn);
			hitCards(cards, cardsImage, GameTienLenLogicHelper.identifyContainerByPlayer(this, _currentTurn), 
					true, _currentTurn);
		}
		else{
			ArrayList<Card> cards = yieldCards(_cardsToPrevent, _currentTurn);
			if(cards == null){
				Turn.suspendPlayer(_currentTurn);
				aiHitCard();
			}
			else{
				_cardsToPrevent = cards;
				_hostPlayer = _currentTurn;
				ArrayList<ImageView> cardsImage = 
						GameTienLenLogicHelper.getImageFromCorresponseCards(this, cards, _currentTurn);
				hitCards(cards, cardsImage, GameTienLenLogicHelper.identifyContainerByPlayer(this, _currentTurn), 
						true, _currentTurn);
			}
		}
	}

	private void humanHitCard(){
		ArrayList<Card> raisedCards = GameTienLenLogicHelper.
				getRaisedCards(_linear_layout_bottom, _allCards.get(0));
		ArrayList<ImageView> raisedCardsImage = GameTienLenUIHelper.
				getRaisedCards(_linear_layout_bottom);
		if(_currentTurn != _hostPlayer){
			if(Combines.isValidPrevent(raisedCards, _cardsToPrevent)){
				GameTienLenUIHelper.controlBtn(this, 0);
				_cardsToPrevent = raisedCards;
				_hostPlayer = 0;
				hitCards(raisedCards, raisedCardsImage, _linear_layout_bottom, false, 0);
			}
		}
		else{
			if(Combines.isValidPrevent(raisedCards, null)){
				GameTienLenUIHelper.controlBtn(this, 0);
				Turn.releaseAllPlayer();
				_cardsToPrevent = raisedCards;
				hitCards(raisedCards, raisedCardsImage, _linear_layout_bottom, false, 0);
			}
		}
	}
	
	private void hitCards(ArrayList<Card> cards, ArrayList<ImageView> cardsImage, 
			LinearLayout container, boolean isAI, int player){
		GameTienLenUIHelper.hitCardsToTable(cards, cardsImage, isAI, this);
		GameTienLenLogicHelper.removeCardFromArrayList(cards);
		GameTienLenUIHelper.removeCardFromLinearLayout(cardsImage, 
				container);
		if(GameTienLenLogicHelper.isFinish(_allCards.get(player))){
			_rank[_numPlayerFinished] = player;
			//_text_view_nhung_thang_da_ve.setText(String.valueOf(player));
			Turn.suspendPlayer(player);
			Turn.finishPlayer(player);
			_numPlayerFinished ++;
			if(_numPlayerFinished == _numOfPlayers - 1){
				_isEndGame = true;
				_rank[_numPlayerFinished] = Turn.nextTurn(_currentTurn);
				_history.add(GameTienLenLogicHelper.calRank(_rank));
				
				Bundle bundle = new Bundle();
				bundle.putSerializable("history", _history);
				Intent intent = new Intent(_context, EndGameActivity.class);
				intent.putExtra("history", bundle);
				_context.startActivityForResult(intent, MainActivity.REQUEST_CODE_ENDGAME_ACTIVITY);
			}
		}
	}
	
	private ArrayList<Card> yieldCards(ArrayList<Card> listBase, int whichPlayer){
        if (listBase == null){
            return initializeLoopCard(whichPlayer);
        }
        else{
            return preventOpponent(listBase, whichPlayer);
        }
    }
	
	private ArrayList<Card> initializeLoopCard(int whichPlayer){
        _encapsulateCombines.parseDeck(GameTienLenLogicHelper.
        		getCardsExist(_allCards.get(whichPlayer)));
        return _encapsulateCombines.getCombineUponRandom();
    }

	
    private ArrayList<Card> preventOpponent(ArrayList<Card> listBase, int whichPlayer){
    	_encapsulateCombines.parseDeck(GameTienLenLogicHelper.
        		getCardsExist(_allCards.get(whichPlayer)));
        return _encapsulateCombines.preventOpponent(listBase);
    }
    
	@Override
	public void refreshGame() {
		
		Deck.RefreshDeck(_allCards, this._numCardEachPlayer);
		
		_currentTurn = 0;
		_hostPlayer = 0;
		Turn.releaseAllPlayer();
		Turn.restartAllPlayer();
		_numPlayerFinished = 0;
		_isEndGame = false;
		_cardsToPrevent = null;
		
		GameTienLenLogicHelper.sortAllCards(_allCards);
		GameTienLenUIHelper.controlBtn(this, 0);
		GameTienLenUIHelper.refreshRaisedCards(this);
		GameTienLenUIHelper.refreshMarginCards(_linear_layout_bottom, _context);
		GameTienLenUIHelper.refreshMarginCards(_linear_layout_top, _context);
		GameTienLenUIHelper.refreshMarginCards(_linear_layout_left, _context);
		GameTienLenUIHelper.refreshMarginCards(_linear_layout_right, _context);
		GameTienLenUIHelper.resetTable(_listCenterCard);
		
		/*flipCard(_linear_layout_bottom, 0);
		flipCard(_linear_layout_top, 1);
		flipCard(_linear_layout_left, 2);
		flipCard(_linear_layout_right, 3);*/
		
		unFlipCard(_linear_layout_bottom);
		unFlipCard(_linear_layout_top);
		unFlipCard(_linear_layout_left);
		unFlipCard(_linear_layout_right);
		
		GameTienLenUIHelper.dealCard(this);
	}

	@Override
	public void onActivityResultHandler(int requestCode, int resultCode, Intent data) {
		if(requestCode == MainActivity.REQUEST_CODE_ENDGAME_ACTIVITY){
			refreshGame();
		}
	}

	@Override
	public void beginGame() {
		refreshGame();
	}
}
