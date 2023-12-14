public class ObservadorEstacionamento implements Observer {
    private String ultimoTop5; // Variável para armazenar o último Top 5

    @Override
    public void atualizar(String top5) {
        // Salva o novo Top 5
        this.ultimoTop5 = top5;

        // Imprime a mensagem no console (ou realiza outra ação com o novo Top 5)
        System.out.println("Atualização no Top 5 Clientes:\n" + top5);
    }

    // Método para obter o último Top 5 salvo pelo observador
    public String getUltimoTop5() {
        return this.ultimoTop5;
    }
}
