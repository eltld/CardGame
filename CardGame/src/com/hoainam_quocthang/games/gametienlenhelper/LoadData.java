package com.hoainam_quocthang.games.gametienlenhelper;

import java.util.ArrayList;

import com.hoainam_quocthang.cardgame.R;

import android.view.View;
import android.widget.ImageView;

public class LoadData {
	
	public static ArrayList<ImageView> getCenterCard(View view){
		ArrayList<ImageView> listCard = new ArrayList<ImageView>();
		
		listCard.add((ImageView)view.findViewById(R.id.image_view_bai_tien_len_1));
		listCard.add((ImageView)view.findViewById(R.id.image_view_bai_tien_len_2));
		listCard.add((ImageView)view.findViewById(R.id.image_view_bai_tien_len_3));
		listCard.add((ImageView)view.findViewById(R.id.image_view_bai_tien_len_4));
		listCard.add((ImageView)view.findViewById(R.id.image_view_bai_tien_len_5));
		listCard.add((ImageView)view.findViewById(R.id.image_view_bai_tien_len_6));
		listCard.add((ImageView)view.findViewById(R.id.image_view_bai_tien_len_7));
		listCard.add((ImageView)view.findViewById(R.id.image_view_bai_tien_len_8));
		listCard.add((ImageView)view.findViewById(R.id.image_view_bai_tien_len_9));
		listCard.add((ImageView)view.findViewById(R.id.image_view_bai_tien_len_10));
		listCard.add((ImageView)view.findViewById(R.id.image_view_bai_tien_len_11));
		listCard.add((ImageView)view.findViewById(R.id.image_view_bai_tien_len_12));
		listCard.add((ImageView)view.findViewById(R.id.image_view_bai_tien_len_13));
		
		return listCard;
	}
}
