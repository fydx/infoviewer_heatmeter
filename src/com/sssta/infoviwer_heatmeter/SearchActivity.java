package com.sssta.infoviwer_heatmeter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends Activity {
     private Button company_selectButton;
     private EditText numberEditText;
     private Button toSearchButton;
     private Button ManageButton;
     private DBAdapter sqlHelper;
     private String company_nameString;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
       //绑定控件
        company_selectButton = (Button)findViewById(R.id.select_company);
        numberEditText=(EditText)findViewById(R.id.number_input);
        toSearchButton=(Button)findViewById(R.id.toSearch);
         sqlHelper = new DBAdapter(this);
   
         ManageButton =(Button)findViewById(R.id.button_manage);
        //打开数据库
        
        try {
        	
        	 sqlHelper.open();
             
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(getApplicationContext(), "无法打开数据库", Toast.LENGTH_LONG).show();
		}
        company_selectButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new  Intent(SearchActivity.this, SelectCompanyActivity.class);
				startActivityForResult(intent, 0);
				
				
			}
		});
        toSearchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String resultString = sqlHelper.searchDate(company_nameString
						, Long.valueOf(numberEditText.getText().toString()));
			
				Toast.makeText(getApplicationContext(), "检定日期为" + resultString, Toast.LENGTH_LONG).show();
			}
		});
        ManageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SearchActivity.this, ManageActivity.class);
				startActivity(intent);
			}
		});
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search, menu);
        return true;
    }
    @Override
    protected void onResume() {
    	super.onResume();
    }
    
}
