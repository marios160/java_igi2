
package pl.mario.igi2_ranking;

/**
 *
 * @author Mario PL
 */
public class Player {
    private String login;
    private String pass;
    private String id;
    private int intid;
    private int kills;
    private int deaths;
    private int obj;
    private String team; //0 - igi, 1 - cons
    private String stats;

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = this.stats+"\n"+stats;
    }
    
    public void clrStats(){
        this.stats = "";
    }

    public Player(String login, String pass) {
        this.login = login;
        this.pass = pass;
        this.intid = 0;
        this.id = "0";
        this.kills = 0;
        this.deaths = 0;
        this.obj = 0;
        this.team = "";
        this.stats = "";
    }

    public int getIntid() {
        return intid;
    }

    public void setIntid(int intid) {
        this.intid = intid;
        setId(Integer.toString(intid));
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }
    
    public void addKills(){
        this.kills++;
        Glowne.jLabel4.setText(Integer.toString(this.kills));
    }
    
    public void subKills(){
        this.kills--;
        Glowne.jLabel4.setText(Integer.toString(this.kills));
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
    
    public void addDeaths(){
        this.deaths++;
        Glowne.jLabel6.setText(Integer.toString(this.deaths));
    }

    public int getObj() {
        return obj;
    }

    public void setObj(int obj) {
        this.obj = obj;
    }
    
    public void addObj(){
        this.obj++;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
    
    
    
}
