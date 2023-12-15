
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Estacionamento estacionamento = new Estacionamento("estacionamento ABC", 5, 10);
        Scanner scanner = new Scanner(System.in);

        // Carga de dados para clientes
        Cliente cliente1 = new Cliente("João", "001", TipoCliente.HORISTA);
        Cliente cliente2 = new Cliente("Maria", "002", TipoCliente.TURNO_TARDE);
        Cliente cliente3 = new Cliente("Pedro", "003", TipoCliente.MENSALISTA);
        Cliente cliente4 = new Cliente("Ana", "004", TipoCliente.TURNO_TARDE);
        Cliente cliente5 = new Cliente("Carlos", "005", TipoCliente.TURNO_NOITE);
        Cliente cliente6 = new Cliente("Paulo", "006", TipoCliente.TURNO_NOITE);

        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);
        estacionamento.addCliente(cliente3);
        estacionamento.addCliente(cliente4);
        estacionamento.addCliente(cliente5);
        estacionamento.addCliente(cliente6);

        // Carga de dados para veículos
        Veiculo veiculo1 = new Veiculo("ABC1234");
        Veiculo veiculo2 = new Veiculo("DEF5678");
        Veiculo veiculo3 = new Veiculo("GHI9012");
        Veiculo veiculo4 = new Veiculo("JKL3456");
        Veiculo veiculo5 = new Veiculo("MNO7890");
        Veiculo veiculo6 = new Veiculo("MHAV090");

        estacionamento.addVeiculo(veiculo1, "001");
        estacionamento.addVeiculo(veiculo2, "002");
        estacionamento.addVeiculo(veiculo3, "003");
        estacionamento.addVeiculo(veiculo4, "004");
        estacionamento.addVeiculo(veiculo5, "005");
        estacionamento.addVeiculo(veiculo6, "006");

        try {
            estacionamento.estacionar(veiculo1);
            estacionamento.estacionar(veiculo2);
            estacionamento.estacionar(veiculo3);
            estacionamento.estacionar(veiculo4);
            estacionamento.estacionar(veiculo5);
            estacionamento.estacionar(veiculo6);

            estacionamento.sair(veiculo1.getPlaca());
            estacionamento.sair(veiculo2.getPlaca());
            estacionamento.sair(veiculo3.getPlaca());
            estacionamento.sair(veiculo4.getPlaca());
            estacionamento.sair(veiculo5.getPlaca());
            estacionamento.sair(veiculo6.getPlaca());

            estacionamento.estacionar(veiculo1);
            estacionamento.estacionar(veiculo2);
            estacionamento.estacionar(veiculo3);
            estacionamento.estacionar(veiculo4);
            estacionamento.estacionar(veiculo5);
            estacionamento.estacionar(veiculo6);

            estacionamento.sair(veiculo1.getPlaca());
            estacionamento.sair(veiculo2.getPlaca());
            estacionamento.sair(veiculo3.getPlaca());
            estacionamento.sair(veiculo4.getPlaca());
            estacionamento.sair(veiculo5.getPlaca());
            estacionamento.sair(veiculo6.getPlaca());
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean continua = true;
        while (continua) {
            System.out.println("\n====== Menu do estacionamento ======");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Adicionar Veículo a Cliente");
            System.out.println("3. Estacionar Veículo");
            System.out.println("4. Retirar Veículo");
            System.out.println("5. Total Arrecadado");
            System.out.println("6. Arrecadação por Mês");
            System.out.println("7. Valor Médio por Uso");
            System.out.println("8. Remover Cliente");
            System.out.println("9. Top 5 Clientes");
            System.out.println("10. Sair do Programa");
            System.out.println("11. Apresentar dados: Cliente");
            System.out.println("12. Apresentar dados: estacionamento");
            System.out.println("13. Pesquisar Histórico de Cliente(Veiculo)");
            System.out.println("14. Mudar Plano");
            System.out.println("0. Sair do Programa");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException i) {
                opcao = -1;
            } finally {
                scanner.nextLine();
            }
            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do cliente: ");
                    String nomeCliente = scanner.nextLine();
                    System.out.print("Digite o ID do cliente: ");
                    String idCliente = scanner.nextLine();
                    System.out.println("Selecione o tipo de cliente:");
                    for (TipoCliente tipo : TipoCliente.values()) {
                        System.out.println(tipo.ordinal() + 1 + ". " + tipo.name());
                    }
                    System.out.print("Escolha o tipo de cliente: ");
                    int tipoClienteEscolhido = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer

                    // Ajuste para corresponder ao índice do array (começa do zero)
                    TipoCliente tipoClienteSelecionado = TipoCliente.values()[tipoClienteEscolhido - 1];

                    Cliente novoCliente = new Cliente(nomeCliente, idCliente, tipoClienteSelecionado);
                    estacionamento.addCliente(novoCliente);
                    System.out.println("Cliente adicionado com sucesso!");
                    break;

                case 2:
                    System.out.print("Digite o ID do Cliente: ");
                    String idVeiculoCliente = scanner.nextLine();
                    System.out.print("Digite a placa do veículo: ");
                    String placaVeiculo = scanner.nextLine();
                    Veiculo novoVeiculo = new Veiculo(placaVeiculo);
                    estacionamento.addVeiculo(novoVeiculo, idVeiculoCliente);
                    System.out.println("Veículo adicionado ao cliente!");
                    break;
                case 3:
                    System.out.print("Digite a placa do veículo: ");
                    String placaEstacionar = scanner.nextLine();
                    System.out.println("Deseja escolher um serviço? (S/N): ");
                    String escolherServico = scanner.nextLine();

                    if (escolherServico.equalsIgnoreCase("S")) {
                        try {
                            System.out.println("Escolha o serviço:");
                            for (Servico servico : Servico.values()) {
                                System.out.println(servico.ordinal() + 1 + ". " + servico.name() + " - Preço: "
                                        + servico.getPreco() + " - Tempo Mínimo: " + servico.getTempoMinimo()
                                        + " minutos");
                            }
                            System.out.print("Escolha o serviço: ");
                            int servicoEscolhido = scanner.nextInt();
                            scanner.nextLine();

                            Servico servicoSelecionado = Servico.values()[servicoEscolhido - 1];
                            Veiculo veiculo = estacionamento.buscarVeiculo(placaEstacionar);

                            estacionamento.estacionarComServicos(veiculo, servicoSelecionado);
                            estacionamento.estacionarComServicos(veiculo, servicoSelecionado);
                            System.out.println("Veículo estacionado com sucesso!");
                        } catch (Exception e) {
                            System.out.println("Erro ao estacionar veículo: " + e.getMessage());
                        }
                    } else if (escolherServico.equalsIgnoreCase("N")) {
                        try {
                            Veiculo veiculo = estacionamento.buscarVeiculo(placaEstacionar);
                            estacionamento.estacionar(veiculo); // Modifique o método estacionar para aceitar
                                                                // cliente e placa apenas
                            System.out.println("Veículo estacionado com sucesso!");
                        } catch (Exception e) {
                            System.out.println("Erro ao estacionar veículo: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Opção inválida!");
                    }

                    break;

                case 4:
                    System.out.print("Digite a placa do veículo: ");
                    String placaRetirar = scanner.nextLine();

                    try {
                        double valorPago = estacionamento.sair(placaRetirar);
                        System.out.println("Veículo retirado. Valor a pagar: " + valorPago);
                    } catch (Exception e) {
                        System.out.println("Erro ao retirar veículo: " + e.getMessage());
                    }

                    break;

                case 5:
                    System.out.println("Total arrecadado: " + estacionamento.totalArrecadado());
                    break;
                case 6:
                    System.out.print("Digite o mês: ");
                    int mes = scanner.nextInt();
                    System.out.println("Arrecadação no mês: " + estacionamento.arrecadacaoNoMes(mes));
                    break;
                case 7:
                    System.out.println("Valor médio por uso: " + estacionamento.valorMedioPorUso());
                    break;
                case 8:
                    System.out.print("Digite o ID do cliente a ser removido: ");
                    String idClienteRemover = scanner.nextLine();
                    estacionamento.removeCliente(idClienteRemover);
                    System.out.println("Cliente removido com sucesso!");
                    break;
                case 9:
                    System.out.print("Digite o mês para o top 5: ");
                    int mesTop5 = scanner.nextInt();
                    System.out.println(estacionamento.top5Clientes(mesTop5));
                    break;
                case 11:
                    System.out.print("Digite o ID do cliente: ");
                    String idClienteDados = scanner.nextLine();
                    Cliente c = estacionamento.buscarClientePorId(idClienteDados);

                    if (c != null) {
                        System.out.println(c.dataToText());
                    } else {
                        System.out.println("Cliente não encontrado!");
                    }
                    break;

                case 12:
                    System.out.println(estacionamento.dataToText());
                    break;
                case 13:
                    System.out.print("Digite o mês: ");
                    int mesPesquisa = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Digite a placa do veículo: ");
                    String placaPesquisa = scanner.nextLine();
                    System.out.print("Digite o ID do cliente: ");
                    String idClientePesquisa = scanner.nextLine();
                    Cliente clientePesquisa = estacionamento.buscarClientePorId(idClientePesquisa);
                    if (clientePesquisa != null) {
                        double historico = clientePesquisa.pesquisarHistorico(mesPesquisa,
                                placaPesquisa);
                        System.out.println("Arrecadação do veículo no mês: " + historico);
                    } else {
                        System.out.println("Cliente não encontrado!");
                    }
                    break;
                case 14:
                    System.out.print("Digite o ID do cliente: ");
                    String id = scanner.nextLine();

                    // Recupera o cliente pelo ID
                    Cliente cliente = estacionamento.buscarClientePorId(id);
                    System.out.println("Escolha o novo plano: ");
                    for (TipoCliente tipo : TipoCliente.values()) {
                        System.out.println(tipo.ordinal() + 1 + ". " + tipo.name());
                    }
                    System.out.print("Escolha o novo plano: ");
                    int novoPlanoEscolhido = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer

                    TipoCliente novoPlanoSelecionado = TipoCliente.values()[novoPlanoEscolhido - 1];
                    cliente.escolherPlano(novoPlanoSelecionado);
                    System.out.println("Plano alterado com sucesso!");
                    break;
                case 0:
                    continua = false;
                    break;
                default:
                    System.out.println("Opção inválida! Escolha novamente.");
            }
        }
        scanner.close();
    }
}
