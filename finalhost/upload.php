<?php
    include "connect.php";
$id=$_POST['id'];
$home_name=$_POST['home_name'];
$home_price=$_POST['home_price'];
$home_person=$_POST['home_person'];
$home_phone=$_POST['home_phone'];
$home_describe=$_POST['home_describe'];
$locate_id=$_POST['locate_id'];
$style_id= $_POST['style_id'];
$img=$_POST['home_img'];
$check = $_POST['check'];
$target_dir = "upload/images";
if(!file_exists($target_dir)){
    mkdir($target_dir, 0777, true);
}

$target_dir = $target_dir ."/". rand() ."_". time(). "jpeg";
$home_img = "https://appandroidhome.000webhostapp.com/finalhost/". $target_dir;

if($check == 'false'){
$query= "UPDATE home SET home_name= '$home_name', home_price= '$home_price', home_person='$home_person', home_phone='$home_phone', home_describe='$home_describe', locate_id='$locate_id', style_id='$style_id', home_img='$img' WHERE id='$id' ";
}else{
    $query= "UPDATE home SET home_name= '$home_name', home_price= '$home_price', home_person='$home_person', home_phone='$home_phone', home_describe='$home_describe', locate_id='$locate_id', style_id='$style_id', home_img='$home_img' WHERE id='$id' ";
}

if(mysqli_query($conn,$query)){
    //thanh cong
    if(file_put_contents($target_dir, base64_decode($img))){
        echo "sucess";

    }else{
    //loi
        echo "error";
    }
}




?>