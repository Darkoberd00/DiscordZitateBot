package io.github.darkoberd.zitatebot.v2.utils;

import io.github.darkoberd.zitatebot.v2.ZitateBot;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class Utils {
    public static Color help = Color.yellow;
    public static Color error = Color.red;
    public static Color correct = Color.green;
    public static Color zitat = Color.CYAN;
    public static Color zitatCreator = Color.WHITE;
    public static String button_id_ja = "button-ja";
    public static String button_id_nein = "button-nein";

    public static User getUserbyId(long id){
        return ZitateBot.getJda().getUserById(id);
    }

    public static User getUserbyId(String s){
        String[] anfang = s.split("<@!");
        String[] ende = anfang[0].split(">");
        return ZitateBot.getJda().getUserById(ende[0]);
    }

    public static boolean isUser(String s){
        String[] anfang = s.split("<@!");
        String[] ende = anfang[0].split(">");
        System.out.println(ende[0]);
        return false;
    }
}
