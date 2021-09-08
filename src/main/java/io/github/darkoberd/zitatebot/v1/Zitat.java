package io.github.darkoberd.zitatebot.v1;

import io.github.darkoberd.zitatebot.v1.utils.Utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class Zitat {


    /**
     * Das Zitat z.B. "Wie heißen die Säcke in die man sich reinsetzen kann?"
     */
    private String zitat;

    /**
     * Die belibt heit der user des Zitates.
     */
    private int points = 0;

    /**
     * Liste der gevoteten User.
     */
    private List<String> votedusers;

    /**
     * Der Aussaggebender des zitates. z.B Pascal "Inneneinrichtungsexperte" Klaßen
     */
    private String name;

    /**
     * UUID für die einfachre wieder findung.
     */
    private final UUID uuid;

    /**
     * Tag der einreichung.
     */
    private final Calendar calendar;

    /**
     * ID des Erstellers des Zitat
     */
    private final String erstelleruserid;

    /**
     * Die ID des Servers.
     */
    private final String guildid;

    public Zitat(String zitat, int points, List<String> votedusers, String name, UUID uuid, Calendar calendar, String erstelleruserid, String guildid) {
        this.zitat = zitat;
        this.points = points;
        this.votedusers = votedusers;
        this.name = name;
        this.uuid = uuid;
        this.calendar = calendar;
        this.erstelleruserid = erstelleruserid;
        this.guildid = guildid;
    }

    public Zitat(String erstelleruserid, String guildid) {
        this.uuid = UUID.randomUUID();
        this.erstelleruserid = erstelleruserid;
        this.calendar = Calendar.getInstance();
        this.guildid = guildid;
    }

    public String getGuildid(){
        return guildid;
    }

    public String getZitat() {
        return zitat;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public String getErstelleruserid() {
        return erstelleruserid;
    }

    public int getPoints() {
        return points;
    }

    public List<String> getVotedusers() {
        return votedusers;
    }

    public void setZitat(String zitat) {
        this.zitat = zitat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPoints(){
        points++;
    }

    public boolean haveAllImplements(){
        return zitat != null && name != null;
    }

    public void removePoints(){
        points--;
    }

    public MessageEmbed getZitatEmbed() {
        EmbedBuilder eb = new EmbedBuilder();

        User user = ZitateBot.getJda().getUserById(erstelleruserid);
        if(user != null){
            eb.setAuthor(user.getName(), null, user.getAvatarUrl());
        }
        eb.setColor(Utils.zitat);
        eb.setThumbnail(Utils.zitatPB);
        eb.setTitle(getZitat());
        eb.addField("~"+getName(), "Points: "+ getPoints(), false);
        eb.setFooter("UUID:" +getUuid());
        eb.setTimestamp(getCalendar().toInstant());

        return eb.build();
    }

    @Override
    public String toString() {
        return "Zitat{" +
                "zitat='" + zitat + '\'' +
                ", points=" + points +
                ", votedusers=" + votedusers +
                ", name='" + name + '\'' +
                ", uuid=" + uuid +
                ", calendar=" + calendar +
                ", erstelleruserid='" + erstelleruserid + '\'' +
                ", guildid='" + guildid + '\'' +
                '}';
    }

}
