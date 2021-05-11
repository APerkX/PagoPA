/**
 * 
 */
package it.perk.pagopa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Service;

import it.perk.pagopa.clients.IOclient;
import it.perk.pagopa.clients.request.SubmitMessageforUserRequestDTO;
import it.perk.pagopa.clients.response.GetMessageResponseDTO;
import it.perk.pagopa.clients.response.SubmitMessageforUserResponseDTO;
import it.perk.pagopa.dto.ContentDTO;
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
	public GetMessageResponseDTO sendMessage() {
		GetMessageResponseDTO output = null;
		
		try {
			log.info("sendMessage()");
			
			// Costruzione del messaggio.
			String subject = "IO sono l'oggetto"; //E IO sono il body - utile per i test con questo va in errore
			String markdown = "# This is a markdown header\\n\\nto show how easily markdown can be converted to **HTML**\\n\\nRemember: this has to be a long text.";
//			ContentDTO content = ContentDTO.builder().subject(subject).markdown(markdown).build();
			ContentDTO content = new ContentDTO();
			content.setSubject(subject);
			content.setMarkdown(markdown);
			
			output = sendMessageToUser(content, FISCAL_CODE_DEFAULT);
			
		} catch (Exception e) {
			log.error("sendMessage()", e);
			throw new RuntimeException("", e);
		}
		
		return output;
	}

	@Override
	public GetMessageResponseDTO sendMessageToUser(ContentDTO content, String fiscalCode) {
		GetMessageResponseDTO output = null;
		SubmitMessageforUserResponseDTO res = null;
		
		try {
			log.info("sendMessageToUser()");
			
			/*
			 * INVOCARE API getProfile() PER VERIFICARE SE L'UTENTE ESISTE ED è CONFIGURATO PER IL PROPRIO SERVIZIO.
			 */
			boolean sendEnabled = ioClient.getProfile(fiscalCode);
			
			/*
			 * DOPO AVER VERIFICATO LA CONFIGURAZIONE UTENTE INVIARE IL MESSAGGIO TRAMITE API submitMessageforUser().
			 */
			if (sendEnabled) {
				
				// Costruzione della request nella sua interezza.
				SubmitMessageforUserRequestDTO req = SubmitMessageforUserRequestDTO.builder().content(content).fiscal_code(fiscalCode).build(); 
				
				// Call dell'operazione d'invio.
				res = ioClient.submitMessageforUser(req);
				
			} else {
				//TODO: prevedere respnse consona per informare chi chiama che il fiscal code non è configurato per ricevere messaggi.
			}
			
			/*
			 * PER ULTIMO VERIFICARE CHE IL MESSAGGIO SIA STATO CORRETTAMENTE PROCESSATO INTERROGANDO L'API getMessage().
			 */
			if (res != null && res.getId() != null) {
				output = ioClient.getMessage(fiscalCode, res.getId());
			}
			
			log.info("sendMessageToUser()");
		} catch (Exception e) {
			log.error("sendMessageToUser()", e);
			throw new RuntimeException("", e);
		}
		
		return output;
	}

}
