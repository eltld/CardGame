package com.hoainam_quocthang.deck;

public enum Suits {
	Spade(0) , Club(1), Diamond(2), Heart(3) , Empty(4);
	
	private int _code;
	private Suits(int code){
		_code = code;
	}
	public int getCode(){
		return _code;
	}
	public static Suits getInstanceByCode(int code){
		for(Suits instance : Suits.values()){
			if(instance.getCode() == code){
				return instance;
			}
		}
		return null;
	}
}
