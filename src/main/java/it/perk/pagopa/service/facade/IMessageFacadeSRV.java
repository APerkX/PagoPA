/**
 * 
 */
package it.perk.pagopa.service.facade;

import java.io.Serializable;

/**
 * @author Perk
 *
 */
public interface IMessageFacadeSRV extends Serializable {
	
	Object sendMessage();
	
	Object sendMessageToUser(Object message, String fiscalCode);

}
