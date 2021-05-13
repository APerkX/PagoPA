/**
 * 
 */
package it.perk.pagopa.dto;

import java.io.Serializable;

import it.perk.pagopa.enums.NotificationChannelStatusValueEnum;
import lombok.Data;

/**
 * @author AndreaPerquoti
 *
 */
@Data
public class NotificationDTO implements Serializable {

	/**
	 * Serializable.
	 */
	private static final long serialVersionUID = 8231758882447283850L;

	/**
	 * Stato del canale email.
	 */
	private NotificationChannelStatusValueEnum email;
	
	/**
	 * Stato del canale webhook.
	 */
	private NotificationChannelStatusValueEnum webhook;
}
