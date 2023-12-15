public class Cliente implements IDataToText {

    private String nome;
    private String id;
    private Veiculo[] veiculos = new Veiculo[10];
    private TipoCliente tipoCliente;

    /**
     * Construtor da classe Cliente.
     *
     * @param nome        O nome do cliente.
     * @param id          O identificador único do cliente.
     * @param tipoCliente O tipo do cliente (HORISTA, MENSALISTA, etc.).
     */
    public Cliente(String nome, String id, TipoCliente tipoCliente) {
        this.nome = nome;
        this.id = id;
        this.tipoCliente = tipoCliente;
    }

    /**
     * Adiciona um veículo à lista de veículos do cliente.
     *
     * @param veiculo O veículo a ser adicionado.
     * @throws Exception Se o veículo com a mesma placa já existir para este
     *                   cliente.
     */
    public void addVeiculo(Veiculo veiculo) throws Exception {
        boolean placaExistente = false;
        if (veiculos != null) {
            for (Veiculo v : veiculos) {
                if (v != null && v.getPlaca().equals(veiculo.getPlaca())) {
                    placaExistente = true;
                    break;
                }
            }
            if (!placaExistente) {
                for (int i = 0; i < veiculos.length; i++) {
                    if (veiculos[i] == null) {
                        veiculos[i] = veiculo;
                        veiculo.definirTipo(tipoCliente);
                        break;
                    }
                }
            } else {
                throw new Exception("Veículo com placa igual já existe para este cliente.");
            }
        } else {
            veiculos[0] = veiculo;
        }
    }

    /**
     * Verifica se o cliente possui um veículo com a placa fornecida.
     *
     * @param placa A placa do veículo a ser verificada.
     * @return O veículo associado à placa, se existir; caso contrário, retorna
     *         null.
     */
    public Veiculo possuiVeiculo(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo != null && veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    /**
     * Calcula o total de usos de veículos pelo cliente.
     *
     * @return O número total de usos de veículos pelo cliente.
     */
    public int totalDeUsos() {
        int totalUsos = 0;
        for (Veiculo veiculo : veiculos) {
            if (veiculo != null) {
                totalUsos += veiculo.totalDeUsos();
            }
        }
        return totalUsos;
    }

    /**
     * Calcula a arrecadação total do cliente.
     *
     * @return A arrecadação total do cliente.
     */
    public double arrecadadoTotal() {
        double totalArrecadado = 0;
        for (Veiculo veiculo : veiculos) {
            if (veiculo != null) {
                totalArrecadado += veiculo.totalArrecadado();
            }
        }

        return totalArrecadado;
    }

    /**
     * Calcula a arrecadação do cliente em um determinado mês.
     *
     * @param mes O mês para o qual se deseja calcular a arrecadação.
     * @return A arrecadação do cliente no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        double arrecadadoNoMes = 0;
        for (Veiculo veiculo : veiculos) {
            if (veiculo != null) {
                arrecadadoNoMes += veiculo.arrecadadoNoMes(mes);
            }
        }
        return arrecadadoNoMes;
    }

    /**
     * Converte os dados do cliente em uma representação textual.
     *
     * @return Uma representação textual dos dados do cliente.
     */
    @Override
    public String toString() {
        return id + ";" + nome;
    }
    

    /**
     * Pesquisa o histórico de arrecadação de um veículo associado ao cliente em um
     * mês específico.
     *
     * @param mes   O mês para o qual se deseja pesquisar o histórico.
     * @param placa A placa do veículo para o qual se deseja pesquisar o histórico.
     * @return A arrecadação do veículo associado ao cliente no mês especificado.
     */
    public double pesquisarHistorico(int mes, String placa) {
        Veiculo veiculo = possuiVeiculo(placa);
        if (veiculo != null) {
            return veiculo.arrecadadoNoMes(mes);
        }
        return 0.0;
    }

    /**
     * Define o novo plano do cliente.
     *
     * @param novoTipoCliente O novo tipo de cliente a ser atribuído.
     */

    public void escolherPlano(TipoCliente novoTipoCliente) {
        this.tipoCliente = novoTipoCliente;
    }

    /**
     * Obtém o identificador único do cliente.
     *
     * @return O identificador único do cliente.
     */
    public String getId() {
        return id;
    }

    /**
     * Converte os dados do cliente para texto.
     *
     * @return Uma representação textual dos dados do cliente.
     */
    @Override
    public String dataToText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente:\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Veículos: ").append(veiculos.length).append("\n");
        sb.append("Arrecadação Total: ").append(arrecadadoTotal()).append("\n");
        sb.append("Tipo Cliente: ").append(tipoCliente).append("\n");
        return sb.toString();
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Obtém o tipo de cliente.
     *
     * @return O tipo de cliente (HORISTA, MENSALISTA, etc.).
     */
    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }
}