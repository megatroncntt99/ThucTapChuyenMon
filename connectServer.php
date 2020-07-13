<?php
    $hostname="localhost";
    $username="root";
    $pass="";
    $databasename="appmucis";
    $con=mysqli_connect($hostname,$username,$pass,$databasename);
    mysqli_query($con,"SET NAMES 'utf8'");
    
?>