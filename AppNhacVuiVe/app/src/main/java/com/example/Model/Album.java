package com.example.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Album implements Serializable {

@SerializedName("IdAlbum")
@Expose
private String idAlbum;
@SerializedName("NameAlbum")
@Expose
private String nameAlbum;
@SerializedName("SingerAlbum")
@Expose
private String singerAlbum;
@SerializedName("ImgAlbum")
@Expose
private String imgAlbum;

public String getIdAlbum() {
return idAlbum;
}

public void setIdAlbum(String idAlbum) {
this.idAlbum = idAlbum;
}

public String getNameAlbum() {
return nameAlbum;
}

public void setNameAlbum(String nameAlbum) {
this.nameAlbum = nameAlbum;
}

public String getSingerAlbum() {
return singerAlbum;
}

public void setSingerAlbum(String singerAlbum) {
this.singerAlbum = singerAlbum;
}

public String getImgAlbum() {
return imgAlbum;
}

public void setImgAlbum(String imgAlbum) {
this.imgAlbum = imgAlbum;
}

}