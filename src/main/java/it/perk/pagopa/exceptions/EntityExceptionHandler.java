/**
 * 
 */
package it.perk.pagopa.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.perk.pagopa.controller.response.OutputMessageResponseDTO;
import it.perk.pagopa.enums.MessageStateEnum;

/**
 * Questa classe rappresenta un esempio di come gestire eccezioni dei servizi
 * rest a livello "globale" di applicazione
 * 
 * @author Perk
 */
@ControllerAdvice
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * 
	 * @param ex      eccezione di tipo RuntimeException
	 * @param request interfaccia generica di una Web request
	 * @return un oggetto ResponseEntity con la risposta dedicata
	 * 
	 */
	@ExceptionHandler(value = { ResourceBadRequestException.class, IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String status = MessageStateEnum.BAD_REQUEST_IO.getStato();
		OutputMessageResponseDTO bodyOfResponse = OutputMessageResponseDTO.builder().message(null).status(status).build();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, headers, status);        
	}

 }
