package io.github.darkoberd.zitatebot.v2.events;


import io.github.darkoberd.zitatebot.v2.ZitateBot;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadyListener implements EventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZitateBot.class);

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof ReadyEvent)
            LOGGER.debug("API is ready!");
    }

}
