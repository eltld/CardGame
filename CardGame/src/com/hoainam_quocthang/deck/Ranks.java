package com.hoainam_quocthang.deck;

public enum Ranks {
	Ace(0) , Deuce(1), Three(2), Four(3), Five(4), Six(5), Seven(6), 
	Eight(7), Nine(8), Ten(9), Jack(10), Queen(11), King(12) , Empty(13);
	
	private int _code;
	private Ranks(int code){
		_code = code;
	}
	public int getCode(){
		return _code;
	}
	public static Ranks getInstanceByCode(int code){
		for(Ranks instance : Ranks.values()){
			if(instance.getCode() == code){
				return instance;
			}
		}
		return null;
	}
}
