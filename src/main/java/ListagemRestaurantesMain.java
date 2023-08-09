import org.springframework.web.client.RestTemplate;

import com.algaworks.algafoodjavaclient.api.ClientApiException;
import com.algaworks.algafoodjavaclient.api.RestauranteClient;

public class ListagemRestaurantesMain {

	public static void main(String[] args) {
		try {
		RestTemplate restTemplate = new RestTemplate();
		
		RestauranteClient restauranteClient = new RestauranteClient("http://api.algafood.local:8080", restTemplate);
		
		restauranteClient.listar()
		.stream()
		.forEach(restaurante -> System.out.println(restaurante));
		} catch ( ClientApiException e) {
			if( e.getProblem() != null)
			{
				System.out.println(e.getProblem());
			}
			else {
				System.out.println("Erro desconhecido!");
				e.printStackTrace();
			}
		}

	}

}
