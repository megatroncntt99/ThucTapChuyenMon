<?php
    require "connectServer.php";

    $query="SELECT * FROM `ranksong` ORDER BY ranksong.IdRankSong";

    $data=mysqli_query($con,$query);


    class RankSong{
        function RankSong($id,$name,$img,$icon){
            $this->IdRankSong=$id;
            $this->NameRankSong=$name;
            $this->ImgRankSong=$img;
            $this->IconRankSong=$icon;
           
        }
    }
    $arrayRankSong=array();
    
    
    while($row=mysqli_fetch_assoc($data)){
        array_push($arrayRankSong,new RankSong( $row['IdRankSong'],
        $row['nameRankSong'],
        $row['imgRankSong'],
        $row['iconRankSong']));
        
    }
    echo json_encode($arrayRankSong);
    
?>