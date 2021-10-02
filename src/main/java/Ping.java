public class Ping {
    public static void run() {
        EventListener.CHANNEL.sendMessage("pong!").queue();
    }
}
