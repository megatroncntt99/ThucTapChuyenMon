<?php
    require "connectServer.php";
   
   if(isset($_POST['Page'])){
    $page=$_POST['Page'];
    $space=10;
    $limit=($page-1)*$space;

    $query="SELECT * FROM `song` WHERE $page<=3 ORDER BY song.likeSong DESC LiMIT  $limit,$space";
   
}
  


    $data=mysqli_query($con,$query);

    class SongLike{
        function SongLike($id,$name,$img,$singer,$link,$like){
            $this->IdSong=$id;
            $this->NameSong=$name;
            $this->ImgSong=$img;
            $this->Singer=$singer;
            $this->LinkSong=$link;
            $this->LikeSong=$like;
        }
    }
    $arraySongLike=array();

    while($row=mysqli_fetch_assoc($data)){
        array_push($arraySongLike,new SongLike( $row['idSong'],
        $row['nameSong'],
        $row['imgSong'],
        $row['singer'],
        $row['linkSong'],
        $row['likeSong']));
    
    
    }
    echo json_encode($arraySongLike); 
?>