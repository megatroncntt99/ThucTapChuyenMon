<?php
    require "connectServer.php";
    $query="select  * from playlist ";

    $data=mysqli_query($con,$query);

    class PlayList{
        function Playlist($id,$name,$img,$icon){
            $this->IdPlayList=$id;
            $this->NamePlayList=$name;
            $this->ImgPlayList=$img;
            $this->IconPlayList=$icon;
    
        }
    }
    $arrayPlaylists=array();

    while($row=mysqli_fetch_assoc($data)){
        array_push($arrayPlaylists,new Playlist( $row['idPlaylist'],
        $row['namePlayList'],
        $row['imgPlayList'],
        $row['iconPlayList']));
    
    
    }


    echo json_encode($arrayPlaylists);


    
?>