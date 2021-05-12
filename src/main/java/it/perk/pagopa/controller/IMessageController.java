/**
 * 
 */
package it.perk.pagopa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import it.perk.pagopa.clients.response.GetMessageResponseDTO;
import it.perk.pagopa.controller.response.OutputMessageResponseDTO;

/**
 * @author AndreaPerquoti
 *
 */
@RequestMapping(value = "/api/v1/message")
public interface IMessageController {

	@PostMapping("/submit")
	ResponseEntity<OutputMessageResponseDTO> submitMessage();
	
	@PostMapping("/submit/{fiscal_code}")
	ResponseEntity<OutputMessageResponseDTO> submitMessageTo(@RequestBody Object message, @PathVariable(required = true, name = "fiscal_code") String fiscalCode);

}
