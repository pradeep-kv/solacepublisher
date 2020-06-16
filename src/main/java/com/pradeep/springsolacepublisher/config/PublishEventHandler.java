package com.pradeep.springsolacepublisher.config;

import com.solacesystems.jcsmp.JCSMPException;
import com.solacesystems.jcsmp.JCSMPStreamingPublishEventHandler;

public class PublishEventHandler implements JCSMPStreamingPublishEventHandler {


    @Override
    public void handleError(String s, JCSMPException e, long l) {
        System.out.println("Producer error "+s);
    }

    @Override
    public void responseReceived(String s) {
        System.out.println("Producer success "+s);
    }
}
