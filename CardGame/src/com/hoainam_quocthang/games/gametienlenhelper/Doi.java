package com.hoainam_quocthang.games.gametienlenhelper;

import java.util.ArrayList;

import com.hoainam_quocthang.deck.Card;
import com.hoainam_quocthang.deck.Ranks;

public class Doi extends Combines{

	public Doi(){
        this._priority = 15;
        initializeBaseProperties();
    }
	
	@Override
	public void getCombines(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		getTheSameCardCombines(this._combines, cards, 2);
	}

	@Override
	public ArrayList<Card> preventOpponent(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		ArrayList<Card> result = new ArrayList<Card>();
		WhichCombine whichCombine = identifyCombine(cards);
        switch (whichCombine){
            case Doi:
                for (ArrayList<Card> list : this._combines){
                    if (list.get(1).Compare(cards.get(1)) > 0){
                        if (list.get(1).getRank().getCode() != Ranks.Deuce.getCode()){
                            return list;
                        }
                        if (_random.nextInt(10) + 1 <= 3){
                            return list;
                        }
                    }
                }
                return null;
            case Rac:
                if (this._combines.size() == 0){
                    return null;
                }
                if (this._combines.get(this._combines.size() - 1).get(0).getRank().getCode() != 
                		Ranks.Deuce.getCode()){
                    return null;
                }
                for (Card card : this._combines.get(this._combines.size() - 1)){
                    if (card.Compare(cards.get(0)) > 0 && _random.nextInt(10) + 1 <= 5){
                    	result.add(card);
                        return result;
                    }
                }
                return null;
            default:
                return null;
        }
	}
	
	public static boolean isValidPrevent(ArrayList<Card> preventor, ArrayList<Card> prevented){
		WhichCombine whichCombine = identifyCombine(prevented);
		if(whichCombine.getCode() == WhichCombine.Doi.getCode()){
			if(preventor.get(preventor.size() - 1).Compare(prevented.get(prevented.size() - 1)) > 0){
    			return true;
    		}
		}
		return false;
	}

}
