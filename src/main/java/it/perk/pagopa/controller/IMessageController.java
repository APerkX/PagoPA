/**
 * 
 */
package it.perk.pagopa.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.perk.pagopa.controller.response.OutputMessageResponseDTO;

/**
 * @author AndreaPerquoti
 *
 */
@RequestMapping(value = "/api/v1/message")
public interface IMessageController {

	@PostMapping("/submit")
	@Operation(summary = "Invio messaggio", description = "Servizio che invia un messaggio attraverso le IO API.")
	@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OutputMessageResponseDTO.class)))
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Invio messaggio avvenuto con successo"),
							@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content) })
	ResponseEntity<OutputMessageResponseDTO> submitMessage();
//	
//	@PostMapping("/submit/{fiscal_code}")
//	ResponseEntity<OutputMessageResponseDTO> submitMessageTo(@RequestBody Object message, @PathVariable(required = true, name = "fiscal_code") String fiscalCode);

}
