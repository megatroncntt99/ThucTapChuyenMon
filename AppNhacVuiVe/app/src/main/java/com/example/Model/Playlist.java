package com.example.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist  implements Serializable {

    @SerializedName("IdPlayList")
    @Expose
    private String idPlayList;
    @SerializedName("NamePlayList")
    @Expose
    private String namePlayList;
    @SerializedName("ImgPlayList")
    @Expose
    private String imgPlayList;
    @SerializedName("IconPlayList")
    @Expose
    private String iconPlayList;

    public Playlist(String idPlayList, String namePlayList, String imgPlayList, String iconPlayList) {
        this.idPlayList = idPlayList;
        this.namePlayList = namePlayList;
        this.imgPlayList = imgPlayList;
        this.iconPlayList = iconPlayList;
    }

    public String getIdPlayList() {
        return idPlayList;
    }

    public void setIdPlayList(String idPlayList) {
        this.idPlayList = idPlayList;
    }

    public String getNamePlayList() {
        return namePlayList;
    }

    public void setNamePlayList(String namePlayList) {
        this.namePlayList = namePlayList;
    }

    public String getImgPlayList() {
        return imgPlayList;
    }

    public void setImgPlayList(String imgPlayList) {
        this.imgPlayList = imgPlayList;
    }

    public String getIconPlayList() {
        return iconPlayList;
    }

    public void setIconPlayList(String iconPlayList) {
        this.iconPlayList = iconPlayList;
    }

}