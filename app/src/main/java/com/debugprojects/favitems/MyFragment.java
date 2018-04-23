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

    private static final String ID_KEY = "id_key";
    public static final String SERIES_ARRAY_LIST = "series_array_list";
    private SeriesAdapter adapter_default;
    private SeriesAdapterFav adapter_fav;
    private RecyclerView rv;
    private LinearLayoutManager lManager;
    private Bundle bundle;
    private static boolean favorited;
    ArrayList<Serie> series;
    ArrayList<Serie> seriesFav;


    public MyFragment() {}

    public static MyFragment newInstance(Bundle bundle_args, boolean isFavorited){
        MyFragment my =  new MyFragment();
        my.setArguments(bundle_args);
        favorited = isFavorited;
        return my;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.series_layout,container,false);

        bundle = getArguments();

        rv= view.findViewById(R.id.recycler_series);
        rv.setHasFixedSize(true);

        lManager = new LinearLayoutManager(getContext());

        rv.setLayoutManager(lManager);

        series = (ArrayList<Serie>) bundle.getSerializable(SERIES_ARRAY_LIST);
        series = (ArrayList<Serie>) bundle.getSerializable(SERIES_ARRAY_LIST_FAV);

        adapter_default = new SeriesAdapter(series,seriesFav);
        adapter_fav = new SeriesAdapterFav(seriesFav);

        if(favorited){
            rv.setAdapter();
        }else{

        }



        rv.setAdapter(adapter_default);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter_default.load(series);
    }
}
