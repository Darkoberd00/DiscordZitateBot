package io.github.darkoberd.zitatebot.v1.utils;

import io.github.darkoberd.zitatebot.v1.ZitateBot;

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
        id = id.toLowerCase();
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
                eb.setTitle("Du bist immoment schon bei einer erstellung eines Zitates!");
                eb.addField("Was kann ich tun?", "Du hast eine Privat Nachricht vom Bot erhalten. Lies dir die Nachricht durch!",false);
                eb.addField("Ich habe keine Nachricht erhalten!", "Du kanst mit dem Command \"!zitat (r)eset\" dich zurück setzen!",false);
                break;
            case "zitatgelöscht":
                eb.setTitle("Dein Zitat wurde gelöscht!");
                eb.addField("Was ist passiert?","Dein Zitat wurde von ein Command oder eines Admins gelöscht",false);
                eb.addField("Oder!","Du hast bei der Erstellung eines Zitates auf Nein geantwortet!",false);
                break;
            case "nichtvomserver":
                eb.setTitle("Der Command muss von einem Server ausgehen!");
                eb.addField("Wie kann ich den Command benutzen?", "Du kannst den vorgesehenen Channel des Jeweiligen Server benutzen, um diesen Command aus zu führen.", false);
                eb.addField("Es gibt keinen Channel!", "Du kannst einfach einen Admin fragen oder wenn du selbst Admin bist mit dem Command \"!zitat (h)ilfe\" dir alles durch Lesen.", false);
                break;
            case "scshortarguments":
                eb.setTitle("Der Command hat zuwenig Argumente!");
                eb.addField("Wie geht der Command?","Mit dem Command \"!zitat setchannel (h)elp\"",false);
                break;
            case "sclongarguments":
                eb.setTitle("Der Command hat zuviele Argumente!");
                eb.addField("Wie geht der Command?","Mit dem Command \"!zitat setchannel (h)elp\"",false);
                break;
            case "scnichtexistent":
                eb.setTitle("Der eingetragende Channel wurde nicht gefunden!");
                eb.addField("Warum?","der Bot kann die Channel nicht erkennen oder sie wurden gelöscht.",false);
                break;
            case "zitatchatnotfound":
                eb.setTitle("Es wurde kein Channel hinzugefügt");
                eb.addField("Es muss ein zitate Channel existieren!", "Ganz einfach. Frag ein Admin oder den Server besitzer das er ein Channel erstellt",false);
            case "admin":
                eb.setTitle("Dieser Command kann nur durch ein Admin ausgefürt werden!");
                break;
            case "easteregg":
                eb.setAuthor("Dein Command muss über einem Discord Server Geschrieben werden", "https://gist.github.com/7743dc8f4f11a85cbeb8f6d6ec05f9b3.git", Utils.zitatPB);
                eb.setTitle("Dein Command muss über einem Discord Server Geschrieben werden");
                eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , true);
                eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , true);
                eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , true);
                eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , false);
                eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , false);
                eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , false);
                eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , true);
                eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , true);
                eb.addField("Dein Command muss über einem Discord Server Geschrieben werden", "Dein Command muss über einem Discord Server Geschrieben werden" , true);
                eb.setFooter("Wustest du das Dein Command nur auf einem Discord Server Funktioniert, Fun Fact!");
                break;
            default:
                eb.setTitle("Error Not Found");
                eb.addField("Die Error ID: \"" + id + "\" wurde nicht gefunden!", "",false);
                break;
        }

        return eb.build();
    }

    public static MessageEmbed zitatCreate(String id) {
        id = id.toLowerCase();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Utils.zitatCreator);
        eb.setAuthor("Zitate Creator Center");
        eb.setThumbnail(Utils.zitatCreatorPB);

        switch (id) {
            case "start":
                eb.addField("Wie funktioniert das?", "Ganz einfach die Erste Nachricht die du mir schreibst ist das Zitat. z.B. Wie heißen die Säcke in die man sich reinsetzen kann? " +
                    "\n\n Die Zweite Nachricht ist der Jenige der das Zitat getätigt hat. \nz.B. Pascal \"Inneneinrichtungsexperte\" Klaßen" +
                    "\n\n Bei der dritten Nachtricht wird abgefragt ob du zufrieden bist. Antworte mit (J)a oder (N)ein." +
                    "\n\n Der Rest kümmert sich der Bot. Viel Spaß!", false);
                break;
            case "abfrage":
                eb.setTitle("Gefält dir das Zitat?");
                eb.addField("Antwort:", "(J)a oder (N)ein",false);
                break;
            default:
                eb.setTitle("ID Not Found");
                eb.addField("Die ID: \"" + id + "\" wurde nicht gefunden!", "",false);
                break;
        }

        return eb.build();
    }

    public static MessageEmbed zitatSussecs(){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Utils.correct);
        eb.setAuthor("Zitate Creator Center");
        eb.setThumbnail(Utils.correctPB);

        eb.addField("Dein Zitat wurde erfolgreich eingereicht!", "Dein Zitat findest du dort, wo du dein Command eingegeben hast.", false);

        return eb.build();
    }

    public static MessageEmbed channelSelectSussecs(String channelID){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Utils.correct);
        eb.setAuthor(ZitateBot.getJda().getSelfUser().getName(),null,Utils.zitatPB);
        eb.setThumbnail(Utils.correctPB);

        eb.setTitle("Der Channel wurden erfolgreich Configuriert!");
        eb.addField("Channelname:","<#"+channelID+">", false);

        return eb.build();
    }
}
