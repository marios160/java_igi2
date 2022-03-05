
package loganalyze;

/**
 *
 * @author Mario PL
 */
public class Player {
    private String nick;
    private String id;
    private int kills;
    private int deaths;
    private String ip;

    public Player(String id, String ip, String nick) {
        this.nick = nick;
        this.id = id;
        this.ip = ip;
        this.kills = 0;
        this.deaths = 0;
    }
    public Player(String id, String ip) {
        this.id = id;
        this.ip = ip;
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
    

    public String getNick() {
        return nick;
    }


    public String getId() {
        return id;
    }


    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }
    
    public void addKills() {
        this.kills++;
    }
    public void subKills() {
        this.kills--;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
    public void addDeaths() {
        this.deaths++;
    }
    public void subDeaths() {
        this.deaths--;
    }
    
    
}
