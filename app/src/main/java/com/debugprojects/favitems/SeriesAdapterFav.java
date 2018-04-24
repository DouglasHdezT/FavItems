package com.debugprojects.favitems;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public abstract class SeriesAdapterFav extends RecyclerView.Adapter<SeriesAdapterFav.SeriesFavViewHolder> implements Modificable{

    private ArrayList<Serie> seriesFav;
    private ArrayList<Serie> series;

    public SeriesAdapterFav(ArrayList<Serie> seriesFav, ArrayList<Serie> series){
        this.seriesFav = seriesFav;
        this.series = series;

    }

    public  class SeriesFavViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView imageView;
        TextView text_title;
        TextView text_rating;
        ImageButton button_star;

        public SeriesFavViewHolder(View itemView) {
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
    public SeriesFavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return (new SeriesFavViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesFavViewHolder holder, final int position) {
        boolean isFavorited= seriesFav.get(position).isFavorited();

        final int pos = position;
        if(isFavorited){
            holder.button_star.setImageResource(R.drawable.star_pressed);
        }
        else{
            holder.button_star.setImageResource(R.drawable.star_default);
        }

        holder.imageView.setImageResource(seriesFav.get(position).getImage_id());

        String rate="Rate: "+ seriesFav.get(position).getRating()+"/5.0";

        holder.text_title.setText(seriesFav.get(position).getName());
        holder.text_rating.setText(rate);

        holder.button_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO Settear falso a Favorited de las series normales
                seriesFav.get(pos).setFavorited(false);
                ((ImageButton) v).setImageResource(R.drawable.star_default);
                seriesFav.remove(seriesFav.get(pos));
                Toast.makeText(v.getContext().getApplicationContext(),pos + "",Toast.LENGTH_LONG).show();
                notifyItemRemoved(pos);
                notifyItemRangeChanged(pos, seriesFav.size());
                remover(//TODO POsicion de la serie en las normales);
            }
        });

    }

    @Override
    public int getItemCount() {
        return seriesFav.size();
    }

}


