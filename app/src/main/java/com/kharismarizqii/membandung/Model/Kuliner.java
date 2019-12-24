package com.kharismarizqii.membandung.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Kuliner implements Parcelable {
    private String destinasi;
    private String rating;
    private String description;
    private String photo;
    private String location;
    private String alamat;
    public Kuliner(){

    }

    protected Kuliner(Parcel in) {
        destinasi = in.readString();
        rating = in.readString();
        description = in.readString();
        photo = in.readString();
        location = in.readString();
        alamat = in.readString();
    }

    public static final Creator<Kuliner> CREATOR = new Creator<Kuliner>() {
        @Override
        public Kuliner createFromParcel(Parcel in) {
            return new Kuliner(in);
        }

        @Override
        public Kuliner[] newArray(int size) {
            return new Kuliner[size];
        }
    };

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDestinasi() {
        return destinasi;
    }

    public void setDestinasi(String destinasi) {
        this.destinasi = destinasi;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(destinasi);
        dest.writeString(rating);
        dest.writeString(description);
        dest.writeString(photo);
        dest.writeString(location);
        dest.writeString(alamat);
    }
}
