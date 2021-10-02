import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class Poll {
    public static void run() {
        String title = EventListener.RAW_TEXT.split("'")[1];
        StringBuilder content = new StringBuilder();
        final String[] alphabets = "abcdefghijklmnopqrstuvwxyz".split("");
        //Eg. !poll 'Milk or cereal first' |Cereal,Milk
        int num = 0;
        for (int i = 0; i < 26; i++) {
            try {
                if (EventListener.RAW_TEXT.split(",")[i] != null) {
                    content.append(":regional_indicator_")
                            .append(alphabets[i])
                            .append(":")
                            .append(" -> ")
                            .append(EventListener.RAW_TEXT.split("\\|")[1].split(",")[i])
                            .append("\n");
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                num = i;
                break;
            }

        }

        System.out.println(title);
        System.out.println("------");
        System.out.println(content);

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(title);
        embedBuilder.setDescription(content);
        embedBuilder.setColor(Color.BLUE);
        embedBuilder.setAuthor("A poll by " + EventListener.AUTHOR.getName());
        final String[] unicode = {"U+1F1E6\n", "U+1F1E7\n", "U+1F1E8\n", "U+1F1E9\n", "U+1F1EA\n", "U+1F1EB\n", "U+1F1EC\n", "U+1F1ED\n", "U+1F1EE\n", "U+1F1EF\n", "U+1F1F0\n", "U+1F1F1\n", "U+1F1F2\n", "U+1F1F3\n", "U+1F1F4\n", "U+1F1F5\n", "U+1F1F6\n", "U+1F1F7\n", "U+1F1F8\n", "U+1F1F9\n", "U+1F1FA\n", "U+1F1FB\n", "U+1F1FC\n", "U+1F1FD\n", "U+1F1FE\n", "U+1F1FF\n"};
        int finalNum = num;
        EventListener.CHANNEL.sendMessage(embedBuilder.build()).queue(message -> {
            for (int i = 0; i < finalNum; i++) {
                message.addReaction(unicode[i]).queue();
            }

        });
    }
}
