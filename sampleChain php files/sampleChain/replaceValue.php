<?php

	include 'dbConnection.php';
	$query=$_REQUEST["query"];
	$result=mysqli_query($con,$query);
	echo "success..";

?>