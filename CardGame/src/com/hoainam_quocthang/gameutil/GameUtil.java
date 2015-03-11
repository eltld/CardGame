package com.hoainam_quocthang.gameutil;

import com.hoainam_quocthang.cardgame.R;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class GameUtil {

	public static Animation createAnimation(Context context, int anim, 
			final CallBackAnimationEnd callBack) {
		
    	Animation animation = AnimationUtils.loadAnimation(context, anim);
    	
    	animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {}
			
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				callBack.animationEnded();
			}
		});
    	return animation;
	}
	
	public static void flipView(final ImageView view, final int frontImage, 
			Context context, final IFlipCardEnd handler){
		Animation animToMiddle = AnimationUtils.loadAnimation(context, R.anim.flip_card_to_middle);
		final Animation animFromMiddle = AnimationUtils.loadAnimation(context, R.anim.flip_card_from_middle);
		
		animToMiddle.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {}
			
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				view.setImageResource(frontImage);
				view.clearAnimation();
				view.setAnimation(animFromMiddle);
				view.startAnimation(animFromMiddle);
			}
		});
		animFromMiddle.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {}
			
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				handler.flipCardEnd();
			}
		});
		
		view.clearAnimation();
		view.setAnimation(animToMiddle);
		view.startAnimation(animToMiddle);
	}
	
	@SuppressWarnings("deprecation")
	public static void disableImageButton(ImageButton btn){
		btn.setEnabled(false);
		btn.setAlpha(70);
	}
	
	@SuppressWarnings("deprecation")
	public static void enableImageButton(ImageButton btn){
		btn.setEnabled(true);
		btn.setAlpha(255);
	}
	
	public static interface CallBackAnimationEnd{
		void animationEnded();
	}
	
	public interface IFlipCardEnd{
		void flipCardEnd();
	}
}
