package com.examopedia.beta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class UserDetails extends AppCompatActivity {

    EditText name,email,age,course,interest,ten,twelve;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details);
        name=(EditText)findViewById(R.id.editText);
        email=(EditText)findViewById(R.id.editText2);
        age=(EditText)findViewById(R.id.editText3);
        course=(EditText)findViewById(R.id.editText4);
        interest=(EditText)findViewById(R.id.editText5);
        ten=(EditText)findViewById(R.id.editText6);
        twelve=(EditText)findViewById(R.id.editText7);
        submit= (Button) findViewById(R.id.button2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //method to send details to server yet to be added
                startActivity(new Intent(UserDetails.this,MainActivity.class));
                finish();
            }
        });
    }



}
