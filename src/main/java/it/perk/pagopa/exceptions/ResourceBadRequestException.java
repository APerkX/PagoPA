/**
 * 
 */
package it.perk.pagopa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Eccezione generica per le richieste non consentite.
 * HTTP STATUS CODE: {@code 400 Bad Request}.
 * 
 * @author Perk
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceBadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3646020922146249085L;

	/**
	 * Costruttore.
	 * 
	 * @param e	eccezione
	 */
	public ResourceBadRequestException(final Exception e) {
		super(e);
	}

	/**
	 * Costruttore.
	 * 
	 * @param msg	messaggio
	 */
	public ResourceBadRequestException(final String msg) {
		super(msg);
	}
	
	/**
	 * Costruttore.
	 * 
	 * @param msg	messaggio
	 * @param e		eccezione root
	 */
	public ResourceBadRequestException(final String msg, final Exception e) {
		super(msg, e);
	}
	

}