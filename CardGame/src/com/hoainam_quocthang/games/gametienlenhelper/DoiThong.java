package com.hoainam_quocthang.games.gametienlenhelper;

import java.util.ArrayList;

import com.hoainam_quocthang.deck.Card;
import com.hoainam_quocthang.deck.Ranks;

public class DoiThong extends Combines{

	public DoiThong(){
        this._priority = 5;
        initializeBaseProperties();
    }
	
	@Override
	public void getCombines(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		if (cards.size() == 0){
            return;
        }

		ArrayList<Card> tempList = new ArrayList<Card>();
        Card tempCard = cards.get(0);
        tempList.add(tempCard);

        int numCardTemp = 1;

        for (int i = 1; i < cards.size(); i++){
            if (cards.get(i).getRank().getCode() == Ranks.Deuce.getCode()){
                break;
            }

            if (cards.get(i).getRank().getCode() == tempCard.getRank().getCode()){
                if (numCardTemp == 1){
                    numCardTemp++;
                    tempList.add(cards.get(i));
                }
            }
            else{
                if (cards.get(i).getRank().getCode() == tempCard.getRank().getCode() + 1 ||
                    (cards.get(i).getRank().getCode() == Ranks.Ace.getCode() && 
                    tempCard.getRank().getCode() == Ranks.King.getCode())){
                    if (numCardTemp == 1){
                        tempList.clear();
                    }
                    tempList.add(cards.get(i));
                }
                else{
                    if (tempList.size() >= 6 && tempList.size() % 2 == 0){
                        this._combines.add(moveList(tempList, false));
                    }
                    tempList.clear();
                    tempList.add(cards.get(i));
                }
                numCardTemp = 1;
            }
            tempCard = cards.get(i);
        }

        if (tempList.size() >= 6 && tempList.size() % 2 == 0){
            this._combines.add(moveList(tempList, true));
        }
	}

	@Override
	public ArrayList<Card> preventOpponent(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		WhichCombine whichCombine = identifyCombine(cards);
        switch (whichCombine){
            case DoiThong:
                for (ArrayList<Card> list : this._combines){
                    if (list.size() > cards.size()){
                        return list;
                    }
                    if (list.size() == cards.size() && 
                        list.get(list.size() - 1).Compare(cards.get(cards.size() - 1)) > 0){
                        return list;
                    }
                }
                return null;
            case TuQui:
                for (ArrayList<Card> list : this._combines){
                    if (list.size() > 6){
                        return list;
                    }
                }
                return null;
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
                    for (ArrayList<Card> list : this._combines){
                        if (list.size() > 6){
                            return list;
                        }
                    }
                    return null;
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
            case DoiThong:
                if(preventor.size() > prevented.size()){
                	return true;
                }
                else{
                	if(preventor.size() == prevented.size()){
                		if(preventor.get(preventor.size() - 1).Compare(prevented.get(prevented.size() - 1)) > 0){
                			return true;
                		}
                		else{
                			return false;
                		}
                	}
                	else{
                		return false;
                	}
                }
            case TuQui:
                if(preventor.size() > 6){
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
                	if(preventor.size() > 6){
                    	return true;
                    }
                	else{
                		return false;
                	}
                }
                else{
                    return false;
                }
            default:
                return false;
        }
	}

}
