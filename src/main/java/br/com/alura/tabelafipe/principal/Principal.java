package br.com.alura.tabelafipe.principal;

import br.com.alura.tabelafipe.model.Dados;
import br.com.alura.tabelafipe.model.Modelos;
import br.com.alura.tabelafipe.model.Veiculo;
import br.com.alura.tabelafipe.service.ConsomeApi;
import br.com.alura.tabelafipe.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private final String  ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
    private final String MARCAS = "/marcas";
    private String veiculo;
    private  ConsomeApi consumo = new ConsomeApi();
    private ConverteDados converteDados = new ConverteDados();
    private Scanner leitor = new Scanner(System.in);
    private int numero;
    public void exibeMenu(){
        var opcoes = """
                *** Opções ***
                
                Carros
                Motos
                Caminhões
                
                *** Digite ***
                """;
        System.out.print(opcoes);
        buscarAutomovel();
//        var veiculo = leitor.nextLine();
//
//        var json = consumo.obterDados(ENDERECO + veiculo.toLowerCase() + MARCAS);
//           List<Dados> dados =converteDados.obterLista(json, Dados.class);
//        dados.stream().sorted(Comparator.comparing(Dados::nome)).forEach(System.out::println);
        opcoes = """
                *** Código ***
                 
                Digite o código da marca desejada
                """;
        System.out.print(opcoes);
       numero = leitor.nextInt();
              getMarca(numero).modelos()
                    .stream()
                    .sorted(Comparator.comparing(Dados::nome))
                    .forEach(System.out::println);

        System.out.println("Digite o nome do carro a ser buscado");
                filtroModelos(getMarca(numero).modelos());
//
//
//        System.out.println("****************************");
//        List<Dados> anos =null;
//        List<Veiculo> veiculoList = new ArrayList<>();
//        for (int x =0;x<modelosFiltrados.size();x++){
//        json = consumo.obterDados(ENDERECO + veiculo.toLowerCase()
//                + MARCAS +"/"+ numero + "/modelos/" + modelosFiltrados.get(0).codigo()+"/anos");
//            anos = converteDados.obterLista(json,Dados.class);
//            json = consumo.obterDados(ENDERECO + veiculo.toLowerCase()
//                    + MARCAS +"/"+ numero + "/modelos/" + modelosFiltrados.get(0).codigo()
//                    +"/anos/"+ anos.get(x).codigo());
//            System.out.println(json);
//            Veiculo veiculo1 = converteDados.obterDados(json,Veiculo.class);
//                veiculoList.add(veiculo1);
//        }
//       anos.stream().forEach(System.out::println);
//        veiculoList.stream().forEach(System.out::println);
    }
    public void buscarAutomovel(){
        var dados = getAutomovel();
        dados.stream().forEach(System.out::println);

    }
    private List<Dados> getAutomovel(){
         veiculo = leitor.nextLine().toLowerCase();
        var json = consumo.obterDados(ENDERECO + veiculo + MARCAS);
        List<Dados> dados =converteDados.obterLista(json, Dados.class);
        return dados;
    }
    private Modelos getMarca(int numero){
        var json = consumo.obterDados(ENDERECO + veiculo + MARCAS +"/"+ numero + "/modelos" );
        Modelos modeloLista = converteDados.obterDados(json,Modelos.class);
        return modeloLista;
    }
    private void filtroModelos(List<Dados> modelos){
       leitor.nextLine();
        String nomeVeiculo = leitor.nextLine();
        System.out.println(nomeVeiculo);
        List<Dados> modelosFiltrados = modelos.stream()
                .filter(m -> m.nome()
                        .toLowerCase()
                        .contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("\nmodelos filtrados");
        modelosFiltrados.stream().forEach(System.out::println);
        System.out.println("****************************");
        getVeiculo(modelosFiltrados);

    }
    private void getVeiculo(List<Dados> modelosFiltrados) {
        List<Dados> anos = null;
        List<Veiculo> veiculoList = new ArrayList<>();
        for (int x = 0; x < modelosFiltrados.size(); x++) {
            var json = consumo.obterDados(ENDERECO + veiculo.toLowerCase()
                    + MARCAS + "/" + numero + "/modelos/" + modelosFiltrados.get(x).codigo() + "/anos");
            anos = converteDados.obterLista(json, Dados.class);
        }
        anos.stream().forEach(System.out::println);
        for (int i = 0; i<anos.size();i++){
         var json = consumo.obterDados(ENDERECO + veiculo.toLowerCase()
                    + MARCAS + "/" + numero + "/modelos/" + modelosFiltrados.get(0).codigo()
                    + "/anos/" + anos.get(i).codigo());

            Veiculo veiculo = converteDados.obterDados(json, Veiculo.class);
            veiculoList.add(veiculo);
        }
        veiculoList.stream().forEach(System.out::println);
    }
}
