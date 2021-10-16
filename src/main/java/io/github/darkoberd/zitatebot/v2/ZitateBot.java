package io.github.darkoberd.zitatebot.v2;

import io.github.darkoberd.zitatebot.v2.commands.ZitatCMD;
import io.github.darkoberd.zitatebot.v2.commands.ZitatDeleteCMD;
import io.github.darkoberd.zitatebot.v2.listeners.CommandListener;
import io.github.darkoberd.zitatebot.v2.listeners.ComponentListener;
import io.github.darkoberd.zitatebot.v2.utils.Command;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

public class ZitateBot {

    private static JDA jda;
    private static final Logger LOGGER = LoggerFactory.getLogger(ZitateBot.class);
    public static List<Command> commandList = new ArrayList<>();
    public static List<Zitat> zitate = new ArrayList<>();

    public static void main(String[] args){
        if (args.length < 1) {
            LOGGER.error("You have to provide a token as first argument!");
            System.exit(1);
        }

        try {
            start(args[0]);
        }catch (LoginException | IllegalArgumentException e){
            System.err.println("Der BOT konnte nicht erstellt werden!");
            e.printStackTrace();
        }
    }

    private static void start(String token) throws LoginException, IllegalArgumentException{
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setActivity(Activity.listening("/zitat"));
        builder.setStatus(OnlineStatus.ONLINE);
        addCommands();
        builder.addEventListeners(new CommandListener());
        builder.addEventListeners(new ComponentListener());
        jda = builder.build();
        addSlashCommands();
        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void addCommands() {
        commandList.add(new ZitatCMD());
        commandList.add(new ZitatDeleteCMD());
    }

    private static void addSlashCommands() {
        for (Command cmds : commandList){
            jda.upsertCommand(cmds.commandData()).queue();
        }
    }

    public static JDA getJda() {
        return jda;
    }
}
