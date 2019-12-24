package com.kharismarizqii.membandung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kharismarizqii.membandung.Fragment.HotelFragment;

public class HotelDetailActivity extends AppCompatActivity {
    public final static String DEST_NAME = "destname";
    public final static String DEST_PHOTO = "destphoto";
    public final static String DEST_DESC = "destdesc";
    public final static String DEST_RATING = "destrating";
    public final static String DEST_LOC = "destloc";
    public final static String DEST_ALAMAT = "destalamat";
    String name,photo,desc,rating, location, alamat;

    TextView tvName, tvRating, tvDesc, tvLocation;
    ImageView ivPhoto;
    Button btnKunungi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
        Bundle b = new Bundle();
        b = getIntent().getExtras();

        name = b.getString(DEST_NAME);
        photo = b.getString(DEST_PHOTO);
        desc = b.getString(DEST_DESC);
        rating = b.getString(DEST_RATING);
        location = b.getString(DEST_LOC);
        alamat = b.getString(DEST_ALAMAT);

        final String geoUri = "http://maps.google.com/maps?daddr="+location;

        prepare();
        btnKunungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                startActivity(intent);
            }
        });
        set();

    }

    private void prepare(){
        tvName = (TextView)findViewById(R.id.tv_nama_hotel);
        tvRating = (TextView)findViewById(R.id.tv_rating_hotel);
        tvDesc = (TextView)findViewById(R.id.tv_desc_hotel);
        ivPhoto = (ImageView)findViewById(R.id.iv_photo_hotel);
        btnKunungi = (Button)findViewById(R.id.kunjungihotel);
        tvLocation = (TextView)findViewById(R.id.tv_alamat);
    }

    private void set(){
        tvName.setText(name);
        tvDesc.setText(desc);
        tvRating.setText(rating);
        tvLocation.setText(alamat);
        tvDesc.setMovementMethod(new ScrollingMovementMethod());

        Glide.with(this)
                .load(photo)
                .apply(new RequestOptions().centerCrop())
                .into(ivPhoto);
    }
}
