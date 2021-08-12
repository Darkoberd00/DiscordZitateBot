package io.github.darkoberd.zitatebot.utils;

import io.github.darkoberd.zitatebot.ZitateBot;

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
}
