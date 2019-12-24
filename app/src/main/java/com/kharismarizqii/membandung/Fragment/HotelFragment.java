package com.kharismarizqii.membandung.Fragment;


import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.kharismarizqii.membandung.Adapter.ListHotelAdapter;
import com.kharismarizqii.membandung.MainActivity;
import com.kharismarizqii.membandung.Model.Hotel;
import com.kharismarizqii.membandung.R;
import com.kharismarizqii.membandung.HotelDetailActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelFragment extends Fragment {

    private RecyclerView rvHotel;
    private TextView tvSapa;
    ListHotelAdapter adapter;
    int counter = 0;
    private ArrayList<Hotel> list = new ArrayList<Hotel>();



    public HotelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotel, container, false);
        rvHotel = (RecyclerView)view.findViewById(R.id.rv_hotel);
        tvSapa = (TextView)view.findViewById(R.id.tv_sapa);
        if(counter < 1)
            list.addAll(getListHotel());
        counter++;
        showRecyclerList();


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        buttonAnimation(tvSapa);

    }

    public ArrayList<Hotel> getListHotel(){
        String[] dataDest = getResources().getStringArray(R.array.data_nama_hotel);
        String[] dataDesc = getResources().getStringArray(R.array.data_desc_hotel);
        String[] dataRating = getResources().getStringArray(R.array.data_rating_hotel);
        String[] dataPhoto = getResources().getStringArray(R.array.data_photo_hotel);
        String[] dataLocation = getResources().getStringArray(R.array.data_loc_hotel);
        String[] dataAlamat = getResources().getStringArray(R.array.data_alamat_hotel);

        ArrayList<Hotel> listHotel = new ArrayList<>();
        for(int i = 0; i< dataDest.length; i++){
            Hotel hotel = new Hotel();
            hotel.setPhoto(dataPhoto[i]);
            hotel.setDestinasi(dataDest[i]);
            hotel.setDescription(dataDesc[i]);
            hotel.setRating(dataRating[i]);
            hotel.setLocation(dataLocation[i]);
            hotel.setAlamat(dataAlamat[i]);

            list.add(hotel);
        }
        return listHotel;
    }

    private void showRecyclerList(){
        rvHotel.setLayoutManager(new LinearLayoutManager(getContext()));
        ListHotelAdapter listHeroAdapter = new ListHotelAdapter(list);
        rvHotel.setAdapter(listHeroAdapter);

        listHeroAdapter.setOnItemClickCallback(new ListHotelAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Hotel data) {
                showSelectedDest(data);
            }
        });
    }

    private void showSelectedDest(Hotel hotel){
        Intent intent = new Intent(getContext(), HotelDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(HotelDetailActivity.DEST_NAME, hotel.getDestinasi());
        bundle.putString(HotelDetailActivity.DEST_PHOTO, hotel.getPhoto());
        bundle.putString(HotelDetailActivity.DEST_DESC, hotel.getDescription());
        bundle.putString(HotelDetailActivity.DEST_RATING, hotel.getRating());
        bundle.putString(HotelDetailActivity.DEST_LOC, hotel.getLocation());
        bundle.putString(HotelDetailActivity.DEST_ALAMAT,hotel.getAlamat());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void buttonAnimation(View v){
        Animation scale = AnimationUtils.loadAnimation(getContext(), R.anim.scale);
        v.startAnimation(scale);
    }

}
