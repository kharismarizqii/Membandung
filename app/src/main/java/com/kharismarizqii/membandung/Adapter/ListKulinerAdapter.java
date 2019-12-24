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
import com.kharismarizqii.membandung.Model.Kuliner;
import com.kharismarizqii.membandung.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ListKulinerAdapter extends RecyclerView.Adapter<ListKulinerAdapter.ListViewHolder> {
    private ArrayList<Kuliner> listKuliner;
    Context context;

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public Context getContext() {
        return context;
    }

    public ListKulinerAdapter(Context context){
        this.context = context;
        listKuliner = new ArrayList<>();
    }

    public ArrayList<Kuliner> getListKuliner(){
        return listKuliner;
    }

    public void setListKuliner(ArrayList<Kuliner> listKuliner) {
        this.listKuliner = listKuliner;
    }

    public ListKulinerAdapter(ArrayList<Kuliner> list){
        this.listKuliner = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_kuliner, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Kuliner kuliner = listKuliner.get(position);

        Glide.with(holder.itemView.getContext())
                .load(kuliner.getPhoto())
                .apply(new RequestOptions().centerCrop())
                .into(holder.ivDest);

        holder.tvDest.setText(kuliner.getDestinasi());
        holder.tvDesc.setText(kuliner.getDescription());
        holder.tvRating.setText(kuliner.getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listKuliner.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKuliner.size();
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
        void onItemClicked (Kuliner data);
    }
}
