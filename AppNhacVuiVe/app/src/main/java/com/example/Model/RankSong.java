package com.example.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RankSong implements Serializable {

@SerializedName("IdRankSong")
@Expose
private String idRankSong;
@SerializedName("NameRankSong")
@Expose
private String nameRankSong;
@SerializedName("ImgRankSong")
@Expose
private String imgRankSong;
@SerializedName("IconRankSong")
@Expose
private String iconRankSong;

public String getIdRankSong() {
return idRankSong;
}

public void setIdRankSong(String idRankSong) {
this.idRankSong = idRankSong;
}

public String getNameRankSong() {
return nameRankSong;
}

public void setNameRankSong(String nameRankSong) {
this.nameRankSong = nameRankSong;
}

public String getImgRankSong() {
return imgRankSong;
}

public void setImgRankSong(String imgRankSong) {
this.imgRankSong = imgRankSong;
}

public String getIconRankSong() {
return iconRankSong;
}

public void setIconRankSong(String iconRankSong) {
this.iconRankSong = iconRankSong;
}

}