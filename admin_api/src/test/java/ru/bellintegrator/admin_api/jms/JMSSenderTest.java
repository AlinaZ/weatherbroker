package ru.bellintegrator.admin_api.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.bellintegrator.admin_api.jms.JMSSender;
import ru.bellintegrator.weatherparser.CityAndRegion;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.atLeast;

@RunWith(MockitoJUnitRunner.class)

public class JMSSenderTest {
    @Mock
    private JMSContext context;
    @Mock
    private Queue queue;

    @InjectMocks
    private JMSSender jmsSender;

    @Test
    public void sendTest() {

        CityAndRegion location = new CityAndRegion("ufa", "ru");
        JMSProducer producer = mock(JMSProducer.class);

        when(context.createProducer()).thenReturn(producer);

        jmsSender.send(location);

        verify(context, atLeast(1)).createProducer();
        verify(producer, atLeast(1)).send(queue, location);

    }

}
