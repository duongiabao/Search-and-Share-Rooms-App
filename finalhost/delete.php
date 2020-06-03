<?php
	include "connect.php";


$id= $_POST['id'];


$query= "DELETE FROM home WHERE id='$id' ";


if(mysqli_query($conn,$query)){
	//thanh cong
		echo "sucess";

	}else{
	//loi
		echo "error";
	}

?>