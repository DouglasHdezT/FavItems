package com.debugprojects.favitems;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static com.debugprojects.favitems.MainActivity.SERIES_ARRAY_LIST_FAV;

public class MyFragment extends Fragment {

    public static final String FAV_KEY = "id_key";
    public static final String SERIES_ARRAY_LIST = "series_array_list";
    private static final String BUNDLE_KEY = "bundle_key";
    private SeriesAdapter adapter_default;
    private SeriesAdapterFav adapter_fav;
    private RecyclerView rv;
    private LinearLayoutManager lManager;
    private Bundle bundle;
    ArrayList<Serie> series;
    ArrayList<Serie> seriesFav;


    public MyFragment() {}

    public static MyFragment newInstance(ArrayList<Serie> series, ArrayList<Serie> seriesFav, boolean isFavorited){
        Bundle bundle = new Bundle();
        MyFragment my =  new MyFragment();
        bundle.putSerializable(SERIES_ARRAY_LIST , series);
        bundle.putSerializable(SERIES_ARRAY_LIST_FAV , seriesFav);
        bundle.putBoolean(FAV_KEY, isFavorited);
        my.setArguments(bundle);
        return my;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.series_layout,container,false);

        rv= view.findViewById(R.id.recycler_series);

        lManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lManager);
        rv.setHasFixedSize(true);

        bundle = getArguments();

        series = (ArrayList<Serie>) bundle.getSerializable(SERIES_ARRAY_LIST);
        seriesFav = (ArrayList<Serie>) bundle.getSerializable(SERIES_ARRAY_LIST_FAV);

        boolean favorited = bundle.getBoolean(FAV_KEY);

        adapter_default = new SeriesAdapter(series,seriesFav);
        adapter_fav = new SeriesAdapterFav(seriesFav);

        if(favorited){
            rv.setAdapter(adapter_fav);
        }else{
            rv.setAdapter(adapter_default);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter_default.load(series,seriesFav);
        adapter_fav.load(seriesFav);
    }
}
