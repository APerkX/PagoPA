/**
 * 
 */
package it.perk.pagopa.controller.impl;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import it.perk.pagopa.clients.response.GetMessageResponseDTO;
import it.perk.pagopa.controller.response.OutputMessageResponseDTO;
import it.perk.pagopa.dto.MessageDTO;
import it.perk.pagopa.service.facade.IMessageFacadeSRV;

/**
 * @author AndreaPerquoti
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test") // point to application-test.properties
@AutoConfigureMockMvc
@DisplayName("Message Controller Unit Test")
class MessageControllerUnitTest {
	 
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private IMessageFacadeSRV messageSRV;
	
	@Test
	@DisplayName("Submit Message status InternalServerError")
	void submitMessageStatusOk() throws Exception {
		mvc.perform(post("/api/v1/message/submit").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());
	}
	
	@Test
	@DisplayName("Submit Message response OK")
	void submitMessageResponseOk() throws Exception {
		
		MessageDTO mess = MessageDTO.builder().id("identificativo").build();
		OutputMessageResponseDTO res = OutputMessageResponseDTO.builder().message(mess).build();
		given(messageSRV.sendMessage()).willReturn(res);
		
		mvc.perform(post("/api/v1/message/submit").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message.id").value(mess.getId()));
	}
	
	@Test
	@DisplayName("Submit Message response KO")
	void submitMessageResponseKo() throws Exception {
		
		MessageDTO mess = MessageDTO.builder().id(null).build();
		OutputMessageResponseDTO res = OutputMessageResponseDTO.builder().message(mess).build();
		given(messageSRV.sendMessage()).willReturn(res);
		
		mvc.perform(post("/api/v1/message/submit").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message.id").value(IsNull.nullValue()));
	}
}
