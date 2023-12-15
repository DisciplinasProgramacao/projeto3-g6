import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe que representa um Observador para o Estacionamento.
 */
public class ObservadorEstacionamento implements Observer {
    private Map<Integer, Map<String, Double>> clientesMes = new HashMap<Integer, Map<String, Double>>();

    /**
     * Método chamado quando há uma atualização no Top 5.
     *@param mes da atualisaçao
     * @param cliente a ser comparado
     */
    @Override
    public void atualizar(Integer mes, Cliente c) {

        if (!this.clientesMes.containsKey(mes)) {
            this.clientesMes.put(mes, new HashMap<String, Double>());
        }

        Map<String, Double> clientes = this.clientesMes.get(mes);
        clientes.put(c.getNome(), c.arrecadadoNoMes(mes));
        this.clientesMes.put(mes, clientes);

    }

    /**
     * Obtém o último Top 5 salvo pelo observador.
     * 
     * @param mes do top5
     * @return Último Top 5 atualizado.
     */
    public String getUltimoTop5(Integer mes) {
        List<Map.Entry<String, Double>> listaEntradas = new ArrayList<>(this.clientesMes.get(mes).entrySet());

        listaEntradas.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));
        Map<String, Double> hashMapOrdenado = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : listaEntradas) {
            hashMapOrdenado.put(entry.getKey(), entry.getValue());
        }

        StringBuilder result = new StringBuilder("Top 5 Clientes em " + mes + ":\n");
        Integer i = 0;
        for (Map.Entry<String, Double> entry : hashMapOrdenado.entrySet()) {
            if (i == 5) {
                break;
            }
            result.append(i + 1)
                    .append(". ")
                    .append(entry.getKey())
                    .append(" - Valor: ")
                    .append(entry.getValue())
                    .append("\n");
            i++;
        }

        return result.toString();
    }

}
