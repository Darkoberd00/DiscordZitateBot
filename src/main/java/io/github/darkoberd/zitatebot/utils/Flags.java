package io.github.darkoberd.zitatebot.utils;

public enum Flags {
    REMOVEFROMBOT,
    ABFRAGE;

    public Flags flag;
    public Flags getFlag() {
        return flag;
    }
    public boolean isState(Flags flag) {
        return this.flag == flag;
    }
}
