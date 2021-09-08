package io.github.darkoberd.zitatebot.v2;

import io.github.darkoberd.zitatebot.v2.events.ReadyListener;
import io.github.darkoberd.zitatebot.v2.listeners.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.util.Collections;

public class ZitateBot {

    private static JDA jda;
    private static final Logger LOGGER = LoggerFactory.getLogger(ZitateBot.class);

    public static void main(String[] args) throws LoginException, InterruptedException {
        if (args.length < 1) {
            LOGGER.error("You have to provide a token as first argument!");
            System.exit(1);
        }

        JDABuilder builder = JDABuilder.createLight(args[0], Collections.emptyList());

        builder.addEventListeners(new ReadyListener());
        builder.addEventListeners(new CommandListener());

        jda = builder.build();

        jda.upsertCommand("ping","Calculate ping of the bot").queue();

        LOGGER.debug("JDA ready!");
    }

}
