<?php
include 'dbConnection.php';
$result=mysqli_query($con,"SELECT * FROM genisis_table Limit 1");
$row = $result -> fetch_array();
$new=$row[0];
 
echo $new;
?>