
<?php
	if($nick == "")
	{
		$nick = "BRAK";
	}

	$fp=fopen("ip", "a");
	$dane=date('[d-m-Y H:i:s')."] ".$_SERVER['REMOTE_ADDR']."	".$nick."\n";
	fwrite($fp, $dane); 
	fclose($fp);

?>