package com.raghavendra.raghavendra;

public class Entry {
    String Date;
    String entryMno;
    String entrysname;
    String entryswt;
    String entryqty;
    public Entry(String Date, String entryMno, String entrysname, String entryswt, String entryqty)
    {
        this.Date = Date;
        this.entryMno = entryMno;
        this.entrysname = entrysname;
        this.entryswt = entryswt;
        this.entryqty=entryqty;
    }
    public String getDate(){
        return Date;
    }

    public String getEntryMno() {
        return entryMno;
    }

    public String getEntrysname() {
        return entrysname;
    }

    public String getEntryswt() {
        return entryswt;
    }

    public String getEntryqty() {
        return entryqty;
    }
}
