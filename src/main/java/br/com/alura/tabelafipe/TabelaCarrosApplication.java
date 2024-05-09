package br.com.alura.tabelafipe;

import br.com.alura.tabelafipe.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabelaCarrosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TabelaCarrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();


		//		ConsomeApi consumo = new ConsomeApi();
//		var json = consumo.obterDados("https://parallelum.com.br/fipe/api/v1/carros/marcas");
//		ConverteDados converteDados = new ConverteDados();
//		//uma outra maneira de executar a api lembrando de uma coisa importante uma api de Arrays
//		// não pode ser convertida em um objeto
//		// List<Marca> marca = Arrays.asList(converteDados.obterDados(json, Marca[].class));
//		List<Marca> marcas = converteDados.obterLista(json,Marca.class);

//		System.out.println(marcas);
	}
}
