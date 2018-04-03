<?php
	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "survey";
	$conn = new mysqli($servername, $username, $password, $dbname);
	if ($conn->connect_error) {
    	die("Connection failed: " . $conn->connect_error);
	} 
		
	$username=$_REQUEST['username'];
	$password=$_REQUEST['password'];

	$sql="select t1.id, username, password, email, phone, address, t2.name from user t1, user_role t2 where t1.r_id=t2.id and username='$username' and password='$password'";
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
