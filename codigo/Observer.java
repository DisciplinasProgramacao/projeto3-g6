
/**
 * Interface que define o comportamento de um observador.
 */
public interface Observer {

    /**
     * Método chamado para atualizar o observador com informações específicas.
     *
     * @param top5 Informações a serem atualizadas no observador.
     */
    void atualizar(Integer mes, Cliente cliente);

    String getUltimoTop5(Integer mes);

  
}
