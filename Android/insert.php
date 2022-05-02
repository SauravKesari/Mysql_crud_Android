<?php
include 'dbcon.php';
 if(isset($_REQUEST['username'],$_REQUEST['usergender'],$_REQUEST['userpass'],$_REQUEST['useremail'])){
    $res=array();
    $name=$_REQUEST['username'] ;
    $pass=$_REQUEST['userpass'] ;
    $email=$_REQUEST['useremail'] ;
    $gender=$_REQUEST['usergender'] ;
    $sql="Insert into users values(null,'$name','$email','$pass','$gender')";
    if(mysqli_query($con,$sql)){
        $res["msg"]="Successfully inserted!";
        $res["status"]=1;
    }else{
        $res["msg"]="Insertion Failed!";
        $res["status"]=0;
    }
    echo json_encode($res);

 }
 else{
     echo "Database fail";
 }
?>