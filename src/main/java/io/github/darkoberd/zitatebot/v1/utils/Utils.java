package io.github.darkoberd.zitatebot.v1.utils;

import io.github.darkoberd.zitatebot.v1.ZitateBot;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static Color help = Color.yellow;
    public static Color error = Color.red;
    public static Color correct = Color.green;
    public static Color zitat = Color.CYAN;
    public static Color zitatCreator = Color.WHITE;

    public static String helpPB = "https://i.imgur.com/08ONFQ9.png";
    public static String errorPB = "https://imgur.com/2uVpXZ2.png";
    public static String correctPB = "https://imgur.com/MdFylk7.png";
    public static String zitatPB = ZitateBot.getJda().getSelfUser().getAvatarUrl();
    public static String zitatCreatorPB = "https://imgur.com/TMQAR5W.png";

    public static int deleteTime = 1;
    public static TimeUnit deleteTimeUnit = TimeUnit.MINUTES;


    public static boolean isChannel(MessageReceivedEvent event, String channelid){
        try {
            Guild g = ZitateBot.getJda().getGuildById(event.getGuild().getId());
            if ( g == null){
                return false;
            }
            TextChannel tc = g.getTextChannelById(channelid);
            if(tc == null){
                return false;
            }
            String name = tc.getName();
        }catch (NullPointerException | NumberFormatException e){
            return false;
        }
        return true;
    }

    public static boolean isAdmin(User author, Guild guild) {
        Member member = guild.getMember(author);
        if(member != null) {
            if (!member.getRoles().isEmpty()) {
                for (Role role : member.getRoles()) {
                    if (!role.getPermissions().isEmpty()) {
                        for (Permission per : role.getPermissions()) {
                            if (per == Permission.ADMINISTRATOR) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
