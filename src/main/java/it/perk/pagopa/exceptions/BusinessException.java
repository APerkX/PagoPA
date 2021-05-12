/**
 * 
 */
package it.perk.pagopa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Eccezione di business.
 * 
 * @author Perk
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7334906743764486169L;

	/**
	 * Costruttore.
	 * 
	 * @param msg	messaggio
	 */
	public BusinessException(final String msg) {
		super(msg);
	}
	
	/**
	 * Costruttore.
	 * 
	 * @param msg	messaggio
	 * @param e		eccezione
	 */
	public BusinessException(final String msg, final Exception e) {
		super(msg, e);
	}
	
	/**
	 * Costruttore.
	 * 
	 * @param e	eccezione
	 */
	public BusinessException(final Exception e) {
		super(e);
	}
	
}
