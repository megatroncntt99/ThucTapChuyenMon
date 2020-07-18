<?php

     require "connectServer.php";
     class KeyHot{
        function KeyHot($id,$name){
             $this->IdKeyHot=$id;
             $this->NameKeyHot=$name;
             
        }
    }

    $query="SELECT * FROM `keyhot` ";
 
    $data=mysqli_query($con,$query);
 
    
    $arrayKeyHot=array();
 
    while($row=mysqli_fetch_assoc($data)){
     array_push($arrayKeyHot,new KeyHot( $row['idKeyHot'],
     $row['nameKeyHot']));
    }
    echo json_encode($arrayKeyHot);
?>