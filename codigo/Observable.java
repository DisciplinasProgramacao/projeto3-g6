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
     * Notifica os observadores sobre uma atualização associada a um determinado mês
     * e item.
     *
     * @param mes  O mês associado à notificação.
     * @param item O item a ser notificado.
     */
    public void notify(Integer mes, T item);
}