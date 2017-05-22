package com.example.carolina.notesexcersice.contactList.entities;

import java.util.Map;

/**
 * Created by carolina on 26/04/17.
 */

public class User {
    String email;
    boolean online;
    Map<String, Boolean> contacts;

    public final static boolean OFFLINE = false;
    public final static boolean ONLINE = true;


    public User(){

    }

    public User(String email, boolean online, Map<String, Boolean> contacts) {
        this.email = email;
        this.online = online;
        this.contacts = contacts;
    }

    public String getEmail() {
        return email;
    }

    public boolean isOnline() {
        return online;
    }

    public Map<String, Boolean> getContacts() {
        return contacts;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void setContacts(Map<String, Boolean> contacts) {
        this.contacts = contacts;
    }



}

