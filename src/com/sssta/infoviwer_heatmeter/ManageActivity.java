package com.sssta.infoviwer_heatmeter;

import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.widget.ListView;

public class ManageActivity extends Activity {
	   private DBAdapter sqlHelper2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        ListView listView=(ListView)findViewById(R.id.allinfo);
        sqlHelper2 = new DBAdapter(this);
        sqlHelper2.open();
        
        Cursor cursor=sqlHelper2.getAll();
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(  
                this,R.layout.listitem, cursor,  
                new String[]{"company_name","product_id","date" },   
                new int[]{R.id.ComName,R.id.number,R.id.date});  
          
        listView.setAdapter(simpleCursorAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_manage, menu);
        return true;
    }

    
}
