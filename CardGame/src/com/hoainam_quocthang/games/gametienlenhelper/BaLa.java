package com.hoainam_quocthang.games.gametienlenhelper;

import java.util.ArrayList;

import com.hoainam_quocthang.deck.Card;
import com.hoainam_quocthang.deck.Ranks;

public class BaLa extends Combines{

	public BaLa(){
        this._priority = 10;
        initializeBaseProperties();
    }
	
	@Override
	public void getCombines(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		getTheSameCardCombines(this._combines, cards, 3);
	}

	@Override
	public ArrayList<Card> preventOpponent(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		ArrayList<Card> result = new ArrayList<Card>();
		
		WhichCombine whichCombine = identifyCombine(cards);
        switch (whichCombine){
            case BaLa:
                for (ArrayList<Card> list : this._combines){
                    if (list.get(0).Compare(cards.get(0)) > 0 && 
                    		list.get(0).getRank().getCode() != Ranks.Deuce.getCode()){
                        return list;
                    }
                }
                return null;
            case Rac:
                for (ArrayList<Card> list : this._combines){
                    for (Card card : list){
                        if (card.Compare(cards.get(0)) > 0 && _random.nextInt(10) + 1 <= 7){
                        	result.add(card);
                            return result;
                        }
                    }
                }
                return null;
            case Doi:
                for (ArrayList<Card> list : this._combines){
                    if (list.get(0).Compare(cards.get(0)) > 0){
                        if (list.get(0).getRank().getCode() != Ranks.Deuce.getCode()){
                        	result.add(list.get(0));
                        	result.add(list.get(1));
                            return result;
                        }
                        if (_random.nextInt(10) + 1 <= 3){
                        	result.add(list.get(0));
                        	result.add(list.get(1));
                            return result;
                        }
                    }
                }
                return null;
            default:
                return null;
        }
	}
	
	public static boolean isValidPrevent(ArrayList<Card> preventor, ArrayList<Card> prevented){
		WhichCombine whichCombine = identifyCombine(prevented);
		if(whichCombine.getCode() == WhichCombine.BaLa.getCode()){
			if(preventor.get(preventor.size() - 1).Compare(prevented.get(prevented.size() - 1)) > 0){
    			return true;
    		}
		}
		return false;
	}

}
