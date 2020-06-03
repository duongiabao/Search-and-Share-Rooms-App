<?php
	include "connect.php";
	$arrloction =array();
	$query = " SELECT * FROM location ORDER BY ID DESC LIMIT 6";

	$data = mysqli_query($conn,$query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arrloction, new Location(
			$row['id'],
			$row['location']));
	}
	echo json_encode($arrloction);
	class Location{
		function Location($id,$location){
			$this-> Id =$id;
			$this-> Location=$location;
		}
	}
?>