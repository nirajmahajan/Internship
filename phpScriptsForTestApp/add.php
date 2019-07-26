<?php
   $con=mysqli_connect("127.0.0.1","nirajm","12345678","db1");

   if (mysqli_connect_errno($con)) {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }

   $username = $_GET['username'];
   $password = $_GET['password'];
   $name = $_GET['name'];

   $result = mysqli_query($con,"SELECT Name FROM userData where Username='$username'");
   $row = mysqli_fetch_array($result);
   $data = $row[0];

   $sql = "INSERT INTO userData (Username, Password, Name)
           VALUES ('$username', AES_Encrypt('$password', CONCAT('_ENCRYPT_', '$username')), '$name')";
   if ($data) {
       echo "The username '$username' is already taken";
   }
   else if (mysqli_query($con, $sql)) {
       echo "New record created successfully";
   } else {
       echo "Error: " . $sql . "<br>" . mysqli_error($con);
   }
   mysqli_close($con);
?>
