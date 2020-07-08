<?php
    require "connectServer.php";

    $query="select Distinct * from theme order by rand(". date("Ymd") . ") limit 4";
    $dataTheme=mysqli_query($con,$query);

    $query="select Distinct * from category order by rand(". date("Ymd") . ") limit 4";
    $dataCategory=mysqli_query($con,$query);

    class Theme{
        function Theme($id,$name,$img){
              $this->IdTheme=$id;
              $this->NameTheme=$name; 
              $this->ImgTheme=$img;   
        }
    }
    class Category{
        function Category($idC,$idT,$name,$img){
            $this->IdCategory=$idC;
            $this->IdTheme=$idT;
            $this->NameCategory=$name; 
            $this->ImgCategory=$img;   
        }
    }
    $arrayTheme=array();
    $arrayCategory=array();

    while($row=mysqli_fetch_assoc( $dataTheme)){

        array_push($arrayTheme, new Theme($row['idTheme'],$row['nameTheme'],$row['imgTheme']));
    }

    while($row=mysqli_fetch_assoc($dataCategory)){
       array_push($arrayCategory,new Category($row['idCategory'],$row['idTheme'],$row['nameCategory'],$row['imgCategory']));
    }

    $arrayTheme_Category=array('Theme'=>$arrayTheme,'Category'=>$arrayCategory);


    echo json_encode($arrayTheme_Category);



?>