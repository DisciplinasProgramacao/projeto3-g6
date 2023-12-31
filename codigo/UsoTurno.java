import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Representa o uso de uma vaga de estacionamento durante um turno.
 */
public class UsoTurno extends UsoDeVaga {

    private TipoCliente tipoCliente;
    private boolean mesPago = false; // Indica se o mês está pago ou não

    /**
     * Construtor para registrar a entrada de um cliente por turno em uma vaga.
     *
     * @param vaga         Vaga utilizada.
     * @param tipoCliente  Tipo do cliente.
     */
    public UsoTurno(Vaga vaga, TipoCliente tipoCliente) {
        super(vaga);
        this.tipoCliente = tipoCliente;
    }

    /**
     * Construtor para registrar a entrada de um cliente por turno em uma vaga com a contratação de um serviço.
     *
     * @param vaga         Vaga utilizada.
     * @param servico      Serviço contratado.
     * @param tipoCliente  Tipo do cliente.
     */
    public UsoTurno(Vaga vaga, Servico servico, TipoCliente tipoCliente) {
        super(vaga, servico);
        this.tipoCliente = tipoCliente;
    }

    /**
     * Construtor para definir um uso por turno de vaga com horário de entrada e saída específicos.
     *
     * @param vaga          Vaga utilizada.
     * @param tipoCliente2  Tipo do cliente.
     * @param entrada       Horário de entrada.
     * @param saida         Horário de saída.
     */
    public UsoTurno(Vaga vaga, TipoCliente tipoCliente2, LocalDateTime entrada, LocalDateTime saida) {
        super(vaga, entrada, saida);
        this.tipoCliente = tipoCliente2;
    }

    /**
     * Calcula o valor a ser pago pelo uso da vaga.
     *
     * @return O valor a ser pago pelo uso durante um turno (0 se o mês já estiver pago ou se estiver fora do horário do tipo de cliente)
     */
    @Override
    public double valorPago() {
        if (!mesPago) {
            // Se o mês não estiver pago, verifica se está após o horário permitido
            if (tipoCliente.getHoraFim() != null) {
                LocalTime horarioAtual = LocalTime.now();
                if (horarioAtual.isAfter(tipoCliente.getHoraFim())) {
                    UsoHorista usoHorista = new UsoHorista(vaga, entrada, saida);
                    // Calcula o valor a ser pago para usoHorista com base no tempo excedido
                    return usoHorista.valorPago();
                }
            }
            return 0; // Mês não está pago, mas dentro do horário do tipo de cliente
        } else {
            // Mês está pago, retorna o valor do tipo de cliente
            return tipoCliente.getValor();
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
