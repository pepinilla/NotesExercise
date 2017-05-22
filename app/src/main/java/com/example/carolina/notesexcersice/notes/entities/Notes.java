package com.example.carolina.notesexcersice.notes.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by carolina on 28/04/17.
 */

public class Notes {

    private String title;
    private String content;
    private Date timestamp;
    private long id_;
    private boolean createbyMe;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Notes (){

    }
    public long getId_() {
        return id_;
    }

    public void setId_(long id_) {
        this.id_ = id_;
    }

    public Notes(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getDateFormatted(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        return simpleDateFormat.format(timestamp);
    }

    public boolean isCreatebyMe() {
        return createbyMe;
    }

    public void setCreatebyMe(boolean createbyMe) {
        this.createbyMe = createbyMe;
    }




}
