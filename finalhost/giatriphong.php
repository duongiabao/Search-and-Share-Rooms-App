<?php
	include "connect.php";
	$page = $_GET['page'];
	$idP = $_POST['style_id'];
	$space = 5;
	$limit = ($page -1 ) * $space;
	$arrPhong = array();
	$query = "SELECT * FROM home WHERE style_id = $idP LIMIT $limit,$space";
	$data = mysqli_query($conn,$query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arrPhong, new Phong(
			$row['id'],
			$row['home_name'],
			$row['home_price'],
			$row['home_person'],
			$row['home_phone'],
			$row['home_describe'],
			$row['locate_id'],
			$row['home_img'],
			$row['style_id'],
			$row['user_id']
		));
	}
	echo json_encode($arrPhong);
	class Phong{
		function Phong($id,$home_name,$home_price,$home_person,$home_phone,$home_describe,$locate_id,$home_img,$style_id,$user_id){
			$this-> id =$id;
			$this-> home_name =$home_name;
			$this-> home_price =$home_price;
			$this-> home_person =$home_person;
			$this-> home_phone = $home_phone;
			$this-> home_describe = $home_describe;
			$this-> locate_id= $locate_id;
			$this-> home_img= $home_img;
			$this-> style_id= $style_id;
			$this-> user_id= $user_id;
		}
	} 

?>