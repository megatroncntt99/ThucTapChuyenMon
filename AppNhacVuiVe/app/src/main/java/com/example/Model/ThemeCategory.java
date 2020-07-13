package com.example.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThemeCategory {

@SerializedName("Theme")
@Expose
private List<Theme> theme = null;
@SerializedName("Category")
@Expose
private List<Category> category = null;

public List<Theme> getTheme() {
return theme;
}

public void setTheme(List<Theme> theme) {
this.theme = theme;
}

public List<Category> getCategory() {
return category;
}

public void setCategory(List<Category> category) {
this.category = category;
}

}