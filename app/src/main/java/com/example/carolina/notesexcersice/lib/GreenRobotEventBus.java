package com.example.carolina.notesexcersice.lib;

/**
 * Created by carolina on 26/04/17.
 */

public class GreenRobotEventBus implements EventBus {
    org.greenrobot.eventbus.EventBus eventBus;

    public static class SingletonHolder{
        private static final GreenRobotEventBus INSTANCE = new GreenRobotEventBus();
    }

    public static GreenRobotEventBus getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public GreenRobotEventBus(){
        eventBus = org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Override
    public void register(Object subscriber) {
        eventBus.register(subscriber);

    }

    @Override
    public void unregister(Object subscriber) {
        eventBus.unregister(subscriber);

    }

    @Override
    public void post(Object event) {
        eventBus.post(event);

    }
}
