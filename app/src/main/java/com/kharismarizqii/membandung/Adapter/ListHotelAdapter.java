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
import com.kharismarizqii.membandung.Model.Hotel;
import com.kharismarizqii.membandung.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ListHotelAdapter extends RecyclerView.Adapter<ListHotelAdapter.ListViewHolder> {
    private ArrayList<Hotel> listHotel;
    Context context;

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public Context getContext() {
        return context;
    }

    public ListHotelAdapter(Context context){
        this.context = context;
        listHotel = new ArrayList<>();
    }

    public ArrayList<Hotel> getListHotel(){
        return listHotel;
    }

    public void setListHotel(ArrayList<Hotel> listHotel) {
        this.listHotel = listHotel;
    }

    public ListHotelAdapter(ArrayList<Hotel> list){
        this.listHotel = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_hotel, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Hotel hotel = listHotel.get(position);

        Glide.with(holder.itemView.getContext())
                .load(hotel.getPhoto())
                .apply(new RequestOptions().centerCrop())
                .into(holder.ivDest);

        holder.tvDest.setText(hotel.getDestinasi());
        holder.tvDesc.setText(hotel.getDescription());
        holder.tvRating.setText(hotel.getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listHotel.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHotel.size();
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
        void onItemClicked (Hotel data);
    }
}
