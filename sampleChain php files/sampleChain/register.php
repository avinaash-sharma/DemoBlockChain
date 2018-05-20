<?php
 include 'dbConnection.php';
 $user_id=$_REQUEST["name"];
 $password=$_REQUEST["password"];
 //$security_question=$_REQUEST["security_question"];
 //$answer=$_REQUEST["answer"];
 $result=mysqli_query($con,"SELECT * FROM data2");
 $rowcount=mysqli_num_rows($result);
 $hash_value=md5($password);
 $id_no=1;
 if( $rowcount > 0)
 {
	 $id_no=$rowcount+1;
	mysqli_query($con,"insert into data2 values('$user_id','$password','$hash_value','$pre_hash','$id_no')");
	mysqli_query($con,"insert into users values('$user_id', 0)");
 }
 else
 {
	 mysqli_query($con,"insert into data2 values('$user_id','$password','$hash_value','$pre_hash','$id_no')");
	 mysqli_query($con,"insert into users values('$user_id', 0)");
 }
 
 echo "registor success";
 ?>