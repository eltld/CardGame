package com.hoainam_quocthang.cardgame;

import com.hoainam_quocthang.games.GameCard;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	public static final int REQUEST_CODE_BET_ACTIVITY = 1;
	public static final int REQUEST_CODE_ENDGAME_ACTIVITY = 2;
	
	private GameCard _game;
	private boolean _isFirst = true;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();
        String className = intent.getStringExtra("com.hoainam_quocthang.cardgame.GAME_NAME");
        _game = GameCard.getObjectByName(className, this);
        if(_game != null){
        	setContentView(_game.getView());
        }
    }

    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		_game.onActivityResultHandler(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void card_Click(View view){
    	ImageView imageView = (ImageView)view;
    	if(((String)imageView.getTag()).equals("down")){
    		LinearLayout.LayoutParams lg = (LinearLayout.LayoutParams)imageView.
    				getLayoutParams();
    		lg.bottomMargin += 50;
    		lg.setMargins(lg.leftMargin, lg.topMargin, lg.rightMargin, lg.bottomMargin);
    		imageView.setLayoutParams(lg);
    		imageView.setTag("up");
    	}
    	else{
    		LinearLayout.LayoutParams lg = (LinearLayout.LayoutParams)imageView.
    				getLayoutParams();
    		lg.bottomMargin -= 50;
    		lg.setMargins(lg.leftMargin, lg.topMargin, lg.rightMargin, lg.bottomMargin);
    		imageView.setLayoutParams(lg);
    		imageView.setTag("down");
    	}
    }

	
    @Override
	protected void onResume() {
    	if(_isFirst && _game != null){
    		_game.beginGame();
    	}
    	_isFirst = false;
		super.onResume();
	}
    
}
