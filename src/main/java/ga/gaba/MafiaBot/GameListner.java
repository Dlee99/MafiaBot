package ga.gaba.MafiaBot;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Created by glyczak on 10/14/17.
 */
public class GameListner extends ListenerAdapter {
    private static boolean gameSetupInProgress = false;
    private static Game game;

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Message message = event.getMessage();
        if (!message.getChannel().getId().equals("368768874222452747") || event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        String content = message.getRawContent();
        MessageChannel channel = event.getChannel();
        User author = message.getAuthor();
        event.getChannel().sendMessage("test");
        if (content.charAt(0) != '%') return;
        content = content.substring(1);
        String response = "";
        switch (content) {
            case "newgame":
                if (gameSetupInProgress) {
                    response = ChatStrings.SETUP_IN_PROGRESS;
                } else {
                    response = author.getName() + " would like to start a game.\n**6** more players are needed to begin.\nTo join, say `%joingame`.";
                    game = new Game(author, channel);
                    gameSetupInProgress = true;
                }
                break;
            case "joingame":
                if (!gameSetupInProgress) {
                    response = ChatStrings.GAME_NOT_CREATED;
                } else {
                    if(game.isInGame(author)) {
                        response = ChatStrings.ALREADY_JOINED;
                    } else {
                        game.addPlayer(author);
                        response = playersNeeded();
                    }
                }
                break;
            case "startgame":
                if(game.inProgress) {
                    response = ChatStrings.GAME_ALREADY_RUNNING;
                } else if(game.moderator.equals(author)) {
                    if(game.start()) {
                        response = ChatStrings.GAME_STARTED;
                    } else {
                        response = playersNeeded();
                    }
                } else {
                    response = ChatStrings.ONLY_MOD_CAN_START_GAME;
                }
                break;
        }
        if (!content.isEmpty())
            channel.sendMessage(response).queue();
    }

    private static String playersNeeded() {
        int playersNeeded = game.getPlayersNeeded();
        switch (playersNeeded) {
            case 0:
                return game.moderator.getName() + ", start the game with `%startgame`";
            case 1:
                return "**1** more player is needed to begin.";
            default:
                return "**" + playersNeeded + "** more players are needed to begin.";
        }
    }
}
