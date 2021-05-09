/**
 * 
 */
package it.perk.pagopa.clients.request;

import java.io.Serializable;

import it.perk.pagopa.dto.ContentDTO;
import lombok.Builder;
import lombok.Data;

/**
 * @author Perk
 *
 */
@Data
@Builder
public class SubmitMessageforUserRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5730434292139844464L;

	/**
	 * Entità che rappresenta il messaggio da inviare.
	 */
	private ContentDTO content;
	
	/**
	 * Codice fiscale dell'utente.
	 */
	private String fiscal_code;
	

//    public boolean checkCar(@Valid @NotNull Car car) {
//        //...
//        return false;
//    }
}
