package io.github.darkoberd.zitatebot.handler;


import io.github.darkoberd.zitatebot.utils.Command;
import io.github.darkoberd.zitatebot.utils.CommandParser;

import java.util.HashMap;

public class CommandHandler {

	public static final CommandParser parse = new CommandParser();
	public static HashMap<String, Command> commands = new HashMap<>();
	
	public static void handleCommand(CommandParser.commandContainer cmd) {
		
		if(commands.containsKey(cmd.invoke)) {
			
			boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);
			
			if(!safe) {
				commands.get(cmd.invoke).action(cmd.args, cmd.event);
				commands.get(cmd.invoke).executed(safe, cmd.event);
			} else {
				commands.get(cmd.invoke).executed(safe, cmd.event);
			}
			
		}
		
	}
	
}
