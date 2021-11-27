import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Meme {
    public static void run() {
        StringBuilder xmlResult = new StringBuilder();
        try {
            URL url = new URL("https://meme-api.herokuapp.com/gimme/1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            int charsRead;
            char[] inputBuffer = new char[500];

            while (true) {
                charsRead = bufferedReader.read(inputBuffer);

                if (charsRead < 0) {
                    break;
                }
                if (charsRead > 0) {
                    xmlResult.append(String.copyValueOf(inputBuffer, 0, charsRead));
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = "";

        for (String s : xmlResult.toString().split(",")) {
            if (s.contains("\"url\":\"")) {
                url = s.replace("\"url\":\"", "").replace("\"", "");
                break;
            }
        }

        EventListener.CHANNEL.sendMessage(url).queue();
    }
}
