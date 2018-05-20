<?php
	include 'dbConnection.php';
	$value=$_REQUEST["value"];
	$result=mysqli_query($con,"Select * from block_chain");
	$rowcount=mysqli_num_rows($result);
	echo $rowcount;
	if($rowcount > 0)
	{

		$result=mysqli_query($con,"Select post_hash from block_chain order by time_stamp DESC LIMIT 1");
		$row = $result -> fetch_array();
		$previous_hash=$row[0];
		echo $previous_hash;
		$current=md5("$value.$previous_hash");
		$timeing=mysqli_query($con,"select time_stamp from temp_table order by Serial_no DESC LIMIT 1");
		$row2=$timeing -> fetch_array();
		$time_stamp=$row2[0];
		mysqli_query($con,"Insert into block_chain values('$previous_hash','$value','$current','$time_stamp')");
		echo "Successsssss";
	}
	else
	{
		
		$result1=mysqli_query($con,"SELECT * FROM genisis_table Limit 1");
		$row = $result1 -> fetch_array();
		$genisi=$row[0];
		var_dump($genisi);
		
		$current=md5($genisi.$value);
		$timeing=mysqli_query($con,"select time_stamp from temp_table order by Serial_no DESC LIMIT 1");
		$row2=$timeing -> fetch_array();
		$time_stamp=$row2[0];
		mysqli_query($con,"Insert into block_chain values('$genisi','$value','$current','$time_stamp')");
	}
	echo "success..";
?>