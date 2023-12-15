/**
 * Classe que representa uma vaga no estacionamento.
 */
public class Vaga implements Comparable<Vaga>, IDataToText {
    private String id; // Identificação única da vaga
    private boolean disponivel; // Indica se a vaga está disponível

    /**
     * Construtor para criar uma Vaga com uma identificação única.
     *
     * @param id Identificação única da vaga
     */
    public Vaga(String id) {
        this.id = id;
        this.disponivel = true; // Inicialmente, a vaga está disponível
    }

    /**
     * Obtém a disponibilidade da vaga.
     *
     * @return true se a vaga está disponível, false caso contrário
     */
    public boolean disponivel() {
        return this.disponivel;
    }

    /**
     * Estaciona um veículo na vaga.
     *
     * @return true se o veículo foi estacionado com sucesso, false se a vaga já
     *         estiver ocupada
     */
    public boolean estacionar() {
        if (disponivel) {
            this.disponivel = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Libera a vaga, indicando que o veículo saiu.
     *
     * @return true se a vaga foi liberada com sucesso, false se a vaga já estiver
     *         livre
     */
    public boolean sair() {
        if (!disponivel) {
            this.disponivel = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método de comparação para ordenar vagas com base na disponibilidade.
     *
     * @param outraVaga Vaga a ser comparada.
     * @return Um valor negativo se esta vaga está disponível e outraVaga está
     *         ocupada,
     *         um valor positivo se esta vaga está ocupada e outraVaga está
     *         disponível,
     *         ou zero se ambas têm a mesma disponibilidade.
     */
    @Override
    public int compareTo(Vaga outraVaga) {
        if (this.disponivel && !outraVaga.disponivel()) {
            return -1; // Esta vaga está disponível, outraVaga está ocupada, portanto é "menor"
        } else if (!this.disponivel && outraVaga.disponivel()) {
            return 1; // Esta vaga está ocupada, outraVaga está disponível, portanto é "maior"
        } else {
            return 0; // Ambas as vagas têm a mesma disponibilidade
        }
    }

    /**
     * Método que retorna uma representação textual dos dados da vaga.
     *
     * @return String com os dados da vaga
     */
    @Override
    public String dataToText() {
        StringBuilder data = new StringBuilder();
        data.append("ID da Vaga: ").append(this.id).append("\n");
        data.append("Disponibilidade: ").append(this.disponivel ? "Disponível" : "Ocupada").append("\n");
        return data.toString();
    }

}
