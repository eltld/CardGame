package com.hoainam_quocthang.deck;

import java.util.ArrayList;
import java.util.Random;

import com.hoainam_quocthang.cardgame.R;

public class Deck {

	private static Random _random = new Random();
	
	public static ArrayList<ArrayList<Card>> createEmptyDeck(int numPlayer, 
			int numCardEachPlayer){
		
		ArrayList<ArrayList<Card>> listReturn = new ArrayList<ArrayList<Card>>();
        for (int i = 0; i < numPlayer; i++){
        	
        	ArrayList<Card> innerList = new ArrayList<Card>();
            for (int j = 0; j < numCardEachPlayer; j++){
            	innerList.add(new Card(Ranks.Empty, Suits.Empty));
            }
            listReturn.add(innerList);
        }
        return listReturn;
	}
	
	private static void EmptyDeck(ArrayList<ArrayList<Card>> oldDeck){
		
        int numPlayer = oldDeck.size();
        int numCardEachPlayer = oldDeck.get(0).size();

        for (int i = 0; i < numPlayer; i++){
            for (int j = 0; j < numCardEachPlayer; j++){
            	
            	oldDeck.get(i).get(j).setRank(Ranks.Empty);
            	oldDeck.get(i).get(j).setSuit(Suits.Empty);
            }
        }
    }
	
	public static void RefreshDeck(ArrayList<ArrayList<Card>> oldDeck, int numCardEachPlayer){
		
        int numPlayer = oldDeck.size();
        Card card = new Card();

        EmptyDeck(oldDeck);

        for (int i = 0; i < numPlayer; i++){
        	
            for (int j = 0; j < numCardEachPlayer; j++){
                do{
                    int suit = _random.nextInt(4);
                    int rank = _random.nextInt(13);
                    card.setSuit(Suits.getInstanceByCode(suit));
                    card.setRank(Ranks.getInstanceByCode(rank));

                } while (IsHave(oldDeck, card));

                oldDeck.get(i).get(j).setRank(card.getRank());
            	oldDeck.get(i).get(j).setSuit(card.getSuit());
            	oldDeck.get(i).get(j).restoreCard();
            }
        }
    }
	
	public static Card DealOneCard(ArrayList<ArrayList<Card>> availableDeck){
        Card card = new Card();
        do{
        	int suit = _random.nextInt(4);
            int rank = _random.nextInt(13);
            card.setSuit(Suits.getInstanceByCode(suit));
            card.setRank(Ranks.getInstanceByCode(rank));

        } while (IsHave(availableDeck, card));

        return card;
    }
	
	private static boolean IsHave(ArrayList<ArrayList<Card>> listOflist, Card paraCard){
        
		if (listOflist.size() == 0){
            return false;
        }
        for (ArrayList<Card> listInner : listOflist){
            if (listInner.size() == 0){
                return false;
            }
            for (Card card : listInner){
                if (card.Compare(paraCard) == 0){
                    return true;
                }
            }
        }
        return false;
    }
	
	public static int getIdImage(Card card){
		Ranks rank = card.getRank();
		Suits suit = card.getSuit();
		
		if(rank.getCode() == Ranks.Ace.getCode() && suit.getCode() == Suits.Spade.getCode()){
			return R.drawable.card_spade_ace;
		}
		
		if(rank.getCode() == Ranks.Ace.getCode() && suit.getCode() == Suits.Club.getCode()){
			return R.drawable.card_club_ace;
		}

		if(rank.getCode() == Ranks.Ace.getCode() && suit.getCode() == Suits.Diamond.getCode()){
			return R.drawable.card_diamond_ace;
		}

		if(rank.getCode() == Ranks.Ace.getCode() && suit.getCode() == Suits.Heart.getCode()){
			return R.drawable.card_heart_ace;
		}

		if(rank.getCode() == Ranks.Deuce.getCode() && suit.getCode() == Suits.Spade.getCode()){
			return R.drawable.card_spade_deuce;
		}

		if(rank.getCode() == Ranks.Deuce.getCode() && suit.getCode() == Suits.Club.getCode()){
			return R.drawable.card_club_deuce;
		}

		if(rank.getCode() == Ranks.Deuce.getCode() && suit.getCode() == Suits.Diamond.getCode()){
			return R.drawable.card_diamond_deuce;
		}

		if(rank.getCode() == Ranks.Deuce.getCode() && suit.getCode() == Suits.Heart.getCode()){
			return R.drawable.card_heart_deuce;
		}

		if(rank.getCode() == Ranks.Three.getCode() && suit.getCode() == Suits.Spade.getCode()){
			return R.drawable.card_spade_three;
		}

		if(rank.getCode() == Ranks.Three.getCode() && suit.getCode() == Suits.Club.getCode()){
			return R.drawable.card_club_three;
		}

		if(rank.getCode() == Ranks.Three.getCode() && suit.getCode() == Suits.Diamond.getCode()){
			return R.drawable.card_diamond_three;
		}

		if(rank.getCode() == Ranks.Three.getCode() && suit.getCode() == Suits.Heart.getCode()){
			return R.drawable.card_heart_three;
		}

		if(rank.getCode() == Ranks.Four.getCode() && suit.getCode() == Suits.Spade.getCode()){
			return R.drawable.card_spade_four;
		}

		if(rank.getCode() == Ranks.Four.getCode() && suit.getCode() == Suits.Club.getCode()){
			return R.drawable.card_club_four;
		}

		if(rank.getCode() == Ranks.Four.getCode() && suit.getCode() == Suits.Diamond.getCode()){
			return R.drawable.card_diamond_four;
		}

		if(rank.getCode() == Ranks.Four.getCode() && suit.getCode() == Suits.Heart.getCode()){
			return R.drawable.card_heart_four;
		}

		if(rank.getCode() == Ranks.Five.getCode() && suit.getCode() == Suits.Spade.getCode()){
			return R.drawable.card_spade_five;
		}

		if(rank.getCode() == Ranks.Five.getCode() && suit.getCode() == Suits.Club.getCode()){
			return R.drawable.card_club_five;
		}

		if(rank.getCode() == Ranks.Five.getCode() && suit.getCode() == Suits.Diamond.getCode()){
			return R.drawable.card_diamond_five;
		}

		if(rank.getCode() == Ranks.Five.getCode() && suit.getCode() == Suits.Heart.getCode()){
			return R.drawable.card_heart_five;
		}

		if(rank.getCode() == Ranks.Six.getCode() && suit.getCode() == Suits.Spade.getCode()){
			return R.drawable.card_spade_six;
		}

		if(rank.getCode() == Ranks.Six.getCode() && suit.getCode() == Suits.Club.getCode()){
			return R.drawable.card_club_six;
		}

		if(rank.getCode() == Ranks.Six.getCode() && suit.getCode() == Suits.Diamond.getCode()){
			return R.drawable.card_diamond_six;
		}

		if(rank.getCode() == Ranks.Six.getCode() && suit.getCode() == Suits.Heart.getCode()){
			return R.drawable.card_heart_six;
		}

		if(rank.getCode() == Ranks.Seven.getCode() && suit.getCode() == Suits.Spade.getCode()){
			return R.drawable.card_spade_seven;
		}

		if(rank.getCode() == Ranks.Seven.getCode() && suit.getCode() == Suits.Club.getCode()){
			return R.drawable.card_club_seven;
		}

		if(rank.getCode() == Ranks.Seven.getCode() && suit.getCode() == Suits.Diamond.getCode()){
			return R.drawable.card_diamond_seven;
		}

		if(rank.getCode() == Ranks.Seven.getCode() && suit.getCode() == Suits.Heart.getCode()){
			return R.drawable.card_heart_seven;
		}

		if(rank.getCode() == Ranks.Eight.getCode() && suit.getCode() == Suits.Spade.getCode()){
			return R.drawable.card_spade_eight;
		}

		if(rank.getCode() == Ranks.Eight.getCode() && suit.getCode() == Suits.Club.getCode()){
			return R.drawable.card_club_eight;
		}

		if(rank.getCode() == Ranks.Eight.getCode() && suit.getCode() == Suits.Diamond.getCode()){
			return R.drawable.card_diamond_eight;
		}

		if(rank.getCode() == Ranks.Eight.getCode() && suit.getCode() == Suits.Heart.getCode()){
			return R.drawable.card_heart_eight;
		}

		if(rank.getCode() == Ranks.Nine.getCode() && suit.getCode() == Suits.Spade.getCode()){
			return R.drawable.card_spade_nine;
		}

		if(rank.getCode() == Ranks.Nine.getCode() && suit.getCode() == Suits.Club.getCode()){
			return R.drawable.card_club_nine;
		}

		if(rank.getCode() == Ranks.Nine.getCode() && suit.getCode() == Suits.Diamond.getCode()){
			return R.drawable.card_diamond_nine;
		}

		if(rank.getCode() == Ranks.Nine.getCode() && suit.getCode() == Suits.Heart.getCode()){
			return R.drawable.card_heart_nine;
		}

		if(rank.getCode() == Ranks.Ten.getCode() && suit.getCode() == Suits.Spade.getCode()){
			return R.drawable.card_spade_ten;
		}

		if(rank.getCode() == Ranks.Ten.getCode() && suit.getCode() == Suits.Club.getCode()){
			return R.drawable.card_club_ten;
		}

		if(rank.getCode() == Ranks.Ten.getCode() && suit.getCode() == Suits.Diamond.getCode()){
			return R.drawable.card_diamond_ten;
		}

		if(rank.getCode() == Ranks.Ten.getCode() && suit.getCode() == Suits.Heart.getCode()){
			return R.drawable.card_heart_ten;
		}

		if(rank.getCode() == Ranks.Jack.getCode() && suit.getCode() == Suits.Spade.getCode()){
			return R.drawable.card_spade_jack;
		}

		if(rank.getCode() == Ranks.Jack.getCode() && suit.getCode() == Suits.Club.getCode()){
			return R.drawable.card_club_jack;
		}

		if(rank.getCode() == Ranks.Jack.getCode() && suit.getCode() == Suits.Diamond.getCode()){
			return R.drawable.card_diamond_jack;
		}

		if(rank.getCode() == Ranks.Jack.getCode() && suit.getCode() == Suits.Heart.getCode()){
			return R.drawable.card_heart_jack;
		}

		if(rank.getCode() == Ranks.Queen.getCode() && suit.getCode() == Suits.Spade.getCode()){
			return R.drawable.card_spade_queen;
		}

		if(rank.getCode() == Ranks.Queen.getCode() && suit.getCode() == Suits.Club.getCode()){
			return R.drawable.card_club_queen;
		}

		if(rank.getCode() == Ranks.Queen.getCode() && suit.getCode() == Suits.Diamond.getCode()){
			return R.drawable.card_diamond_queen;
		}

		if(rank.getCode() == Ranks.Queen.getCode() && suit.getCode() == Suits.Heart.getCode()){
			return R.drawable.card_heart_queen;
		}

		if(rank.getCode() == Ranks.King.getCode() && suit.getCode() == Suits.Spade.getCode()){
			return R.drawable.card_spade_king;
		}

		if(rank.getCode() == Ranks.King.getCode() && suit.getCode() == Suits.Club.getCode()){
			return R.drawable.card_club_king;
		}

		if(rank.getCode() == Ranks.King.getCode() && suit.getCode() == Suits.Diamond.getCode()){
			return R.drawable.card_diamond_king;
		}

		if(rank.getCode() == Ranks.King.getCode() && suit.getCode() == Suits.Heart.getCode()){
			return R.drawable.card_heart_king;
		}
		
		return 0;
	}
}
