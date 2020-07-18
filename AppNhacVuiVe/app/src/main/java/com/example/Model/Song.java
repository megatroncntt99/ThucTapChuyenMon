package com.example.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Comparator;

public class Song implements Parcelable {

    @SerializedName("IdSong")
    @Expose
    private String idSong;
    @SerializedName("NameSong")
    @Expose
    private String nameSong;
    @SerializedName("ImgSong")
    @Expose
    private String imgSong;
    @SerializedName("Singer")
    @Expose
    private String singer;
    @SerializedName("LinkSong")
    @Expose
    private String linkSong;

    public Song(String idSong, String nameSong, String imgSong, String singer, String linkSong, String likeSong) {
        this.idSong = idSong;
        this.nameSong = nameSong;
        this.imgSong = imgSong;
        this.singer = singer;
        this.linkSong = linkSong;
        this.likeSong = likeSong;
    }

    @SerializedName("LikeSong")
    @Expose

    private String likeSong;

    protected Song(Parcel in) {
        idSong = in.readString();
        nameSong = in.readString();
        imgSong = in.readString();
        singer = in.readString();
        linkSong = in.readString();
        likeSong = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };


    public String getIdSong() {
        return idSong;
    }

    public void setIdSong(String idSong) {
        this.idSong = idSong;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getImgSong() {
        return imgSong;
    }

    public void setImgSong(String imgSong) {
        this.imgSong = imgSong;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(String linkSong) {
        this.linkSong = linkSong;
    }

    public String getLikeSong() {
        return likeSong;
    }

    public void setLikeSong(String likeSong) {
        this.likeSong = likeSong;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idSong);
        parcel.writeString(nameSong);
        parcel.writeString(imgSong);
        parcel.writeString(singer);
        parcel.writeString(linkSong);
        parcel.writeString(likeSong);
    }
}