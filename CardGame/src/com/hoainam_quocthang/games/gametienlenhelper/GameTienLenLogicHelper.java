package com.hoainam_quocthang.games.gametienlenhelper;

import java.util.ArrayList;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hoainam_quocthang.deck.Card;
import com.hoainam_quocthang.games.GameTienLen;
import com.hoainam_quocthang.gameutil.DataListviewEndAct;

public class GameTienLenLogicHelper {

	private static void sortCards(ArrayList<Card> cards){
		Card _tempCard = new Card();
        int size = cards.size();
        for (int i = 0; i < size - 1; i++){
            for (int j = i + 1; j < size; j++){
                if (cards.get(j).Compare(cards.get(i)) < 0){
                	_tempCard.assignCard(cards.get(i));
                	cards.get(i).assignCard(cards.get(j));
                	cards.get(j).assignCard(_tempCard);
                }
            }
        }
    }
	
	public static void sortAllCards(ArrayList<ArrayList<Card>> allCards){
		for (ArrayList<Card> arrayList : allCards) {
			sortCards(arrayList);
		}
	}
	
	public static ArrayList<Card> getCardsExist(ArrayList<Card> cards){
    	ArrayList<Card> cardsResult = new ArrayList<Card>();
    	for (int i = 0; i < cards.size(); i++){
            if(!cards.get(i).isRemoved()){
            	cardsResult.add(cards.get(i));
            }
        }
    	return cardsResult;
    }
	
	public static void removeCardFromArrayList(ArrayList<Card> cards){
		for(int i = 0; i < cards.size(); i++){
			(cards.get(i)).removeCard();
		}
	}
	
	public static ArrayList<Card> getRaisedCards(LinearLayout container, ArrayList<Card> cards) {
		ArrayList<Card> raisedCards = new ArrayList<Card>();
		for(int i = 0; i < container.getChildCount(); i++){
			ImageView image = (ImageView)container.getChildAt(i);
			if(image.getVisibility() == View.VISIBLE && ((String)image.getTag()).equals("up")){
				raisedCards.add(cards.get(i));
			}
		}
		return raisedCards;
	}
	
	public static boolean isFinish(ArrayList<Card> cards){
		for (Card card : cards) {
			if(!card.isRemoved()){
				return false;
			}
		}
		return true;
	}
	
	public static ArrayList<ImageView> getImageFromCorresponseCards(GameTienLen game,
			ArrayList<Card> cards, int player) {
		ArrayList<ImageView> cardsImage = new ArrayList<ImageView>();
		LinearLayout container = identifyContainerByPlayer(game, player);
		for (Card card : cards) {
			int index = game._allCards.get(player).indexOf(card);
			cardsImage.add((ImageView)container.getChildAt(index));
		}
		return cardsImage;
	}
	
	public static LinearLayout identifyContainerByPlayer(GameTienLen game, int player) {
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
	
	public static DataListviewEndAct calRank(int[] scores){
		String[] rank = new String[4];
		
		rank[scores[0]] = "Nhất";
		rank[scores[1]] = "Nhì";
		rank[scores[2]] = "Ba";
		rank[scores[3]] = "Tư";
		
		return new DataListviewEndAct(rank[0], rank[2], rank[1], rank[3]);
	}
}
