import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class Timer {
    public static void run() {
        String[] split = EventListener.RAW_TEXT.split(" ");
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    int when = Integer.parseInt(split[2]);
                    switch (split[3]) {
                        case "s":
                            Thread.sleep(when * 1000L);
                            break;
                        case "m":
                            Thread.sleep(when * 60000L);
                            break;
                        case "h":
                            Thread.sleep(when * 3600000L);
                            break;
                        case "d":
                            Thread.sleep(when * 86400000L);
                            break;

                    }

                    for (int i = 0; i < 3; i++) {
                        EventListener.CHANNEL.sendMessage("Beep! The reminder '" + split[1] + "' is ringing!").queue();
                    }


                } catch (NumberFormatException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setTitle("The timer has been set!");
        embedBuilder.setColor(Color.red);
        embedBuilder.setDescription("Your " + split[2] + split[3] + " called '" + split[1] + "' has been set successfully");
        embedBuilder.setAuthor("Monke Bot");
        EventListener.CHANNEL.sendMessage(embedBuilder.build()).queue();
    }
}
