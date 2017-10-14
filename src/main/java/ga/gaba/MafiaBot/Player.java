package ga.gaba.MafiaBot;

import net.dv8tion.jda.core.entities.User;

/**
 * Created by glyczak on 10/14/17.
 */
public class Player {
    public User user;
    public Role role;

    public Player(User user) {
        this.user = user;
    }
}
