package com.hoainam_quocthang.games.gametienlenhelper;

import java.util.ArrayList;

import com.hoainam_quocthang.deck.Card;
import com.hoainam_quocthang.deck.Ranks;

public class TuQui extends Combines{

	public TuQui(){
        this._priority = 5;
        initializeBaseProperties();
    }
	
	@Override
	public void getCombines(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		getTheSameCardCombines(this._combines, cards, 4);
	}

	@Override
	public ArrayList<Card> preventOpponent(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		WhichCombine whichCombine = identifyCombine(cards);
        switch (whichCombine){
            case TuQui:
                for (ArrayList<Card> list : this._combines){
                    if (list.get(0).Compare(cards.get(0)) > 0){
                        return list;
                    }
                }
                return null;
            case DoiThong:
                if (cards.size() == 6){
                    if (this._combines.size() > 0){
                        return this._combines.get(0);
                    }
                    else{
                        return null;
                    }
                }
                else{
                    return null;
                }
            case Rac:
                if (cards.get(0).getRank().getCode() == Ranks.Deuce.getCode()){
                    if (this._combines.size() > 0){
                        return this._combines.get(0);
                    }
                    else{
                        return null;
                    }
                }
                else{
                    return null;
                }
            case Doi:
                if (cards.get(0).getRank().getCode() == Ranks.Deuce.getCode()){
                    if (this._combines.size() > 0){
                        return this._combines.get(0);
                    }
                    else{
                        return null;
                    }
                }
                else{
                    return null;
                }
            default:
                return null;
        }
	}
	
	public static boolean isValidPrevent(ArrayList<Card> preventor, ArrayList<Card> prevented){
		WhichCombine whichCombine = identifyCombine(prevented);
		switch (whichCombine){
		case TuQui:
			if(preventor.get(preventor.size() - 1).Compare(prevented.get(prevented.size() - 1)) > 0){
    			return true;
    		}
			else{
				return false;
			}
        case DoiThong:
            if (prevented.size() == 6){
                return true;
            }
            else{
                return false;
            }
        case Rac:
            if (prevented.get(0).getRank().getCode() == Ranks.Deuce.getCode()){
                return true;
            }
            else{
                return false;
            }
        case Doi:
            if (prevented.get(0).getRank().getCode() == Ranks.Deuce.getCode()){
            	return true;
            }
            else{
                return false;
            }
        default:
            return false;
		}
	}

}
