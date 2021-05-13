/**
 * 
 */
package it.perk.pagopa.service.impl;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import it.perk.pagopa.clients.IOclient;
import it.perk.pagopa.clients.request.SubmitMessageforUserRequestDTO;
import it.perk.pagopa.clients.response.SubmitMessageforUserResponseDTO;
import it.perk.pagopa.controller.response.OutputMessageResponseDTO;
import it.perk.pagopa.dto.ContentDTO;
import it.perk.pagopa.enums.MessageStateEnum;
import it.perk.pagopa.service.facade.IMessageFacadeSRV;

/**
 * @author AndreaPerquoti
 *
 */
@SpringBootTest(classes = MessageSRV.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test") 
@DisplayName("Message Service Unit Test")
class MessageSRVUnitTest {
	
	/**
	 * URL base per IO API
	 */
	private static final String FISCAL_CODE_DEFAULT = "AAAAAA00A00A000A";
	
	/**
	 * IO Client.
	 */
	@MockBean
	private IOclient ioClient;
	
	@Autowired
	private IMessageFacadeSRV messageSRV;
	
	@Test
	@DisplayName("Send Message To User status OK")
	void sendMessageToUserStatusOk() throws Exception {
		
		// Mock del api getProfile().
		given(ioClient.getProfile(FISCAL_CODE_DEFAULT)).willReturn(true);
		
		// Costruzione del messaggio.
		String subject = "IO sono l'oggetto"; //E IO sono il body - utile per i test con questo va in errore
		String markdown = "# This is a markdown header\\n\\nto show how easily markdown can be converted to **HTML**\\n\\nRemember: this has to be a long text.";
		ContentDTO content = new ContentDTO();
		content.setSubject(subject);
		content.setMarkdown(markdown);
		
		// Costruzione della request nella sua interezza.
		SubmitMessageforUserRequestDTO req = SubmitMessageforUserRequestDTO.builder().content(content).fiscal_code(FISCAL_CODE_DEFAULT).build(); 
		
		SubmitMessageforUserResponseDTO res = new SubmitMessageforUserResponseDTO();
		res.setId("identificativo");
		
		// Mock del api submitMessageforUser().
		given(ioClient.submitMessageforUser(req)).willReturn(res);
		
		// Run del metodo da testare.
		OutputMessageResponseDTO output = messageSRV.sendMessageToUser(content, FISCAL_CODE_DEFAULT);

		Assertions.assertNotNull(output, "OutputMessageResponseDTO non è stato creato correttamente");
		Assertions.assertEquals(output.getStatus(), MessageStateEnum.MSG_CREATED.getStato());
		Assertions.assertNotNull(output.getMessage(), "Il MessageDTO non è stato creato correttamente");
		Assertions.assertEquals(output.getMessage().getId(), res.getId());
		
	}
	
	@Test
	@DisplayName("Send Message To User status KO")
	void sendMessageToUserStatusKo() throws Exception {
		
		// Mock del api getProfile().
		given(ioClient.getProfile(FISCAL_CODE_DEFAULT)).willReturn(false);
		
		// Costruzione del messaggio.
		String subject = "IO sono l'oggetto"; //E IO sono il body - utile per i test con questo va in errore
		String markdown = "# This is a markdown header\\n\\nto show how easily markdown can be converted to **HTML**\\n\\nRemember: this has to be a long text.";
		ContentDTO content = new ContentDTO();
		content.setSubject(subject);
		content.setMarkdown(markdown);
		
		// Run del metodo da testare.
		OutputMessageResponseDTO output = messageSRV.sendMessageToUser(content, FISCAL_CODE_DEFAULT);
		
		Assertions.assertNotNull(output, "OutputMessageResponseDTO non è stato creato correttamente");
		Assertions.assertEquals(output.getStatus(), MessageStateEnum.USER_NOT_CONFIGURED.getStato());
		
	}
}
