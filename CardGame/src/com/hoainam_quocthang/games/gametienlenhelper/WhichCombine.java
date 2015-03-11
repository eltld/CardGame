package com.hoainam_quocthang.games.gametienlenhelper;


public enum WhichCombine {
	Rac(1), Doi(2), BaLa(3), TuQui(4), Sanh(5), DoiThong(6), Invalid(7);
	
	private int _code;
	private WhichCombine(int code){
		_code = code;
	}
	public int getCode(){
		return _code;
	}
	public static WhichCombine getInstanceByCode(int code){
		for(WhichCombine instance : WhichCombine.values()){
			if(instance.getCode() == code){
				return instance;
			}
		}
		return null;
	}
}
