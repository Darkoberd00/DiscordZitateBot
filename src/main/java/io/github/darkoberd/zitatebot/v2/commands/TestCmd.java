package io.github.darkoberd.zitatebot.v2.commands;

import io.github.darkoberd.zitatebot.v2.utils.Command;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class TestCmd implements Command {
    @Override
    public String cmdname() {
        return "test";
    }

    @Override
    public void action(SlashCommandEvent event) {

    }

    @Override
    public CommandData commandData() {
        CommandData data = new CommandData(cmdname(),help());
        return data;
    }

    @Override
    public String help() {
        return "das ist ein test";
    }
}
