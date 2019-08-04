package com.sam.submission2;

import android.os.Parcel;
import android.os.Parcelable;

public class Movies implements Parcelable {
    private String title;
    private int picture;
    private String desc;
    private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.picture);
        dest.writeString(this.desc);
        dest.writeString(this.date);
    }

    public Movies() {
    }

    protected Movies(Parcel in) {
        this.title = in.readString();
        this.picture = in.readInt();
        this.desc = in.readString();
        this.date = in.readString();
    }

    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
