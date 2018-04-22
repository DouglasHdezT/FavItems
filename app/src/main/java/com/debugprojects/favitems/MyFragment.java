package com.debugprojects.favitems;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class MyFragment extends Fragment {

    private static final String ID_KEY = "id_key";
    private static final String SERIES_ARRAY_LIST = "series_array_list";
    private SeriesAdapter adapter;
    private RecyclerView rv;
    private LinearLayoutManager lManager;


    public MyFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.series_layout,container,false);
        Bundle bundle = getArguments();

        rv= getActivity().findViewById(R.id.recycler_series);
        rv.setHasFixedSize(true);
        lManager = new LinearLayoutManager(getContext());
        

        if(bundle != null){
            ArrayList<Serie> series = (ArrayList<Serie>) bundle.getSerializable(SERIES_ARRAY_LIST);
        }




        return view;
    }
}
