package com.example.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KeyHot {

@SerializedName("IdKeyHot")
@Expose
private String idKeyHot;
@SerializedName("NameKeyHot")
@Expose
private String nameKeyHot;

public String getIdKeyHot() {
return idKeyHot;
}

public void setIdKeyHot(String idKeyHot) {
this.idKeyHot = idKeyHot;
}

public String getNameKeyHot() {
return nameKeyHot;
}

public void setNameKeyHot(String nameKeyHot) {
this.nameKeyHot = nameKeyHot;
}

}