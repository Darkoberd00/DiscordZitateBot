package io.github.darkoberd.zitatebot.v2.commands;

import io.github.darkoberd.zitatebot.v1.utils.Massages;
import io.github.darkoberd.zitatebot.v2.Zitat;
import io.github.darkoberd.zitatebot.v2.utils.Command;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class ZitatCMD implements Command {


    @Override
    public String cmdname() {
        return "zitat";
    }

    @Override
    public void action(SlashCommandEvent event) {
        if(event.getOption("kontextuser") != null || event.getOption("kontext") != null){
            if (event.getOption("kontext") == null){
                event.reply("Du hast kein kontext hinzugefügt").setEphemeral(true).queue();
            } else if (event.getOption("kontextuser") == null){
                event.reply("Du hast kein kontextuser hinzugefügt").setEphemeral(true).queue();
            } else {
                Zitat zitat = new Zitat(event.getOption("zitat").getAsString(),
                        event.getOption("user").getAsUser().getIdLong(),
                        event.getOption("kontext").getAsString(),
                        event.getOption("kontextuser").getAsUser().getIdLong());
                event.reply(zitat.getEmbed()).queue();
            }
        } else {
            Zitat zitat = new Zitat(event.getOption("zitat").getAsString(),
                    event.getOption("user").getAsUser().getIdLong());
            event.reply(zitat.getEmbed()).queue();
        }
    }

    @Override
    public CommandData commandData() {
        CommandData data = new CommandData(cmdname(),help());
        data.addOption(OptionType.USER,"user", "Der das Zitat gesagt hat.",true);
        data.addOption(OptionType.STRING,"zitat", "Das Zitat!",true);
        data.addOption(OptionType.USER, "kontextuser", "Der den Kontext gesagt hat");
        data.addOption(OptionType.STRING, "kontext", "Der Kontext des zitats");
        return data;
    }

    @Override
    public String help() {
        return "Der Command erstellt ein Zitat, den du einreichst.";
    }

}
