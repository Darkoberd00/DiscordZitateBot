package io.github.darkoberd.zitatebot;

import io.github.darkoberd.zitatebot.commands.ZitatCMD;
import io.github.darkoberd.zitatebot.handler.CommandHandler;
import io.github.darkoberd.zitatebot.listeners.CommandListener;
import io.github.darkoberd.zitatebot.listeners.PrivateMessageListener;
import io.github.darkoberd.zitatebot.utils.Flags;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Darkoberd00
 */
public class ZitateBot {

    /*
     * TODO: Reactions, Feste Channel, Leaderboard oder so
     */

    private static String token;
    private static JDA jda;

    public static boolean DEBUG = false;
    public static String PREFIX = "!";

    public static Map<String, Flags> flags = new HashMap<>();

    public static Map<String, Zitat> zitate = new HashMap<>();

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("You have to provide a token as first argument!");
            System.exit(1);
        }

        token = args[0];

        try {
            start();
        }catch (LoginException | IllegalArgumentException e){
            System.err.println("Der BOT konnte nicht erstellt werden!");
        }
        shutdown();
    }

    private static void start() throws LoginException, IllegalArgumentException{
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.setActivity(Activity.listening(PREFIX+"zitat"));
        builder.setStatus(OnlineStatus.ONLINE);
        addCommands();
        builder.addEventListeners(new CommandListener());
        builder.addEventListeners(new PrivateMessageListener());
        jda = builder.build();
    }

    private static void addCommands() {
        CommandHandler.commands.put("zitat", new ZitatCMD());
    }

    private static void shutdown() {
        new Thread(() -> {
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while ((line = reader.readLine()) != null) {
                    if (line.equalsIgnoreCase("exit")) {
                        if (jda != null) {
                            reader.close();
                            jda.shutdown();
                            System.out.println("Der Bot geht Offline");
                            System.exit(1);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }).start();
    }

    public static JDA getJda() {
        return jda;
    }

}
