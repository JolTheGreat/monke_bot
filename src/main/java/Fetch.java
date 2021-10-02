import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.requests.ErrorResponse;

import java.awt.*;
import java.util.List;

public class Fetch {
    public static void run() {
        long id = Long.parseLong(EventListener.RAW_TEXT.split("/")[6]);
        List<TextChannel> channels = EventListener.CHANNELS;

        for (TextChannel textChannel : channels) {
            textChannel.retrieveMessageById(id).queue((message) -> {
                System.out.println("Message Content: " + message.getContentDisplay());
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(Color.MAGENTA);
                embedBuilder.setTitle("'" + message.getContentDisplay() + "'");
                embedBuilder.setThumbnail(message.getAuthor().getAvatarUrl());
                embedBuilder.setDescription("Channel: " + message.getChannel().getName());
                embedBuilder.setAuthor(message.getAuthor().getName());
                EventListener.CHANNEL.sendMessage(embedBuilder.build()).queue();
            }, (failure) -> {
                if (failure instanceof ErrorResponseException) {
                    ErrorResponseException ex = (ErrorResponseException) failure;
                    if (ex.getErrorResponse() == ErrorResponse.UNKNOWN_MESSAGE) {
                        // this means the message doesn't exist
                        System.out.println("That message doesn't exist");
                    }
                }
                failure.printStackTrace();
            });
        }
    }
}
