/**
 * 
 */
package it.perk.pagopa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Service;

import it.perk.pagopa.clients.IOclient;
import it.perk.pagopa.service.IMessageSRV;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Perk
 *
 */
@Slf4j
@Service
public class MessageSRV implements IMessageSRV {

	/**
	 * Serializable.
	 */
	private static final long serialVersionUID = 4753457529053563796L;

	/**
	 * Contesto.
	 */
	@Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;
	
	/**
	 * IO Client.
	 */
	@Autowired
	private IOclient ioClient;
	
	@Override
	public Object sendMessage() {
		Object output = null;
		
		try {
			log.info(webServerAppCtxt.getApplicationName().concat("-").concat("sendMessage()"));
			
		} catch (Exception e) {
			log.error(webServerAppCtxt.getApplicationName().concat("-").concat("sendMessage()"), e);
			throw new RuntimeException("", e);
		}
		
		return output;
	}

	@Override
	public Object sendMessageToUser(Object message, String fiscalCode) {
		Object output = null;
		
		try {
			log.info(webServerAppCtxt.getApplicationName().concat("-").concat("sendMessageToUser()"));
			
		} catch (Exception e) {
			log.error(webServerAppCtxt.getApplicationName().concat("-").concat("sendMessageToUser()"), e);
			throw new RuntimeException("", e);
		}
		
		return output;
	}

}
