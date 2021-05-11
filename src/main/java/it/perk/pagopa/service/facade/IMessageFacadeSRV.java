/**
 * 
 */
package it.perk.pagopa.service.facade;

import java.io.Serializable;

import it.perk.pagopa.clients.response.GetMessageResponseDTO;
import it.perk.pagopa.dto.ContentDTO;

/**
 * @author Perk
 *
 */
public interface IMessageFacadeSRV extends Serializable {
	
	GetMessageResponseDTO sendMessage();
	
	GetMessageResponseDTO sendMessageToUser(ContentDTO message, String fiscalCode);

}
