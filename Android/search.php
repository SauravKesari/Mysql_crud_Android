<?php
 include 'dbcon.php';
 if(isset($_REQUEST['txtSearchid'])){
     $id=$_REQUEST['txtSearchid'];
     $userList=array();
     $sql="Select *from users where userid=$id";
     $res=mysqli_query($con,$sql);

         while($row=mysqli_fetch_assoc($res)){
            $userList['data'][]=$row;
        }
        echo json_encode($userList);


 }
?>