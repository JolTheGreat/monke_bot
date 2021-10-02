import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {
    private static JDA jda;
    public static void main(String[] args) throws LoginException, InterruptedException {
        jda = JDABuilder
                .createDefault(System.getenv("TOKEN"))
                .enableIntents(GatewayIntent.GUILD_MESSAGES)
                .setActivity(Activity.listening("!help for help"))
                .build();

        jda.awaitReady();

        jda.addEventListener(new EventListener());
    }
}
