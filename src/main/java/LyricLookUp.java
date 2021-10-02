import net.dv8tion.jda.api.EmbedBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LyricLookUp {
    public static void run() {
        try {

            Document doc = Jsoup.connect("https://www.youtube.com/results?search_query=" + EventListener.RAW_TEXT.replace("!music", "").split("'")[1] + "&sp=EgIQAQ%253D%253D").get();

            List<String> arrayList = Arrays.asList(doc.toString().split(":"));
            String url = "https://www.youtube.com/watch?v=" + arrayList.get(arrayList.indexOf("{\"videoId\"") + 1).split(",")[0].replace("\"", "");
            System.out.println(url);

            EventListener.CHANNEL.sendMessage("Is this the song that you were looking for? \n" + url).queue(message -> {
               message.addReaction("U+2705").queue();
               message.addReaction("U+274C").queue();
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
