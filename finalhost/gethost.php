<?php
    include "connect.php";
    //$user_id=$_Post['user_id'];

    $query = " SELECT * FROM home "; //where $user_id = user_id(database)
    $data = mysqli_query($conn,$query);
    
    class home{
        function home($id,$home_name,$home_price,$home_person,$home_phone,$home_describe,$locate_id,$home_img,$style_id,$user_id){
            $this->Id = $id;
            $this->Home_name = $home_name;
            $this->Home_price=$home_price;
            $this->Home_person=$home_person;
            $this->Home_phone=$home_phone;
            $this->Home_describe=$home_describe;
            $this->Locate_id=$locate_id;
            $this->Home_img= $home_img;
            $this->Style_id= $style_id;
            $this->User_id= $user_id;

            
        }
    }
    

    
    $manghome = array();
    while ( $row= mysqli_fetch_assoc($data)) {
        # code...
        array_push($manghome, new home(
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
    echo json_encode($manghome);

    
?>




<?php
/*
    include "connect.php";
    $query = " SELECT * FROM home";
    $data = mysqli_query($conn,$query);
    
    class home{
        function home($id,$home_name,$home_price,$home_person,$home_phone,$home_describe){
            $this->Id = $id;
            $this->Home_name = $home_name;
            $this->Home_price=$home_price;
            $this->Home_person=$home_person;
            $this->Home_phone=$home_phone;
            $this->Home_describe=$home_describe;

            
        }
    }
    

    
    $manghome = array();
    while ( $row= mysqli_fetch_assoc($data)) {
        # code...
        array_push($manghome, new home(
            $row['id'],
            $row['home_name'],
            $row['home_price'],
            $row['home_person'],
            $row['home_phone'],
            $row['home_describe']));
           
    }
    echo json_encode($manghome);

    */
?>
