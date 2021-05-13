/**
 * 
 */
package it.perk.pagopa.controller.response;

import java.io.Serializable;

import it.perk.pagopa.dto.MessageDTO;
import lombok.Builder;
import lombok.Data;

/**
 * @author AndreaPerquoti
 *
 */
@Data
@Builder
public class OutputMessageResponseDTO implements Serializable {
	
	/**
	 * Serializable.
	 */
	private static final long serialVersionUID = 843810944700246673L;

	/**
	 * Entità messaggio.
	 */
	private MessageDTO message;
	
	/**
	 * Stato messaggio.
	 */
	private String status;
	
}
