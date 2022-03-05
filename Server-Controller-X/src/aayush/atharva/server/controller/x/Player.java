package aayush.atharva.server.controller.x;

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
        return this.id;
    }

    public String getNick() {
        return this.nick;
    }

    public String getStats() {
        return this.stats;
    }

    public String getPing() {
        return this.ping;
    }

    public String getTeam() {
        return this.team;
    }
}
