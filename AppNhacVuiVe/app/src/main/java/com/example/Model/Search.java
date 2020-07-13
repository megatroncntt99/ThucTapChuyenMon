package com.example.Model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Search implements Serializable {

@SerializedName("Song")
@Expose
private List<Song> song = null;

@SerializedName("Playlist")
@Expose
private List<Playlist> playlist = null;
@SerializedName("MVMusic")
@Expose
private List<MVMusic> mVMusic = null;

public List<Song> getSong() {
return song;
}

public void setSong(List<Song> song) {
this.song = song;
}

public List<Playlist> getPlaylist() {
return playlist;
}

public void setPlaylist(List<Playlist> playlist) {
this.playlist = playlist;
}

public List<MVMusic> getMVMusic() {
return mVMusic;
}

public void setMVMusic(List<MVMusic> mVMusic) {
this.mVMusic = mVMusic;
}

}