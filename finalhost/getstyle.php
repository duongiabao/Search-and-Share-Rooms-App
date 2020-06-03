<?php
    include "connect.php";

    $query= "SELECT * FROM style";

    $data= mysqli_query($conn,$query);
    $mangstyle = array();
    while ( $row= mysqli_fetch_assoc($data)) {
        array_push($mangstyle, new style(
            $row['id'],
            $row['stylehome'],
            $row['styleimg']));
    }
    echo json_encode($mangstyle);
    class style{
    	function style($id, $stylehome, $styleimg){
    		$this->Id = $id;
    		$this->Stylehome = $stylehome;
            $this->Styleimg = $styleimg;


    	}
    }

   








?>