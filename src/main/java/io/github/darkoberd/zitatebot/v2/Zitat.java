package io.github.darkoberd.zitatebot.v2;

public class Zitat {
    private String kontext;
    private long kontextuser;
    private String zitat;
    private long user;

    private long massegeid;
    private long channelid;

    public Zitat(String zitat, long user, String kontext, long kontextuser) {
        this.kontext = kontext;
        this.kontextuser = kontextuser;
        this.zitat = zitat;
        this.user = user;
    }

    public Zitat(String zitat, long user){
        this(zitat, user,null,0);
    }

    public String getKontext() {
        return kontext;
    }

    public void setKontext(String kontext) {
        this.kontext = kontext;
    }

    public long getKontextuser() {
        return kontextuser;
    }

    public void setKontextuser(long kontextuser) {
        this.kontextuser = kontextuser;
    }

    public String getZitat() {
        return zitat;
    }

    public void setZitat(String zitat) {
        this.zitat = zitat;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public String getEmbed(){
        String s = "";
        if(this.kontextuser != 0){
            s += "Kontext:\n\"" + this.kontext + "\" ~<@" + this.kontextuser + ">\n";
        }
        s += "Zitat:\n\"" + zitat + "\" ~<@" + this.user + ">";
        return s;
    }
}
