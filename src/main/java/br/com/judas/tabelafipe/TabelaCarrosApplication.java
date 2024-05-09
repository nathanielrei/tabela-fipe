package br.com.judas.tabelafipe;

import br.com.judas.tabelafipe.principal.Principal;
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

	}
}
