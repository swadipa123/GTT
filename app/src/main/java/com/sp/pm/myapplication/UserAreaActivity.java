package com.sp.pm.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        int age = intent.getIntExtra("age", -1);
        final Button bLogout = (Button) findViewById(R.id.bLogout);
        TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
       // EditText etUsername = (EditText) findViewById(R.id.etUsername);
       //EditText etAge = (EditText) findViewById(R.id.etAge);

        // Display user details
        String message =  "Data is submitted successfully";
        tvWelcomeMsg.setText(message);
       // etUsername.setText(username);
       // etAge.setText(age + "");

        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserAreaActivity.this, LoginActivity.class);
                startActivity(intent);
                SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                finish();

            }
        });
    }


}