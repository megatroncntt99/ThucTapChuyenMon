<?php
    require "connectServer.php";
    $query="SELECT advertisement.idAdvertisement,advertisement.imgAdvertisement,
    advertisement.Content,advertisement.idSong,song.nameSong,song.imgSong ,song.linkSong
    FROM `advertisement` ,`song` 
    WHERE advertisement.idSong=song.idSong 
    order by rand(".date("Ymd").") limit 6 ";
    $data=mysqli_query($con,$query);

    class Advertisement {
        function Advertisement($idAD,$imgAD,$contentAD,$idSong,$nameSong,$imgSong,$link){
            $this->IdAD=$idAD;
            $this->ImgAD=$imgAD;
            $this->ContentAD=$contentAD;
            $this->IdSong=$idSong;
            $this->NameSong=$nameSong;
            $this->ImgSong=$imgSong;
            $this->LinkSong=$link;

        }
    }

    $arrayAdvertisement =array();

    while($row=mysqli_fetch_assoc($data)){

        array_push($arrayAdvertisement,new Advertisement(
        $row['idAdvertisement'],
        $row['imgAdvertisement'],
        $row['Content'],
        $row['idSong'],
        $row['nameSong'],
        $row['imgSong'],
        $row['linkSong']));
    }

    echo json_encode($arrayAdvertisement);
    
?>