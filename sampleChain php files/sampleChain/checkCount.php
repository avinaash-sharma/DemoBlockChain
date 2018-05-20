<?php
	include 'dbConnection.php';
	$query=$_REQUEST["query"];
	echo $query;
	$result=mysqli_query($con,"query");
	
	$rowcount=mysqli_num_rows($result);
	echo $rowcount;
	?>