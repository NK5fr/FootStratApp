package fr.nk5.model;

import fr.univlille.iutinfo.utils.Observer;

public class TeamManager {
    private Team blue;
    private Team red;

    public TeamManager() {
        this.blue = new Team("Blue");
        this.red = new Team("Red");
    }

    public void addPlayer(String name, String team) {
        String playerName = name;
        if(red.contains(name) || blue.contains(name)){
            playerName = "Exists";
        }
        if (team.equals("Blue")) {
            this.blue.addPlayer(playerName);
        } else if (team.equals("Red")) {
            this.red.addPlayer(playerName);
        }
    }

    public void removePlayer(String name, String team) {
        if (team.equals("Blue")) {
            this.blue.removePlayer(name);
        } else if (team.equals("Red")) {
            this.red.removePlayer(name);
        }
    }

    public void attach(Observer observer) {
        this.blue.attach(observer);
        this.red.attach(observer);
    }
}
