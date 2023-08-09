import java.math.BigDecimal;

import org.springframework.web.client.RestTemplate;

import com.algaworks.algafoodjavaclient.api.ClientApiException;
import com.algaworks.algafoodjavaclient.api.RestauranteClient;
import com.algaworks.algafoodjavaclient.input.CidadeIdInput;
import com.algaworks.algafoodjavaclient.input.CozinhaIdInput;
import com.algaworks.algafoodjavaclient.input.EnderecoInput;
import com.algaworks.algafoodjavaclient.input.RestauranteInput;
import com.algaworks.algafoodjavaclient.model.RestauranteModel;

public class InserirRestauranteMain {

	public static void main(String[] args) {
		try {
		RestTemplate restTemplate = new RestTemplate();
		
		RestauranteClient restauranteClient = new RestauranteClient("http://api.algafood.local:8080", restTemplate);
		
		RestauranteInput restaurante = new RestauranteInput();
		CozinhaIdInput cozinhaId     = new CozinhaIdInput();
		CidadeIdInput cidadeId       = new CidadeIdInput();
		EnderecoInput endereco       = new EnderecoInput();
		
		//cozinhaId.setId(1L);
		cidadeId.setId(1L);
		endereco.setLogradouro("Rua Tabata Monteiro");
		endereco.setBairro("Bairro das Nações");
		endereco.setCep("78040-100");
		endereco.setCidade(cidadeId);
		endereco.setNumero("3201");
		endereco.setComplemento("apto 301");
		restaurante.setCozinha(cozinhaId);
		restaurante.setEndereco(endereco);
		restaurante.setNome("");
		restaurante.setTaxaFrete(BigDecimal.valueOf(15.0));
		
		RestauranteModel restauranteModel = restauranteClient.inserir(restaurante);
		
		System.out.println(restauranteModel);

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
