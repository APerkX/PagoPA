/**
 * 
 */
package it.perk.pagopa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author AndreaPerquoti
 *
 */
@RequestMapping(value = "/api/v1/message")
public interface IMessageController {

	@PostMapping("/submit")
	ResponseEntity<Long> submitMessage();
	
	@PostMapping("/submit/{fiscal_code}")
	ResponseEntity<Long> submitMessageTo(@RequestBody Object message, @PathVariable(required = true, name = "fiscal_code") String fiscalCode);

}
