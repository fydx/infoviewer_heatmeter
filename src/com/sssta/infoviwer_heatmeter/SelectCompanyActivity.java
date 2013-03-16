package com.sssta.infoviwer_heatmeter;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SimpleCursorAdapter;

public class SelectCompanyActivity extends Activity {
	private DBAdapter sqlHelper2;
	private String  company_nameString;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_company);
        sqlHelper2 = new DBAdapter(this);
        sqlHelper2.open();
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
        final ListView listView1= (ListView)findViewById(R.id.listView1);
        final Intent intent2 = new Intent();
        
        List<String>company =sqlHelper2.getall_company();
        for (String str : company)
        {
        	HashMap<String, String> map = new HashMap<String, String>();
			map.put("company_name", str);
			Log.i("company", str);
		//	map.put("ItemText", food.toString2());
			mylist.add(map);
        }
        /* SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(  
                this,R.layout.listitem, cursor,  
                new String[]{"company_name","product_id","date" },   
                new int[]{R.id.ComName,R.id.number,R.id.date});  */
        SimpleAdapter mSchedule = new SimpleAdapter(this, // 没什么解释
				mylist,// 数据来源
				R.layout.select_item,// ListItem的XML实现

				// 动态数组与ListItem对应的子项
				new String[] { "company_name" },

				// TextView ID
				new int[] { R.id.company_name });
		// 添加并且显示
        listView1.setAdapter(mSchedule);
        listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				company_nameString = (String) listView1.getItemAtPosition(arg2);
				Log.i("selection_company_name",company_nameString);
				intent2.putExtra("company_name", company_nameString);
				
			}
		});
    }
    
   
   
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_select_company, menu);
        return true;
    }

    
}
