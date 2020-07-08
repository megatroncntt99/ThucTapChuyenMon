package com.example.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Theme  implements Serializable {

@SerializedName("IdTheme")
@Expose
private String idTheme;
@SerializedName("NameTheme")
@Expose
private String nameTheme;
@SerializedName("ImgTheme")
@Expose
private String imgTheme;

public String getIdTheme() {
return idTheme;
}

public void setIdTheme(String idTheme) {
this.idTheme = idTheme;
}

public String getNameTheme() {
return nameTheme;
}

public void setNameTheme(String nameTheme) {
this.nameTheme = nameTheme;
}

public String getImgTheme() {
return imgTheme;
}

public void setImgTheme(String imgTheme) {
this.imgTheme = imgTheme;
}

}