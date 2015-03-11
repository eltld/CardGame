package com.hoainam_quocthang.games.gametienlenhelper;

import java.util.ArrayList;

import com.hoainam_quocthang.deck.Card;
import com.hoainam_quocthang.deck.Ranks;

public class Rac extends Combines{

	public Rac(){
        this._priority = 50;
        initializeBaseProperties();
    }
	
	@Override
	public void getCombines(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		for (Card card : cards)
        {
			ArrayList<Card> temp = new ArrayList<Card>();
			temp.add(card);
            this._combines.add(temp);
        }
	}

	@Override
	public ArrayList<Card> preventOpponent(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		WhichCombine whichCombine = identifyCombine(cards);
        switch (whichCombine){
            case Rac:
                for (ArrayList<Card> list : this._combines){
                    if (list.get(0).Compare(cards.get(0)) > 0){
                        if (list.get(0).getRank().getCode() != Ranks.Deuce.getCode()){
                            return list;
                        }
                        if (_random.nextInt(10) + 1 <= 4){
                            return list;
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
		if(whichCombine.getCode() == WhichCombine.Rac.getCode()){
			if(preventor.get(preventor.size() - 1).Compare(prevented.get(prevented.size() - 1)) > 0){
    			return true;
    		}
		}
		return false;
	}

}
