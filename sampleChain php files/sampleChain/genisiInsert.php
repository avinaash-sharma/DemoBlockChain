<?php
	include 'dbConnection.php';
	$something="hello World";
	$something=md5($something);
	echo $something;
	if(mysqli_query($con,"insert into genisis_table values('$something')"))
		echo "Genisi Block Ready";
?>