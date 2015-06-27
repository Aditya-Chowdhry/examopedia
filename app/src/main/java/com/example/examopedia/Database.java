package com.example.examopedia;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class Database {
    Context context;
    DbHelper dbHelper;
    public Database(Context con) {
        context=con;
        dbHelper=new DbHelper(con);

    }

    public long adddata(String category,String exams,String about,String fees,String date){
       long id=0;
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues cn=new ContentValues();
        cn.put(dbHelper.CATEGORY,category);
        cn.put(dbHelper.EXAMS,exams);
        cn.put(dbHelper.ABOUT,about);
        cn.put(dbHelper.FEES,fees);
        cn.put(dbHelper.DATE,date);

        id=sqLiteDatabase.insert(dbHelper.TABLENAME,null,cn);
        return id;
    }
    public ArrayList displaylist(String category){
        String currentcategory,exams;
        ArrayList<String> ar=new ArrayList<>();

        String[] columns={dbHelper.CATEGORY,dbHelper.EXAMS,dbHelper.ABOUT,dbHelper.FEES,dbHelper.DATE};
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.query(dbHelper.TABLENAME,columns,null,null,null,null,null);

        while(cursor.moveToNext()){
            currentcategory=cursor.getString(cursor.getColumnIndex(dbHelper.CATEGORY));
            if(category.compareToIgnoreCase(currentcategory)==0){
                    exams=cursor.getString(cursor.getColumnIndex(dbHelper.EXAMS));
                    ar.add(exams);
            }
        }
        return ar;
    }

    Cursor changelocation;
    String about,fees,date;
    public void changecursorposition(String exams){

        String currentexams;
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        String[]  columns={dbHelper.CATEGORY,dbHelper.EXAMS,dbHelper.ABOUT,dbHelper.FEES,dbHelper.DATE};
        changelocation=sqLiteDatabase.query(dbHelper.TABLENAME,columns,null,null,null,null,null);
        while(changelocation.moveToNext()){
            currentexams=changelocation.getString(changelocation.getColumnIndex(dbHelper.EXAMS));
            if(exams.compareToIgnoreCase(currentexams)==0){
                about=changelocation.getString(changelocation.getColumnIndex(dbHelper.ABOUT));
                fees=changelocation.getString(changelocation.getColumnIndex(dbHelper.FEES));
                date=changelocation.getString(changelocation.getColumnIndex(dbHelper.DATE));

                break;
            }
        }
    }

    public String getAbout(){   return about;}
    public String getDate(){    return date;}
    public String getFees(){    return fees;}

    static public class DbHelper extends SQLiteOpenHelper{

        private static final String TABLENAME="Details";
        private static final String CATEGORY="category";
        private static final String EXAMS="exams";
        private static final String ABOUT="about";
        private static final String FEES="fees";
        private static final String DATE="date";
        private static final int DBVERSION=1;
        private static final String CREATETABLE="CREATE TABLE "+TABLENAME+"("+CATEGORY+" VARCHAR(256),"+EXAMS+" VARCHAR(256),"
                                                +ABOUT+" VARCHAR(256),"+FEES+" VARCHAR(256),"+DATE+" VARCHAR(256));";
        private static final String DROPTABLE="DROP TABLE IF EXISTS "+TABLENAME;
        private Context conn;
        public DbHelper(Context context) {
            super(context, TABLENAME, null, DBVERSION);
            conn=context;

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(CREATETABLE);
                Toast.makeText(conn,"Created",Toast.LENGTH_SHORT).show();
            }
            catch(Exception e){
                Toast.makeText(conn,"Failed",Toast.LENGTH_SHORT).show();
            }

        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try{
                db.execSQL(DROPTABLE);
                Toast.makeText(conn,"upated",Toast.LENGTH_SHORT).show();
            }
            catch(Exception e){
                Toast.makeText(conn,"update Failed",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
