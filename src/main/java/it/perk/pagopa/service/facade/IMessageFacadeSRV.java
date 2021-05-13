/**
 * 
 */
package it.perk.pagopa.service.facade;

import java.io.Serializable;

import it.perk.pagopa.controller.response.OutputMessageResponseDTO;
import it.perk.pagopa.dto.ContentDTO;

/**
 * The Interface IMessageFacadeSRV.
 *
 * @author AndreaPerquoti
 */
public interface IMessageFacadeSRV extends Serializable {
	
	/**
	 * Send message.
	 *
	 * @return the output message response DTO
	 */
	OutputMessageResponseDTO sendMessage();
	
	/**
	 * Send message to user.
	 *
	 * @param message the message
	 * @param fiscalCode the fiscal code
	 * @return the output message response DTO
	 */
	OutputMessageResponseDTO sendMessageToUser(ContentDTO message, String fiscalCode);

}
