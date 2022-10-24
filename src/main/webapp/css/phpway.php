<?php
        $servername= "127.0.0.1";
        $username="root";
        $password="";
        $database="webappdemo";


        //create connection
        $connection= new mysqli($servername,$username,$password,$database);

        if ($connection -> connect_error) {
            die("Connection failed: ".$connection->connect_error);
        }

        $sql="SELECT name,surname FROM user";
        $result = $connection->query($sql);

        while ($row=$result ->fetch_assoc()) {
            echo "<tr>
	
	        <td>". $row["name"] ."</td>
	        <td>. $row["surname"] ."</td>
	
	
	
	
	
	
	
	
	
	    </tr>";
        }


        
	   
        ?>
	