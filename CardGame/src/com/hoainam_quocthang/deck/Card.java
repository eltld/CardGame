package com.hoainam_quocthang.deck;

public class Card {

	private Ranks _rank;
    private Suits _suit;
    private boolean _isRemoved;
    
    public Card(Ranks rank, Suits suit){
    	_rank = rank;
    	_suit = suit;
    	_isRemoved = false;
    }
    
    public Card() {
		_rank = Ranks.Empty;
		_suit = Suits.Empty;
		_isRemoved = false;
	}

	public void setRank(Ranks rank){
    	_rank = rank;
    }
    
    public void setSuit(Suits suit) {
		_suit = suit;
	}
    
    public Ranks getRank() {
		return _rank;
	}

	public Suits getSuit() {
		return _suit;
	}
	
	public boolean isRemoved(){
		return _isRemoved;
	}
	
	public void removeCard(){
		_isRemoved = true;
	}
	
	public void restoreCard(){
		_isRemoved = false;
	}
	
	public void emptyCard(){
		_rank = Ranks.Empty;
		_suit = Suits.Empty;
	}
	
	public void assignCard(Card card){
		_rank = card.getRank();
		_suit = card.getSuit();
	}
    
    public int Compare(Card paraCard){
        if (this._rank == paraCard._rank){
            return this._suit.getCode() - paraCard._suit.getCode();
        }

        if ((this._rank.getCode() > 1 && paraCard._rank.getCode() > 1) || 
            (this._rank.getCode() <= 1 && paraCard._rank.getCode() <= 1)){
            return this._rank.getCode() - paraCard._rank.getCode();
        }

        return this._rank.getCode() < paraCard._rank.getCode() ? 1 : -1;
    }
    
    public static boolean isImageCard(Card card){
    	
        if (card.getRank().getCode() == Ranks.Jack.getCode() || 
        		card.getRank().getCode() == Ranks.Queen.getCode() || 
        		card.getRank().getCode() == Ranks.King.getCode()){
        	
            return true;
        }
        return false;
    }
	
}
