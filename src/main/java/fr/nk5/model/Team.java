package fr.nk5.model;

import fr.univlille.iutinfo.utils.Subject;

import java.util.HashSet;
import java.util.Set;

public class Team extends Subject{
    private String name;
    private Set<Player> players = new HashSet<>();
    private static final int MAX_PLAYERS = 11;

    public Team(String name){
        this.name = name;
    }

    public void addPlayer(String name){
        Player player = new Player(name, this.name);
        if(name.equals("Exists")){
            notifyObservers("PlayerExists");
        }else if(players.size() == MAX_PLAYERS){
            notifyObservers("TeamFull");
        }else{
            players.add(player);
            notifyObservers(new MessagePlayer("add", player));
        }
    }

    public void removePlayer(String name){
        Player player = new Player(name, this.name);
        players.remove(player);
        notifyObservers(new MessagePlayer("remove", player));
    }

    public boolean contains(String name){
        Player player = new Player(name, this.name);
        return players.contains(player);
    }
}
