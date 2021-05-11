/**
 * 
 */
package it.perk.pagopa.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * @author Perk
 *
 */
@Data
@Builder
public class MessageDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7273524292757107923L;

	/**
	 * Identificativo del messaggio creato.
	 */
	private String id;
	
	/**
	 * Codice fiscale dell'utente.
	 */
	private String fiscal_code;
	
	/**
	 * Entità che rappresenta il messaggio inviato.
	 */
	private ContentDTO content;
	
	/**
	 * Data creazione
	 */
	private Date created_at;
	
	/**
	 * Identificativo servizio mittente.
	 */
	private String sender_service_id;
	
}
