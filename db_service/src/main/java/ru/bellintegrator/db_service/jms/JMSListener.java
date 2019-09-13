package ru.bellintegrator.db_service.jms;

import ru.bellintegrator.db_service.service.DBService;
import ru.bellintegrator.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bellintegrator.weatherparser.Result;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "Listener", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/jms/queue/weather"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "java:jboss/exported/jms/RemoteConnectionFactory"),
        @ActivationConfigProperty(propertyName = "user", propertyValue = "joao"),
        @ActivationConfigProperty(propertyName = "password", propertyValue = "br1o+sa"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
@Named
public class JMSListener implements MessageListener {

    private final Logger LOG = LoggerFactory.getLogger(JMSListener.class);

    private DBService dbService;

    @Inject
    public JMSListener(DBService dbservice) {
        this.dbService = dbservice;
    }

    public JMSListener() {

    }

    @Override
    public void onMessage(Message message) {
        LOG.info("Message from yahoo_weather received");
        if (message == null) {
            try {
                throw new CustomException("Message cant be null");
            } catch (CustomException e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (message.isBodyAssignableTo(Result.class)) {
                    Result result = message.getBody(Result.class);
                    LOG.info("Message mapped to Result.class");
                    try {
                        dbService.saveResult(result);
                        dbService.saveCurObserv(result);
                        dbService.saveCurObservDetails(result);
                        LOG.info("YAHOO WEATHER info successfully saved into database");
                    } catch (CustomException e) {
                        e.printStackTrace();
                    }

                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }


    }
}