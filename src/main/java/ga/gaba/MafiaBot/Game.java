package ga.gaba.MafiaBot;

import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;

/**
 * Created by glyczak on 10/14/17.
 */
public class Game {
    private ArrayList<Player> players;
    public boolean inProgress;
    public User moderator;
    public MessageChannel channel;

    public Game(User moderator, MessageChannel channel) {
        this.moderator = moderator;
        this.channel = channel;
        this.inProgress = false;
        players = new ArrayList<Player>(1);
        players.add(new Player(moderator));
    }

    public boolean start() {
        if(getPlayersNeeded() == 0)
            inProgress = true;
        return inProgress;
    }

    public int getPlayersNeeded() {
        return (players.size() < 7) ? (7 - players.size()) : 0;
    }

    public void addPlayer(User user) {
        players.add(new Player(user));
    }

    public boolean isInGame(User user) {
        for (Player player:
             players) {
            if(player.user.equals(user)) return true;
        }
        return false;
    }
}
