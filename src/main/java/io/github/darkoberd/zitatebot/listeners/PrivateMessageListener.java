package io.github.darkoberd.zitatebot.listeners;

import io.github.darkoberd.zitatebot.Zitat;
import io.github.darkoberd.zitatebot.ZitateBot;
import io.github.darkoberd.zitatebot.utils.Flags;
import io.github.darkoberd.zitatebot.utils.Massages;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class PrivateMessageListener extends ListenerAdapter {

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {

        if(!event.getAuthor().getId().equals(ZitateBot.getJda().getSelfUser().getId())){

            if(ZitateBot.zitate.containsKey(event.getAuthor().getId())){

                Zitat zitat = ZitateBot.zitate.get(event.getAuthor().getId());

                String s = event.getMessage().getContentRaw();

                if(zitat.getZitat() != null && zitat.getName() == null){
                    zitat.setName(s);
                }else if(zitat.getZitat() == null){
                    if(s.charAt(0) != '\"'){
                        s = "\"" + s;
                    }
                    if(s.charAt(s.length()-1) != '\"'){
                        s = s + "\"";
                    }
                    zitat.setZitat(s);
                }

                if(zitat.haveAllImplements()){

                    if(ZitateBot.flags.containsKey(event.getAuthor().getId())) {

                        if(ZitateBot.flags.get(event.getAuthor().getId()) == Flags.ABFRAGE) {

                            s = s.toLowerCase();

                            if(s.equals("j") || s.equals("ja") || s.equals("y") || s.equals("yes")){

                                ZitateBot.zitate.put(zitat.getUuid()+"", zitat);

                                /*
                                    TODO: zitat in einem Channel versenden!
                                 */

                                event.getChannel().sendMessageEmbeds(Massages.zitatSussecs()).queue();

                            }else{

                                event.getChannel().sendMessageEmbeds(Massages.error("zitatgelöscht")).queue();

                            }

                            ZitateBot.zitate.remove(event.getAuthor().getId());
                        }
                        return;
                    }

                    event.getChannel().sendMessageEmbeds(zitat.getZitatEmbed()).queue();
                    event.getChannel().sendMessageEmbeds(Massages.zitatCreate("abfrage")).queue();
                    ZitateBot.flags.put(event.getAuthor().getId(), Flags.ABFRAGE);

                }

                ZitateBot.zitate.replace(event.getAuthor().getId(), zitat);

            }

        }

    }
}
