import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Meme {
    public static void run() {
        hourlyCaller();
        StringBuilder xmlResult = new StringBuilder();
        try {
            URL url = new URL("https://meme-api.herokuapp.com/gimme/1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            System.out.println("The response code was " + responseCode);
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
        System.out.println(xmlResult);
        System.out.println(xmlResult.toString().indexOf("url"));
        String url = "";

        for (String s : xmlResult.toString().split(",")) {
            if (s.contains("\"url\":\"")) {
                url = s.replace("\"url\":\"", "").replace("\"", "");
                break;
            }
        }

        System.out.println(url);
        EventListener.CHANNEL.sendMessage(url).queue();
    }

    private static void hourlyCaller() {
        new Thread(() -> {
            run();
            try {
                Thread.sleep(7200000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
