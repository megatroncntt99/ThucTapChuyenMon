package com.example.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {

@SerializedName("IdCategory")
@Expose
private String idCategory;
@SerializedName("IdTheme")
@Expose
private String idTheme;
@SerializedName("NameCategory")
@Expose
private String nameCategory;

@SerializedName("ImgCategory")
@Expose
private String imgCategory;





public String getIdCategory() {
return idCategory;
}

public void setIdCategory(String idCategory) {
this.idCategory = idCategory;
}

public String getIdTheme() {
return idTheme;
}

public void setIdTheme(String idTheme) {
this.idTheme = idTheme;
}

public String getNameCategory() {
return nameCategory;
}

public void setNameCategory(String nameCategory) {
this.nameCategory = nameCategory;
}

public String getImgCategory() {
return imgCategory;
}

public void setImgCategory(String imgCategory) {
this.imgCategory = imgCategory;
}



}
