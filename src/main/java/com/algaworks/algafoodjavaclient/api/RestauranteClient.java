package com.algaworks.algafoodjavaclient.api;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.algaworks.algafoodjavaclient.input.RestauranteInput;
import com.algaworks.algafoodjavaclient.model.RestauranteModel;
import com.algaworks.algafoodjavaclient.model.RestauranteResumoModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteClient {
	
	private static String RESOURCE_PATH = "/restaurantes";
	private String url;;
	private RestTemplate restTemplate;
	
	public List<RestauranteResumoModel> listar(){
		try {
		URI resourceUri = URI.create(url + RESOURCE_PATH);
		
		RestauranteResumoModel[] restaurantes = restTemplate.getForObject(resourceUri,RestauranteResumoModel[].class);
		
		return Arrays.asList(restaurantes);
		} catch (RestClientResponseException e) {
			throw new ClientApiException(e.getMessage(),e);
		}
		
	}
	
	public  RestauranteModel inserir(RestauranteInput restauranteModel){
		try {
		URI resourceUri = URI.create(url + RESOURCE_PATH);
		
		RestauranteModel restaurante = restTemplate.postForObject(resourceUri, restauranteModel, RestauranteModel.class);
		
		return restaurante;
		} catch (RestClientResponseException e) {
			throw new ClientApiException(e.getMessage(),e);
		}
		
	}
	
	

}
