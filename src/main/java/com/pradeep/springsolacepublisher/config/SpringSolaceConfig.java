package com.pradeep.springsolacepublisher.config;

import com.solacesystems.jcsmp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SpringSolaceConfig {
    @Autowired
    private SpringJCSMPFactory solaceFactory;
    private JCSMPSession session;

    @PostConstruct
    public void solaceConnection() throws JCSMPException {
        session = solaceFactory.createSession();
    }

    public JCSMPSession getSpringSolaceSession(){
        return this.session;
    }

    public Topic springSolaceCreateTopic(String topicName){
        Topic topic = JCSMPFactory.onlyInstance().createTopic(topicName);
        return topic;
    }

    public void springSolaceAddSubscriptionTopic(JCSMPSession session, Topic topic) throws JCSMPException {
        if(!session.getSubscriptionCache().contains(topic)){
            session.addSubscription(topic);
        }
        return;
    }

//    public void springSolaceSendMessage(JCSMPSession session, Object msg, Topic topic) throws JCSMPException {
//        PublishEventHandler publishEventHandler = new PublishEventHandler();
//        XMLMessageProducer producer = session.getMessageProducer(publishEventHandler);
//        producer.send((XMLMessage) msg, topic);
//    }
}
