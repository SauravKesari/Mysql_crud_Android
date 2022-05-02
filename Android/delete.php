<?php
include "dbcon.php";
if(isset($_REQUEST['txtDeletename'])){
    $name=$_REQUEST['txtDeletename'];
    $sql="Delete from users where username='$name'";
    if(mysqli_query($con,$sql)){
        echo "Deleted";
    }
    else{
        echo "Not Deleted";
    }
}
?>