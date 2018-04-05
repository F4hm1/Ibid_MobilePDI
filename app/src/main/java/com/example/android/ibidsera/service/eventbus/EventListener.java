package com.example.android.ibidsera.service.eventbus;

import android.content.Context;
import android.widget.Toast;

import com.google.common.eventbus.Subscribe;

/**
 * Created by Fahmi Hakim on 13/03/2018.
 * for SERA
 */

public class EventListener {

    public String lastMessage;

    @Subscribe
    public void listen(OurTestEvent event) {
        lastMessage = event.getMessage();
    }

    public String getLastMessage() {
        return lastMessage;
    }


    public static class OurTestEvent {

        private final String message;

        public OurTestEvent(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

}
