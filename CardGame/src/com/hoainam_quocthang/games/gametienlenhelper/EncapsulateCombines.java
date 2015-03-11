package com.hoainam_quocthang.games.gametienlenhelper;

import java.util.ArrayList;
import java.util.Random;

import com.hoainam_quocthang.deck.Card;

public class EncapsulateCombines {

	private Combines[] _encapsulatedCombines = new Combines[6];
    private int[][] _orderProcess = new int[6][];

    private Random _random = new Random();
    
    public EncapsulateCombines(){
        _encapsulatedCombines[0] = new TuQui();
        _encapsulatedCombines[1] = new DoiThong();
        _encapsulatedCombines[2] = new Sanh();
        _encapsulatedCombines[3] = new BaLa();
        _encapsulatedCombines[4] = new Doi();
        _encapsulatedCombines[5] = new Rac();

        _orderProcess[0] = new int[] { 1, 0, 5, 3, 4 };
        _orderProcess[1] = new int[] { 0, 1, 4, 3 };
        _orderProcess[2] = new int[] { 3 };
        _orderProcess[3] = new int[] { 0, 1 };
        _orderProcess[4] = new int[] { 2 };
        _orderProcess[5] = new int[] { 0, 1 };
    }
    
    public void parseDeck(ArrayList<Card> deck){
        refreshObject();

        ArrayList<Card> tempList = Combines.moveList(deck, false);

        for (int i = 0; i < _encapsulatedCombines.length; i++){
            _encapsulatedCombines[i].getCombines(tempList);
            removeCard(tempList, _encapsulatedCombines[i].getCombinesAttr());
        }
    }
    
    private void removeCard(ArrayList<Card> deck, ArrayList<ArrayList<Card>> removedCards){
        for (ArrayList<Card> cards : removedCards){
            for (Card card : cards){
                deck.remove(card);
            }
        }
    }
    
    private int updatePriority(){
        int sum = 0, actualTotalPercent = 0;

        for (int i = 0; i < _encapsulatedCombines.length; i++){
            _encapsulatedCombines[i]._actualPriority = 0;
            if (_encapsulatedCombines[i].getCombinesAttr().size() > 0){
                sum += _encapsulatedCombines[i]._priority;
                _encapsulatedCombines[i]._actualPriority = _encapsulatedCombines[i]._priority;
            }
        }

        for (int i = 0; i < _encapsulatedCombines.length; i++){
            _encapsulatedCombines[i]._actualPriority = 
            		(100 * _encapsulatedCombines[i]._actualPriority) / sum;
            actualTotalPercent += _encapsulatedCombines[i]._actualPriority;
        }

        return actualTotalPercent;
    }
    
    public ArrayList<Card> getCombineUponRandom(){
        int totalPercent = updatePriority();
        int down, up = 0;
        int randomNum = _random.nextInt(totalPercent) + 1;

        for (int i = 0; i < _encapsulatedCombines.length; i++){
            down = up + 1;
            up += _encapsulatedCombines[i]._actualPriority;
            if (randomNum >= down && randomNum <= up){
                return _encapsulatedCombines[i].getCombinesAttr().get(0);
            }
        }

        return null;
    }
    
    public ArrayList<Card> preventOpponent(ArrayList<Card> listBase){
    	WhichCombine whichCombine = Combines.identifyCombine(listBase);
        if (whichCombine == WhichCombine.Invalid){
            return null;
        }
        ArrayList<Card> result = null;
        int index = 0;
        do{
            result = _encapsulatedCombines[_orderProcess[whichCombine.getCode() - 1][index]].
            		preventOpponent(listBase);
            index++;

        } while (result == null && index < _orderProcess[whichCombine.getCode() - 1].length);

        return result;
    }
    
    private void refreshObject(){
        for (int i = 0; i < _encapsulatedCombines.length; i++){
            _encapsulatedCombines[i].getCombinesAttr().clear();
        }
    }
}
