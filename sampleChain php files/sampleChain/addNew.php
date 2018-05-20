<?php
	include 'dbConnection.php';
	$value=$_REQUEST["value"];
	$result=mysqli_query($con,"SELECT * FROM temp_table");
	$rowcount=mysqli_num_rows($result);
	$id_no=1;
	if($rowcount > 0)
	{
		$id_no=$rowcount+1;
		$t=time();
		$t=date("Y-m-d",$t);
		mysqli_query($con,"insert into temp_table values('$id_no','$value','$t')");
	}
	else
	{
		$t=time();
		$t=date("Y-m-d",$t);
		mysqli_query($con,"insert into temp_table values('$id_no','$value','$t')");
	}
	echo "success..";
?>