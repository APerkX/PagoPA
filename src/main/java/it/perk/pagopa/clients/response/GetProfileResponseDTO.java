/**
 * 
 */
package it.perk.pagopa.clients.response;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Perk
 *
 */
@Data
public class GetProfileResponseDTO implements Serializable {
	
	/**
	 * Serializable.
	 */
	private static final long serialVersionUID = -9032975662654008274L;
	
	private boolean sender_allowed;

}
