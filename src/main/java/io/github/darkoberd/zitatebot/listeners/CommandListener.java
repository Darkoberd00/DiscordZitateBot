package io.github.darkoberd.zitatebot.listeners;

import io.github.darkoberd.zitatebot.ZitateBot;
import io.github.darkoberd.zitatebot.handler.CommandHandler;
import io.github.darkoberd.zitatebot.utils.CommandParser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		if(event.getMessage().getContentRaw().startsWith(ZitateBot.PREFIX) && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()) {
			
			CommandHandler.handleCommand(CommandParser.parser(event.getMessage().getContentRaw(), event));
			
		}
		
	}

}