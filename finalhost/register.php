<?php
    
    require_once 'connect.php';
    $name=$_POST['name'];
    $password =$_POST['password'];
    $email= $_POST['email'];
    $photo= 'https://appandroidhome.000webhostapp.com/finalhost/profile_image/add.png';
    
    $password = password_hash($password, PASSWORD_DEFAULT);
    
    $sql = "INSERT INTO users_table VALUES (null,'$name', '$email','$password','$photo')";
    
    if(mysqli_query($conn,$sql)){
        echo "success";
        
    }else{
        echo "error";
    }


?>