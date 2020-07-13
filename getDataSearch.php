<?php
    require "connectServer.php";

    class Song {
        function Song($id,$name,$img,$singer,$linkSong,$like){
            $this->IdSong=$id;
            $this->NameSong=$name;
            $this->ImgSong=$img;
            $this->Singer=$singer;
            $this->LinkSong=$linkSong;
            $this->LikeSong=$like;
        
        }
    }
    class PlayList{
        function Playlist($id,$name,$img,$icon){
            $this->IdPlayList=$id;
            $this->NamePlayList=$name;
            $this->ImgPlayList=$img;
            $this->IconPlayList=$icon;
    
        }
    }
    

    class MV_Music {
        function MV_Music($id,$key,$imgmv,$imgsinger,$txtTimeMV,$nameSong,$namesinger){
            $this->IdMV=$id;
            $this->KeyMV=$key;
            $this->ImgSongMV=$imgmv;
            $this->ImgSinger=$imgsinger;
            $this->TxtTimeMV=$txtTimeMV;
            $this->NameSong=$nameSong;
            $this->NameSinger=$namesinger;
        }
    }


    //Create 3 arraySearch
    $arraySong =array();
    $arrayMV_Music=array();
    $arrayPlaylists=array();

    
    
   
   //Search Song 
   
    if(isset($_POST['KeySearch'])){
        $keySearch=$_POST['KeySearch'];
        $querySong="SELECT * from `song` WHERE MATCH(song.nameSong) against ('$keySearch')";

        $dataSong=mysqli_query($con,$querySong);
      
        while($rowS=mysqli_fetch_assoc($dataSong)){
        array_push($arraySong,new Song($rowS['idSong'],
            $rowS['nameSong'],
            $rowS['imgSong'],
            $rowS['singer'],
            $rowS['linkSong'],
            $rowS['likeSong']));
        
        }
        
    }


    //Search Playlist
    

    if(isset($_POST['KeySearch'])){
        $keySearch=$_POST['KeySearch'];
        $queryPlaylist="SELECT * from `playlist` WHERE MATCH(playlist.namePlayList) against ('$keySearch')";

        $dataPlaylist=mysqli_query($con,$queryPlaylist);
        while($row=mysqli_fetch_assoc($dataPlaylist)){
        array_push($arrayPlaylists,new Playlist( $row['idPlaylist'],
            $row['namePlayList'],
            $row['imgPlayList'],
            $row['iconPlayList']));
        }
    }

   
    //Search MV
    if(isset($_POST['KeySearch'])){
        $keySearch=$_POST['KeySearch'];
        $queryMV="SELECT mv_music.idMV,mv_music.keyMV,mv_music.imgMV,mv_music.imgSinger,mv_music.txtTimeMV, song.nameSong,song.singer
        FROM `mv_music`,`song` 
        WHERE mv_music.idSong=song.idSong AND MATCH(song.nameSong) AGAINST('$keySearch')";

        $dataMV=mysqli_query($con,$queryMV);

       
        while($row=mysqli_fetch_assoc($dataMV)){

            array_push($arrayMV_Music,new MV_Music(
            $row['idMV'],
            $row['keyMV'],
            $row['imgMV'],
            $row['imgSinger'],
            $row['txtTimeMV'],
            $row['nameSong'],
            $row['singer']));
        }
    }

    $arraySearch=array('Song'=>$arraySong,'Playlist'=>$arrayPlaylists,'MVMusic'=>$arrayMV_Music);
    echo json_encode($arraySearch);
?>