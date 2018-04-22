package com.debugprojects.favitems;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder> {

    private ArrayList<Serie> series;

    public SeriesAdapter(ArrayList<Serie> series){
        this.series = series;
    }

    public static class SeriesViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView text_title;
        TextView text_rating;
        ImageButton button_star;

        public SeriesViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_view);
            text_title = itemView.findViewById(R.id.card_title);
            text_rating = itemView.findViewById(R.id.card_rating);
            button_star =  itemView.findViewById(R.id.card_star);

        }

    }

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return (new SeriesViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        boolean isFavorited=series.get(position).isFavorited();

        if(isFavorited){
            holder.button_star.setImageResource(R.drawable.star_pressed);
        }
        else{
            holder.button_star.setImageResource(R.drawable.star_default);
        }

        String rate="Rate: "+series.get(position).getRating()+"/5.0";

        holder.text_title.setText(series.get(position).getName());
        holder.text_rating.setText(rate);

    }

    @Override
    public int getItemCount() {
        return series.size();
    }
}
