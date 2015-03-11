package com.hoainam_quocthang.gameutil;

import android.widget.TextView;

public class Turn {

	private static final int NUM_OF_PLAYERS = 4;
	
	private static int[] _orderPlayer = new int[] { 0, 3, 1, 2 };
    private static int[] _indexPlayer = new int[] { 0, 2, 3, 1 };
    private static boolean[] _isSuspended = new boolean[] {false, false, false, false};
    private static boolean[] _isFinished = new boolean[] {false, false, false, false};
	
	public static int nextTurn(int currentTurn){
        for (int i = 0; i < NUM_OF_PLAYERS; i++){
            int j = (i + _indexPlayer[currentTurn] + 1) % NUM_OF_PLAYERS;
            if (!_isSuspended[_orderPlayer[j]]){
                return _orderPlayer[j];
            }
        }
        return -1;
    }
	
	public static void suspendPlayer(int player){
		_isSuspended[player] = true;
	}
	
	public static void finishPlayer(int player){
		_isFinished[player] = true;
	}
	
	public static void releaseAllPlayer(){
		for(int i = 0; i < _isSuspended.length; i++){
			if(!_isFinished[i]){
				_isSuspended[i] = false;
			}
		}
	}
	
	public static void restartAllPlayer(){
		for(int i = 0; i < _isFinished.length; i++){
			_isFinished[i] = false;
		}
	}
	
	public static void updateTurnUI(TextView textView, int player){
		switch(player){
		case 0:
			textView.setText("Bottom");
			break;
		case 1:
			textView.setText("Top");
			break;
		case 2:
			textView.setText("Left");
			break;
		case 3:
			textView.setText("Right");
			break;
		}
	}
}
