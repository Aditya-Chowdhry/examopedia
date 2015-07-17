package com.example.examopedia.JSON;


import android.content.Context;
import android.util.Log;

import com.example.examopedia.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJSON {
    Database database;
    Integer id;
    String title, description, section, level, image_file_name, image_content_type, image_file_size, image_updated_at, exam_date;
    String form_release_date, form_last_date, link1_name, link1, link2_name, link2, link3_name, link3, link4_name, link4, created_at, updated_at;
    String exam_review, gen_fees_boys, gen_fees_girls, sc_fees_boys, sc_fees_girls, others_note, others;
    Context context;
    public ParseJSON(Context context) {
        super();
        this.context=context;

    }

    public void jsonParsing(String json) {

        database=new Database(context);

        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                id = object.getInt("id");
                title = object.getString("title");
                description = object.getString("description");
                section = object.getString("section");
                level = object.getString("level");
                image_file_name = object.getString("image_file_name");
                image_content_type = object.getString("image_content_type");
                image_file_size = object.getString("image_file_size");
                image_updated_at = object.getString("image_updated_at");
                exam_date = object.getString("exam_date");
                form_release_date = object.getString("form_release_date");
                form_last_date = object.getString("form_last_date");
                link1_name = object.getString("link1_name");
                link1 = object.getString("link1");
                link2_name = object.getString("link2_name");
                link2 = object.getString("link2");
                link3_name = object.getString("link3_name");
                link3 = object.getString("link3");
                link4_name = object.getString("link4_name");
                link4 = object.getString("link4");
                created_at = object.getString("created_at");
                updated_at = object.getString("updated_at");
                exam_review = object.getString("exam_review");
                gen_fees_boys = object.getString("gen_fees_boys");
                gen_fees_girls = object.getString("gen_fees_girls");
                sc_fees_boys = object.getString("sc_fees_boys");
                sc_fees_girls = object.getString("sc_fees_girls");
                others_note = object.getString("others_note");
                others = object.getString("others");

                //Adding one row to database.
                database.addData(id,title,description,section,level,image_file_name,image_content_type,image_file_size,image_updated_at, exam_date,form_release_date,form_last_date,link1_name,link1,link2_name,
                        link2,link3_name,link3,link4_name,link4,created_at,updated_at,exam_review,gen_fees_boys,gen_fees_girls,sc_fees_boys,sc_fees_girls,others_note,others);

            }
        } catch (JSONException e) {
            Log.d("My", " " + e);
        }


    }
}
