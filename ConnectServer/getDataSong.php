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


    if(isset($_POST['IdCategory'])){

        $idCategory=$_POST['IdCategory'];
        $querySong="SELECT * FROM `song` WHERE FIND_IN_SET('$idCategory',idCategory)";
        
    }

    
    if(isset($_POST['IdAlbum'])){

        $idAlbum=$_POST['IdAlbum'];
        $querySong="SELECT * FROM `song` WHERE FIND_IN_SET('$idAlbum',idAlbum)";
        
    }


    if(isset($_POST['IdPlaylist'])){

        $idPlaylist=$_POST['IdPlaylist'];
        $querySong="SELECT * FROM `song` WHERE  FIND_IN_SET('$idPlaylist',idPlaylist) order by rand(".date("Ymd").") ";

    }

   
    if( isset($_POST['IdAD'])){

        $idAdvertisement=$_POST['IdAD'];
        $queryAD="SELECT * FROM `advertisement` where advertisement.idAdvertisement='$idAdvertisement'";
        $dataAD=mysqli_query($con,$queryAD);
        $rowAD=mysqli_fetch_assoc($dataAD);
        $idSong=$rowAD['idSong'];
        $querySong="SELECT * FROM `song` WHERE song.idSong='$idSong'";

    }

    $dataSong=mysqli_query($con,$querySong);
    $arraySong =array();
    while($rowS=mysqli_fetch_assoc($dataSong)){

        array_push($arraySong,new Song($rowS['idSong'],
        $rowS['nameSong'],
        $rowS['imgSong'],
        $rowS['singer'],
        $rowS['linkSong'],
        $rowS['likeSong']));
       
    }

    echo json_encode($arraySong);

?>