package io.github.darkoberd.zitatebot;

public class ZitatChannel {
    /**
     * Die Channel ID des Erstelung eins zitates.
     */
    private final String createZitatChannelID;
    /**
     * Die Channel ID für die zitate selber.
     */
    private final String zitatChannelID;

    public ZitatChannel(String createZitatChannelID, String zitatChannelID) {
        this.createZitatChannelID = createZitatChannelID;
        this.zitatChannelID = zitatChannelID;
    }

    public String getCreateZitatChannelID() {
        return createZitatChannelID;
    }

    public String getZitatChannelID() {
        return zitatChannelID;
    }

    @Override
    public String toString() {
        return "ZitatChannel{" +
                "createZitatChannelID='" + createZitatChannelID + '\'' +
                ", zitatChannelID='" + zitatChannelID + '\'' +
                '}';
    }

}
