package com.sssta.infoviwer_heatmeter;

import java.util.Timer;
import java.util.TimerTask;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class Welcome extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        final Intent intent2 = new Intent(Welcome.this,
				SearchActivity.class);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				startActivity(intent2);
				finish(); // Ö´ÐÐ
			}
		};
	timer.schedule(task, 1000 * 2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_welcome, menu);
        return true;
    }

    
}
