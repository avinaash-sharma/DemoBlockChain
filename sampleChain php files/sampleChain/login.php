<?php
include 'dbConnection.php';
$name=strip_tags($_REQUEST["name"]);
$password=strip_tags($_REQUEST["password"]);
$result=mysqli_query($con,"select * from data2 where name='$name' and password='$password'");
$arr=mysqli_fetch_assoc($result);
if($arr["name"]==$name)
{
	echo "success";
}
else
{
	echo "invalid";
}
?>