import java.io.IOException;

/**
 * Interface genérica para manipulação de objetos.
 * @param <T> Tipo genérico que implementa IDataToText
 */
public interface DAO<T extends IDataToText> {
    
    /**
     * Obtém todos os objetos do tipo T.
     *
     * @return Array de objetos do tipo T
     * @throws IOException Exceção lançada caso ocorra um erro na leitura
     */
    public T[] getAll() throws IOException;

    /**
     * Adiciona um objeto do tipo T.
     *
     * @param dado Objeto do tipo T a ser adicionado
     * @throws IOException Exceção lançada caso ocorra um erro na escrita
     */
    public void add(T dado) throws IOException;

    /**
     * Adiciona vários objetos do tipo T.
     *
     * @param dados Array de objetos do tipo T a serem adicionados
     */
    public void addAll(T[] dados);
}

