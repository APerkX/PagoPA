/**
 * 
 */
package it.perk.pagopa.service.facade;

import java.io.Serializable;

import it.perk.pagopa.controller.response.OutputMessageResponseDTO;
import it.perk.pagopa.dto.ContentDTO;

/**
 * @author Perk
 *
 */
public interface IMessageFacadeSRV extends Serializable {
	
	OutputMessageResponseDTO sendMessage();
	
	OutputMessageResponseDTO sendMessageToUser(ContentDTO message, String fiscalCode);

}
