/**
 * 
 */
package it.perk.pagopa.clients.response;

import java.io.Serializable;

import it.perk.pagopa.dto.MessageDTO;
import it.perk.pagopa.dto.NotificationDTO;
import lombok.Data;

/**
 * @author AndreaPerquoti
 *
 */
@Data
public class GetMessageResponseDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7898371126964102739L;

	/**
	 * Entità messaggio.
	 */
	private MessageDTO message;
	
	/**
	 * Entità stato notifiche.
	 */
	private NotificationDTO notification;
	
	/**
	 * Stato messaggio.
	 */
	private String status;
	
}
