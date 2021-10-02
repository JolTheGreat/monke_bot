import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

class EventListener extends ListenerAdapter {
    public static TextChannel CHANNEL;
    public static List<TextChannel> CHANNELS;
    public static User AUTHOR;
    public static Message MESSAGE;
    public static String RAW_TEXT;

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        super.onGuildMessageReceived(event);
        if (!event.getAuthor().isBot()) {
            CHANNEL = event.getChannel();
            CHANNELS = event.getGuild().getTextChannels();
            AUTHOR = event.getAuthor();
            MESSAGE = event.getMessage();
            RAW_TEXT = event.getMessage().getContentRaw();
            String[] split = RAW_TEXT.split(" ");

            if (RAW_TEXT.equalsIgnoreCase("!mhelp")) {
                Information.run();
            } else if (RAW_TEXT.equalsIgnoreCase("!ping")) {
                Ping.run();
            } else if (RAW_TEXT.equalsIgnoreCase("!memes")) {
                Meme.run();
            } else if (RAW_TEXT.split(" ")[0].equalsIgnoreCase("!timer")) {
                Timer.run();
            } else if (RAW_TEXT.contains("https://discord.com/")) {
                Fetch.run();
            } else if (split[0].equalsIgnoreCase("!poll")) {
                Poll.run();
            } else if (RAW_TEXT.equalsIgnoreCase("among us")) {
                event.getMessage().reply("https://imgur.com/hKwoXZX").queue();
            } else if (RAW_TEXT.split(" ")[0].equalsIgnoreCase("!lyrics")) {
                LyricLookUp.run();
            }
        }
    }
}
