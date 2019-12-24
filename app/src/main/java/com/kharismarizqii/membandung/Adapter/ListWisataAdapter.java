package com.kharismarizqii.membandung.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kharismarizqii.membandung.Model.Wisata;
import com.kharismarizqii.membandung.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ListWisataAdapter extends RecyclerView.Adapter<ListWisataAdapter.ListViewHolder> {
    private ArrayList<Wisata> listWisata;
    Context context;

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public Context getContext() {
        return context;
    }

    public ListWisataAdapter(Context context){
        this.context = context;
        listWisata = new ArrayList<>();
    }

    public ArrayList<Wisata> getListWisata(){
        return listWisata;
    }

    public void setListWisata(ArrayList<Wisata> listWisata) {
        this.listWisata = listWisata;
    }

    public ListWisataAdapter(ArrayList<Wisata> list){
        this.listWisata = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_wisata, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Wisata wisata = listWisata.get(position);

        Glide.with(holder.itemView.getContext())
                .load(wisata.getPhoto())
                .apply(new RequestOptions().centerCrop())
                .into(holder.ivDest);

        holder.tvDest.setText(wisata.getDestinasi());
        holder.tvDesc.setText(wisata.getDescription());
        holder.tvRating.setText(wisata.getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listWisata.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listWisata.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvDest, tvRating, tvDesc;
        ImageView ivDest;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            ivDest = itemView.findViewById(R.id.iv_dest);
            tvDest = itemView.findViewById(R.id.tv_dest);
            tvRating = itemView.findViewById(R.id.tv_rating);
            tvDesc = itemView.findViewById(R.id.tv_desc);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked (Wisata data);
    }
}
