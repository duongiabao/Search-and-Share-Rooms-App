<?php
    include "connect.php";
    $query = " SELECT * FROM location";
    $data = mysqli_query($conn,$query);
    
    class location{
        function location($id,$location){
            $this->Id = $id;
            $this->Location = $location;
        }
    }


    $manglocation = array();
    while ( $row= mysqli_fetch_assoc($data)) {
        # code...
        array_push($manglocation, new location(
            $row['id'],$row['location']));
    }
    echo json_encode($manglocation);
    
?>