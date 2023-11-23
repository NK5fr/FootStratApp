package fr.nk5.model;

public class MessagePlayer {
    private String message;
    private Player player;

    public MessagePlayer(String message, Player player) {
        this.message = message;
        this.player = player;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPlayerName() {
        return this.player.name;
    }

    public String getPlayerTeam() {
        return this.player.team;
    }
}
