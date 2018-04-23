package com.debugprojects.favitems;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import static com.debugprojects.favitems.MyFragment.SERIES_ARRAY_LIST;

public class MainActivity extends AppCompatActivity {

    public static final String SERIES_ARRAY_LIST_FAV = "series_array_list_fav" ;
    TabLayout mtabLayout;
    ViewPager mPager;
    MyPagerAdapter adapter;
    public Bundle args;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        args = getFirstSeries();

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        mtabLayout = findViewById(R.id.tab_layout);
        mPager=  findViewById(R.id.pager);
        mPager.setAdapter(adapter);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter{


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            MyFragment frag;
            if(position == 0){
                    frag= MyFragment.newInstance(args,true);
            }else{
                frag= MyFragment.newInstance(args,false);
            }

            return frag;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    private Bundle getFirstSeries(){
        ArrayList<Serie> series = new ArrayList<>();
        ArrayList<Serie> seriesFav = new ArrayList<>();

        Bundle b = new Bundle();

        series.add(new Serie("The Walking Dead", "Zombies",4, R.drawable.twd,false));
        series.add(new Serie("Narcos", "Drogas",3, R.drawable.n,false));
        series.add(new Serie("House Of Cards", "Politica",5, R.drawable.hoc,false));

        b.putSerializable(SERIES_ARRAY_LIST, series);
        b.putSerializable(SERIES_ARRAY_LIST_FAV, seriesFav);
        return b;
    }
}

