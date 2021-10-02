import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.w3c.dom.Text;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.ListIterator;

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
