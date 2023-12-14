public class Cliente implements IDataToText {

    private String nome;
    private String id;
    private Veiculo[] veiculos = new Veiculo[10];
    private TipoCliente tipoCliente;

    // Construtor da classe Cliente
    public Cliente(String nome, String id, TipoCliente tipoCliente) {
        this.nome = nome;
        this.id = id;
        this.tipoCliente = tipoCliente;
    }

    // Método para adicionar um veículo ao cliente
    public void addVeiculo(Veiculo veiculo) {
        if (veiculos != null) {
            for (int i = 0; i < veiculos.length; i++) {
                if (veiculos[i] == null) {
                    veiculos[i] = veiculo;
                    break;
                }
            }
        } else {
            veiculos[0] = veiculo;
        }
    }

    // Método para verificar se o cliente possui um veículo com uma determinada
    // placa
    public Veiculo possuiVeiculo(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo != null && veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    // Método para calcular o total de usos de veículos pelo cliente
    public int totalDeUsos() {
        int totalUsos = 0;
        for (Veiculo veiculo : veiculos) {
            if (veiculo != null) {
                totalUsos += veiculo.totalDeUsos();
            }
        }
        return totalUsos;
    }

    // Método para calcular a arrecadação total do cliente
    public double arrecadadoTotal() {
        double totalArrecadado = 0;
        for (Veiculo veiculo : veiculos) {
            if (veiculo != null) {
                totalArrecadado += veiculo.totalArrecadado();
            }
        }

        return totalArrecadado;
    }

    // Método para calcular a arrecadação do cliente em um determinado mês
    public double arrecadadoNoMes(int mes) {
        double arrecadadoNoMes = 0;
        for (Veiculo veiculo : veiculos) {
            if (veiculo != null) {
                arrecadadoNoMes += veiculo.arrecadadoNoMes(mes);
            }
        }
        return arrecadadoNoMes;
    }

    @Override
    public String toString() {
        return id + ";" + nome;
    }

    public double pesquisarHistorico(int mes, String placa) {
        Veiculo veiculo = possuiVeiculo(placa);
        if (veiculo != null) {
            return veiculo.arrecadadoNoMes(mes);
        }
        return 0.0;
    }

    public void escolherPlano(TipoCliente novoTipoCliente) {
        this.tipoCliente = novoTipoCliente;
    }

    public String getId(){
        return id;
    }

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

    public String getNome() {
        return this.nome;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }
}