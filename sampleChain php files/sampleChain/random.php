<?php
include 'dbConnection.php';

$random=rand(10,100);
$p=some
$random=md5($p . $random);
mysqli_query($con,"insert into genisis_table values('$random')");
echo "Genesis Success" 
$result=mysqli_query($con,"SELECT genisis_hash FROM genisis_table");
$arr=mysqli_fetch_all($result,MYSQLI_ASSOC);
echo $arr;
?> 