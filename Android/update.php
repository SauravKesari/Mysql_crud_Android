<?php
include 'dbcon.php';
//if(isset($_REQUEST['txtUpdateName'],$_REQUEST['txtUpdateEmail'],$_REQUEST['txtUpdateGender'],$_REQUEST['txtUpdatePass'],$_REQUEST['txtUpdateid'])){
    $name=$_REQUEST['txtUpdateName'];
    $email=$_REQUEST['txtUpdateEmail'];
    $gender=$_REQUEST['txtUpdateGender'];
    $pass=$_REQUEST['txtUpdatePass'];
    $id=$_REQUEST['txtUpdateid'];
    $sql="update users set username='Mihir' and useremail='mihir@gmail.com' and usergender='Female' and userpass='mihir223' where userid=2";
    if(mysqli_query($con,$sql)){
        echo "Updated";
    }else{
        echo "Failed";
    }

//}

?>