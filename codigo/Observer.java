
/**
 * Interface que define o comportamento de um observador.
 */
public interface Observer {

    /**
     * Método chamado para atualizar o observador com informações específicas.
     *@param mes Atualizar cliente top5
     *@param cliente Cliente a ser comparado
     */
    void atualizar(Integer mes, Cliente cliente);

    /**
     * Método chamado para informar o top5do mes
     * @param mes mes que vai ser procurado
     * @return string com informaçoes do top 5
     */
    String getUltimoTop5(Integer mes);

  
}
