import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Classe que representa um estacionamento.
 */
public class Estacionamento implements Observable, IDataToText {
    private String nome;
    private Map<String, Cliente> clientes; // Mapa de clientes usando o ID como chave
    private Vaga[] vagas;
    private int quantFileiras;
    private int vagasPorFileira;
    private PriorityQueue<Vaga> filaPrioridades; // Fila de prioridades para vagas
    private List<Observer> top5Observers = new ArrayList<>();

    /**
     * Construtor da classe Estacionamento.
     *
     * @param nome         Nome do estacionamento.
     * @param fileiras     Número de fileiras no estacionamento.
     * @param vagasPorFila Número de vagas por fileira no estacionamento.
     */
    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.clientes = new HashMap<>(); // Utilizando um mapa para os clientes
        this.vagas = new Vaga[fileiras * vagasPorFila];
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.filaPrioridades = new PriorityQueue<>(); // Inicializando a fila de prioridades para vagas
        gerarVagas();
    }

    /**
     * Adiciona um veículo a um cliente no estacionamento.
     *
     * @param veiculo Veículo a ser adicionado.
     * @param idCli   ID do cliente ao qual o veículo será associado.
     */
    public void addVeiculo(Veiculo veiculo, String idCli) {
        Cliente cliente = clientes.get(idCli);
        if (cliente != null) {
            cliente.addVeiculo(veiculo);
        }
    }

    /**
     * Adiciona um cliente ao estacionamento.
     *
     * @param cliente Cliente a ser adicionado.
     */
    public void addCliente(Cliente cliente) {
        this.clientes.put(cliente.getId(), cliente); // Adiciona o cliente ao mapa usando o ID como chave
    }

    /**
     * Gera as vagas do estacionamento.
     */
    public void gerarVagas() {
        int index = 0;
        for (int i = 0; i < this.quantFileiras; i++) {
            for (int j = 0; j < this.vagasPorFileira; j++) {
                String id = String.format("Fila%dVaga%02d", i, j);
                Vaga vaga = new Vaga(id);
                this.vagas[index] = vaga;
                this.filaPrioridades.offer(vaga); // Adiciona a vaga à fila de prioridades
                index++;
            }
        }
    }

    /**
     * Estaciona um veículo no estacionamento.
     *
     * @param placa Placa do veículo a ser estacionado.
     * @param time  Horário de entrada do veículo.
     * @throws Exception Se o carro já estiver estacionado.
     */
    public void estacionar(String placa, TipoCliente tipo) throws Exception {
        boolean estacionado = false;
        Cliente clienteEstacionado = null;

        for (Vaga vaga : filaPrioridades) {
            if (vaga.disponivel() && !estacionado) {
                for (Cliente cliente : clientes.values()) {
                    Veiculo veiculo = cliente.possuiVeiculo(placa);
                    if (veiculo != null) {
                        clienteEstacionado = cliente;
                        veiculo.estacionar(vaga, tipo);
                        estacionado = true;
                        break;
                    }
                }
            }
            if (estacionado) {
                break;
            }
        }
        if (estacionado && clienteEstacionado != null) {
            double arrecadacaoAnterior = clienteEstacionado.arrecadadoTotal();
            if (clienteEstacionado.arrecadadoTotal() > arrecadacaoAnterior) {
                notify(); // Método para notificar a mudança no Top 5 aos observadores
            }
        } else {
            throw new Exception("Carro já está estacionado");
        }
    }

    /**
     * Estaciona um veículo no estacionamento com data determinada.
     *
     * @param placa   Placa do veículo a ser estacionado.
     * @param tipo    Tipo de cliente.
     * @param entrada Horário de entrada do veículo.
     * @param saida   Horário de saída do veículo.
     * @throws Exception Se o carro já estiver estacionado.
     */
    public void estacionar(String placa, TipoCliente tipo, LocalDateTime entrada, LocalDateTime saida)
            throws Exception {
        boolean estacionado = false;

        for (Vaga vaga : filaPrioridades) {
            if (vaga.disponivel() && !estacionado) {
                for (Cliente cliente : clientes.values()) {
                    Veiculo veiculo = cliente.possuiVeiculo(placa);
                    if (veiculo != null) {
                        veiculo.estacionar(vaga, tipo, entrada, saida, null);
                        estacionado = true;
                        break;
                    }
                }
            }
            if (estacionado) {
                break;
            }
        }
        if (!estacionado) {
            throw new Exception("Carro já está estacionado");
        }
    }

    /**
     * Calcula o total arrecadado pelo estacionamento.
     *
     * @return Valor total arrecadado.
     */
    public double totalArrecadado() {
        double sum = 0;
        for (Cliente cliente : clientes.values()) {
            sum += cliente.arrecadadoTotal();
        }
        return sum;
    }

    /**
     * Calcula a arrecadação do estacionamento em um determinado mês.
     *
     * @param mes Mês para o qual se deseja calcular a arrecadação.
     * @return Valor arrecadado no mês.
     */
    public double arrecadacaoNoMes(int mes) {
        double totalArrecadadoNoMes = 0.0;
        for (Cliente cliente : clientes.values()) {
            totalArrecadadoNoMes += cliente.arrecadadoNoMes(mes);
        }
        return totalArrecadadoNoMes;
    }

    /**
     * Calcula o valor médio gasto por uso de vaga.
     *
     * @return Valor médio por uso de vaga.
     */
    public double valorMedioPorUso() {
        double sum = 0;
        int count = 0;
        for (Cliente cliente : clientes.values()) {
            sum += (cliente.arrecadadoTotal() / cliente.totalDeUsos());
            count++;
        }
        if (count > 0) {
            return sum / count;
        } else {
            return 0;
        }
    }

    /**
     * Remove um cliente do estacionamento.
     *
     * @param idCli ID do cliente a ser removido.
     */
    public void removeCliente(String idCli) {
        this.clientes.remove(idCli); // Remove o cliente do mapa usando o ID como chave
    }

    /**
     * Retorna os cinco principais clientes do estacionamento em um determinado mês.
     *
     * @param mes Mês para o qual se deseja obter os principais clientes.
     * @return Lista com os dados dos cinco principais clientes no formato de texto.
     */
    public String top5Clientes(int mes) {
        // Criar uma lista ordenada dos clientes com base na arrecadação no mês
        // fornecido
        List<Cliente> topClientes = new ArrayList<>(clientes.values());
        topClientes.sort(Comparator.comparingDouble(cliente -> -cliente.arrecadadoNoMes(mes)));

        // Selecionar os cinco principais clientes ou todos, caso haja menos de cinco
        int count = Math.min(5, topClientes.size());
        StringBuilder result = new StringBuilder("Top 5 Clientes em " + mes + ":\n");
        for (int i = 0; i < count; i++) {
            Cliente cliente = topClientes.get(i);
            result.append(i + 1)
                    .append(". ")
                    .append(cliente.getNome())
                    .append(" - Valor: ")
                    .append(cliente.arrecadadoNoMes(mes))
                    .append("\n");
        }

        String top5 = result.toString(); // Supondo que 'result' é a lista dos top 5 clientes

        notify(top5); // Notifica os observadores sobre a atualização no top 5

        return top5;

    }

    /**
     * Busca um cliente pelo ID.
     *
     * @param idClientePesquisa ID do cliente a ser buscado.
     * @return Cliente correspondente ao ID ou null se não encontrado.
     */
    public Cliente buscarClientePorId(String idClientePesquisa) {
        for (Cliente cliente : clientes.values()) {
            if (cliente.getId().equals(idClientePesquisa)) {
                return cliente;
            }
        }
        return null; // Retorna null se o cliente não for encontrado
    }

    /**
     * Remove um veículo estacionado do estacionamento.
     * 
     * @param placa Placa do veículo a ser removido.
     * @param tipo  Tipo do cliente.
     * @return double valor pago
     * @throws Exception
     */
    public double sair(String placa, TipoCliente tipo) throws Exception {
        double valorPago = -1.0; // Definir um valor padrão para indicar que o veículo não foi encontrado

        for (Cliente cliente : clientes.values()) {
            Veiculo veiculo = cliente.possuiVeiculo(placa);

            if (veiculo != null) {
                valorPago = veiculo.sair(tipo); // Atualizar o horário de saída
            }
        }
        if (valorPago < 0.0) {
            throw new Exception("Veículo não estacionado");
        }

        // Obtém o mês atual do sistema
        LocalDateTime dataAtual = LocalDateTime.now();
        int mesAtual = dataAtual.getMonthValue();

        notify(top5Clientes(mesAtual)); // Notifica os observadores sobre a atualização no top 5 usando o mês atual

        return valorPago;
    }

    /**
     * Anexa um observador à lista de observadores de top 5.
     *
     * @param observer Observador a ser anexado.
     */
    @Override
    public void attatch(Observer observer) {
        top5Observers.add(observer);
    }

    /**
     * Desanexa um observador da lista de observadores de top 5.
     *
     * @param observer Observador a ser desanexado.
     */
    @Override
    public void detach(Observer observer) {
        top5Observers.remove(observer);
    }

    /**
     * Notifica os observadores sobre a atualização no top 5.
     *
     * @param top5 Lista com os dados dos cinco principais clientes no formato de
     *             texto.
     */
    @Override
    public void notify(String top5) {
        for (Observer observer : top5Observers) {
            observer.atualizar(top5);
        }
    }

    /**
     * Converte os dados do estacionamento em formato de texto.
     *
     * @return String com os detalhes do estacionamento.
     */
    @Override
    public String dataToText() {
        StringBuilder data = new StringBuilder();

        data.append("Nome do Estacionamento: ").append(nome).append("\n");
        data.append("Quantidade de Clientes: ").append(clientes.size()).append("\n");
        data.append("Quantidade de Vagas: ").append(vagas.length).append("\n");
        data.append("Quantidade de Fileiras: ").append(quantFileiras).append("\n");
        data.append("Vagas por Fileira: ").append(vagasPorFileira).append("\n");

        return data.toString();
    }

    public void estacionarComServicos(String placa, TipoCliente tipo, Servico servicoSelecionado) throws Exception {
         boolean estacionado = false;

        for (Vaga vaga : filaPrioridades) {
            if (vaga.disponivel() && !estacionado) {
                for (Cliente cliente : clientes.values()) {
                    Veiculo veiculo = cliente.possuiVeiculo(placa);
                    if (veiculo != null) {
                        veiculo.estacionar(vaga, tipo);
                        estacionado = true;
                        break;
                    }
                }
            }
            if (estacionado) {
                break;
            }
        }
        if (!estacionado) {
            throw new Exception("Carro já está estacionado");
        }
    }

}
