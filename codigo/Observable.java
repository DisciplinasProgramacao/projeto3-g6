/**
 * Interface que define o comportamento de um objeto observável.
 */
public interface Observable<T> {

    /**
     * Anexa um observador à lista de observadores.
     *
     * @param observer Observador a ser anexado.
     */
    public void attatch(Observer observer);

    /**
     * Desanexa um observador da lista de observadores.
     *
     * @param observer Observador a ser desanexado.
     */
    public void detach(Observer observer);

    /**
     * Notifica os observadores sobre alguma atualização.
     *
     * @param top5 Informações a serem enviadas aos observadores.
     */
    public void notify(Integer mes, T item);
}