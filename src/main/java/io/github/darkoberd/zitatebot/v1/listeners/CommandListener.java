package io.github.darkoberd.zitatebot.v1.listeners;

import io.github.darkoberd.zitatebot.v1.ZitatChannel;
import io.github.darkoberd.zitatebot.v1.ZitateBot;
import io.github.darkoberd.zitatebot.v1.handler.CommandHandler;
import io.github.darkoberd.zitatebot.v1.utils.CommandParser;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {

		if(event.getMessage().isFromGuild() && !event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())){
			if(ZitateBot.zitatChannel.containsKey(event.getMessage().getGuild().getId())){
				ZitatChannel zc = ZitateBot.zitatChannel.get(event.getGuild().getId());
				if(event.getMessage().getChannel().getId().equals(zc.getChannelID())){
					return;
				}
			}
		}
		
		if(event.getMessage().getContentRaw().startsWith(ZitateBot.PREFIX) && !event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
			
			CommandHandler.handleCommand(CommandParser.parser(event.getMessage().getContentRaw(), event));
			
		}
		
	}

}