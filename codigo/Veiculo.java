import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Veiculo implements IDataToText {

    String placa;
    private List<UsoDeVaga> usos;
    TipoCliente tipoCliente;
    boolean estacionado;

    /**
     * Construtor para criar um Veículo com uma placa e um número máximo de usos.
     * 
     * @param placa A placa do veículo
     */
    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new ArrayList<>();

    }

    /**
     * Estaciona o veículo em uma vaga de acordo com o tipo de cliente.
     * @param vaga        A vaga onde o veículo será estacionado
     * @param tipo O tipo de cliente (Mensalista, Horista, Turno)
     */
    public void estacionar(Vaga vaga, TipoCliente tipo) {
        UsoDeVaga novoUso;
        definirTipo(tipo);
        switch (tipoCliente) {
            case MENSALISTA:
                novoUso = new UsoMensalista(vaga);
                break;
            case HORISTA:
                novoUso = new UsoHorista(vaga);
                break;
            default:
                novoUso = new UsoTurno(vaga, tipoCliente);
                break;
        }
        // Estaciona o veículo na vaga e associa o novo uso ao veículo
        vaga.estacionar();
        usos.add(novoUso);
        estacionado = true;
    }

    public void estacionarComServicos(Vaga vaga, Servico servicoSelecionado) {
        UsoDeVaga novoUso;

        switch (tipoCliente) {
            case MENSALISTA:
                novoUso = new UsoMensalista(vaga, servicoSelecionado); // Adicionando o serviço ao criar o uso
                break;
            case HORISTA:
                novoUso = new UsoHorista(vaga, servicoSelecionado); // Adicionando o serviço ao criar o uso
                break;
            default:
                novoUso = new UsoTurno(vaga, servicoSelecionado, tipoCliente); // Adicionando o serviço ao criar o uso
                break;
        }
        estacionado = true;
        vaga.estacionar();
        usos.add(novoUso);
    }

    /**
     * Estaciona um veículo em uma vaga para o cliente com base no tipo de cliente.
     *
     * @param vaga    Vaga onde o veículo será estacionado.
     * @param entrada Horário de entrada do veículo.
     * @param saida   Horário de saída do veículo.
     */
    public void estacionar(Vaga vaga, LocalDateTime entrada, LocalDateTime saida) {
        UsoDeVaga novoUso;

        switch (tipoCliente) {
            case MENSALISTA:
                novoUso = new UsoMensalista(vaga, entrada, saida);
                break;
            case HORISTA:
                novoUso = new UsoHorista(vaga, entrada, saida);
                break;
            default:
                novoUso = new UsoTurno(vaga, tipoCliente, entrada, saida);
                break;
        }
        vaga.estacionar();
        usos.add(novoUso);
        estacionado = true;
    }

    /**
     * Calcula o valor total a ser pago ao sair do estacionamento.
     *
     * @return O valor total a ser pago.
     * @throws Exception Se houver algum erro no cálculo do valor.
     */
    public double sair() throws Exception {

        double totalPago = 0.0;

        for (UsoDeVaga usoVaga : usos) {
            if (usoVaga != null && usoVaga.getSaida() == null) {
                totalPago += usoVaga.sair();
            }
        }
        estacionado = false;
        return totalPago;
    }

    /**
     * Gera um texto contendo informações sobre os usos de vaga associados ao
     * veículo.
     *
     * @return Uma string com informações detalhadas sobre os usos de vaga do
     *         veículo
     */
    @Override
    public String dataToText() {
        StringBuilder data = new StringBuilder();
        data.append("Placa do Veículo: ").append(placa).append("\n");

        for (UsoDeVaga usoVaga : usos) {
            data.append(usoVaga.dataToText()).append("\n");
        }

        return data.toString();
    }

    /**
     * Calcula o total arrecadado pelo veículo em um determinado mês.
     *
     * @param mes O mês para o qual se deseja calcular a arrecadação (1 a 12,
     *            representando janeiro a dezembro)
     * @return O valor total arrecadado pelo veículo no mês especificado
     */
    public double arrecadadoNoMes(int mes) {
        double totalArrecadadoNoMes = 0.0;
        for (UsoDeVaga usoVaga : usos) {
            if (usoVaga != null && usoVaga.getSaida() != null) {
                LocalDateTime data = usoVaga.getSaida();
                int mesData = data.getMonthValue();
                if (data != null && mesData == mes) {
                    totalArrecadadoNoMes += usoVaga.valorPago();
                }
            }
        }
        return totalArrecadadoNoMes;
    }

    /**
     * Calcula o valor total arrecadado pelo veículo.
     *
     * @return O valor total arrecadado pelo veículo por todos os usos realizados
     */
    public double totalArrecadado() {
        double totalArrecadado = 0.0;
        for (UsoDeVaga usoVaga : usos) {
            if (usoVaga != null && usoVaga.getSaida() != null) {
                totalArrecadado += usoVaga.valorPago();
            }
        }
        return totalArrecadado;
    }

    /**
     * Retorna o número total de usos realizados pelo veículo.
     *
     * @return O número total de usos realizados pelo veículo
     */
    public int totalDeUsos() {
        return usos.size();
    }

    /**
     * Obtém a placa do veículo.
     *
     * @return A placa do veículo
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Define o tipo de cliente para o estacionamento.
     *
     * @param tipo Tipo de cliente a ser definido.
     */
    public void definirTipo(TipoCliente tipo) {
        this.tipoCliente = tipo;
    }

    /**
     * Verifica se há um veículo estacionado no momento.
     *
     * @return true se houver um veículo estacionado, false caso contrário.
     */
    public boolean estaEstacionado() {
        return this.estacionado;
    }
}
