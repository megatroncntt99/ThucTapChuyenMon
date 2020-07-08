package com.example.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MVMusic implements Serializable {

    @SerializedName("IdMV")
    @Expose
    private String idMV;
    @SerializedName("KeyMV")
    @Expose
    private String keyMV;
    @SerializedName("ImgSongMV")
    @Expose
    private String imgSongMV;
    @SerializedName("ImgSinger")
    @Expose
    private String imgSinger;
    @SerializedName("TxtTimeMV")
    @Expose
    private String txtTimeMV;
    @SerializedName("NameSong")
    @Expose
    private String nameSong;
    @SerializedName("NameSinger")
    @Expose
    private String nameSinger;

    public String getKeyMV() {
        return keyMV;
    }

    public void setKeyMV(String keyMV) {
        this.keyMV = keyMV;
    }

    public String getIdMV() {
        return idMV;
    }

    public void setIdMV(String idMV) {
        this.idMV = idMV;
    }

    public String getImgSongMV() {
        return imgSongMV;
    }

    public void setImgSongMV(String imgSongMV) {
        this.imgSongMV = imgSongMV;
    }

    public String getImgSinger() {
        return imgSinger;
    }

    public void setImgSinger(String imgSinger) {
        this.imgSinger = imgSinger;
    }

    public String getTxtTimeMV() {
        return txtTimeMV;
    }

    public void setTxtTimeMV(String txtTimeMV) {
        this.txtTimeMV = txtTimeMV;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

}