package com.kharismarizqii.membandung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kharismarizqii.membandung.Adapter.ViewPagerAdapter;
import com.kharismarizqii.membandung.Fragment.HotelFragment;
import com.kharismarizqii.membandung.Fragment.KulinerFragment;
import com.kharismarizqii.membandung.Fragment.WisataFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    private ViewPager vp;
    HotelFragment hotelFragment;
    KulinerFragment kulinerFragment;
    WisataFragment wisataFragment;
    MenuItem prevMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnv = (BottomNavigationView)findViewById(R.id.bottomNavigationView);
        vp = (ViewPager)findViewById(R.id.viewpager);

        setupViewPager(vp);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_wisata:
                        vp.setCurrentItem(0);
                        return true;
                    case R.id.action_kuliner:
                        vp.setCurrentItem(1);
                        return true;
                    case R.id.action_hotel:
                        vp.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bnv.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bnv.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bnv.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        hotelFragment = new HotelFragment();
        wisataFragment = new WisataFragment();
        kulinerFragment = new KulinerFragment();
        adapter.addFragment(wisataFragment);
        adapter.addFragment(kulinerFragment);
        adapter.addFragment(hotelFragment);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.about_menu:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;

            case R.id.info_menu:
                Intent infointent = new Intent(this, InfoActivity.class);
                startActivity(infointent);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setTitle("Keluar");
        myAlert.setMessage("Apakah anda yakin?");
        myAlert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        myAlert.setNegativeButton("TIDAK", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //agar tidak bisa dklik selain dialog
        myAlert.setCancelable(false);

        myAlert.show();
    }
}
