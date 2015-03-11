package com.hoainam_quocthang.cardgame;

import com.hoainam_quocthang.gameutil.GameUtil;
import com.hoainam_quocthang.gameutil.GameUtil.CallBackAnimationEnd;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class BetActivity extends Activity implements OnClickListener {

	public ImageButton _chip_5;
	public ImageButton _chip_10;
	public ImageButton _chip_25;
	public ImageButton _chip_50;
	public ImageButton _chip_100;
	public ImageButton _chip_500;
	
	public ImageButton _btn_bet;
	public ImageButton _btn_unbet;
	public ImageButton _btn_OK;
	
	public ImageView _image_view_hand;
	public TextView _text_bet_guide;
	public ImageView _image_view_betted_chips;
	public TextView _text_betted_chips;
	
	public int _currentBet;
    public int _totalBet = 0;
    public int _chosenChip;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bet);
		
		retrieveViews();
		setListener();
		animateHand();
	}
	
	private void animateHand() {
		Animation animationHand = AnimationUtils.loadAnimation(this, 
    			R.anim.animate_hand);
		_image_view_hand.startAnimation(animationHand);
	}

	private void retrieveViews() {
		
		_chip_5 = (ImageButton)findViewById(R.id.chip_5_bai_cao);
		_chip_10 = (ImageButton)findViewById(R.id.chip_10_bai_cao);
		_chip_25 = (ImageButton)findViewById(R.id.chip_25_bai_cao);
		_chip_50 = (ImageButton)findViewById(R.id.chip_50_bai_cao);
		_chip_100 = (ImageButton)findViewById(R.id.chip_100_bai_cao);
		_chip_500 = (ImageButton)findViewById(R.id.chip_500_bai_cao);
		
		_btn_bet = (ImageButton)findViewById(R.id.btn_bet_bai_cao);
		_btn_unbet = (ImageButton)findViewById(R.id.btn_unbet_bai_cao);
		_btn_OK = (ImageButton)findViewById(R.id.btn_ok_bai_cao);
		
		_image_view_hand = (ImageView)findViewById(R.id.image_view_hand_guide_bai_cao);
		_text_bet_guide = (TextView)findViewById(R.id.text_view_bai_cao_bet_guide);
		_image_view_betted_chips = (ImageView)findViewById(R.id.image_view_bai_cao_betted);
		_text_betted_chips = (TextView)findViewById(R.id.text_view_bai_cao_betted);
	}

	private void setListener() {
    	
    	_chip_5.setOnClickListener(this);
    	_chip_10.setOnClickListener(this);
    	_chip_25.setOnClickListener(this);
    	_chip_50.setOnClickListener(this);
    	_chip_100.setOnClickListener(this);
    	_chip_500.setOnClickListener(this);
    	
    	_btn_bet.setOnClickListener(this);
    	_btn_unbet.setOnClickListener(this);
    	_btn_OK.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.btn_bet_bai_cao:
			betHandler();
			break;
		case R.id.btn_unbet_bai_cao:
			unbetHandler();
			break;
		case R.id.btn_ok_bai_cao:
			okHandler();
			break;
		default:
			chipPressedHandler(view);
			break;
		}
	}
	
	private void betHandler() {
		_btn_bet.startAnimation(GameUtil.createAnimation(this, R.anim.animate_btn_menu, 
				new CallBackAnimationEnd() {
			
			@Override
			public void animationEnded() {
				betHandlerCallBack();
			}
		}));
	}

	protected void betHandlerCallBack() {
		GameUtil.enableImageButton(_btn_unbet);
		GameUtil.enableImageButton(_btn_OK);
		
		_totalBet += _currentBet;
		_text_betted_chips.setVisibility(View.VISIBLE);
		StringBuilder str = new StringBuilder("Bạn đã cược ");
		str.append(_totalBet);
		str.append("$");
		_text_betted_chips.setText(str.toString());
		
		_image_view_betted_chips.setVisibility(View.VISIBLE);
		_image_view_betted_chips.setImageResource(_chosenChip);
	}
	
	private void unbetHandler() {
		_btn_unbet.startAnimation(GameUtil.createAnimation(this, R.anim.animate_btn_menu, 
				new CallBackAnimationEnd() {
			
			@Override
			public void animationEnded() {
				unbetHandlerCallBack();
			}
		}));
	}

	protected void unbetHandlerCallBack() {
		_totalBet = 0;
		GameUtil.disableImageButton(_btn_OK);
		GameUtil.disableImageButton(_btn_unbet);
		_image_view_betted_chips.setVisibility(View.INVISIBLE);
		_text_betted_chips.setVisibility(View.INVISIBLE);
	}
	
	private void okHandler() {
		_btn_OK.startAnimation(GameUtil.createAnimation(this, R.anim.animate_btn_menu, 
				new CallBackAnimationEnd() {
			
			@Override
			public void animationEnded() {
				okHandlerCallBack();
			}
		}));
	}

	protected void okHandlerCallBack() {
		setResult(_totalBet);
		finish();
	}

	private void chipPressedHandler(View view) {
		prepareToBet();
		resetChips();
		focusChip((ImageButton)view);
	}

	private void prepareToBet() {
		if(_btn_bet.getVisibility() == View.VISIBLE){
			return;
		}
		
		_text_bet_guide.setVisibility(View.INVISIBLE);
		_image_view_hand.clearAnimation();
		_image_view_hand.setVisibility(View.INVISIBLE);
		
		_btn_bet.setVisibility(View.VISIBLE);
		_btn_unbet.setVisibility(View.VISIBLE);
		_btn_OK.setVisibility(View.VISIBLE);
		
		GameUtil.disableImageButton(_btn_OK);
		GameUtil.disableImageButton(_btn_unbet);
	}

	private void resetChips() {
		_chip_5.setImageResource(R.drawable.chip_5);
		_chip_10.setImageResource(R.drawable.chip_10);
		_chip_25.setImageResource(R.drawable.chip_25);
		_chip_50.setImageResource(R.drawable.chip_50);
		_chip_100.setImageResource(R.drawable.chip_100);
		_chip_500.setImageResource(R.drawable.chip_500);
	}

	private void focusChip(ImageButton chip) {
		switch(chip.getId()){
		case R.id.chip_5_bai_cao:
			chip.setImageResource(R.drawable.chip_5_focus);
			_currentBet = 5;
			_chosenChip = R.drawable.chip_5_tilt;
			break;
		case R.id.chip_10_bai_cao:
			chip.setImageResource(R.drawable.chip_10_focus);
			_currentBet = 10;
			_chosenChip = R.drawable.chip_10_tilt;
			break;
		case R.id.chip_25_bai_cao:
			chip.setImageResource(R.drawable.chip_25_focus);
			_currentBet = 25;
			_chosenChip = R.drawable.chip_25_tilt;
			break;
		case R.id.chip_50_bai_cao:
			chip.setImageResource(R.drawable.chip_50_focus);
			_currentBet = 50;
			_chosenChip = R.drawable.chip_50_tilt;
			break;
		case R.id.chip_100_bai_cao:
			chip.setImageResource(R.drawable.chip_100_focus);
			_currentBet = 100;
			_chosenChip = R.drawable.chip_100_tilt;
			break;
		case R.id.chip_500_bai_cao:
			chip.setImageResource(R.drawable.chip_500_focus);
			_currentBet = 500;
			_chosenChip = R.drawable.chip_500_tilt;
			break;
		}
	}
}
