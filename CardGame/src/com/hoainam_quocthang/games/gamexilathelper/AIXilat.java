package com.hoainam_quocthang.games.gamexilathelper;

import java.util.ArrayList;
import java.util.Random;

import com.hoainam_quocthang.deck.Card;
import com.hoainam_quocthang.games.gamexilathelper.ScoreXilat.TypeScore;

public class AIXilat {
	
	private static Random _random = new Random();
	
	public static boolean isAddCard(ArrayList<Card> player){
		
    	ScoreXilat score = GetScoreXilat.calculateScore(player);
    	
        if (score.typeScore == TypeScore.Non){
            return true;
        }
        if (score.typeScore == TypeScore.Quac || 
        		(score.typeScore == TypeScore.Normal && score.score >= 20)){
            return false;
        }

        switch (score.score){
            case 16:
                return calByPercent(60);
            case 17:
                return calByPercent(35);
            case 18:
                return calByPercent(15);
            default:
                return calByPercent(5);
        }
    }
	
	private static boolean calByPercent(int percentage){
        int randomNum = _random.nextInt(100) + 1;

        if (randomNum <= percentage){
            return true;
        }
        else{
            return false;
        }
    }
}
