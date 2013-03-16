package com.sssta.infoviwer_heatmeter;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.R.string;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
	// ���徲̬����
	public static final String KEY_ROWID = "_id";
	public static final String KEY_COMNAME = "company_name";
	public static final String KEY_COMPRODID = "product_id";
	// public static final String KEY_COMID = "company_id";
	public static final String KEY_DATE = "product_date";
	private static final String TAG = "DBApapter";
	
	// �������ݿ�����
	private static final String DATABASE_NAME = "heatmeter.db";
	// ���ݿ�İ汾��
	private static final int DATABASE_VERSION = 1;
	// ����
	private static final String DATABASE_TABLE = "heatmeter";
	private static final String DATABASE_CREATE = "create table heatmeter("
			+ "_id INTEGER PRIMARY KEY autoincrement,"
			+ "company_name TEXT NOT NULL," + "product_id TEXT NOT NULL,"
			+ "product_date TEXT NOT NULL" + ");";

	// delete company_id TEXT NOT NULL,
	// private static final Context context;
	// private DatabaseHelper DBHelper;

	// ����һ����̬����Databasehelper�̳���SQLiteOpenHelper
	private static class DatabaseHelper extends SQLiteOpenHelper {
		// ����Ĺ��췽��
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		// ��ʵ����������ʱ�򽫻���ø÷���,�÷����ͻᴴ��һ�������ݿ��д���һ�ű�
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(DATABASE_CREATE);

		}

		// �÷����Ǹ������ݿ�
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			// ������ô�����������������ɾ����,Ȼ�������´���һ����
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}

	}

	// ������һЩ��Ա����
	private Context mCtx = null;
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	// �÷�������һ�����Զ�д�����ݿ�����������
	public DBAdapter open() throws SQLException {
		dbHelper = new DatabaseHelper(mCtx);
		db = dbHelper.getWritableDatabase();
		return this;
	}

	// �ر����ݿ�
	public void close() {
		dbHelper.close();
	}

	//
	String[] strCols = new String[] { KEY_ROWID, KEY_COMNAME, KEY_COMPRODID,
			KEY_DATE };

	// �õ��α����
	public Cursor getAll() {
		return db.query(DATABASE_TABLE, strCols, null, null, null, null, null);
	}

	// ���Ӽ�¼
	public long create(String com_name, String com_prodid, String date) {
		ContentValues args = new ContentValues();
		args.put(KEY_COMNAME, com_name);
		args.put(KEY_COMPRODID, com_prodid);
		args.put(KEY_DATE, date);
		return db.insert(DATABASE_TABLE, null, args);
	}

	// ɾ����¼
	public boolean delete(long rowId) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	// ��ѯ������¼
	public Cursor get(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_COMNAME, KEY_COMPRODID, KEY_DATE }, KEY_ROWID
				+ "=" + rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	// ���¼�¼
	public boolean update(long rowId, String com_name, String com_prodid,
			String date) {
		ContentValues args = new ContentValues();
		args.put(KEY_COMNAME, com_name);
		args.put(KEY_COMPRODID, com_prodid);
		args.put(KEY_DATE, date);
		return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}
	//��ѯ�춨ʱ��
	public String searchDate(String com_name,long com_prodid)
	{
		String date = null;
		com_name = "1111";
		String sql="select product_date from heatmeter where company_name"
				+"="+"'"+com_name+"'"+"AND" + " product_id" + "=" + String.valueOf(com_prodid);
		Cursor cursor = db.rawQuery(sql, null);
		//int dateColumnIndex = cursor.getColumnIndex("product_date");
		//String date = cursor.getString(dateColumnIndex);		
	
		  if(cursor.moveToFirst()){
		date= cursor.getString(0);
		  }
		 cursor.close();
		return date;
	}
    //��ȡ���й�˾����
	
	public List<String> getall_company()
	{
		List<String> company = null;
		company=new ArrayList<String>();
		String sql = "select * from heatmeter";
		int nameIndex  = 0 ;
		Cursor cursor =db.rawQuery(sql, null);
		if(cursor.getCount() >= 0) {  
             nameIndex = cursor.getColumnIndex("company_name");       
        }  
		while(cursor.moveToNext()) {         
            String name = cursor.getString(nameIndex);  
            company.add(name);  
        }  
		return company;
		
	}
}
