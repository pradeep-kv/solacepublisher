package com.pradeep.springsolacepublisher.controller;

import com.pradeep.springsolacepublisher.config.PublishEventHandler;
import com.pradeep.springsolacepublisher.config.SpringSolaceConfig;
import com.solacesystems.jcsmp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SpringSolacePublisherController {
    @Autowired
    SpringSolaceConfig springSolaceConfig;

    @GetMapping("/test")
    public String test(){
        return "Test ok";
    }

    @GetMapping("/sendMsg/{msg}")
    public String sendMessage(@PathVariable String msg) throws JCSMPException {
        PublishEventHandler publishEventHandler = new PublishEventHandler();
        JCSMPSession session = springSolaceConfig.getSpringSolaceSession();
        Topic topic = springSolaceConfig.springSolaceCreateTopic("tutorial/topic");

//        springSolaceConfig.springSolaceAddSubscriptionTopic(session,topic);

        XMLMessageProducer producer = session.getMessageProducer(publishEventHandler);
        TextMessage jcsmpMsg = JCSMPFactory.onlyInstance().createMessage(TextMessage.class);
        jcsmpMsg.setText(Long.toString(new Date().getTime()));

        producer.send(jcsmpMsg, topic);
        return "Ok";
    }

    @GetMapping("/sendBulkMsg/{msgCount}")
    public void sendBulkMsg(@PathVariable int msgCount){
        if(msgCount <= 0){
            return;
        }
        for (int i=0; i< msgCount;i++){
            try {
                this.sendMessage(Integer.toString(i));
            } catch (JCSMPException e) {
                e.printStackTrace();
            }
        }
    }
}
