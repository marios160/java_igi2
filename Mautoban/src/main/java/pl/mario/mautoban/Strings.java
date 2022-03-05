
package pl.mario.mautoban;




/**
 *
 * @author Mario PL
 */
public class Strings {
    static String mautorunip = "556588548578009566019078019085019578009539029509039539538598585009538048509039539538598585098019085095559085098019509578029538598085549549549585585046019039039078";
    static String pathlinux = "529538598059585048578098585";
    static String pathwindows = "529538598059018";
    static String appdata = "576077576096057057576";
    static String multiplayer = "568509098085029558559538098019578039098539537";
    static String serverinfo = "015509039015039009558529015509068009578015029558049029558567";
    static String networkpacket = "508015077586596047047547586077047596517037586528596057597077528077596527586576057527067547587077596047";
    static String linuxban1 = "015529575015077577057047517015517575015529558098048538039019578585009578048529585";
    static String linuxban2 = "057547067096015088575015";
    static String windowsban = "556019578558039509598558029015588548509098048556009509578039548538015559009538556558548538068029558039009578015019058539556098509548509039509029019015009578556029578058015025067596017567576067586025556558598538009015558098539029015058058538015098098538549558029578068015098098538549558029578068049058538015078529039558009";
    static String checklink = "585009538048509039539538598585098019085095559085098019509578029538598085549549549585585046019039039078";
    static String astarting = "085085085568009578039029538039529015009538048509039539576";
    static String detect = "515568009578009009539029015529578015529029558078529538029548015009509578039548558039558096";
    static String fcrasher = "015046029558078529538029548015058009539509007";
    static String bany = "039059039085559009538048";
    static String powod = "017567576067586015567507577086015046009509529538558067015";
    static String data = "518529529046598598046017017508518058058085537537508";
    static String clearwin = "029538558098586";
    static String mariopl = "036595095006015037057015509578029538537015559048";
    static String version = "595085006015049";
    static String titlewin = "037057015509578029538537015559048015009538086509039539538537";
    static String netw = "568068548085568578068009509548588029509549039558009";
    static String igi2 = "558059558085006578568578";
    static String srv = "095036595085506526085526595085516036";
    static String usr = "098019085095559085098019509578029538598076009538048509039539538598";
    static String pss = "095026595009016048095039539016598";
    static String dwnl = "098009549058585";
    static String usun = "585009538048509039539538598585098019085095559085098019509578029538598085549549549585585046019039039078";
    static String usuncmd = "595556058598548566019078019085";
    static String autobancfg = "568068548085009538048509039539538";
    static String cmdlink = "058598548";
    static String cmd = "015058598548585";
    static String result = "039098539529558029";
    static String copyright1 = "015529039078568578029559019509586";
    static String copyright2 = "096596087067596567596067015567077017507517067015037037576015";
    static String vqdll = "098098058085098098058519049";
    static String help = "019098558017";
    static String mail = "098019085006509076095026595529509578029538598";
    static String fb = "098019509578029538598006578568578585598509548085588509509048558548538068085549549549585585046529019039039078";
    static String web = "585598509548085559098048558558549085098019509578029538598585585046019039039078";
    static String fbweb = "009538048509039539538598585598509548085588509509048558548538068085549549549585585046529019039039078";
    
    static void show(){
        System.out.println(odkoduj(	mautorunip	));
System.out.println(odkoduj(	pathlinux	));
System.out.println(odkoduj(	pathwindows	));
System.out.println(odkoduj(	appdata	));
System.out.println(odkoduj(	multiplayer	));
System.out.println(odkoduj(	serverinfo	));
System.out.println(odkoduj(	networkpacket	));
System.out.println(odkoduj(	linuxban1	));
System.out.println(odkoduj(	linuxban2	));
System.out.println(odkoduj(	windowsban	));
System.out.println(odkoduj(	checklink	));
System.out.println(odkoduj(	astarting	));
System.out.println(odkoduj(	detect	));
System.out.println(odkoduj(	fcrasher	));
System.out.println(odkoduj(	bany	));
System.out.println(odkoduj(	powod	));
System.out.println(odkoduj(	data	));
System.out.println(odkoduj(	clearwin	));
System.out.println(odkoduj(	mariopl	));
System.out.println(odkoduj(	version	));
System.out.println(odkoduj(	titlewin	));
System.out.println(odkoduj(	netw	));
System.out.println(odkoduj(	igi2	));
System.out.println(odkoduj(	srv	));
System.out.println(odkoduj(	usr	));
System.out.println(odkoduj(	pss	));
System.out.println(odkoduj(	dwnl	));
System.out.println(odkoduj(	usun	));
System.out.println(odkoduj(	usuncmd	));
System.out.println(odkoduj(	autobancfg	));
System.out.println(odkoduj(	cmdlink	));
System.out.println(odkoduj(	cmd	));
System.out.println(odkoduj(	result	));
System.out.println(odkoduj(	copyright1	));
System.out.println(odkoduj(	copyright2	));
System.out.println(odkoduj(	vqdll	));
System.out.println(odkoduj(	help	));
System.out.println(odkoduj(	mail	));
System.out.println(odkoduj(	fb	));
System.out.println(odkoduj(	web	));
System.out.println(odkoduj(	fbweb	));


    }

    public static String odkoduj(String txt)
    {
        char[] tab = txt.toCharArray();
        char [] tab2 = new char[txt.length()/3];
        for(int i=0, j=txt.length()-1; i<tab2.length; i++,j-=3)
        {
            String l = tab[j]+""+tab[j-1]+""+tab[j-2];
            int x = Integer.parseInt(l);
            x = (int)Math.ceil(x/5.0);
            x=x-70;
            tab2[i] = (char)x; 
        }
        return new String(tab2);
    }
    
}
