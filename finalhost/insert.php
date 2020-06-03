<?php
	include "connect.php";
	
	$home_name=$_POST['home_name'];
	$home_price=$_POST['home_price'];
	$home_person=$_POST['home_person'];
	$home_phone=$_POST['home_phone'];
	$home_describe=$_POST['home_describe'];
	$locate_id=$_POST['locate_id'];
	$style_id= $_POST['style_id'];
	$home_img=$_POST['home_img'];
	$user_id= $_POST['user_id'];
	
//	$home_name="l1";
//	$home_price="133556";
//	$home_person="ss6pc";
//	$home_phone="110982";
//	$home_describe="adapojdpoas";
//	$locate_id="1";
//	$home_img="https://infok.vn/images/media/infok.vn-phong-tro-cao-cap_w650.jpg";
	
	$query="INSERT INTO home VALUES(null,'$home_name','$home_price','$home_person','$home_phone','$home_describe','$locate_id','$style_id',
	'$home_img','$user_id')";
	

	if(mysqli_query($conn,$query)){
	//thanh cong
		echo "sucess";

	}else{
	//loi
		echo "error";
	}


?>




<?php
/*
	include "connect.php";
	
	$home_name=$_POST['home_name'];
	$home_price=$_POST['home_price'];
	$home_person=$_POST['home_person'];
	$home_phone=$_POST['home_phone'];
	$home_describe=$_POST['home_describe'];
	//$home_img=$POST['home_img'];
	
//	$home_name="l1";
//	$home_price="133556";
//	$home_person="ss6pc";
//	$home_phone="110982";
//	$home_describe="adapojdpoas";
	
	$query="INSERT INTO home VALUES(null,'$home_name','$home_price','$home_person','$home_phone','$home_describe')";
	

	if(mysqli_query($conn,$query)){
	//thanh cong
		echo "sucess";

	}else{
	//loi
		echo "error";
	}
*/

?>