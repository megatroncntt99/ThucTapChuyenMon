<?php
  require "connectServer.php";
  $query="SELECT mv_music.idMV,mv_music.keyMV,mv_music.imgMV,mv_music.imgSinger,mv_music.txtTimeMV, song.nameSong,song.singer
  FROM `mv_music`,`song` 
  WHERE mv_music.idSong=song.idSong 
  order by rand(".date("Ymd").") LIMIT 25";
    $data=mysqli_query($con,$query);

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
    $arrayMV_Music=array();

    while($row=mysqli_fetch_assoc($data)){

        array_push($arrayMV_Music,new MV_Music(
        $row['idMV'],
        $row['keyMV'],
        $row['imgMV'],
        $row['imgSinger'],
        $row['txtTimeMV'],
        $row['nameSong'],
        $row['singer']));
    }

    echo json_encode($arrayMV_Music);

  
?>