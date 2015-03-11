package com.hoainam_quocthang.cardgame;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.widget.ProgressBar;

public class StartActivity extends Activity{

	//private final int SPLASH_TIME_OUT = 5000;
	private final int SPLASH_TIME_OUT = 1;
	private int _progressStatus = 0;
	private Handler _handler = new Handler();
	private ProgressBar _progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		_progressBar = (ProgressBar)findViewById(R.id.progress_bar_splash_screen);
		
		new LoadData().execute();
		
		new Thread(new Runnable() {
            
            @Override
            public void run() {
                  while (_progressStatus < 100) {
                	  _progressStatus++;
                        try {
                              Thread.sleep(SPLASH_TIME_OUT / 100);
                        } catch (InterruptedException e) {
                              e.printStackTrace();
                        }
                        // Update the progress bar
                        _handler.post(new Runnable() {
                             
                              @Override
                              public void run() {
                            	  _progressBar.setProgress(_progressStatus);
                              }
                        });
                  }
                  Intent intent = new Intent(StartActivity.this, MenuActivity.class);
                  startActivity(intent);
                  finish();
            }
		}).start();
	}
	
	private class LoadData extends AsyncTask<Void, Void, Void>{

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

		@Override
		protected Void doInBackground(Void... params) {
			// Load data
			return null;
		}
		
	}

}
