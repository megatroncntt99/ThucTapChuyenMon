<?php
    require "connectServer.php";
    class Theme{
        function Theme($id,$name,$img){
            $this->IdTheme=$id;
            $this->NameTheme=$name;
            $this->ImgTheme=$img;
        }
    }

    $queryTheme="SELECT * FROM `theme` limit 6";
    $data=mysqli_query($con,$queryTheme);
  
    $arrayTheme =array();
    while($row=mysqli_fetch_assoc($data)){
        array_push($arrayTheme,new Theme($row['idTheme'],
        $row['nameTheme'],
        $row['imgTheme']));
    }

    echo json_encode($arrayTheme);


?>