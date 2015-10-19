package com.examopedia.beta;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Database {
    Context context;
    DbHelper dbHelper;
    public Database(Context con) {
        context=con;
        dbHelper=new DbHelper(con);

    }

    //INSERTING DATA INTO DATABASE AFTER PARSING IT..
    public long addData( Integer id,String title,String description,String section,String level,String image_file_name,String image_content_type,String image_file_size,
                          String image_updated_at, String exam_date,String form_release_date,String form_last_date,String link1_name,String link1,String link2_name,
                           String link2,String link3_name,String link3,String link4_name,String link4,String created_at,String updated_at,String exam_review,String gen_fees_boys,
                            String gen_fees_girls,String sc_fees_boys,String sc_fees_girls,String others_note,String others)
    {
       long result=0;
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues cn=new ContentValues();
        cn.put(dbHelper.id,id);
        cn.put(dbHelper.title,title);
        cn.put(dbHelper.description,description);
        cn.put(dbHelper.section,section);
        cn.put(dbHelper.level,level);
        cn.put(dbHelper.image_file_name,image_file_name);
        cn.put(dbHelper.image_content_type,image_content_type);
        cn.put(dbHelper.image_file_size,image_file_size);
        cn.put(dbHelper.image_updated_at,image_updated_at);
        cn.put(dbHelper.exam_date,exam_date);
        cn.put(dbHelper.form_release_date,form_release_date);
        cn.put(dbHelper.form_last_date,form_last_date);
        cn.put(dbHelper.link1_name,link1_name);
        cn.put(dbHelper.link1,link1);
        cn.put(dbHelper.link2_name,link2_name);
        cn.put(dbHelper.link2,link2);
        cn.put(dbHelper.link3_name,link3_name);
        cn.put(dbHelper.link3,link3);
        cn.put(dbHelper.link4_name,link4_name);
        cn.put(dbHelper.link4,link4);
        cn.put(dbHelper.created_at,created_at);
        cn.put(dbHelper.updated_at,updated_at);
        cn.put(dbHelper.exam_review,exam_review);
        cn.put(dbHelper.gen_fees_boys,gen_fees_boys);
        cn.put(dbHelper.gen_fees_girls,gen_fees_girls);
        cn.put(dbHelper.sc_fees_boys,sc_fees_boys);
        cn.put(dbHelper.sc_fees_girls,sc_fees_girls);
        cn.put(dbHelper.others_note,others_note);
        cn.put(dbHelper.others,others);

        result=sqLiteDatabase.insert(dbHelper.TABLENAME,null,cn);
        sqLiteDatabase.close();
        return result;
    }


    //DISPLAYING ALL THE DATA IN LIST
    public List displaylist(){
        Integer id;
        String title,description, section,level,image_file_name,image_content_type,image_file_size,image_updated_at,exam_date;
        String form_release_date,form_last_date,link1_name,link1,link2_name,link2,link3_name,link3,link4_name,link4,created_at,updated_at;
        String exam_review, gen_fees_boys,gen_fees_girls,sc_fees_boys,sc_fees_girls,others_note,others;
        List<String> ar=new ArrayList<>();

        String[] columns={dbHelper.id,dbHelper.title,dbHelper.description,dbHelper.section,dbHelper.level,dbHelper.image_file_name,dbHelper.image_content_type,
                         dbHelper.image_file_size,dbHelper.image_updated_at,dbHelper.exam_date,dbHelper.form_release_date,dbHelper.form_last_date,dbHelper.link1_name,
                           dbHelper.link1,dbHelper.link2_name,dbHelper.link2,dbHelper.link3_name,dbHelper.link3,dbHelper.link4_name,dbHelper.link4,dbHelper.created_at,
                            dbHelper.updated_at,dbHelper.exam_review,dbHelper.gen_fees_boys,dbHelper.gen_fees_girls,dbHelper.sc_fees_boys,dbHelper.sc_fees_girls,dbHelper.others_note,dbHelper.others};

        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.query(dbHelper.TABLENAME,columns,null,null,null,null,null);

        while(cursor.moveToNext()){
            //currentcategory=cursor.getString(cursor.getColumnIndex(dbHelper.CATEGORY));
           // if(category.compareToIgnoreCase(currentcategory)==0){
             //       exams=cursor.getString(cursor.getColumnIndex(dbHelper.EXAMS));
               //     ar.add(exams);
            //}
            id=cursor.getInt(cursor.getColumnIndex(dbHelper.id));
            title=cursor.getString(cursor.getColumnIndex(dbHelper.title));
            description=cursor.getString(cursor.getColumnIndex(dbHelper.description));
            section=cursor.getString(cursor.getColumnIndex(dbHelper.section));
            level=cursor.getString(cursor.getColumnIndex(dbHelper.level));
            image_file_name=cursor.getString(cursor.getColumnIndex(dbHelper.image_file_name));
            image_content_type=cursor.getString(cursor.getColumnIndex(dbHelper.image_content_type));
            image_file_size=cursor.getString(cursor.getColumnIndex(dbHelper.image_file_size));
            image_updated_at=cursor.getString(cursor.getColumnIndex(dbHelper.image_updated_at));
            exam_date=cursor.getString(cursor.getColumnIndex(dbHelper.exam_date));
            form_release_date=cursor.getString(cursor.getColumnIndex(dbHelper.form_release_date));
            form_last_date=cursor.getString(cursor.getColumnIndex(dbHelper.form_last_date));
            link1_name=cursor.getString(cursor.getColumnIndex(dbHelper.link1_name));
            link1=cursor.getString(cursor.getColumnIndex(dbHelper.link1));
            link2_name=cursor.getString(cursor.getColumnIndex(dbHelper.link2_name));
            link2=cursor.getString(cursor.getColumnIndex(dbHelper.link2));
            link3_name=cursor.getString(cursor.getColumnIndex(dbHelper.link3_name));
            link3=cursor.getString(cursor.getColumnIndex(dbHelper.link3));
            link4_name=cursor.getString(cursor.getColumnIndex(dbHelper.link4_name));
            link4=cursor.getString(cursor.getColumnIndex(dbHelper.link4));
            created_at=cursor.getString(cursor.getColumnIndex(dbHelper.created_at));
            updated_at=cursor.getString(cursor.getColumnIndex(dbHelper.updated_at));
            exam_review=cursor.getString(cursor.getColumnIndex(dbHelper.exam_review));
            gen_fees_boys=cursor.getString(cursor.getColumnIndex(dbHelper.gen_fees_boys));
            gen_fees_girls=cursor.getString(cursor.getColumnIndex(dbHelper.gen_fees_girls));
            sc_fees_boys=cursor.getString(cursor.getColumnIndex(dbHelper.sc_fees_boys));
            sc_fees_girls=cursor.getString(cursor.getColumnIndex(dbHelper.sc_fees_girls));
            others_note=cursor.getString(cursor.getColumnIndex(dbHelper.others_note));
            others=cursor.getString(cursor.getColumnIndex(dbHelper.others));

            ar.add("Id: "+id+"\nTitle :"+title+" \nDescription: "+description+"  \n Section: "+section+"   \nLevel: "+level+"   Image:"+image_file_name+"   Image:"+image_content_type+"   "+image_file_size+"   "+image_updated_at+"  \n Exam Date: "+exam_date+" \nForm release date"+
                    form_release_date+"  \n Form Last Date: "+form_last_date+"   "+link1_name+"   "+link1+"   "+link2_name+"   "+link2+"   "+link3_name+"   "+link3+"   "+link4_name+"   "+link4+"   "+created_at+"   "+updated_at+"   "+
                    exam_review+"   "+gen_fees_boys+"   "+gen_fees_girls+"   "+sc_fees_boys+"   "+sc_fees_girls+"   "+others_note+"   "+others+"\n");
        }
        sqLiteDatabase.close();
        return ar;
    }

    public static String title,description, section,level,image_file_name,image_content_type,image_file_size,image_updated_at,exam_date;
    public static String form_release_date,form_last_date,link1_name,link1,link2_name,link2,link3_name,link3,link4_name,link4,created_at,updated_at;
    public static String exam_review, gen_fees_boys,gen_fees_girls,sc_fees_boys,sc_fees_girls,others_note,others;

    public void giveExamDetails(String exam){

        String currentexams;
        String[] columns={dbHelper.title,dbHelper.description,dbHelper.section,dbHelper.level,dbHelper.image_file_name,dbHelper.image_content_type,
                dbHelper.image_file_size,dbHelper.image_updated_at,dbHelper.exam_date,dbHelper.form_release_date,dbHelper.form_last_date,dbHelper.link1_name,
                dbHelper.link1,dbHelper.link2_name,dbHelper.link2,dbHelper.link3_name,dbHelper.link3,dbHelper.link4_name,dbHelper.link4,dbHelper.created_at,
                dbHelper.updated_at,dbHelper.exam_review,dbHelper.gen_fees_boys,dbHelper.gen_fees_girls,dbHelper.sc_fees_boys,dbHelper.sc_fees_girls,dbHelper.others_note,dbHelper.others};

        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.query(dbHelper.TABLENAME,columns,null,null,null,null,null);

        while(cursor.moveToNext()){
            //currentcategory=cursor.getString(cursor.getColumnIndex(dbHelper.CATEGORY));
            // if(category.compareToIgnoreCase(currentcategory)==0){
            //       exams=cursor.getString(cursor.getColumnIndex(dbHelper.EXAMS));
            //     ar.add(exams);
            //}
            currentexams=cursor.getString(cursor.getColumnIndex(dbHelper.title));

            if(exam.compareToIgnoreCase(currentexams)==0){

                title=cursor.getString(cursor.getColumnIndex(dbHelper.title));
                description=cursor.getString(cursor.getColumnIndex(dbHelper.description));
                section=cursor.getString(cursor.getColumnIndex(dbHelper.section));
                level=cursor.getString(cursor.getColumnIndex(dbHelper.level));
                image_file_name=cursor.getString(cursor.getColumnIndex(dbHelper.image_file_name));
                image_content_type=cursor.getString(cursor.getColumnIndex(dbHelper.image_content_type));
                image_file_size=cursor.getString(cursor.getColumnIndex(dbHelper.image_file_size));
                image_updated_at=cursor.getString(cursor.getColumnIndex(dbHelper.image_updated_at));
                exam_date=cursor.getString(cursor.getColumnIndex(dbHelper.exam_date));
                form_release_date=cursor.getString(cursor.getColumnIndex(dbHelper.form_release_date));
                form_last_date=cursor.getString(cursor.getColumnIndex(dbHelper.form_last_date));
                link1_name=cursor.getString(cursor.getColumnIndex(dbHelper.link1_name));
                link1=cursor.getString(cursor.getColumnIndex(dbHelper.link1));
                link2_name=cursor.getString(cursor.getColumnIndex(dbHelper.link2_name));
                link2=cursor.getString(cursor.getColumnIndex(dbHelper.link2));
                link3_name=cursor.getString(cursor.getColumnIndex(dbHelper.link3_name));
                link3=cursor.getString(cursor.getColumnIndex(dbHelper.link3));
                link4_name=cursor.getString(cursor.getColumnIndex(dbHelper.link4_name));
                link4=cursor.getString(cursor.getColumnIndex(dbHelper.link4));
                created_at=cursor.getString(cursor.getColumnIndex(dbHelper.created_at));
                updated_at=cursor.getString(cursor.getColumnIndex(dbHelper.updated_at));
                exam_review=cursor.getString(cursor.getColumnIndex(dbHelper.exam_review));
                gen_fees_boys=cursor.getString(cursor.getColumnIndex(dbHelper.gen_fees_boys));
                gen_fees_girls=cursor.getString(cursor.getColumnIndex(dbHelper.gen_fees_girls));
                sc_fees_boys=cursor.getString(cursor.getColumnIndex(dbHelper.sc_fees_boys));
                sc_fees_girls=cursor.getString(cursor.getColumnIndex(dbHelper.sc_fees_girls));
                others_note=cursor.getString(cursor.getColumnIndex(dbHelper.others_note));
                others=cursor.getString(cursor.getColumnIndex(dbHelper.others));


                break;
            }


        }
        sqLiteDatabase.close();

    }

    public ArrayList<String> populateList(String section,String level){

        ArrayList<String> list=new ArrayList<>();
        String checksection,checklevel,examtitle;
        int examid;
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        String[] columns={dbHelper.id,dbHelper.title,dbHelper.section,dbHelper.level};
        Cursor cursor=sqLiteDatabase.query(dbHelper.TABLENAME,columns,null,null,null,null,null);

        while(cursor.moveToNext()){
            checklevel=cursor.getString(cursor.getColumnIndex(dbHelper.level));
            checksection=cursor.getString(cursor.getColumnIndex(dbHelper.section));
            if(section.compareToIgnoreCase(checksection)==0  &&  level.compareToIgnoreCase(checklevel)==0){
                examtitle=cursor.getString(cursor.getColumnIndex(dbHelper.title));
                examid=cursor.getInt(cursor.getColumnIndex(dbHelper.id));
                list.add(examtitle);
            }
        }
        sqLiteDatabase.close();
        return list;
    }

    public void resetdata(){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        try{
            sqLiteDatabase.delete(dbHelper.TABLENAME,null,null);
        }
        catch (Exception e){
            Log.d("My",""+e);
        }
    }




    static public class DbHelper extends SQLiteOpenHelper{

        private static final String TABLENAME="Details";
        private static final String id="id";
        private static final String title="title";
        private static final String description="description";
        private static final String section="section";
        private static final String level="level";
        private static final String image_file_name="image_file_name";
        private static final String image_content_type="image_content_type";
        private static final String image_file_size="image_file_size";
        private static final String image_updated_at="image_updated_at";
        private static final String exam_date="exam_date";
        private static final String form_release_date="form_release_date";
        private static final String form_last_date="form_last_date";
        private static final String link1_name="link1_name";
        private static final String link1="link1";
        private static final String link2_name="link2_name";
        private static final String link2="link2";
        private static final String link3_name="link3_name";
        private static final String link3="link3";
        private static final String link4_name="link4_name";
        private static final String link4="link4";
        private static final String created_at="created_at";
        private static final String updated_at="updated_at";
        private static final String exam_review="exam_review";
        private static final String gen_fees_boys="gen_fees_boys";
        private static final String gen_fees_girls="gen_fees_girls";
        private static final String sc_fees_boys="sc_fees_boys";
        private static final String sc_fees_girls="sc_fees_girls";
        private static final String others_note="others_note";
        private static final String others="others";

        private static final int DBVERSION=3;
        private static final String CREATETABLE="CREATE TABLE "+TABLENAME+"("+id+" INTEGER ,"+title+" VARCHAR(256),"+description+" VARCHAR(256),"
                                                +section+" VARCHAR(256),"+level+" VARCHAR(256),"+image_file_name+" VARCHAR(256),"
                                                 +image_content_type+" VARCHAR(256),"+image_file_size+" VARCHAR(256),"+image_updated_at+" VARCHAR(256),"
                                                  +exam_date+" VARCHAR(256),"+form_release_date+" VARCHAR(256),"+form_last_date+" VARCHAR(256),"
                                                  +link1_name+" VARCHAR(256),"+link1+" VARCHAR(256),"+link2_name+" VARCHAR(256),"+link2+" VARCHAR(256),"
                                                  +link3_name+" VARCHAR(256),"+link3+" VARCHAR(256),"+link4_name+" VARCHAR(256),"+link4+" VARCHAR(256),"
                                                 +created_at+" VARCHAR(256),"+updated_at+" VARCHAR(256),"+exam_review+" VARCHAR(256),"+gen_fees_boys+
                                                " VARCHAR(256),"+gen_fees_girls+" VARCHAR(256),"
                +sc_fees_boys+" VARCHAR(256),"+sc_fees_girls+" VARCHAR(256),"+others_note+" VARCHAR(256),"+others+" VARCHAR(256));";
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
                Log.d("My","new");

            }
            catch(Exception e){

            }

        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try{
                db.execSQL(DROPTABLE);
                Toast.makeText(conn,"updated",Toast.LENGTH_SHORT).show();
                onCreate(db);
            }
            catch(Exception e){
                Toast.makeText(conn,"update Failed",Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try{
                db.execSQL(DROPTABLE);
                Toast.makeText(conn,"updated",Toast.LENGTH_SHORT).show();
                onCreate(db);
            }
            catch(Exception e){
                Toast.makeText(conn,"update Failed",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
