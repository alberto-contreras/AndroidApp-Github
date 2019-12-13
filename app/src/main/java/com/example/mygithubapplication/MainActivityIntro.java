package com.example.mygithubapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivityIntro extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.myfirstretrofitapp.example.EXTRA_TEXT";
    //EXTRA_TEXT it's the varible that allow us to pass info to other activities
    private Button searchbtn;
    private ImageView Ghublogo;
    private EditText usertb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intro);
        searchbtn = findViewById(R.id.search_btn);
        Ghublogo = findViewById(R.id.iconGHub);
        Picasso.with(getApplicationContext()).load("https://avatars1.githubusercontent.com/u/583231?s=400&v=4").into(Ghublogo);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }
    public void openActivity2(){
        usertb = findViewById(R.id.usertb);
        String user =  usertb.getText().toString();
        Intent intent = new Intent(this,MainActivity.class);//We call the second activity
        intent.putExtra(EXTRA_TEXT, user);//We pass the objects of this activity that we are going to need in the other one
        startActivity(intent);//Start
    }


}