import java.time.LocalDateTime;

public class UsoMensalista extends UsoDeVaga {
    private boolean mesPago = false; // Indica se o mês está pago ou não

     /**
     * Construtor para registrar a entrada de um mensalista em uma vaga.
     *
     * @param vaga Vaga utilizada.
     */
    public UsoMensalista(Vaga vaga) {
        super(vaga);
    }

    /**
     * Construtor para registrar a entrada de um mensalista em uma vaga com a contratação de um serviço.
     *
     * @param vaga    Vaga utilizada.
     * @param servico Serviço contratado.
     */
    public UsoMensalista(Vaga vaga, Servico servico) {
        super(vaga, servico);
    }

    /**
     * Construtor para definir um uso mensalista de vaga com horário de entrada e saída específicos.
     *
     * @param vaga     Vaga utilizada.
     * @param entrada  Horário de entrada.
     * @param saida    Horário de saída.
     */
    public UsoMensalista(Vaga vaga, LocalDateTime entrada, LocalDateTime saida) {
        super(vaga, entrada, saida);
    }

    /**
     * Calcula o valor a ser ppublic UsoMensalista(Vaga vaga, LocalDateTime entrada) {
    }

    urn O valor a ser pago pelo uso mensalista (0 se o mês já estiver pago)
     */
    @Override
    public double valorPago() {
        if (mesPago) {
            return 0; // Retorna 0 se o mês já estiver pago
        } else {
            // Lógica para calcular o pagamento do mês
            // Ajuste conforme necessário, por exemplo, para calcular o valor do mês atual
            double valorDoMes = 500.0; // Exemplo: pagamento fixo de 500.0
            mesPago = true; // Marca o mês como pago
            return valorDoMes;
        }
    }

    /**
     * Verifica se o mês está pago.
     *
     * @return true se o mês estiver pago, false caso contrário
     */
    public boolean mesEstaPago() {
        return mesPago;
    }
}
