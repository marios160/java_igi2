
package pl.mario.mautorun;

/**
 *
 * @author Mario PL
 */
public class Player {
    
    private String id;
    private String nick;
    private String ip;
    private int team;
    private int rcon;
    private int warrnings;
    private int votes;
    private int frags;
    private int deaths;
    private int[] givVot;   //na ktore id glosowal
    private int[] gotVot;   //od kogo dostal vota
    private int nrVot;      //ile votow dostal
    private int ping;
    
    String sfrag = "frags_";
    String sdeath = "deaths_";
    String sping = "ping_";
    String steam = "team_";
    String splayer = "player_";

    public Player() {
        this.id = "";
        this.nick = "";
        this.ip = "";
        this.team = 0;
        this.rcon = 0;
        this.warrnings = 0;
        this.votes = 0;
        this.frags = 0;
        this.deaths = 0;
        this.givVot = new int[3];
        this.gotVot = new int[5];
        this.nrVot = 0;
        this.ping = 0;
    }
    
    
    public Player(String id, String ip, String nick) {
        this.id = id;
        this.nick = nick;
        this.ip = ip;
        this.rcon = 0;
        this.warrnings = 0;
        this.votes = 0;
        this.givVot = new int[2];
        this.gotVot = new int[5];
        this.nrVot = 0;
        
        this.splayer+=this.id+"\\";
        this.sfrag+=this.id+"\\";
        this.sdeath+=this.id+"\\";
        this.sping+=this.id+"\\";
        this.steam+=this.id+"\\";
        
    }
    
    public Player(String id, String ip) {
        this.id = id;
        this.ip = ip;

    }
    
    /**
     * Ustawianie reszty parametrow gracza czyli wyniku pingu i druzyny
     */
    public void setNewPlayer()
    {
        Pakiety stat = new Pakiety();
        String status = stat.sendStatus();
        if (status.indexOf(splayer) < 0)
            return;
        String pom;
        pom = status.substring(status.indexOf(splayer)+splayer.length(),status.indexOf(sfrag));
        setNick(pom);
        System.out.println(pom);
        pom = status.substring(status.indexOf(sfrag)+sfrag.length(),status.indexOf(sdeath)-1);
        setFrags(Integer.parseInt(pom));
        pom = status.substring(status.indexOf(sdeath)+sdeath.length(),status.indexOf(sping)-1);
        setDeaths(Integer.parseInt(pom));
        pom = status.substring(status.indexOf(sping)+sping.length(),status.indexOf(steam)-1);
        setPing(Integer.parseInt(pom));
        pom = status.substring(status.indexOf(steam)+steam.length(),status.indexOf(steam)+steam.length()+1);
        setTeam(Integer.parseInt(pom));
        Info.players[Integer.parseInt(this.id)] = this;
    }
    
    

    @Override
    public String toString() {
        return "\n\nnick=" + nick + "\nid=" + id +  "\nip=" + ip + "\nteam=" + team + "\nrcon=" + rcon + "\nwarrnings=" + warrnings + "\nvotes=" + votes + "\nfrags=" + frags + "\ndeaths=" + deaths + "\ngivVot=" + givVot + "\ngotVot=" + gotVot + "\nnrVot=" + nrVot + "\nping=" + ping;
    }

    public int getNrVot() {
        return nrVot;
    }

    public void setNrVot(int nrVot) {
        this.nrVot = nrVot;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getRcon() {
        return rcon;
    }

    public void setRcon(int rcon) {
        this.rcon = rcon;
    }

    public int getWarrnings() {
        return warrnings;
    }

    public void setWarrnings(int warrnings) {
        this.warrnings = warrnings;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getFrags() {
        return frags;
    }

    public void setFrags(int frags) {
        this.frags = frags;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getGivVot(int id) {
        return givVot[id];
    }

    public void setGivVot(int vot, int id) {
        this.givVot[id] = vot;
    }
    
    public int getGotVot(int id) {
        return gotVot[id];
    }

    public void setGotVot(int vot, int id) {
        this.gotVot[id] = vot;
    }

    public int getPing() {
        return ping;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }
    
    
}
