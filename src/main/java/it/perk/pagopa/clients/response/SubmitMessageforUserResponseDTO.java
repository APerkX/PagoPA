/**
 * 
 */
package it.perk.pagopa.clients.response;

import java.io.Serializable;

import lombok.Data;

/**
 * @author AndreaPerquoti
 *
 */
@Data
public class SubmitMessageforUserResponseDTO implements Serializable {
	
	/**
	 * Serializable.
	 */
	private static final long serialVersionUID = -6522252062704662985L;
	
	/**
	 * Identificativo del messaggio creato.
	 */
	private String id;
}
