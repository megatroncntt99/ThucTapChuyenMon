package com.example.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Advertisement implements Serializable {

    @SerializedName("IdAD")
    @Expose
    private String idAD;
    @SerializedName("ImgAD")
    @Expose
    private String imgAD;
    @SerializedName("ContentAD")
    @Expose
    private String contentAD;
    @SerializedName("IdSong")
    @Expose
    private String idSong;
    @SerializedName("NameSong")
    @Expose
    private String nameSong;
    @SerializedName("ImgSong")
    @Expose
    private String imgSong;
    @SerializedName("LinkSong")
    @Expose
    private String linkSong;

    public String getIdAD() {
        return idAD;
    }

    public void setIdAD(String idAD) {
        this.idAD = idAD;
    }

    public String getImgAD() {
        return imgAD;
    }

    public void setImgAD(String imgAD) {
        this.imgAD = imgAD;
    }

    public String getContentAD() {
        return contentAD;
    }

    public void setContentAD(String contentAD) {
        this.contentAD = contentAD;
    }

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

    public String getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(String linkSong) {
        this.linkSong = linkSong;
    }
}