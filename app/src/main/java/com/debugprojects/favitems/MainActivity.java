package com.debugprojects.favitems;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import static com.debugprojects.favitems.MyFragment.SERIES_ARRAY_LIST;

public class MainActivity extends AppCompatActivity implements MyFragment.OnCreateAdapter {

    public static final String SERIES_ARRAY_LIST_FAV = "series_array_list_fav" ;
    TabLayout mtabLayout;
    ViewPager mPager;
    MyPagerAdapter adapter;
    public Bundle args;
    ArrayList<Serie> series = new ArrayList<>();
    ArrayList<Serie> seriesFav = new ArrayList<>();
    SeriesAdapter seriesAdapter;
    SeriesAdapterFav seriesAdapterFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFirstSeries();

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        mtabLayout = findViewById(R.id.tab_layout);
        mPager=  findViewById(R.id.pager);
        mPager.setAdapter(adapter);
        mtabLayout.setTabsFromPagerAdapter(adapter);
        mtabLayout.setupWithViewPager(mPager);

        seriesAdapterFav = new SeriesAdapterFav(seriesFav,series) {
            @Override
            public void remover(int series) {
                seriesAdapter.notifyItemChanged(series);
            }

            @Override
            public void agregar(int series) {

            }
        };
        seriesAdapter = new SeriesAdapter(series,seriesFav) {
            @Override
            public void remover(int series) {
                seriesAdapterFav.notifyItemRemoved(series);
            }

            @Override
            public void agregar(int series) {
                seriesAdapterFav.notifyItemInserted(series);

            }
        };
    }

    @Override
    public void onCreateAdapter(RecyclerView rv, boolean fav) {
        if(fav){
            rv.setAdapter(seriesAdapterFav);
        }else{
            rv.setAdapter(seriesAdapter);
        }
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter{


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            MyFragment frag;
            if(position == 0){
                frag= MyFragment.newInstance(false);
            }else{
                frag= MyFragment.newInstance(true);
            }
            return frag;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String name;

            if(position == 0){
                name = "Series";
            }else{
                name = "Favoritos";
            }

            return name;

        }
    }

    private void getFirstSeries(){
        series.add(new Serie("The Walking Dead", "Zombies",4, R.drawable.twd,false));
        series.add(new Serie("Narcos", "Drogas",3, R.drawable.n,false));
        series.add(new Serie("House Of Cards", "Politica",5, R.drawable.hoc,false));
        series.add(new Serie("Breaking Bad", "Metanfetaminas",5, R.drawable.bb,false));
        series.add(new Serie("The Bing Bang Teory", "La teoria del todo",4, R.drawable.tbbt,false));
        series.add(new Serie("Two And A Half Men", "Estupideces Familiares",3, R.drawable.taahm,false));
        series.add(new Serie("Game Of Thrones", "Skyrim?",4, R.drawable.got,false));
        series.add(new Serie("The Simpsons", "Politica y vida",5, R.drawable.ts,false));
    }
}

