package com.example.mygithubapplication;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import java.util.List;

public interface JsonPlaceHolderApi {
    //All the API request that we are going to do

    @GET("/users/{username}")
    Call <GitHubUser> getUser (@Path("username") String Username);

    @GET("/users/{username}/followers")
    Call <List<GitHubUser>> getUserFollowers (@Path("username") String Username);
}
