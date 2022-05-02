<?php
include "dbcon.php";
$sql="Select *from users";
$userList=array();
$res=mysqli_query($con,$sql);
while($row=mysqli_fetch_assoc($res)){
    $userList['data'][]=$row;
}
echo json_encode($userList);

?>