<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname='survey';

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
echo "Connected successfully";

/*$sql = "CREATE DATABASE Malvi";
if ($conn->query($sql) === TRUE) {
    echo "Database created successfully";
} else {
    echo "Error creating database: " . $conn->error;
}

$conn->close();
*/
$password=$_REQUEST['password'];
$username=$_REQUEST['username'];
$email=$_REQUEST['email'];
$phone=$_REQUEST['phone'];
$address=$_REQUEST['address'];


$sql = "INSERT INTO user (username, password,email,phone,address)
VALUES ('$username', '$password','$email','$phone','$address')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>
