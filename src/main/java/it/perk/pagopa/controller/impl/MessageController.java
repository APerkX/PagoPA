/**
 * 
 */
package it.perk.pagopa.controller.impl;

import org.springframework.http.ResponseEntity;

import it.perk.pagopa.controller.AbstractController;
import it.perk.pagopa.controller.IMessageController;

/**
 * @author Perk
 *
 */
public class MessageController extends AbstractController implements IMessageController {

	/**
	 * Serializable.
	 */
	private static final long serialVersionUID = 3034268464519959543L;

	@Override
	public ResponseEntity<Long> submitMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Long> submitMessageTo(final Object message, final String fiscalCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
