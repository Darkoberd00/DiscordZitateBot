package io.github.darkoberd.zitatebot.v1;

import io.github.darkoberd.zitatebot.v1.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class ZitatChannel {
    // TODO: Änderung des Zitate Channels

    /**
     * Die Channel ID
     */
    private final String channelID;

    /**
     *
     */
    private String messageID;

    public ZitatChannel(String channelID) {
        this.channelID = channelID;
    }

    public String getChannelID() {
        return channelID;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public MessageEmbed channelEmbed(){
        EmbedBuilder eb = new EmbedBuilder();

        eb.setAuthor(ZitateBot.getJda().getSelfUser().getName(),null, Utils.zitatPB);
        eb.setThumbnail(Utils.zitatPB);

        eb.setTitle("Erstelle ein Zitat!");
        eb.setDescription("In diesem Channel kann der Bot Commands aufnehemen");

        eb.addField("Über Dieser Nachricht sind Alle Zitate die die User erstellt hat.","Mit dem Commands die du hier unten sehen kanst",false);
        eb.addBlankField(false);
        eb.addField("Commands:","",false);
        eb.addField("!Zitat:","",false);
        eb.addField("!Zitat reset:", "",false);
        eb.addField("!Zitat delete:", "",false);

        return eb.build();
    }

    @Override
    public String toString() {
        return "ZitatChannel{" +
                "channelID='" + channelID + '\'' +
                '}';
    }
}
