<?php
   $con=mysqli_connect("127.0.0.1","nirajm","12345678","db1");

   if (mysqli_connect_errno($con)) {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }

   $username = $_GET['username'];
   $sql = "DELETE FROM userData WHERE Username='$username'";
 
   if (mysqli_query($con, $sql)) {
       echo "DELETED";
   } else {
       echo "Error: " . $sql . "<br>" . mysqli_error($con);
   }
   mysqli_close($con);
?>
