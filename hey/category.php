<?php
	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "survey";
	$conn = new mysqli($servername, $username, $password, $dbname);
	if ($conn->connect_error) {
    	die("Connection failed: " . $conn->connect_error);
	} 

	$flag=$_REQUEST['flag'];
	switch($flag)
	{
		case '1':
			insertCategory($conn);
			break;
		case '2':
			updateCategory($conn);
			break;
		case '3':
			deleteCategory($conn);
			break;
		case '4':
			selectCategory($conn);
		break;
	}

	function insertCategory($conn)
	{
		$category=$_REQUEST['category'];
		$sql="insert into category (name) values ('$category')";
		if ($conn->query($sql) === TRUE) {
	    	echo "New record created successfully";
		} else {
		    echo "Error: " . $sql . "<br>" . $conn->error;
		}
	}
	function updateCategory($conn)
	{
		$id=$_REQUEST['id'];
		$category=$_REQUEST['category'];
		$sql="update category set name='$category' where id=$id";
		if ($conn->query($sql) === TRUE) {
	    	echo "record updated successfully";
		} else {
		    echo "Error: " . $sql . "<br>" . $conn->error;
		}			
	}
	function deleteCategory($conn)
	{
		$id=$_REQUEST['id'];
		/*$sql="delete from cate where c_id=$c_id";*/
		$sql="update category set status='Disable' where id=$id";
		if ($conn->query($sql) === TRUE) {
	    	echo "record deleted successfully";
		} else {
		    echo "Error: " . $sql . "<br>" . $conn->error;
		}				
	}
	function selectCategory($conn)
	{
		 $sql="select * from category where status='Enable'";
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
}

?>