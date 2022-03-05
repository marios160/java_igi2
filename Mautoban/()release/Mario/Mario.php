<?php
if($cmd == 1)
{
$haslo = "";
$fp=fopen("cmd","w");
	fwrite($fp, $haslo); 
	fclose($fp);
}
?>