package io.github.darkoberd.zitatebot.v2;

import io.github.darkoberd.zitatebot.v2.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

import java.util.Date;

public class Zitat {
    private String kontext;
    private String zitat;
    private long userid;
    private String username;
    private long ersteller;
    private int points;
    private Date date;

    private boolean abgesendet = false;

    private long massegeid;

    public Zitat(String zitat, String user, long ersteller, Date date, String kontext) {
        this.kontext = kontext;
        this.zitat = zitat;
        System.out.println(user);
        if (Utils.isUser(user)) {
            this.userid = Utils.getUserbyId(user).getIdLong();
        } else {
            this.username = user;
        }
        this.ersteller = ersteller;
        this.points = 0;
        this.date = date;
    }

    public Zitat(String zitat, String user, long ersteller, Date date){
        this(zitat,user,ersteller,date,null);
    }

    public String getKontext() {
        return kontext;
    }

    public void setKontext(String kontext) {
        this.kontext = kontext;
    }

    public String getZitat() {
        return zitat;
    }

    public void setZitat(String zitat) {
        this.zitat = zitat;
    }

    public long getErsteller() {
        return ersteller;
    }

    public void setErsteller(long ersteller) {
        this.ersteller = ersteller;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getMassegeid() {
        return massegeid;
    }

    public void setMassegeid(long massegeid) {
        this.massegeid = massegeid;
    }

    public boolean isAbgesendet() {
        return abgesendet;
    }

    public void abgesendet(){
        this.abgesendet = true;
    }

    public MessageEmbed getEmbed(){
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(Utils.zitat);
        User ersteller = Utils.getUserbyId(this.ersteller);
        eb.setAuthor(ersteller.getName(), null, ersteller.getAvatarUrl());
        eb.setTitle("\""+zitat+"\"");
        User user = null;
        if(userid != 0) {
            user = Utils.getUserbyId(this.userid);
        }
        eb.addField("~"+(user != null ? user.getName() : username),(kontext != null ? "Kontext: \"" + kontext + "\"\n" : "") + "Points: "+ points, false);
        if(massegeid != 0){
            eb.setFooter(massegeid+"");
        }
        eb.setTimestamp(date.toInstant());
        return eb.build();
    }
}
