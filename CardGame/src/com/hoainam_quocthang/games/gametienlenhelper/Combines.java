package com.hoainam_quocthang.games.gametienlenhelper;

import java.util.ArrayList;
import java.util.Random;

import com.hoainam_quocthang.deck.Card;
import com.hoainam_quocthang.deck.Ranks;

public abstract class Combines {

	protected Random _random = new Random();
	
	protected ArrayList<ArrayList<Card>> _combines;
    protected int _priority;
    protected int _actualPriority;
    
    public abstract void getCombines(ArrayList<Card> cards);
    public abstract ArrayList<Card> preventOpponent(ArrayList<Card> cards);
    
    protected void initializeBaseProperties(){
        _combines = new ArrayList<ArrayList<Card>>();
    }
    
    public ArrayList<ArrayList<Card>> getCombinesAttr(){
    	return _combines;
    }
    
    public static WhichCombine identifyCombine(ArrayList<Card> cards){
        if (cards.size() == 0){
            return WhichCombine.Invalid;
        }

        if (cards.size() == 1){
            return WhichCombine.Rac;
        }

        if (cards.size() == 2){
            return theSameCards(cards);
        }

        if (isSuccessionCards(cards, 0, 1)){
            return WhichCombine.Sanh;
        }

        if (cards.size() == 3 || cards.size() == 4){
            return theSameCards(cards);
        }

        if (cards.size() % 2 == 1 || !isSuccessionCards(cards, 0, 2) || 
        		!isSuccessionCards(cards, 1, 2)){
            return WhichCombine.Invalid;
        }

        if (cards.get(0).getRank().getCode() == cards.get(1).getRank().getCode()){
            return WhichCombine.DoiThong;
        }
        else{
            return WhichCombine.Invalid;
        }
    }
    
    private static WhichCombine theSameCards(ArrayList<Card> cards){
        int count = cards.size();
        for (Card card : cards){
            if (card.getRank().getCode() != cards.get(0).getRank().getCode()){
                return WhichCombine.Invalid;
            }
        }
        return WhichCombine.getInstanceByCode(count);
    }
    
    private static boolean isSuccessionCards(ArrayList<Card> cards, int start, int jump){
        for (int i = start; i < cards.size() - jump; i = i + jump){
            if (cards.get(i).getRank().getCode() == Ranks.Deuce.getCode()){
                return false;
            }
            if (cards.get(i).getRank().getCode() + 1 != cards.get(i + jump).getRank().getCode() &&
                (cards.get(i).getRank().getCode() != Ranks.King.getCode() || 
                cards.get(i + jump).getRank().getCode() != Ranks.Ace.getCode())){
            	
                return false;
            }
        }

        return true;
    }
    
    public static ArrayList<Card> moveList(ArrayList<Card> src, boolean isClearSource){
    	ArrayList<Card> resultList = new ArrayList<Card>();
        for (int i = 0; i < src.size(); i++){
            resultList.add(src.get(i));
        }
        if (isClearSource){
            src.clear();
        }
        return resultList;
    }
    
    protected void getTheSameCardCombines(ArrayList<ArrayList<Card>> desCombines, 
    		ArrayList<Card> deck, int numCards){
    	
        if (deck.size() == 0){
            return;
        }

        ArrayList<Card> tempList = new ArrayList<Card>();
        Card tempCard = deck.get(0);
        tempList.add(tempCard);

        for (int i = 1; i < deck.size(); i++){
            if (deck.get(i).getRank().getCode() == tempCard.getRank().getCode()){
                tempList.add(deck.get(i));
            }
            else{
                if (tempList.size() == numCards){
                    desCombines.add(moveList(tempList, true));
                }
                else{
                    tempList.clear();
                }
                tempList.add(deck.get(i));
            }
            tempCard = deck.get(i);
        }

        if (tempList.size() == numCards){
            desCombines.add(moveList(tempList, true));
        }
    }
    
    public static boolean isValidPrevent(ArrayList<Card> preventor, ArrayList<Card> prevented){
    	WhichCombine combinePreventor = identifyCombine(preventor);
    	
    	if(combinePreventor.getCode() == WhichCombine.Invalid.getCode()){
			return false;
		}
    	if(prevented == null){
    		return true;
    	}
    	
    	switch (combinePreventor) {
		case BaLa:
			return BaLa.isValidPrevent(preventor, prevented);
		case Doi:
			return Doi.isValidPrevent(preventor, prevented);
		case DoiThong:
			return DoiThong.isValidPrevent(preventor, prevented);
		case Rac:
			return Rac.isValidPrevent(preventor, prevented);
		case Sanh:
			return Sanh.isValidPrevent(preventor, prevented);
		case TuQui:
			return TuQui.isValidPrevent(preventor, prevented);
		default:
			return false;
		}
    			
    }
}
