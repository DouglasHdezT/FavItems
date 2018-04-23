package com.debugprojects.favitems;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public abstract class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder> {

    private ArrayList<Serie> series;
    private ArrayList<Serie> seriesFav;


    public SeriesAdapter(ArrayList<Serie> series, ArrayList<Serie> seriesFav){
        this.series = series;
        this.seriesFav= seriesFav;
    }

    public static class SeriesViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView imageView;
        TextView text_title;
        TextView text_rating;
        ImageButton button_star;

        public SeriesViewHolder(View itemView) {
            super(itemView);

            imageView =  itemView.findViewById(R.id.card_image);
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
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, final int position) {
        boolean isFavorited=series.get(position).isFavorited();

        if(isFavorited){
            holder.button_star.setImageResource(R.drawable.star_pressed);
        }
        else{
            holder.button_star.setImageResource(R.drawable.star_default);
        }

        holder.imageView.setImageResource(series.get(position).getImage_id());

        String rate="Rate: "+series.get(position).getRating()+"/5.0";

        holder.text_title.setText(series.get(position).getName());
        holder.text_rating.setText(rate);

        holder.button_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(series.get(position).isFavorited()){
                    Serie serie = series.get(position);

                    serie.setFavorited(false);
                    ((ImageButton) v).setImageResource(R.drawable.star_default);

                    if(seriesFav.contains(serie)){
                        Log.d("Adpater", "Si esta");
                        int index = seriesFav.indexOf(serie);
                        seriesFav.remove(index);
                        remover(index);
                    }

                }else{
                    series.get(position).setFavorited(true);
                    seriesFav.add(series.get(position));
                    ((ImageButton)v).setImageResource(R.drawable.star_pressed);
                    agregar(seriesFav.indexOf(series.get(position)));
                }
               // notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return series.size();
    }

    public abstract void remover(int series);
    public abstract void agregar(int series);

    public void load(ArrayList<Serie> series,ArrayList<Serie> seriesFav){
        this.series = series;
        this.seriesFav = seriesFav;
        notifyDataSetChanged();
    }

}
