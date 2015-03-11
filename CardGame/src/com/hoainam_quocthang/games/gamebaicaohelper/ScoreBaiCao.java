package com.hoainam_quocthang.games.gamebaicaohelper;

import java.util.ArrayList;

import android.view.View;

import com.hoainam_quocthang.cardgame.R;
import com.hoainam_quocthang.deck.Card;
import com.hoainam_quocthang.games.GameBaiCao;
import com.hoainam_quocthang.gameutil.DataListviewEndAct;

public class ScoreBaiCao {

	public static void hideScore(GameBaiCao game){
		game._img_score_1.setVisibility(View.GONE);
		game._img_score_2.setVisibility(View.GONE);
		game._img_score_3.setVisibility(View.GONE);
		game._img_score_4.setVisibility(View.GONE);
		
		game._img_nut_1.setVisibility(View.GONE);
		game._img_nut_2.setVisibility(View.GONE);
		game._img_nut_3.setVisibility(View.GONE);
		game._img_nut_4.setVisibility(View.GONE);
		
		game._img_3_tien_1.setVisibility(View.GONE);
		game._img_3_tien_2.setVisibility(View.GONE);
		game._img_3_tien_3.setVisibility(View.GONE);
		game._img_3_tien_4.setVisibility(View.GONE);
	}
	
	public static int getScore(ArrayList<Card> player){
        int score = 0;
        boolean isSuperLucky = true;
        for (Card card : player)
        {
            if (Card.isImageCard(card)){
                score += 10;
            }
            else{
                isSuperLucky = false;
                score += card.getRank().getCode() + 1;
            }
        }
        if (isSuperLucky){
            score = 10;
        }
        else{
            score %= 10;
        }
        return score;
    }
	
	public static DataListviewEndAct calRank(int[] scores){
		String[] rank = new String[4];
		rank[0] = "";
		rank[1] = "";
		rank[2] = "";
		rank[3] = "";
		int[] players = {1, 2, 3, 0};
		
		for(int i = 0; i < scores.length - 1; i++){
			for(int j = i + 1; j < scores.length; j++){
				if(scores[j] > scores[i]){
					int temp = scores[j];
					scores[j] = scores[i];
					scores[i] = temp;
					
					temp = players[j];
					players[j] = players[i];
					players[i] = temp;
				}
			}
		}
		
		rank[players[0]] = "Nhất";
		rank[players[1]] = "Nhì";
		rank[players[2]] = "Ba";
		rank[players[3]] = "Tư";
		
		return new DataListviewEndAct(rank[0], rank[1], rank[2], rank[3]);
		
	}
	
	public static int getImageOfScore(int score){
		switch(score){
		case 0:
			return R.drawable.char_0;
		case 1:
			return R.drawable.char_1;
		case 2:
			return R.drawable.char_2;
		case 3:
			return R.drawable.char_3;
		case 4:
			return R.drawable.char_4;
		case 5:
			return R.drawable.char_5;
		case 6:
			return R.drawable.char_6;
		case 7:
			return R.drawable.char_7;
		case 8:
			return R.drawable.char_8;
		case 9:
			return R.drawable.char_9;
		case 10:
			return R.drawable.word_3_tien;
		default:
			return -1;
		}
	}
}
