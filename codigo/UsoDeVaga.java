import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

// Classe abstrata que representa o uso de uma vaga de estacionamento
public abstract class UsoDeVaga {

    // Atributos da classe
    Vaga vaga; // Vaga utilizada
    LocalDateTime entrada; // Data e hora de entrada na vaga
    LocalDateTime saida; // Data e hora de saída da vaga
    double valorPago; // Valor pago pelo uso da vaga
    Servico servico; // Serviço contratado durante o uso da vaga

    public UsoDeVaga(Vaga vaga){
        this.vaga = vaga;
        entrada = LocalDateTime.now();
    }
    public UsoDeVaga(Vaga vaga, Servico servico){
        this.vaga = vaga;
        contratarServico(servico);
        entrada = LocalDateTime.now();
    }
    
    // Método para adicionar serviços extras ao valor pago
    public void contratarServico(Servico servico) {
        this.servico = servico;
    }

    /**
     * Verifica se o uso da vaga foi no mês fornecido.
     *
     * @param mes O mês a ser verificado (1 a 12 para janeiro a dezembro)
     * @return true se o uso da vaga foi no mês fornecido, caso contrário false
     */
    public boolean ehDoMes(int mes) {
        return entrada.getMonthValue() == mes;
    }

    /**
     * Registra a saída da vaga e calcula o valor a ser pago, levando em consideração o tempo de permanência
     * e os serviços contratados durante o uso da vaga.
     *
     * @return O valor total a ser pago pelo uso da vaga
     */
    public double sair() {
        vaga.setDisponivel(true); // Define a vaga como disponível
        this.saida = LocalDateTime.now(); // Registra a data e hora de saída
        int tempoPermanenciaMinutos = (int) entrada.until(saida, ChronoUnit.MINUTES); // Calcula o tempo de permanência em minutos

        // Verifica se serviços extras foram contratados
        if (servico != null) {
            // Verifica se o tempo de permanência é maior ou igual ao tempo mínimo do serviço contratado
            if (tempoPermanenciaMinutos >= servico.getTempoMinimo()) {
                // Retorna o valor total a ser pago considerando o valor da vaga e o preço do serviço
                return valorPago() + servico.getPreco();
            } else {
                // Retorna apenas o valor da vaga, pois o tempo mínimo do serviço não foi atingido
                return valorPago();
            }
        }
        // Retorna apenas o valor da vaga, pois nenhum serviço extra foi contratado
        return valorPago();
    }

    /**
     * Método abstrato para calcular o valor a ser pago pelo uso da vaga.
     *
     * @return O valor a ser pago pelo uso da vaga
     */
    public abstract double valorPago();


    public LocalDateTime getEntrada() {
        return entrada;
    }
    public LocalDateTime getSaida() {
        return saida;
    }
    
    /**
     * Método para converter informações do uso de vaga em texto.
     *
     * @return Uma representação em texto das informações do uso de vaga
     */
    public String dataToText() {
        StringBuilder data = new StringBuilder();
        data.append("Data de entrada: ").append(entrada).append("\n");
        data.append("Data de saída: ").append(saida).append("\n");

        return data.toString();
    }

    
}
