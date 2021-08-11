package io.github.darkoberd.zitatebot.commands;

import io.github.darkoberd.zitatebot.Zitat;
import io.github.darkoberd.zitatebot.ZitateBot;
import io.github.darkoberd.zitatebot.utils.Command;
import io.github.darkoberd.zitatebot.utils.Massages;
import io.github.darkoberd.zitatebot.utils.Utils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ZitatCMD implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getMessage().delete().queue();

        if(args.length >= 1){
            System.out.println(args[0]);
            if(args[0].toLowerCase().equals("help") || args[0].toLowerCase().equals("h") || args[0].toLowerCase().equals("hilfe")){
                event.getChannel().sendMessageEmbeds(Massages.help("zitat", help())).queue(message -> {
                    message.delete().queueAfter(Utils.deleteTime, Utils.deleteTimeUnit);
                });
                return;
            }
        }

        if(ZitateBot.zitate.containsKey(event.getAuthor().getId())) {
            if(ZitateBot.DEBUG){
                System.err.println("ZitatCMD -> event.getAuthor().openPrivateChannel() -> privateChannel.sendMessageEmbeds() -> if(ZitateBot.zitate.containsKey()): User hat schon ein Zitat");
            }
            event.getChannel().sendMessageEmbeds(Massages.error("existingzitatcreation")).queue(message -> {
                message.delete().queueAfter(Utils.deleteTime, Utils.deleteTimeUnit);
            });
            return;
        }

        event.getAuthor().openPrivateChannel().queue(privateChannel -> {

            privateChannel.sendMessageEmbeds(Massages.zitatCreate()).queue(massage -> {

                ZitateBot.zitate.put(event.getAuthor().getId(), new Zitat(event.getAuthor().getId()));

            }, e -> {
                if(ZitateBot.DEBUG){
                    System.err.println("ZitatCMD -> event.getAuthor().openPrivateChannel() -> privateChannel.sendMessageEmbeds(): " + e.getMessage());
                }
                event.getChannel().sendMessageEmbeds(Massages.error("privatemessage")).queue(error -> {
                    error.delete().queueAfter(Utils.deleteTime, Utils.deleteTimeUnit);
                });

            });

        });

    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        if(sucess){
            System.err.println("der Zitat Command wurde deaktiviert!");
        }
    }

    @Override
    public String help() {
        return "Der Command erstellt ein Zitat, den du einreichst.";
    }

    @Override
    public String help(String id) {
        return null;
    }
}
