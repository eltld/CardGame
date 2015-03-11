package com.hoainam_quocthang.gameutil;

import java.io.Serializable;

public class DataListviewEndAct implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String text1;
	public String text2;
	public String text3;
	public String text4;
	
	public DataListviewEndAct(String t1, String t2, String t3, String t4){
		text1 = t1;
		text2 = t2;
		text3 = t3;
		text4 = t4;
	}
	
}
