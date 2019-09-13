package ru.bellintegrator.admin_api.jms;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 * Отправляет название города JMS-сообщением модулю yahoo_weather через очередь java:jboss/exported/jms/queue/QueueExample
 */
@ApplicationScoped
public class JMSSender {
    private final Logger log = LoggerFactory.getLogger(JMSSender.class);

    @Resource(mappedName = "java:jboss/exported/jms/queue/QueueExample")
    private Queue queue;

    @Inject
    private JMSContext context;

    /**
     * Отправить сообщение в очередь для модуля yahoo_service
     *
     * @param city город
     */
    public void send(String city) {
        log.info("@@@@@@@@@@@@@@@@@@@@@");
        context.createProducer().send(queue, city);
        log.info("City message has been sent");
    }
}