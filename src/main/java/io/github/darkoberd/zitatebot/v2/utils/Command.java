package io.github.darkoberd.zitatebot.v2.utils;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public interface Command {
    String cmdname();
    void action(SlashCommandEvent event);
    CommandData commandData();
    String help();
}
