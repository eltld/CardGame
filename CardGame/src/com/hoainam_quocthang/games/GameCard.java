package com.hoainam_quocthang.games;

import java.util.ArrayList;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hoainam_quocthang.cardgame.MainActivity;
import com.hoainam_quocthang.cardgame.R;
import com.hoainam_quocthang.deck.Card;
import com.hoainam_quocthang.deck.Deck;
import com.hoainam_quocthang.gameutil.GameUtil;
import com.hoainam_quocthang.gameutil.GameUtil.IFlipCardEnd;

public abstract class GameCard {

	public int _numOfPlayers;
	public int _numCardEachPlayer;
    public ArrayList<ArrayList<Card>> _allCards;
    
    public MainActivity _context = null;
    protected int _idLayout;
    protected View _view;
    
    public abstract void refreshGame();
    public abstract void beginGame();
    public abstract void onActivityResultHandler(int requestCode, int resultCode, Intent data);
    
    public int getNumPlayers(){
        return _numOfPlayers;
    }
    public ArrayList<ArrayList<Card>> getAllCards(){
        return _allCards;
    }
    
    protected void initializeBaseAttributes(MainActivity context){
        _allCards = Deck.createEmptyDeck(_numOfPlayers, _numCardEachPlayer);
        
        _context = context;
        LayoutInflater inflater = _context.getLayoutInflater();
        _view = inflater.inflate(_idLayout, null);
    }
    
    public static GameCard getObjectByName(String className, MainActivity activity){
    	if(className.equals("game_bai_cao")){
    		return new GameBaiCao(activity);
    	}
    	if(className.equals("game_tien_len")){
    		return new GameTienLen(activity);
    	}
    	if(className.equals("game_xi_lat")){
    		return new GameXiLat(activity);
    	}
    	return null;
    }
	public View getView() {
		// TODO Auto-generated method stub
		return _view;
	}
	
	protected void flipCard(LinearLayout container, int whichPlayer){
		int nChild = container.getChildCount();
		for(int i = 0; i < nChild; i++){
			ImageView image = (ImageView)container.getChildAt(i);
			image.setVisibility(View.VISIBLE);
			image.setImageResource(Deck.getIdImage(_allCards.get(whichPlayer).get(i)));
		}
	}
	
	public void flipCard3D(LinearLayout container, int num, int whichPlayer, IFlipCardEnd handler){
		
		for(int i = 0; i < num; i++){
			ImageView image = (ImageView)container.getChildAt(i);
			image.setVisibility(View.VISIBLE);
			GameUtil.flipView(image, Deck.getIdImage(_allCards.get(whichPlayer).get(i)), 
					_context, handler);
		}
	}
	
	protected void unFlipCard(LinearLayout container){
		int nChild = container.getChildCount();
		for(int i = 0; i < nChild; i++){
			ImageView image = (ImageView)container.getChildAt(i);
			image.setVisibility(View.VISIBLE);
			image.setImageResource(R.drawable.card_back);
		}
	}
}
