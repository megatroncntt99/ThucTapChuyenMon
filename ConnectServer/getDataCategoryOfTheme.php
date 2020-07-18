<?php
    require "connectServer.php";

    class Category{
        function Category($idC,$idT,$name,$img){
            $this->IdCategory=$idC;
            $this->IdTheme=$idT;
            $this->NameCategory=$name; 
            $this->ImgCategory=$img;   
        }
    }
    if(isset($_POST['IdTheme'])){

        $idTheme=$_POST['IdTheme'];
        $queryCategory="SELECT * FROM `category` WHERE FIND_IN_SET('$idTheme',idTheme)";
        
    }


    $data=mysqli_query($con,$queryCategory);
    $arrayCategory =array();
    while($row=mysqli_fetch_assoc($data)){
        array_push($arrayCategory,new Category($row['idCategory'],$row['idTheme'],$row['nameCategory'],$row['imgCategory']));
    }
    echo json_encode($arrayCategory);

?>