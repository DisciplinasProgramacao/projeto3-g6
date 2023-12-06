import java.time.temporal.ChronoUnit;

/**
 * Representa o uso de uma vaga de estacionamento para horistas.
 * O valor é calculado com base no tempo de uso por frações de 15 minutos.
 * O valor máximo a ser pago é de 50.0.
 */
public class UsoHorista extends UsoDeVaga {

    public UsoHorista(Vaga vaga) {
        super(vaga);
    }

    public UsoHorista(Vaga vaga, Servico servico) {
        super(vaga, servico);
    }

    /**
     * Calcula o valor a ser pago pelo uso da vaga (baseado no tempo de uso).
     * Limita o valor máximo a ser pago a 50.0.
     *
     * @return O valor a ser pago pelo uso horista
     */
    @Override
    public double valorPago() {
        int calcTempo = (int) entrada.until(saida, ChronoUnit.MINUTES);
        if (calcTempo == 0)
            calcTempo = 1;
        int quantidadeFracoesTempo = (int) Math.ceil(calcTempo / 15.0);
        double valorPago = quantidadeFracoesTempo * 4.0; // Valor fixo por fração de tempo

        return Math.min(valorPago, 50.0); // Limita o valor máximo a 50.0
    }
}
