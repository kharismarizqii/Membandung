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

import com.kharismarizqii.membandung.Adapter.ListKulinerAdapter;
import com.kharismarizqii.membandung.MainActivity;
import com.kharismarizqii.membandung.Model.Kuliner;
import com.kharismarizqii.membandung.R;
import com.kharismarizqii.membandung.KulinerDetailActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class KulinerFragment extends Fragment {

    private RecyclerView rvKuliner;
    private TextView tvSapa;
    ListKulinerAdapter adapter;
    int counter = 0;
    private ArrayList<Kuliner> list = new ArrayList<Kuliner>();



    public KulinerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kuliner, container, false);
        rvKuliner = (RecyclerView)view.findViewById(R.id.rv_kuliner);
        tvSapa = (TextView)view.findViewById(R.id.tv_sapa);
        if(counter < 1)
            list.addAll(getListKuliner());
        counter++;
        showRecyclerList();


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        buttonAnimation(tvSapa);

    }

    public ArrayList<Kuliner> getListKuliner(){
        String[] dataDest = getResources().getStringArray(R.array.data_nama_kuliner);
        String[] dataDesc = getResources().getStringArray(R.array.data_desc_kuliner);
        String[] dataRating = getResources().getStringArray(R.array.data_rating_kuliner);
        String[] dataPhoto = getResources().getStringArray(R.array.data_photo_kuliner);
        String[] dataLocation = getResources().getStringArray(R.array.data_loc_kuliner);
        String[] dataAlamat = getResources().getStringArray(R.array.data_alamat_kuliner);



        ArrayList<Kuliner> listKuliner = new ArrayList<>();
        for(int i = 0; i< dataDest.length; i++){
            Kuliner kuliner = new Kuliner();
            kuliner.setPhoto(dataPhoto[i]);
            kuliner.setDestinasi(dataDest[i]);
            kuliner.setDescription(dataDesc[i]);
            kuliner.setRating(dataRating[i]);
            kuliner.setLocation(dataLocation[i]);
            kuliner.setAlamat(dataAlamat[i]);

            list.add(kuliner);
        }
        return listKuliner;
    }

    private void showRecyclerList(){
        rvKuliner.setLayoutManager(new LinearLayoutManager(getContext()));
        ListKulinerAdapter listHeroAdapter = new ListKulinerAdapter(list);
        rvKuliner.setAdapter(listHeroAdapter);

        listHeroAdapter.setOnItemClickCallback(new ListKulinerAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Kuliner data) {
                showSelectedDest(data);
            }
        });
    }

    private void showSelectedDest(Kuliner kuliner){
        Intent intent = new Intent(getContext(), KulinerDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(KulinerDetailActivity.DEST_NAME, kuliner.getDestinasi());
        bundle.putString(KulinerDetailActivity.DEST_PHOTO, kuliner.getPhoto());
        bundle.putString(KulinerDetailActivity.DEST_DESC, kuliner.getDescription());
        bundle.putString(KulinerDetailActivity.DEST_RATING, kuliner.getRating());
        bundle.putString(KulinerDetailActivity.DEST_LOC, kuliner.getLocation());
        bundle.putString(KulinerDetailActivity.DEST_ALAMAT, kuliner.getAlamat());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void buttonAnimation(View v){
        Animation scale = AnimationUtils.loadAnimation(getContext(), R.anim.scale);
        v.startAnimation(scale);
    }

}
