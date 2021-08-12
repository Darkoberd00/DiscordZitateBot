package io.github.darkoberd.zitatebot.listeners;

import io.github.darkoberd.zitatebot.ZitatChannel;
import io.github.darkoberd.zitatebot.ZitateBot;
import io.github.darkoberd.zitatebot.handler.CommandHandler;
import io.github.darkoberd.zitatebot.utils.CommandParser;
import io.github.darkoberd.zitatebot.utils.Massages;
import io.github.darkoberd.zitatebot.utils.Utils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class CommandListener extends ListenerAdapter {
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {

		if(event.getMessage().isFromGuild() && !event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())){
			if(ZitateBot.zitatChannels.containsKey(event.getMessage().getGuild().getId())){
				ZitatChannel zc = ZitateBot.zitatChannels.get(event.getGuild().getId());
				if(event.getMessage().getChannel().getId().equals(zc.getZitatChannelID())){
					event.getMessage().delete().queue();
					event.getChannel().sendMessageEmbeds(Massages.error("zitatechat")).queue(message -> message.delete().queueAfter(Utils.deleteTime,Utils.deleteTimeUnit));
					return;
				}
			}
		}
		
		if(event.getMessage().getContentRaw().startsWith(ZitateBot.PREFIX) && !event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
			
			CommandHandler.handleCommand(CommandParser.parser(event.getMessage().getContentRaw(), event));
			
		}
		
	}

}