package com.tsystems.stand.jms.listener;


import com.tsystems.stand.bean.ejb.ItemTop;
import org.apache.log4j.Logger;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message listener.
 * It is listening messages from JMS server.
 *
 * @author Siarhei
 * @version 1.0
 */
public class MessageListenerImpl implements MessageListener {

    private static final Logger log = Logger.getLogger(MessageListenerImpl.class);

    /**
     * Injected by JmsConfig bean in order to call update method
     * when message has received.
     */
    private ItemTop itemTop;

    /**
     * Method always listens to the JMS server.
     * @param message
     */
    public void onMessage(Message message) {
        log.info("Message has received from JMS server.");
        itemTop.updateTopProducts();
    }

    /**
     * Method allows us to inject itemTop bean.
     * See {@link ItemTop}
     * @param itemTop exemplar that must be injected.
     */
    public void setItemTop(ItemTop itemTop) {
        this.itemTop = itemTop;
    }
}
