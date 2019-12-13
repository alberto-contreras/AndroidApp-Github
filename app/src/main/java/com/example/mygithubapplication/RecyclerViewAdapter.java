package com.example.mygithubapplication;

import android.content.Context;
import android.content.ContextWrapper;
import android.service.autofill.FillContext;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
public class RecyclerViewAdapter extends  RecyclerView.Adapter <RecyclerViewAdapter.ViewHolderData> {


        List<GitHubUser> listofdata;//List that we are going to receive

    public RecyclerViewAdapter(List<GitHubUser> listofdata) {
        this.listofdata = listofdata;
        }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //Relates this class with the layout
        View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.layout_listitem,null,false);

        return new ViewHolderData(view);
        }

    @Override
    public void onBindViewHolder( ViewHolderData holder, int position) { //Assign the value for each item in the list
        holder.tvFoll.append(listofdata.get(position).login);
        Picasso.with(holder.imgFoll.getContext()).load(""+listofdata.get(position).getAvatar_url()).into(holder.imgFoll);
        }

    @Override
    public int getItemCount() {
        return listofdata.size();
        }

    public class ViewHolderData extends RecyclerView.ViewHolder {

    //We have to make reference to all the data of the layout list --> Image,TextView..
    public ImageView imgFoll;
    public TextView tvFoll;

    public ViewHolderData(@NonNull View itemView) { //Refrence the variable to each component
        super(itemView);
        //Know make reference
        imgFoll = itemView.findViewById(R.id.followerImage);
        tvFoll = itemView.findViewById(R.id.followerUsername);
    }
}
}