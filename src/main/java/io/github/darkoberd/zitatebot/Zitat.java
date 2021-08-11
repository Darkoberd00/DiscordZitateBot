package io.github.darkoberd.zitatebot;

import java.text.SimpleDateFormat;
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
     * liste der gevoteten User;
     */
    private List<String> votedusers;

    /**
     * Pascal "Inneneinrichtungsexperte" Klaßen
     */
    private String vorname;
    private String zweitName;
    private String nachName;

    /**
     * UUID für die einfachre wieder findung;
     */
    private final UUID uuid;

    /**
     * tag der einreichung
     */
    private final Calendar calendar;

    /**
     * wer hat das zitat eingereicht!
     */
    private final String erstelleruserid;

    public Zitat(String zitat, String vorname, String zweitName, String nachName, UUID uuid, Calendar calendar, String erstelleruserid) {
        this.zitat = zitat;
        this.vorname = vorname;
        this.zweitName = zweitName;
        this.nachName = nachName;
        this.uuid = uuid;
        this.calendar = calendar;
        this.erstelleruserid = erstelleruserid;
    }

    public Zitat(String erstelleruserid) {
        this.uuid = UUID.randomUUID();
        this.erstelleruserid = erstelleruserid;
        this.calendar = Calendar.getInstance();
    }

    public String getZitat() {
        return zitat;
    }

    public String getVorname() {
        return vorname;
    }

    public String getZweitName() {
        return zweitName;
    }

    public String getNachName() {
        return nachName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(calendar.getTime());
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

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setZweitName(String zweitName) {
        this.zweitName = zweitName;
    }

    public void setNachName(String nachName) {
        this.nachName = nachName;
    }

    public void addPoints(){
        points++;
    }

    public void removePoints(){
        points--;
    }

    @Override
    public String toString() {
        return "Zitat{" +
                "zitat='" + zitat + '\'' +
                ", vorname='" + vorname + '\'' +
                ", zweitName='" + zweitName + '\'' +
                ", nachName='" + nachName + '\'' +
                ", uuid=" + uuid +
                ", calendar=" + calendar +
                ", erstelleruserid='" + erstelleruserid + '\'' +
                '}';
    }
}
