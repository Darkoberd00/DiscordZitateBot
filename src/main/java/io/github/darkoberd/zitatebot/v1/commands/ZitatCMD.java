package io.github.darkoberd.zitatebot.v1.commands;

import io.github.darkoberd.zitatebot.v1.Zitat;
import io.github.darkoberd.zitatebot.v1.ZitatChannel;
import io.github.darkoberd.zitatebot.v1.ZitateBot;
import io.github.darkoberd.zitatebot.v1.utils.Command;
import io.github.darkoberd.zitatebot.v1.utils.Flags;
import io.github.darkoberd.zitatebot.v1.utils.Massages;
import io.github.darkoberd.zitatebot.v1.utils.Utils;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Random;

public class ZitatCMD implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if(!event.isFromGuild()){
            Random rng = new Random();
            int i = rng.nextInt(1000);
            event.getChannel().sendMessageEmbeds(Massages.error(i == 2 ? "easteregg":"nichtvomserver")).queue();
            return;
        }

        if(ZitateBot.zitatChannel.containsKey(event.getGuild().getId())){
            ZitatChannel zc = ZitateBot.zitatChannel.get(event.getGuild().getId());
            if(!zc.getChannelID().equals(event.getChannel().getId())){
                return;
            }
        }else{
            if(args.length < 1) {
                ZitateBot.flags.put(event.getAuthor().getId(), Flags.ZITATCHATNOTFOUND);
            }
        }

        event.getMessage().delete().queue();

        if(args.length >= 1){

            String first = args[0].toLowerCase();

            if(first.equals("help") || first.equals("h") || first.equals("hilfe")){

                // arg[1] == all    ->  alle subcommands

                event.getChannel().sendMessageEmbeds(Massages.help("zitat", help())).queue(message ->
                        message.delete().queueAfter(Utils.deleteTime, Utils.deleteTimeUnit)
                );
                return;
            }else if(first.equals("setchannel") || first.equals("sc")){
                String serverOwnerID = event.getGuild().getOwnerId();

                if((!event.getAuthor().getId().equals(serverOwnerID)) || (!Utils.isAdmin(event.getAuthor(),event.getGuild()))){
                    event.getChannel().sendMessageEmbeds(Massages.error("admin")).queue(message ->
                            message.delete().queueAfter(Utils.deleteTime, Utils.deleteTimeUnit));
                    return;
                }

                if(args.length >= 2){

                    String sec = args[1];

                    if(sec.equalsIgnoreCase("help") || sec.equalsIgnoreCase("h") || sec.equalsIgnoreCase("hilfe")){
                        event.getChannel().sendMessageEmbeds(Massages.help("zitat setchannel", help("setchannel"))).queue(message ->
                                message.delete().queueAfter(Utils.deleteTime, Utils.deleteTimeUnit)
                        );
                        return;
                    }

                    if(args.length == 2){

                        sec = sec.replace("<#", "");
                        sec = sec.replace(">", "");

                        if(Utils.isChannel(event,sec)){

                            ZitateBot.zitatChannel.remove(event.getGuild().getId());
                            ZitateBot.zitatChannel.put(event.getGuild().getId(), new ZitatChannel(sec));

                            event.getChannel().sendMessageEmbeds(Massages.channelSelectSussecs(sec)).queue();
                            return;
                        }

                        event.getChannel().sendMessageEmbeds(Massages.error("scNichtExistent")).queue(message ->
                                message.delete().queueAfter(Utils.deleteTime, Utils.deleteTimeUnit)
                        );

                        return;
                    }

                    event.getChannel().sendMessageEmbeds(Massages.error("scLongArguments")).queue(message ->
                            message.delete().queueAfter(Utils.deleteTime, Utils.deleteTimeUnit)
                    );
                    return;
                }

                event.getChannel().sendMessageEmbeds(Massages.error("scShortArguments")).queue(message ->
                        message.delete().queueAfter(Utils.deleteTime, Utils.deleteTimeUnit)
                );

                return;
            }
        }

        if(ZitateBot.flags.containsKey(event.getAuthor().getId())){
            if(ZitateBot.flags.get(event.getAuthor().getId()) == Flags.ZITATCHATNOTFOUND){
                ZitateBot.flags.remove(event.getAuthor().getId());
                event.getChannel().sendMessageEmbeds(Massages.error("zitatchatnotfound")).queue(message-> message.delete().queueAfter(Utils.deleteTime, Utils.deleteTimeUnit));
                return;
            }
        }

        if(ZitateBot.zitate.containsKey(event.getAuthor().getId())) {
            if(ZitateBot.DEBUG){
                System.err.println("ZitatCMD -> event.getAuthor().openPrivateChannel() -> privateChannel.sendMessageEmbeds() -> if(ZitateBot.zitate.containsKey()): User hat schon ein Zitat");
            }
            event.getChannel().sendMessageEmbeds(Massages.error("existingzitatcreation")).queue(message ->
                message.delete().queueAfter(Utils.deleteTime, Utils.deleteTimeUnit)
            );
            return;
        }

        event.getAuthor().openPrivateChannel().queue(privateChannel ->

            privateChannel.sendMessageEmbeds(Massages.zitatCreate("start")).queue(massage ->

                ZitateBot.zitate.put(event.getAuthor().getId(), new Zitat(event.getAuthor().getId(), event.getGuild().getId()))

            , e -> {

                if(ZitateBot.DEBUG){
                    System.err.println("ZitatCMD -> event.getAuthor().openPrivateChannel() -> privateChannel.sendMessageEmbeds(): " + e.getMessage());
                }

                event.getChannel().sendMessageEmbeds(Massages.error("privatemessage")).queue(error ->
                    error.delete().queueAfter(Utils.deleteTime, Utils.deleteTimeUnit)
                );

            })

        );

    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        if(sucess){
            System.err.println("der Zitat Command wurde deaktiviert!");
        }
    }

    @Override
    public String help() {
        return "Der Command erstellt ein Zitat, den du einreichst." +
                "\nsyntax: !zitat" +
                "\n\num mehr Commands zu sehen \"!zitat (h)ilfe (a)ll\"";
    }

    @Override
    public String help(String id) {

        switch (id){
            case "delete":
                //TODO: delete help
                return "";
            case "setchannel":
                return "Der Command setzet die Channels!" +
                        "\nsyntax: !zitat setchannel <zitatChannel>";
            default:
                return "ID: \"" + id +"\" nicht gefunden!";
        }

    }

}
