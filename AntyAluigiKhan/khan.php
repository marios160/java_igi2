<?php
if($cmd == 1)
{
$haslo = "C!4p4T3chUj3";
$fp=fopen("khan","w");
	fwrite($fp, $haslo); 
	fclose($fp);
}
?>