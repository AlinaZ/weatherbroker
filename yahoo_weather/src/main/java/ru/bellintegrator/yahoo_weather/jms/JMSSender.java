package ru.bellintegrator.yahoo_weather.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.weatherparser.Result;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

@ApplicationScoped
public class JMSSender {
    private final Logger log = LoggerFactory.getLogger(JMSSender.class);

    @Resource(mappedName = "java:jboss/exported/jms/queue/weather")
    private Queue queue;

    @Inject
    private JMSContext context;

    /**
     * Отправить сообщение в очередь для модуля db_service
     *
     * @param weather для города
     */
    public void send(Result weather) {
        log.info("@@@@@@@@@@@@@@@@@@@@@");
        context.createProducer().send(queue, weather);
        log.info("Weather message has been sent");
    }
}