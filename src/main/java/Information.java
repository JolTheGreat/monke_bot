import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class Information {
    public static void run() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Here are the lists of commands:");
        embedBuilder.setColor(Color.BLUE);
        embedBuilder.setDescription("!help - Displays a list of commands\n" +
                "\n" +
                "!ping - Sends pong\n" +
                "\n" +
                "!memes - Send a meme from Reddit\n" +
                "\n" +
                "!timer - Sets a timer for a selected time\n" +
                "            For example, ‘!timer wap 6 s’\n" +
                "            will set a timer called ‘wap’ for\n" +
                "\t   6 seconds. \n" +
                "\n" +
                "!poll - Creates a poll. eg. !poll 'cat or dog?' |cat, dog");
        EventListener.CHANNEL.sendMessage(embedBuilder.build()).queue();
    }
}
