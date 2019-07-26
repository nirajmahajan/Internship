<?php
$servername = "127.0.0.1";
$dbname = "db1";

$username = $_GET['username'];
$password = $_GET['password'];

try {
    $conn = new PDO("mysql:host=$servername;dbname=$dbname", "nirajm", "12345678");
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $stmt = $conn->prepare("SELECT Name FROM userData where Username='$username' and Password=AES_Encrypt('$password', CONCAT('_ENCRYPT_', '$username'))");
    $stmt->execute();

    $result = $stmt->fetch(PDO::FETCH_ASSOC); 
    $data = $result["Name"];
    if($data){
        echo $data;
    } else {
        echo "NOT_FOUND";
    }
}
catch(PDOException $e) {
    echo "Error: " . $e->getMessage();
}
$conn = null;
?>
