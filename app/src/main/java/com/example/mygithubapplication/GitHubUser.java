package com.example.mygithubapplication;

public class GitHubUser {
    String login;
    int followers;
    int following;
    String avatar_url;
    int public_repos;

    public String getAvatar_url() {
        return avatar_url;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public String getLogin() {
        return login;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }
}
