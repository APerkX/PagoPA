/**
 * 
 */
package it.perk.pagopa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.perk.pagopa.clients.IOclient;
import it.perk.pagopa.clients.request.SubmitMessageforUserRequestDTO;
import it.perk.pagopa.clients.response.SubmitMessageforUserResponseDTO;
import it.perk.pagopa.controller.response.OutputMessageResponseDTO;
import it.perk.pagopa.dto.ContentDTO;
import it.perk.pagopa.dto.MessageDTO;
import it.perk.pagopa.enums.MessageStateEnum;
import it.perk.pagopa.exceptions.BusinessException;
import it.perk.pagopa.exceptions.ResourceBadRequestException;
import it.perk.pagopa.service.IMessageSRV;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Perk
 *
 */
@Slf4j
@Service
public class MessageSRV implements IMessageSRV {

	/**
	 * Serializable.
	 */
	private static final long serialVersionUID = 4753457529053563796L;
	
	/**
	 * URL base per IO API
	 */
	private static final String FISCAL_CODE_DEFAULT = "AAAAAA00A00A000A";
	
	/**
	 * IO Client.
	 */
	@Autowired
	private IOclient ioClient;
	
	@Override
	public OutputMessageResponseDTO sendMessage() {
		OutputMessageResponseDTO output = null;
		
		try {
			log.info("sendMessage()");
			
			// Costruzione del messaggio.
			String subject = "IO sono l'oggetto"; //E IO sono il body - utile per i test con questo va in errore
			String markdown = "CIAO";
			ContentDTO content = new ContentDTO();
			content.setSubject(subject);
			content.setMarkdown(markdown);
			
			output = sendMessageToUser(content, FISCAL_CODE_DEFAULT);
			
		} catch (ResourceBadRequestException e) {
			log.error(e.getMessage());
			throw new ResourceBadRequestException(e);
		} catch (Exception e) {
			log.error("Errore durante il tentativo di inviare un messaggio standard. ", e);
			throw new BusinessException("Errore durante il tentativo di inviare un messaggio standard. ", e);
		}
		
		return output;
	}

	@Override
	public OutputMessageResponseDTO sendMessageToUser(ContentDTO content, String fiscalCode) {
		OutputMessageResponseDTO output = null;
		SubmitMessageforUserResponseDTO res = null;
		
		try {
			log.info("sendMessageToUser()");
			
			// Invocazione API getProfile() per verificare se l'utente
			// esiste ed è configurato per ricevere messaggi da questo servizio.
			boolean sendEnabled = ioClient.getProfile(fiscalCode);
			
			// Dopo aver configurato la configurato la configurazione utente
			// viene invocata API submitMessageforUser() 
			// per la creazione del messaggio sulla piattaforma IO.
			if (sendEnabled) {
				
				// Costruzione della request nella sua interezza.
				SubmitMessageforUserRequestDTO req = SubmitMessageforUserRequestDTO.builder().content(content).fiscal_code(fiscalCode).build(); 
				
				// Call dell'operazione d'invio.
				res = ioClient.submitMessageforUser(req);
				
			} else {

				// Creazione response per dichiarare che l'utente non è configurato a ricevere messaggi.
				MessageDTO mess = MessageDTO.builder().content(content).fiscal_code(fiscalCode).build();
				String status = MessageStateEnum.USER_NOT_CONFIGURED.getStato();
				output = OutputMessageResponseDTO.builder().message(mess).status(status).build();
				
			}
			
			// Verifica che il messaggio sia stato creato correttamente 
			// e che sia stato restituito un identificativo.
			if (res != null && res.getId() != null) {
				
				// Creazione response positiva.
				MessageDTO mess = MessageDTO.builder().content(content).fiscal_code(fiscalCode).id(res.getId()).build();
				String status = MessageStateEnum.MSG_CREATED.getStato();
				output = OutputMessageResponseDTO.builder().message(mess).status(status).build();
				
			}
			
			log.info("sendMessageToUser()");
		} catch (ResourceBadRequestException e) {
			log.error(e.getMessage());
			throw new ResourceBadRequestException(e);
		} catch (Exception e) {
			log.error("Errore durante l'invocazione delle API IO. ", e);
			throw new BusinessException("Errore durante l'invocazione delle API IO. ", e);
		}
		
		return output;
	}

}
