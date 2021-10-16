package io.github.darkoberd.zitatebot.v2.listeners;

import io.github.darkoberd.zitatebot.v2.Zitat;
import io.github.darkoberd.zitatebot.v2.ZitateBot;
import io.github.darkoberd.zitatebot.v2.utils.Utils;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ComponentListener extends ListenerAdapter {

    @Override
    public void onButtonClick(@NotNull ButtonClickEvent event) {
        if (event.getButton().getId().equals(Utils.button_id_ja)) {
            for (Zitat zitat : ZitateBot.zitate) {
                if(!zitat.isAbgesendet()) {
                    if(event.getUser().getIdLong() == zitat.getErsteller()){
                        event.getChannel()
                                .sendMessageEmbeds(zitat.getEmbed())
                                .queue(message -> {
                            zitat.setMassegeid(message.getIdLong());
                            message.editMessageEmbeds(zitat.getEmbed()).queue();
                        });
                        zitat.abgesendet();
                        event.reply("zitat wurde Versendet!")
                                .setEphemeral(true)
                                .queue(reply -> {
                        }, throwable -> {
                        });
                    }
                }
            }
        } else if (event.getButton().getId().equals(Utils.button_id_nein)) {
            Zitat deltezitat = null;
            for (Zitat zitat : ZitateBot.zitate) {
                if (!zitat.isAbgesendet()) {
                    if (event.getUser().getIdLong() == zitat.getErsteller()) {
                        event.reply("zitat wurde Gelöscht!")
                                .setEphemeral(true)
                                .queue();
                        deltezitat = zitat;
                    }
                }
            }
            if (deltezitat != null){
                ZitateBot.zitate.remove(deltezitat);
            }
        }
    }
}
