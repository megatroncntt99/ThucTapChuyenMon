<?php

    require "connectServer.php";
   
    
   //Update Like  in 'song'
    if(  isset($_POST["IdSong"]) && isset( $_POST["LikeSong"])){

        $idSong=$_POST["IdSong"];
        $likeSong=$_POST["LikeSong"];

        $querySong="SELECT * FROM `song` WHERE song.idSong='$idSong'";

        $dataSong=mysqli_query($con,$querySong);
        $row=mysqli_fetch_assoc($dataSong);
        $sumLike= $row['likeSong'];
        $sumLike=$sumLike+$likeSong;
        
        $queryUpdateLike="UPDATE `song` SET likeSong='$sumLike' where song.idSong='$idSong'";
        
    }

    

    //Update Like  in 'playlist'
    if(  isset($_POST["IdPlaylist"]) && isset( $_POST["LikePlaylist"])){

        $idPlaylist=$_POST["IdPlaylist"];
        $likePlaylist=$_POST["LikePlaylist"];

        $queryPlaylist="SELECT * FROM `playlist` WHERE playlist.idPlaylist='$idPlaylist'";

        $dataPlaylist=mysqli_query($con,$queryPlaylist);
        $row=mysqli_fetch_assoc($dataPlaylist);

        $sumLike= $row['likePlaylist'];
        $sumLike=$sumLike+$likePlaylist;
        $queryUpdateLike="UPDATE `playlist` SET likePlaylist='$sumLike' where playlist.idPlaylist='$idPlaylist' ";
        
    }



    //Update Like in 'category'
    if(  isset($_POST["IdCategory"]) && isset( $_POST["LikeCategory"])){

        $idCategory=$_POST["IdCategory"];
        $likeCategory=$_POST["LikeCategory"];

        $queryCategory="SELECT * FROM `category` WHERE category.idCategory='$idCategory'";

        $dataCategory=mysqli_query($con,$queryCategory);
        $row=mysqli_fetch_assoc($dataCategory);

        $sumLike= $row['likeCategory'];
        $sumLike=$sumLike+$likeCategory;

        $queryUpdateLike="UPDATE `category` SET likeCategory='$sumLike' where category.idCategory='$idCategory'";
        
    }

   
    //Update Like in 'album'
    if(  isset($_POST["IdAlbum"]) && isset( $_POST["LikeAlbum"])){

        $idAlbum=$_POST["IdAlbum"];
        $likeAlbum=$_POST["LikeAlbum"];

        $queryAlbum="SELECT * FROM `album` WHERE album.idAlbum='$idAlbum'";

        $dataAlbum=mysqli_query($con,$queryAlbum);
        $row=mysqli_fetch_assoc($dataAlbum);

        $sumLike= $row['likeAlbum'];
        $sumLike=$sumLike+$likeAlbum;

        $queryUpdateLike="UPDATE `album` SET likeAlbum='$sumLike' where  album.idAlbum='$idAlbum'";
        
    }


    

    
     //Update Like in 'mv_music'
    if(  isset($_POST["IdMV"]) && isset( $_POST["LikeMVMusic"])){

        $idMV=$_POST["IdMV"];
        $likeMVMusic=$_POST["LikeMVMusic"];

        $queryMV="SELECT * FROM `mv_music` WHERE mv_music.idMV='$idMV'";

        $dataMV=mysqli_query($con,$queryMV);
        $row=mysqli_fetch_assoc($dataMV);

        $sumLike= $row['likeMVMusic'];
        $sumLike=$sumLike+$likeMVMusic;

        $queryUpdateLike="UPDATE `mv_music`SET likeMVMusic='$sumLike' where   mv_music.idMV='$idMV'";
        
    }


    
    $dataUpdate=mysqli_query($con,$queryUpdateLike);
    if($dataUpdate){
        echo 'success';
    }
    else echo 'fail';
?>