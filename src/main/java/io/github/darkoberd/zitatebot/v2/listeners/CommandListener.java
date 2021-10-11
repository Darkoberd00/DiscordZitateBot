package io.github.darkoberd.zitatebot.v2.listeners;

import io.github.darkoberd.zitatebot.v2.ZitateBot;
import io.github.darkoberd.zitatebot.v2.utils.Command;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandListener extends ListenerAdapter {
    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        boolean abfrage = false;
        for (Command cmds: ZitateBot.commandList) {
            if(cmds.cmdname().equals(event.getName())){
                cmds.action(event);
                break;
            }
        }
    }
}
