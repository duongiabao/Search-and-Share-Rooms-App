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
$home_img=$_POST['home_img'];


$query= "UPDATE home SET home_name= '$home_name', home_price= '$home_price', home_person='$home_person', home_phone='$home_phone', home_describe='$home_describe', locate_id='$locate_id', style_id='$style_id', home_img='$home_img' WHERE id='$id' ";

if(mysqli_query($conn,$query)){
	//thanh cong
		echo "sucess";

	}else{
	//loi
		echo "error";
	}




?>