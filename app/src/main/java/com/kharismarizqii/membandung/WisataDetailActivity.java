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
import com.kharismarizqii.membandung.Fragment.WisataFragment;

public class WisataDetailActivity extends AppCompatActivity {
    public final static String DEST_NAME = "destname";
    public final static String DEST_PHOTO = "destphoto";
    public final static String DEST_DESC = "destdesc";
    public final static String DEST_RATING = "destrating";
    public final static String DEST_LOC = "destloc";
    public final static String DEST_ALAMAT = "destalamat";
    String name,photo,desc,rating, location, alamat;

    TextView tvName, tvRating, tvDesc, tvAlamat;
    ImageView ivPhoto;
    Button btnKunungi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata_detail);
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
        tvName = (TextView)findViewById(R.id.tv_nama_wisata);
        tvRating = (TextView)findViewById(R.id.tv_rating_wisata);
        tvDesc = (TextView)findViewById(R.id.tv_desc_wisata);
        ivPhoto = (ImageView)findViewById(R.id.iv_photo_wisata);
        btnKunungi = (Button)findViewById(R.id.kunjungiwisata);
        tvAlamat = (TextView)findViewById(R.id.tv_alamat);
    }

    private void set(){
        tvName.setText(name);
        tvDesc.setText(desc);
        tvRating.setText(rating);
        tvAlamat.setText(alamat);
        tvDesc.setMovementMethod(new ScrollingMovementMethod());

        Glide.with(this)
                .load(photo)
                .apply(new RequestOptions().centerCrop())
                .into(ivPhoto);
    }
}
