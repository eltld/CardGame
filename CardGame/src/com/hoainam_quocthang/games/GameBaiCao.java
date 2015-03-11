package com.hoainam_quocthang.games;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoainam_quocthang.cardgame.BetActivity;
import com.hoainam_quocthang.cardgame.EndGameActivity;
import com.hoainam_quocthang.cardgame.MainActivity;
import com.hoainam_quocthang.cardgame.R;
import com.hoainam_quocthang.deck.Deck;
import com.hoainam_quocthang.games.gamebaicaohelper.BaiCaoUIHelper;
import com.hoainam_quocthang.games.gamebaicaohelper.BetBaiCao;
import com.hoainam_quocthang.games.gamebaicaohelper.ScoreBaiCao;
import com.hoainam_quocthang.gameutil.DataListviewEndAct;
import com.hoainam_quocthang.gameutil.GameUtil;
import com.hoainam_quocthang.gameutil.GameUtil.CallBackAnimationEnd;

public class GameBaiCao extends GameCard 
	implements OnClickListener{
	
	public LinearLayout _LinearLayout_top;
	public LinearLayout _LinearLayout_bottom;
	public LinearLayout _LinearLayout_left;
	public LinearLayout _LinearLayout_right;
	public ImageButton _btn_flipCard;
	
	public ImageView _img_set_money_1;
	public ImageView _img_set_money_2;
	public ImageView _img_set_money_3;
	public ImageView _img_set_money_4;
	
	public TextView _text_view_set_money_1;
	public TextView _text_view_set_money_2;
	public TextView _text_view_set_money_3;
	public TextView _text_view_set_money_4;
	
	public ImageView _img_score_1;
	public ImageView _img_score_2;
	public ImageView _img_score_3;
	public ImageView _img_score_4;
	
	public ImageView _img_nut_1;
	public ImageView _img_nut_2;
	public ImageView _img_nut_3;
	public ImageView _img_nut_4;
	
	public ImageView _img_3_tien_1;
	public ImageView _img_3_tien_2;
	public ImageView _img_3_tien_3;
	public ImageView _img_3_tien_4;
	
	public int _countDealedCard;
	public int[] _moneys = new int[4];
	public int[] _scores = new int[4];
	public ArrayList<DataListviewEndAct> _history = new ArrayList<DataListviewEndAct>();
	
	public GameBaiCao(MainActivity context){
        this._numOfPlayers = 4;
        this._numCardEachPlayer = 3;
        this._idLayout = R.layout.layout_act_main_bai_cao;

        initializeBaseAttributes(context);
        
        retrieveViews();
        setListener();
    }

	private void retrieveViews() {
		_LinearLayout_top = (LinearLayout)_view.findViewById(R.id.linear_layout_top_bai_cao);
		_LinearLayout_bottom = (LinearLayout)_view.
				findViewById(R.id.linear_layout_bottom_bai_cao);
		_LinearLayout_left = (LinearLayout)_view.findViewById(R.id.linear_layout_left_bai_cao);
		_LinearLayout_right = (LinearLayout)_view.
				findViewById(R.id.linear_layout_right_bai_cao);
		_btn_flipCard = (ImageButton)_view.findViewById(R.id.btn_flip_card_bai_cao);
		
		_img_set_money_1 = (ImageView)_view.findViewById(R.id.img_set_money_1_bai_cao);
		_img_set_money_2 = (ImageView)_view.findViewById(R.id.img_set_money_2_bai_cao);
		_img_set_money_3 = (ImageView)_view.findViewById(R.id.img_set_money_3_bai_cao);
		_img_set_money_4 = (ImageView)_view.findViewById(R.id.img_set_money_4_bai_cao);
		
		_text_view_set_money_1 = (TextView)_view.findViewById(R.id.text_view_set_money_1_bai_cao);
		_text_view_set_money_2 = (TextView)_view.findViewById(R.id.text_view_set_money_2_bai_cao);
		_text_view_set_money_3 = (TextView)_view.findViewById(R.id.text_view_set_money_3_bai_cao);
		_text_view_set_money_4 = (TextView)_view.findViewById(R.id.text_view_set_money_4_bai_cao);
		
		_img_score_1 = (ImageView)_view.findViewById(R.id.img_score_bai_cao_1);
		_img_score_2 = (ImageView)_view.findViewById(R.id.img_score_bai_cao_2);
		_img_score_3 = (ImageView)_view.findViewById(R.id.img_score_bai_cao_3);
		_img_score_4 = (ImageView)_view.findViewById(R.id.img_score_bai_cao_4);
		
		_img_nut_1 = (ImageView)_view.findViewById(R.id.img_nut_bai_cao_1);
		_img_nut_2 = (ImageView)_view.findViewById(R.id.img_nut_bai_cao_2);
		_img_nut_3 = (ImageView)_view.findViewById(R.id.img_nut_bai_cao_3);
		_img_nut_4 = (ImageView)_view.findViewById(R.id.img_nut_bai_cao_4);
		
		_img_3_tien_1 = (ImageView)_view.findViewById(R.id.img_3_tien_bai_cao_1);
		_img_3_tien_2 = (ImageView)_view.findViewById(R.id.img_3_tien_bai_cao_2);
		_img_3_tien_3 = (ImageView)_view.findViewById(R.id.img_3_tien_bai_cao_3);
		_img_3_tien_4 = (ImageView)_view.findViewById(R.id.img_3_tien_bai_cao_4);
	}
	
	private void setListener() {
		_btn_flipCard.setOnClickListener(this);
	}
	
	@Override
	public void beginGame() {
		refreshGame();
	}
	
	@Override
	public void onClick(View view) {
		btnClickHandler();
	}
	
	private void btnClickHandler() {
		_btn_flipCard.startAnimation(GameUtil.createAnimation(_context, R.anim.animate_btn_menu, 
				new CallBackAnimationEnd() {
			
			@Override
			public void animationEnded() {
				btnClickHandlerCallBack();
			}
		}));
	}

	protected void btnClickHandlerCallBack() {
		String tag = (String)_btn_flipCard.getTag();
		
		if(tag.equals("bet")){
			Intent intent = new Intent(_context, BetActivity.class);
			_context.startActivityForResult(intent, MainActivity.REQUEST_CODE_BET_ACTIVITY);
		}
		if(tag.equals("flip")){
			BaiCaoUIHelper.flipCardEnd(this);
		}
		if(tag.equals("end")){
			finishSession();
		}
	}

	private void finishSession() {
		_history.add(ScoreBaiCao.calRank(_scores));
		
		Bundle bundle = new Bundle();
		bundle.putSerializable("history", _history);
		Intent intent = new Intent(_context, EndGameActivity.class);
		intent.putExtra("history", bundle);
		_context.startActivityForResult(intent, MainActivity.REQUEST_CODE_ENDGAME_ACTIVITY);
	}

	@Override
	public void refreshGame() {
		
		Deck.RefreshDeck(_allCards, this._numCardEachPlayer);
		_countDealedCard = 0;
		unFlipCard(_LinearLayout_bottom);
		unFlipCard(_LinearLayout_top);
		unFlipCard(_LinearLayout_left);
		unFlipCard(_LinearLayout_right);
		
		BaiCaoUIHelper.refreshBtn(this);
		ScoreBaiCao.hideScore(this);
		BetBaiCao.refreshBet(this);
		BaiCaoUIHelper.dealCard(this);
	}

	@Override
	public void onActivityResultHandler(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case MainActivity.REQUEST_CODE_BET_ACTIVITY:
			_moneys[3] = resultCode;
			BetBaiCao.betDoneHandler(_moneys, this);
			break;

		case MainActivity.REQUEST_CODE_ENDGAME_ACTIVITY:
			refreshGame();
			break;
		}
	}
}
