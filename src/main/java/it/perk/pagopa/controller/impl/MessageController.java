/**
 * 
 */
package it.perk.pagopa.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import it.perk.pagopa.controller.AbstractController;
import it.perk.pagopa.controller.IMessageController;
import it.perk.pagopa.controller.response.OutputMessageResponseDTO;
import it.perk.pagopa.exceptions.ResourceBadRequestException;
import it.perk.pagopa.service.facade.IMessageFacadeSRV;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Perk
 *
 */
@Slf4j
@RestController
public class MessageController extends AbstractController implements IMessageController {

	/**
	 * Serializable.
	 */
	private static final long serialVersionUID = 3034268464519959543L;
	
	/**
	 * IO Client.
	 */
	@Autowired
	private IMessageFacadeSRV messageSRV;

	@Override
	public ResponseEntity<OutputMessageResponseDTO> submitMessage() {
		ResponseEntity<OutputMessageResponseDTO> output = null;
		
		try {
			log.info("submitMessage()");
			
			OutputMessageResponseDTO state = messageSRV.sendMessage();
			
			if (state.getMessage().getId() != null) {
				// Messaggio creato correttamente.
				output = new ResponseEntity<>(state, HttpStatus.OK);
			} else {
				// Utente non configurato.
				output = new ResponseEntity<>(state, HttpStatus.BAD_REQUEST);
			}
			
		} catch (ResourceBadRequestException e) {
			log.error(e.getMessage());
			output = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("submitMessage()", e);
			output = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return output;
	}

	@Override
	public ResponseEntity<OutputMessageResponseDTO> submitMessageTo(final Object message, final String fiscalCode) {
		// TODO Auto-generated method stub #### @Pattern(regexp = "[A-Z]{6}[0-9LMNPQRSTUV]{2}[ABCDEHLMPRST][0-9LMNPQRSTUV]{2}[A-Z][0-9LMNPQRSTUV]{3}[A-Z]")
		return null;
	}

}
