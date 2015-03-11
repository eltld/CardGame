package com.hoainam_quocthang.games.gametienlenhelper;

import java.util.ArrayList;

import com.hoainam_quocthang.deck.Card;
import com.hoainam_quocthang.deck.Ranks;

public class Sanh extends Combines{

	public Sanh(){
        this._priority = 15;
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

        for (int i = 1; i < cards.size(); i++){
            if (cards.get(i).getRank().getCode() == Ranks.Deuce.getCode()){
                break;
            }

            if (cards.get(i).getRank().getCode() == tempCard.getRank().getCode() + 1 ||
                    (cards.get(i).getRank().getCode() == Ranks.Ace.getCode() && 
                    tempCard.getRank().getCode() == Ranks.King.getCode())){
                tempList.add(cards.get(i));
            }
            else{
                if (cards.get(i).getRank().getCode() != tempCard.getRank().getCode()){
                    if (tempList.size() >= 3){
                        this._combines.add(moveList(tempList, false));
                    }
                    tempList.clear();
                    tempList.add(cards.get(i));
                }
            }
            tempCard = cards.get(i);
        }
        if (tempList.size() >= 3){
            this._combines.add(moveList(tempList, true));
        }
	}

	@Override
	public ArrayList<Card> preventOpponent(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		WhichCombine whichCombine = identifyCombine(cards);
        switch (whichCombine){
            case Sanh:
                for (ArrayList<Card> list : this._combines){
                    if (list.size() == cards.size()){
                        if (list.get(list.size() - 1).Compare(cards.get(cards.size() - 1)) > 0){
                            return list;
                        }
                    }
                    if (list.size() == cards.size() + 1){
                        for (int i = list.size() - 2; i <= list.size() - 1; i++){
                            if (list.get(i).Compare(cards.get(cards.size() - 1)) > 0 && 
                            		_random.nextInt(10) + 1 <= 5){
                            	if(i == list.size() - 2){
                            		list.remove(list.size() - 1);
                            	}
                            	else{
                            		list.remove(0);
                            	}
                                return list;
                            }
                        }
                    }
                    if (list.size() >= cards.size() + 3){
                        for (int i = cards.size() - 1; i < list.size(); i++){
                            if (i != cards.size() + 1 && i != list.size() - 3){
                                if (list.get(i).Compare(cards.get(cards.size() - 1)) > 0 && 
                                		_random.nextInt(10) + 1 <= 4){
                                	ArrayList<Card> result = new ArrayList<Card>();
                                    for (int j = i - cards.size() + 1; j <= i; j++){
                                        result.add(list.get(j));
                                    }
                                    return result;
                                }
                            }
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
		if(whichCombine.getCode() == WhichCombine.Sanh.getCode()){
			if(preventor.size() == prevented.size() && 
					preventor.get(preventor.size() - 1).Compare(prevented.get(prevented.size() - 1)) > 0){
    			return true;
    		}
		}
		return false;
	}

}
