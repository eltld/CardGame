package com.hoainam_quocthang.games.gamexilathelper;

import com.hoainam_quocthang.cardgame.R;
import com.hoainam_quocthang.games.GameXiLat;
import com.hoainam_quocthang.games.gamebaicaohelper.ScoreBaiCao;

import android.view.View;
import android.widget.ImageView;

public class ScoreXilat {
	public static enum TypeScore {XiLat, XiBang, NguLinh, Non, Quac, Normal}
	
	public TypeScore typeScore;
	public int score;
	
	public ScoreXilat(TypeScore typeScoreIni){
		typeScore = typeScoreIni;
		score = 0;
	}
	
	public ScoreXilat(int scoreIni){
		typeScore = TypeScore.Normal;
		score = scoreIni;
	}
	
	public void setScoreImage(ImageView score1, ImageView score2, ImageView word){
		if(typeScore == TypeScore.Normal){
			if(score < 10){
				score1.setVisibility(View.GONE);
				score2.setImageResource(ScoreBaiCao.getImageOfScore(score));
			}
			else{
				score1.setVisibility(View.VISIBLE);
				int digit2 = score % 10;
				int digit1 = (score - digit2) / 10;
				score1.setImageResource(ScoreBaiCao.getImageOfScore(digit1));
				score2.setImageResource(ScoreBaiCao.getImageOfScore(digit2));
			}
			
			score2.setVisibility(View.VISIBLE);
			word.setVisibility(View.GONE);
		}
		else{
			score1.setVisibility(View.GONE);
			score2.setVisibility(View.GONE);
			word.setVisibility(View.VISIBLE);
			
			switch(typeScore){
			case XiLat:
				word.setImageResource(R.drawable.word_xi_lat);
				break;
			case XiBang:
				word.setImageResource(R.drawable.word_xi_bang);
				break;
			case NguLinh:
				word.setImageResource(R.drawable.word_ngu_linh);
				break;
			case Non:
				word.setImageResource(R.drawable.word_non);
				break;
			case Quac:
				word.setImageResource(R.drawable.word_quac);
				break;
			default:
				break;
			}
		}
	}
	
	public static void hideScore(GameXiLat game){
		game._img_score_1_1.setVisibility(View.GONE);
		game._img_score_1_2.setVisibility(View.GONE);
		game._img_score_1_3.setVisibility(View.GONE);
		game._img_score_1_4.setVisibility(View.GONE);
		
		game._img_score_2_1.setVisibility(View.GONE);
		game._img_score_2_2.setVisibility(View.GONE);
		game._img_score_2_3.setVisibility(View.GONE);
		game._img_score_2_4.setVisibility(View.GONE);
		
		game._img_word_1.setVisibility(View.GONE);
		game._img_word_2.setVisibility(View.GONE);
		game._img_word_3.setVisibility(View.GONE);
		game._img_word_4.setVisibility(View.GONE);
	}
}
