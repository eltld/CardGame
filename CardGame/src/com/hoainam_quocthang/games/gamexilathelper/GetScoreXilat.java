package com.hoainam_quocthang.games.gamexilathelper;

import java.util.ArrayList;

import com.hoainam_quocthang.deck.Card;
import com.hoainam_quocthang.deck.Ranks;
import com.hoainam_quocthang.deck.Suits;

public class GetScoreXilat {

	public static ScoreXilat calculateScore(ArrayList<Card> player){
		
        int score = 0;
        int realNumCards = getRealNumCards(player);

        if (realNumCards == 2){
            if (player.get(0).getRank().getCode() == Ranks.Ace.getCode() && 
            		player.get(1).getRank().getCode() == Ranks.Ace.getCode()){
                return new ScoreXilat(ScoreXilat.TypeScore.XiBang);
            }
            if ((player.get(0).getRank().getCode() == Ranks.Ace.getCode() && 
            		isTenScore(player.get(1))) || 
            		(player.get(1).getRank().getCode() == Ranks.Ace.getCode() && 
            				isTenScore(player.get(0)))){
            	return new ScoreXilat(ScoreXilat.TypeScore.XiLat);
            }
        }

        for (int i = 0; i < realNumCards; i++ ){
            score += getScoreCard(player.get(i), realNumCards);
        }
        
        if(score > 21){
        	return new ScoreXilat(ScoreXilat.TypeScore.Quac);
        }
        
        if(realNumCards == 5){
        	return new ScoreXilat(ScoreXilat.TypeScore.NguLinh);
        }
        
        if(score < 16){
        	return new ScoreXilat(ScoreXilat.TypeScore.Non);
        }

        return new ScoreXilat(score);
    }

	public static int getRealNumCards(ArrayList<Card> player) {
		int realNumCards = 0;
		for (Card card : player)
        {
            if (card.getSuit().getCode() == Suits.Empty.getCode()){
                break;
            }
            realNumCards++;
        }
		return realNumCards;
	}
	
	private static boolean isTenScore(Card card){
        if (Card.isImageCard(card) || card.getRank().getCode() == Ranks.Ten.getCode()){
            return true;
        }
        return false;
    }
	
	private static int getScoreCard(Card card, int numCards){
    	
        if (card.getRank().getCode() == Ranks.Ace.getCode()){
            switch (numCards){
                case 2:
                    return 11;
                case 3:
                    return 10;
                default:
                    return 1;
            }
        }
        if (Card.isImageCard(card)){
            return 10;
        }
        return card.getRank().getCode() + 1;
    }
}
