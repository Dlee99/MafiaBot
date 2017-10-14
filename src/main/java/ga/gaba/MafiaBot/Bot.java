package ga.gaba.MafiaBot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

/**
 * Created by glyczak on 10/14/17.
 */
public class Bot {
    private static final String token = "TOKEN_GOES_HERE";
    public static JDA api;

    public static void main(String[] args) throws Exception
    {
        api = new JDABuilder(AccountType.BOT).setToken(token).buildAsync();
        api.addEventListener(new GameListener());
    }
}
