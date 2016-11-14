package com.kyushu.autosum.configurationlayer;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;


/**
 * This class allow to get log message and test it
 * @author ANDRE
 */
@RunWith(PowerMockRunner.class)
public abstract class LoggerTest {

    @Mock
    protected Appender mockAppender;

    @Captor
    protected ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    @Before
    public void setup() {
        final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.addAppender(mockAppender);
    }

    @After
    public void teardown() {
        final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.detachAppender(mockAppender);
    }

    protected LoggingEvent getLoggingEvent() {
        verify(mockAppender).doAppend(captorLoggingEvent.capture());
        final LoggingEvent loggingEvent = captorLoggingEvent.getValue();
        return loggingEvent;
    }
    protected List<LoggingEvent> getLoggingEvents() {

        verify(mockAppender, atLeastOnce()).doAppend(captorLoggingEvent.capture());
        List<LoggingEvent> loggingEvents = captorLoggingEvent.getAllValues();

        return loggingEvents;
    }

}
