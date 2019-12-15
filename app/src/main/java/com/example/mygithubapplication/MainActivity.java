package com.example.mygithubapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ImageView mainUserImage;
    private GitHubUser mainUser;
    private RecyclerView followers;
    private TextView tvUsername;
    private TextView tvRepos;
    private TextView tvFollowing;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private List<GitHubUser> userfollowers;
    private String usertext;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent =getIntent();
        usertext = intent.getStringExtra(MainActivityIntro.EXTRA_TEXT);
        //textCheck = findViewById(R.id.textView1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        followers = (RecyclerView) findViewById(R.id.followers);
        followers.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //HOW TO PRESENT THE LIST OF ITEMS

        tvUsername = findViewById(R.id.username);
        tvRepos = findViewById(R.id.repos);
        tvFollowing = findViewById(R.id.following);
        mainUserImage = findViewById(R.id.mainUserimage);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        getUserFollowers();
        getUser();
        progressBar.setVisibility(View.GONE);
    }
    private void getUser() {

        Call<GitHubUser> call = jsonPlaceHolderApi.getUser(""+usertext);
        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {

                if (!response.isSuccessful()) {

                    return;
                }
                else{

                    mainUser = response.body();//List of posts
                    tvUsername.append(" " + mainUser.getLogin() + "\n");
                    tvRepos.append(" " + mainUser.getPublic_repos() + "\n");
                    tvFollowing.append(" " + mainUser.getFollowing() + "\n");
                    Picasso.with(getApplicationContext()).load(""+mainUser.getAvatar_url()).into(mainUserImage);
                }

            }
            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {

            }
        });
    }
    private void getUserFollowers(){
        Call<List<GitHubUser>> call = jsonPlaceHolderApi.getUserFollowers(""+usertext);
        call.enqueue(new Callback<List<GitHubUser>>() {
            @Override
            public void onResponse(Call<List<GitHubUser>> call, Response<List<GitHubUser>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                else{
                    userfollowers=response.body();
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(userfollowers);
                    followers.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<GitHubUser>> call, Throwable t) {

            }
        });



    }

}
