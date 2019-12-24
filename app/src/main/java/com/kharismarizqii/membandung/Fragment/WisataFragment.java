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

import com.kharismarizqii.membandung.Adapter.ListWisataAdapter;
import com.kharismarizqii.membandung.MainActivity;
import com.kharismarizqii.membandung.Model.Wisata;
import com.kharismarizqii.membandung.R;
import com.kharismarizqii.membandung.WisataDetailActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WisataFragment extends Fragment {

    private RecyclerView rvWisata;
    private TextView tvSapa;
    ListWisataAdapter adapter;
    int counter = 0;
    private ArrayList<Wisata> list = new ArrayList<Wisata>();



    public WisataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wisata, container, false);
        rvWisata = (RecyclerView)view.findViewById(R.id.rv_wisata);
        tvSapa = (TextView)view.findViewById(R.id.tv_sapa);
        if(counter < 1)
            list.addAll(getListWisata());
        counter++;
        showRecyclerList();


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        buttonAnimation(tvSapa);

    }

    public ArrayList<Wisata> getListWisata(){
        String[] dataDest = getResources().getStringArray(R.array.data_nama_wisata);
        String[] dataDesc = getResources().getStringArray(R.array.data_desc_wisata);
        String[] dataRating = getResources().getStringArray(R.array.data_rating_wisata);
        String[] dataPhoto = getResources().getStringArray(R.array.data_photo_wisata);
        String[] dataLocation = getResources().getStringArray(R.array.data_loc_wisata);
        String[] dataAlamat = getResources().getStringArray(R.array.data_alamat_wisata);



        ArrayList<Wisata> listWisata = new ArrayList<>();
        for(int i = 0; i< dataDest.length; i++){
            Wisata wisata = new Wisata();
            wisata.setPhoto(dataPhoto[i]);
            wisata.setDestinasi(dataDest[i]);
            wisata.setDescription(dataDesc[i]);
            wisata.setRating(dataRating[i]);
            wisata.setLocation(dataLocation[i]);
            wisata.setAlamat(dataAlamat[i]);

            list.add(wisata);
        }
        return listWisata;
    }

    private void showRecyclerList(){
        rvWisata.setLayoutManager(new LinearLayoutManager(getContext()));
        ListWisataAdapter listHeroAdapter = new ListWisataAdapter(list);
        rvWisata.setAdapter(listHeroAdapter);

        listHeroAdapter.setOnItemClickCallback(new ListWisataAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Wisata data) {
                showSelectedDest(data);
            }
        });
    }

    private void showSelectedDest(Wisata wisata){
        Intent intent = new Intent(getContext(), WisataDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(WisataDetailActivity.DEST_NAME, wisata.getDestinasi());
        bundle.putString(WisataDetailActivity.DEST_PHOTO, wisata.getPhoto());
        bundle.putString(WisataDetailActivity.DEST_DESC, wisata.getDescription());
        bundle.putString(WisataDetailActivity.DEST_RATING, wisata.getRating());
        bundle.putString(WisataDetailActivity.DEST_LOC, wisata.getLocation());
        bundle.putString(WisataDetailActivity.DEST_ALAMAT, wisata.getAlamat());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void buttonAnimation(View v){
        Animation scale = AnimationUtils.loadAnimation(getContext(), R.anim.scale);
        v.startAnimation(scale);
    }

}
