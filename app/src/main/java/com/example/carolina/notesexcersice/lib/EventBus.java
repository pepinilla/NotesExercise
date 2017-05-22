package com.example.carolina.notesexcersice.lib;

/**
 * Created by carolina on 24/04/17.
 */

public interface EventBus {
        void register(Object subscriber);
        void unregister(Object subscriber);
        void post(Object event);
}
