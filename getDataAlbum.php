<?php
    require "connectServer.php";


    $query="select Distinct *from album order by rand(".date("Ymd").") limit 6 ";

    $data=mysqli_query($con,$query);

   class Album{
       function Album($id,$name,$nameSinger,$img){
            $this->IdAlbum=$id;
            $this->NameAlbum=$name;
            $this->SingerAlbum=$nameSinger;
            $this->ImgAlbum=$img;
       }
   }
   $arrayAlbum=array();

   while($row=mysqli_fetch_assoc($data)){

    array_push($arrayAlbum,new Album( $row['idAlbum'],
    $row['nameAlbum'],
    $row['singerAlbum'],
    $row['imgAlbum']));
   }
   echo json_encode($arrayAlbum);
?>