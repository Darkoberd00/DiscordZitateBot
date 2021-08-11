package io.github.darkoberd.zitatebot.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class Massages {

    public static MessageEmbed help(String cmd, String help){
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(Utils.help);
        eb.setAuthor("Help Center");
        eb.setThumbnail(Utils.helpPB);
        eb.addField("!"+cmd+":", "\"" + help + "\"", false);

        return eb.build();
    }

    public static MessageEmbed error(String id) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(Utils.error);
        eb.setAuthor("Error Center");
        eb.setThumbnail(Utils.errorPB);

        switch (id){
            case "privatemessage":
                eb.setTitle("Ich kann dir keine Private Nachricht schreiben!");
                eb.addField("Was kann ich tun?", "Ganz simpel! Gehe unter \"Benutzereinstellung\" > \"Privatspähre & Sicherheit\" und aktiviere \"Direktnachtrichten von Servermitglieder erlauben\"!", false);
                break;
            case "existingzitatcreation":
                eb.setTitle("Du hast bist Immoment schon bei einer erstellung eines Zitates!");
                eb.addField("Was kann ich tun?", "Du hast eine Privat Nachricht vom Bot erhalten. Lies dir die Nachricht durch!",false);
                eb.addField("Ich habe keine Nachricht erhalten!", "Du kanst mit dem Command \"!zitat (r)eset\" dich zurück setzen!",false);
                break;
            default:
                eb.setTitle("Error Not Found");
                eb.addField("Die Error ID:" + id + "wurde nicht gefunden!", "",false);
                break;
        }

        return eb.build();
    }

    public static MessageEmbed zitatCreate() {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Utils.zitatCreator);
        eb.setAuthor("Zitate Creator Center");
        eb.setThumbnail(Utils.zitatCreatorPB);

        eb.addField("Wie funktioniert das?","Ganz einfach die Erste Nachricht die du mir schreibst ist das Zitat. z.B. Wie heißen die Säcke in die man sich reinsetzen kann? "+
                "\n\n Die Zweite Nachricht ist der Jenige der das Zitat getätigt hat. \nz.B. Pascal \"Inneneinrichtungsexperte\" Klaßen" +
                "\n\n Bei der dritten Nachtricht wird abgefragt ob du zufrieden bist. Antworte mit (J)a oder (N)ein." +
                "\n\n Der Rest kümmert sich der Bot. Viel Spaß!", false);

        return eb.build();
    }
}
