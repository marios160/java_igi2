
package konsola;

/**
 *
 * @author Mateusz
 */
public class Player {
    
    String id;
    String nick;
    String stats;
    String ping;
    String team;
    
    

    public Player(String id, String nick, String stats, String ping, String team) {
        this.id = id;
        this.nick = nick;
        this.stats = stats;
        this.ping = ping;
        this.team = team;

    }

    public String getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public String getStats() {
        return stats;
    }

    public String getPing() {
        return ping;
    }

    public String getTeam() {
        return team;
    }
    
    
    
    
}
