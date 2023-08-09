package com.algaworks.algafoodjavaclient.api;

import org.springframework.web.client.RestClientResponseException;

import com.algaworks.algafoodjavaclient.model.Problem;
import com.algaworks.algafoodjavaclient.model.Problem.Erros;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ClientApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Problem problem;

	public ClientApiException(String message, RestClientResponseException cause) {
		super(message, cause);

		deserializeProblem(cause);
		
	}
	
	private void deserializeProblem(RestClientResponseException cause) {
		
		ObjectMapper mapper = JsonMapper.builder()
			    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			    .addModule(new JavaTimeModule())
			    .build();
		
		try {
			this.problem = mapper.readValue(cause.getResponseBodyAsString(),Problem.class);
			
			System.out.println(" --- EXCEPTION --- " );
			System.out.println("Status..........: " + problem.getStatus());
			System.out.println("timeStamp.......: " + problem.getTimestamp());
			System.out.println("type............: " + problem.getType());
			System.out.println("title...........: " + problem.getTitle());
			System.out.println("detail..........: " + problem.getDetail());
			System.out.println("userMessage.....: " + problem.getUserMessage());
			if(problem.getObjects() != null) {
			System.out.println("---- Detailed user messages--- "  );

				for (Erros field : problem.getObjects()) {
				    System.out.println("name: " + field.getName());
				    System.out.println("userMessage: " + field.getUserMessage());
				    System.out.println("------------------------");
				}

			}
			
			 
		} catch (JsonProcessingException e) {
 			log.warn("Não foi possível deserializar a resposta em um problema",e);
		}
		
	}
	
	

}
