package ru.bellintegrator.yahoo_weather.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.bellintegrator.weatherparser.Result;
import ru.bellintegrator.yahoo_weather.service.YahooRequest;
import ru.bellintegrator.yahoo_weather.service.YahooRequestInterface;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import java.io.IOException;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "Listener", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/jms/queue/QueueExample"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "connectionFactoryLookup",propertyValue="java:jboss/exported/jms/RemoteConnectionFactory"),
        @ActivationConfigProperty(propertyName = "user", propertyValue = "joao"),
        @ActivationConfigProperty(propertyName = "password", propertyValue = "br1o+sa"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
@Named
public class JMSListener implements MessageListener{


    private YahooRequestInterface yahooRequest;
    private JMSSender jmssender;

    private final Logger LOG = LoggerFactory.getLogger(JMSListener.class);

    @Inject
    public JMSListener(YahooRequestInterface yahooRequest, JMSSender jmssender) {

        this.yahooRequest = yahooRequest;
        this.jmssender=jmssender;
    }

    public JMSListener() {
        //this.yahooRequest = null;
    }























    @Override
    public void onMessage(Message message) {
        String city;
        Result weather;
        try {
            city = ((TextMessage) message).getText();
            LOG.info("yahoo_service received message {}",city);
            LOG.info("yahoo request sending");
            weather =yahooRequest.request(city,"ru");
            LOG.info("yahoo response received {}",weather);
            jmssender.send(weather);
            LOG.info("message to db-service sent");

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}