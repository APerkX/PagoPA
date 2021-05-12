/**
 * 
 */
package it.perk.pagopa.enums;

/**
 * @author Perk
 *
 */
public enum MessageStateEnum {
	
	/**
	 * Messaggio creato correttamente.
	 */
	MSG_CREATED(1, "MESSAGGIO CREATO CORRETTAMENTE"),
	
	/**
	 * Utente non configurato.
	 */
	USER_NOT_CONFIGURED(2, "UTENTE NON ABILITATO A RICEVERE MESSAGGI"),
	
	/**
	 * Utente non configurato.
	 */
	BAD_REQUEST_IO(3, "LA PIATTAFORMA IO API HA RESTITUITO UNA BAD_REQUEST");

	/**
	 * Identificativo.
	 */
	private Integer id;
	
	/**
	 * Stato.
	 */
	private String stato;

	MessageStateEnum(final Integer inId, final String inStato) {
		id = inId;
		stato = inStato;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the stato
	 */
	public String getStato() {
		return stato;
	}
	
}
