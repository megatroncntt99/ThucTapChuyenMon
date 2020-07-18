package com.example.Service;


import com.example.Model.Advertisement;
import com.example.Model.Album;
import com.example.Model.Category;
import com.example.Model.KeyHot;
import com.example.Model.MVMusic;
import com.example.Model.Playlist;
import com.example.Model.RankSong;
import com.example.Model.Search;
import com.example.Model.Song;
import com.example.Model.Theme;
import com.example.Model.ThemeCategory;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface DataService {

    @GET("getDataAdvertisement.php")
    Call<ArrayList<Advertisement>> GetDataAdvertisement();

    @GET("getDataPlaylists.php")
    Call<ArrayList<Playlist>> GetDataPlaylists();

    @GET("getDataTheme&category.php")
    Call<ThemeCategory> GetDataTheme_category();


    @FormUrlEncoded
    @POST("getDataMVMusic.php")
    Call<ArrayList<MVMusic>> GetDataMVMusic(@Field("Page") String page);

    @GET("getDataAlbum.php")
    Call<ArrayList<Album>> GetDataAlbum();


    @GET("getDataSongLike.php")
    Call<ArrayList<Song>> GetDataSongLike();

    @FormUrlEncoded
    @POST("getDataSong.php")
    Call<ArrayList<Song>> GetDataSongAdvertisement(@Field("IdAD") String idAD);

    @FormUrlEncoded
    @POST("getDataSong.php")
    Call<ArrayList<Song>> GetDataSongPlaylist(@Field("IdPlaylist") String idPlaylist);

    @GET("getAllDataPlaylists.php")
    Call<ArrayList<Playlist>> GetAllDataPlaylists();

    @FormUrlEncoded
    @POST("getDataSong.php")
    Call<ArrayList<Song>> GetDataSongCategory(@Field("IdCategory") String idCategory);


    @GET("getDataTheme.php")
    Call<ArrayList<Theme>> GetDataTheme();

    @FormUrlEncoded
    @POST("getDataCategoryOfTheme.php")
    Call<ArrayList<Category>> GetDataCategoryOfTheme(@Field("IdTheme") String idTheme);

    @GET("getAllDataAlbum.php")
    Call<ArrayList<Album>> GetAllDataAlbum();

    @FormUrlEncoded
    @POST("getDataSong.php")
    Call<ArrayList<Song>> GetDataSongAlbum(@Field("IdAlbum") String idAlbum);


    @FormUrlEncoded
    @POST("setUpdateLikes.php")
    Call<String> SetUpdateLikeSong(@Field("IdSong") String id,@Field("LikeSong") int like);


    @FormUrlEncoded
    @POST("setUpdateLikes.php")
    Call<String> SetUpdateLikePlaylist(@Field("IdPlaylist") String id,@Field("LikePlaylist") int likeSong);


    @FormUrlEncoded
    @POST("setUpdateLikes.php")
    Call<String> SetUpdateLikeCategory(@Field("IdCategory") String id,@Field("LikeCategory") int like);


    @FormUrlEncoded
    @POST("setUpdateLikes.php")
    Call<String> SetUpdateLikeAlbum(@Field("IdAlbum") String id,@Field("LikeAlbum") int like);

    @FormUrlEncoded
    @POST("setUpdateLikes.php")
    Call<String> SetUpdateLikeMVMusic(@Field("IdMV") String id,@Field("LikeMVMusic") int like);

    @GET("getDataRankSong.php")
    Call<ArrayList<RankSong>> GetDataRankSong();

    @FormUrlEncoded
    @POST("getDataTopSong.php")
    Call<ArrayList<Song>> GetDataTopSong(@Field("IdRankSong") String idRankSong);

    @FormUrlEncoded
    @POST("getDataSearch.php")
    Call<Search> GetDataSearchSong(@Field("KeySearch") String keySearch);


    @GET ("getData6Theme.php")
    Call<ArrayList<Theme>> GetData6Theme();

    @FormUrlEncoded
    @POST("getAllDataSongLike.php")
    Call<ArrayList<Song>> GetAllDataSongLike(@Field("Page") String page);

    @GET ("getDataKeyHot.php")
    Call<ArrayList<KeyHot>> GetDataKeyHot();

}
