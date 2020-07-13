<?php
    require "connectServer.php";

    class TopSong{

        function TopSong($id,$name,$img,$singer,$link,$like){
            $this->IdSong=$id;
           
            $this->NameSong=$name;
            $this->ImgSong=$img;
            $this->Singer=$singer;
            $this->LinkSong=$link;
            $this->LikeSong=$like;
        }
    }
    
    
    if(isset($_POST['IdRankSong'])){

        $idRankSong=$_POST['IdRankSong'];
        $queryTopSong="SELECT * FROM `song` WHERE song.IdRankSong='$idRankSong' ORDER BY song.rank ASC";
    }
    

    $arrayTopSong=array();
    $dataRankSong=mysqli_query($con,$queryTopSong);

    while($row=mysqli_fetch_assoc($dataRankSong)){
        array_push($arrayTopSong,new TopSong( $row['idSong'],
        $row['nameSong'],
        $row['imgSong'],
        $row['singer'],
        $row['linkSong'],
        $row['likeSong']));
    
    
    }
    echo json_encode($arrayTopSong); 

    

  

?>