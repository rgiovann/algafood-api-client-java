package com.algaworks.algafoodjavaclient.model;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Problem {
	
	private Integer status;
	private OffsetDateTime timestamp;
	private String type;
	private String title;
	private String detail;
	
	private String userMessage;
	private List<Erros> objects;

	@Getter
	@Setter
	public static class Erros {
		private String name;
		private String userMessage;
		
		   public Erros() {
		    }
	}

}
