<?$tajneHaslo="chuj";
if($_GET['usun']=='1')
{	file_put_contents ( "unbaniki", '');
}else if($_POST['pass']==$tajneHaslo)
{	$ip=$_SERVER['REMOTE_ADDR'];	
file_put_contents ( "unbaniki", $ip);	
echo("System rozjebany. Mo¿esz graæ.");
}else {
	?>	<html><body><center>Podaj kurwa has³o:<br>
	<form method = "post">
	<input type="password" name = "pass"><br>
	<input type = "submit" value = "NAPIERDALAAAAJ!" >
	</form></center></body></html>
	<?}
	?>