/**
 * Enumeração que representa os tipos de serviço oferecidos pelo estacionamento.
 */
public enum Servico {
    MANOBRISTA(5.0, 0),
    LAVAGEM(20.0, 60),
    POLIMENTO(45.0, 120);

    private final double preco;
    private final double tempoMinimo;

    /**
     * Construtor privado para os tipos de serviço com preço e tempo mínimo.
     *
     * @param preco       Preço do serviço.
     * @param tempoMinimo Tempo mínimo necessário para o serviço.
     */
    Servico(double preco, double tempoMinimo) {
        this.preco = preco;
        this.tempoMinimo = tempoMinimo;
    }

    /**
     * Obtém o preço do serviço.
     *
     * @return Preço do serviço.
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Obtém o tempo mínimo necessário para o serviço.
     *
     * @return Tempo mínimo para o serviço.
     */
    public double getTempoMinimo() {
        return tempoMinimo;
    }
}
