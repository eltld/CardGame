package com.hoainam_quocthang.cardgame;

import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.view.Menu;
import android.widget.TextView;

public class HelpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		
		StringBuilder builder = new StringBuilder();
		builder.append(getResources().getString(R.string.intro_help));
		builder.append(getResources().getString(R.string.help_bai_cao));
		builder.append(getResources().getString(R.string.help_tien_len));
		builder.append(getResources().getString(R.string.help_xi_lat));
		
		TextView textView = (TextView)findViewById(R.id.text_view_help_activity);
		textView.setText(Html.fromHtml(builder.toString()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
		return true;
	}

}
