package io.github.darkoberd.zitatebot.v2.commands;

import io.github.darkoberd.zitatebot.v2.Zitat;
import io.github.darkoberd.zitatebot.v2.ZitateBot;
import io.github.darkoberd.zitatebot.v2.utils.Command;
import io.github.darkoberd.zitatebot.v2.utils.Utils;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.components.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.Component;
import net.dv8tion.jda.internal.interactions.ButtonImpl;

import java.util.ArrayList;
import java.util.Date;

public class ZitatCMD implements Command {

    @Override
    public String cmdname() {
        return "zitat";
    }

    @Override
    public void action(SlashCommandEvent event) {
        Zitat zitat = null;
        if (event.getOption("kontext") != null) {
            zitat = new Zitat(event.getOption("zitat").getAsString(),
                    event.getOption("user").getAsString(),
                    event.getUser().getIdLong(),
                    new Date(),
                    event.getOption("kontext").getAsString());
        } else {
            zitat = new Zitat(event.getOption("zitat").getAsString(),
                    event.getOption("user").getAsString(),
                    event.getUser().getIdLong(),
                    new Date());
        }

        Component button_ja = new ButtonImpl(Utils.button_id_ja, "Ja", ButtonStyle.SUCCESS, false, null);
        Component button_nein = new ButtonImpl(Utils.button_id_nein, "Nein", ButtonStyle.DANGER, false, null);
        ArrayList<Component> components = new ArrayList<>();
        components.add(button_ja);
        components.add(button_nein);

        ZitateBot.zitate.add(zitat);

        event.reply("Hier finden Sie ein Ausschnit des zitats:")
                .addEmbeds(zitat.getEmbed())
                .setEphemeral(true)
                .addActionRow(components)
                .queue();
    }

    @Override
    public CommandData commandData() {
        CommandData data = new CommandData(cmdname(),help());

        data.addOption(OptionType.STRING,"user", "Der das Zitat gesagt hat.",true);
        data.addOption(OptionType.STRING,"zitat", "Das Zitat!",true);
        data.addOption(OptionType.STRING, "kontext", "Der Kontext des zitats");

        return data;
    }

    @Override
    public String help() {
        return "Der Command erstellt ein Zitat, den du einreichst.";
    }

}
