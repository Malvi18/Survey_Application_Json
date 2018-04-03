<?php
	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "student";
	$conn = new mysqli($servername, $username, $password, $dbname);
	if ($conn->connect_error) {
    	die("Connection failed: " . $conn->connect_error);
	} 
		
	$username=$_REQUEST['username'];
	$password=$_REQUEST['password'];

	$sql="select * from adminlogin where a_username='$username' and a_password='$password'";
	$result=$conn->query($sql);
	if($result->num_rows>0)
	{
		while($row = $result->fetch_assoc()) {
			$data['result'][]=$row;        
		}
		echo json_encode($data);
	}else{
		 $data['result']="0 results";
		 echo json_encode($data);
	}
	/*if ($conn->query($sql) === TRUE) {
	    echo "New record created successfully";
	} else {
	    echo "Error: " . $sql . "<br>" . $conn->error;
	}*/

	$conn->close();
	//echo $sql;
?>
