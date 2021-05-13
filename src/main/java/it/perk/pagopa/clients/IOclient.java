/**
 * 
 */
package it.perk.pagopa.clients;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import it.perk.pagopa.clients.request.SubmitMessageforUserRequestDTO;
import it.perk.pagopa.clients.response.GetMessageResponseDTO;
import it.perk.pagopa.clients.response.GetProfileResponseDTO;
import it.perk.pagopa.clients.response.SubmitMessageforUserResponseDTO;
import it.perk.pagopa.exceptions.BusinessException;
import it.perk.pagopa.exceptions.ResourceBadRequestException;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class IOclient.
 *
 * @author AndreaPerquoti
 */
@Slf4j
@Component
public class IOclient implements Serializable {
	
	/**
	 * Serializable.
	 */
	private static final long serialVersionUID = 8025733168019974704L;
	
	/** URL base per IO API. */
	private static final String IO_BASE_URL = "https://api.io.italia.it/api/v1";
	
	/**
	 * Rest Template.
	 */
	@Autowired
	private RestTemplate rt;
	
	/**
	 * Invoca l'endpoint {@code getProfile} fornita da IO REST API e permette di determinare se l'utente
	 * target(fiscalCode) è sottoscritto al servizioche effettua la chiamata e quindi è abilitato a ricevere messaggi.
	 *
	 * @param fiscalCode the fiscal code
	 * @return true se l'utente è configurato per ricevere messaggi, false altrimenti.
	 */
	public boolean getProfile(final String fiscalCode) {
		boolean output = false;
		
		try {
			log.info("getProfile()");
			
			// Build headers.
			HttpHeaders headers = buildHeaders();
			HttpEntity<?> entity = new HttpEntity<>(headers);
			
			// Build endpoint e call.
			String endpoint = buildEndpoint("/profiles/{fiscal_code}");
			ResponseEntity<GetProfileResponseDTO> restExchange = rt.exchange(endpoint, HttpMethod.GET, entity, GetProfileResponseDTO.class, fiscalCode);
			
			// Gestione response
			GetProfileResponseDTO body = restExchange.getBody();
			if (HttpStatus.OK.equals(restExchange.getStatusCode()) && body != null) {
				output = body.isSender_allowed(); 
			}
			
			log.info("getProfile()");
		} catch (HttpClientErrorException e1) {
			String msg = null;
			if (HttpStatus.BAD_REQUEST.equals(e1.getStatusCode())) {
				msg = "Errore durante l'invocazione dell' API getProfile(). Il sistema ha resittuito una BAD_REQUEST. ";
				log.error(msg, e1);
				throw new ResourceBadRequestException(msg, e1);
			}

			if (HttpStatus.INTERNAL_SERVER_ERROR.equals(e1.getStatusCode())) {
				msg = "Errore durante l'invocazione dell' API getProfile(). Il sistema ha resittuito una INTERNAL_SERVER_ERROR. ";
				log.error(msg, e1);
				throw new BusinessException(msg, e1);
			}
			
		} catch (Exception e) {
			log.error("Errore durante l'invocazione dell' API getProfile(). ", e);
			throw new BusinessException("Errore durante l'invocazione dell' API getProfile(). ", e);
		}
		
		return output;
	}
	
	/**
	 * Invoca l'endpoint {@code submitMessageforUser} fornita da IO REST API e permette di creare e inviare un messaggio
	 * all'utente target che è regolarmette sottoscritto al servizio che richiede l'invio.
	 *
	 * @param message the message
	 * @return identificativo del messaggio creato.
	 */
	public SubmitMessageforUserResponseDTO submitMessageforUser(final @Valid SubmitMessageforUserRequestDTO message) {
		SubmitMessageforUserResponseDTO output = null;
		
		try {
			log.info("submitMessageforUser()");
			
			/*
			 * Prevedere una verifica della request.
			 * message.valid().
			 */
			
			// Build headers.
			HttpHeaders headers = buildHeaders();
			HttpEntity<SubmitMessageforUserRequestDTO> entity = new HttpEntity<>(message, headers);

			// Build endpoint e call.
			String endpoint = buildEndpoint("/messages");
			ResponseEntity<SubmitMessageforUserResponseDTO> restExchange = rt.postForEntity(endpoint, entity, SubmitMessageforUserResponseDTO.class);
			
			// Gestione response
			if (HttpStatus.CREATED.equals(restExchange.getStatusCode()) && restExchange.getBody() != null) {
				output = restExchange.getBody(); 
			}

			log.info("submitMessageforUser()");
		} catch (HttpClientErrorException e1) {
			String msg = null;
			if (HttpStatus.BAD_REQUEST.equals(e1.getStatusCode())) {
				msg = "Errore durante l'invocazione dell' API submitMessageforUser(). Il sistema ha resittuito una BAD_REQUEST. ";
				log.error(msg, e1);
				throw new ResourceBadRequestException(msg, e1);
			}

			if (HttpStatus.INTERNAL_SERVER_ERROR.equals(e1.getStatusCode())) {
				msg = "Errore durante l'invocazione dell' API submitMessageforUser(). Il sistema ha resittuito una INTERNAL_SERVER_ERROR. ";
				log.error(msg, e1);
				throw new BusinessException(msg, e1);
			}
			
		} catch (Exception e) {
			log.error("Errore durante l'invocazione dell' API submitMessageforUser(). ", e);
			throw new BusinessException("Errore durante l'invocazione dell' API submitMessageforUser(). ", e);
		}
		
		return output;
	}
	
	/**
	 * Gets the message.
	 *
	 * @param fiscalCode the fiscal code
	 * @param idMessage the id message
	 * @return the message
	 */
	public GetMessageResponseDTO getMessage(final String fiscalCode, final String idMessage) {
		GetMessageResponseDTO output = null;
		
		try {
			log.info("getMessage()");
			
			// Build headers.
			HttpHeaders headers = buildHeaders();
			HttpEntity<?> entity = new HttpEntity<>(headers);
			
			// Build endpoint e call.
			String endpoint = buildEndpoint("/messages/{fiscal_code}/{id}");
			ResponseEntity<GetMessageResponseDTO> restExchange = rt.exchange(endpoint, HttpMethod.GET, entity, GetMessageResponseDTO.class, fiscalCode, idMessage);
			
			// Gestione response
			if (HttpStatus.OK.equals(restExchange.getStatusCode()) && restExchange.getBody() != null) {
				output = restExchange.getBody(); 
			}
			
			log.info("getMessage()");
		} catch (Exception e) {
			log.error("getMessage()",e);
			throw new RuntimeException("", e);
		}
		
		return output;
	}
	
	/**
	 * Builder endpoint IO API.
	 *
	 * @param endpoint the endpoint
	 * @return the string
	 */
	private String buildEndpoint(final String endpoint) {
		// Build dell'endpoint da invocare.
		StringBuilder sb = new StringBuilder(IO_BASE_URL);
		sb.append(endpoint);
		return sb.toString();
	}
	
	/**
	 * Build header necessario per l'Autenticazione del servizio chiamante.
	 *
	 * @return the http headers
	 */
	private HttpHeaders buildHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Ocp-Apim-Subscription-Key", "1d36deca4a984067822118aeec8e2f23");
		return headers;
	}
	
}
