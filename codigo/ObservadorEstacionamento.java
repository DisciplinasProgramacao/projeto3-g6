import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe que representa um Observador para o Estacionamento.
 */
public class ObservadorEstacionamento implements Observer {
    //private Map<Integer, Map<String, Double>> clientesMes = new HashMap<Integer, Map<String, Double>>();
    private Map<Integer, List<Cliente>> topClientesPorMes = new HashMap<>();

    /**
     * Método chamado quando há uma atualização no Top 5.
     *@param mes da atualisaçao
     * @param c a ser comparado
     */
    @Override
    // public void atualizar(Integer mes, Cliente c) {
    

    //     if (!this.clientesMes.containsKey(mes)) {
    //         this.clientesMes.put(mes, new HashMap<String, Double>());
    //     }

    //     Map<String, Double> clientes = this.clientesMes.get(mes);
    //     clientes.put(c.getNome(), c.arrecadadoNoMes(mes));
    //     this.clientesMes.put(mes, clientes);

    // }


    public void atualizar(Integer mes, Cliente c) {  //mantendo o mês só pela interface de vocês. Ele não é necessário aqui.
        int mesAtual = LocalDateTime.now().getMonthValue();
        List<Cliente> topDoMes = topClientesPorMes.get(mesAtual);
        
        if(topDoMes==null){                 //lista vazia: criar e colocar o cliente
            topDoMes = new ArrayList<>(5);
            topDoMes.add(c);
        }
        else{
            if(topDoMes.size()<5){  //ainda não tem 5 clientes
                topDoMes.add(c);
            }
            else{ // procura se entra em lugar de algum dos 5.
                if(!topDoMes.contains(c)){      //se o cliente ainda não está na lista
                    Collections.sort(topDoMes, (c1,c2) -> c1.arrecadadoNoMes(mesAtual)>c2.arrecadadoNoMes(mesAtual)?1:-1);
                    Collections.reverse(topDoMes);          //dava para fazer direto em cima, só para mostrar aqui que ordenamos do maior
            
                    for(int i=0; i<5; i++){                 //procura se o cliente que mudou é maior que alguém da lista.
                        Cliente top = topDoMes.get(i);
                        if(c.arrecadadoNoMes(mesAtual) > top.arrecadadoNoMes(mesAtual)){
                            topDoMes.add(i, c);
                            break;
                        }
                    }
                }
                topDoMes = topDoMes.subList(0, 5);
            }
        }
        this.topClientesPorMes.put(mesAtual, topDoMes);

    }

    public String getUltimoTop5(Integer mes) {
        return topClientesPorMes.get(mes).stream()
                .map(Cliente::dataToText)
                .reduce((a,b) -> a+"\n"+b)
                .orElse("Não há clientes neste mês");
            }
    /**
     * Obtém o último Top 5 salvo pelo observador.
     * 
     * @param mes do top5
     * @return Último Top 5 atualizado.
     */
    // public String getUltimoTop5(Integer mes) {
    //     List<Map.Entry<String, Double>> listaEntradas = new ArrayList<>(this.clientesMes.get(mes).entrySet());

    //     listaEntradas.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));
    //     Map<String, Double> hashMapOrdenado = new LinkedHashMap<>();
    //     for (Map.Entry<String, Double> entry : listaEntradas) {
    //         hashMapOrdenado.put(entry.getKey(), entry.getValue());
    //     }

    //     StringBuilder result = new StringBuilder("Top 5 Clientes em " + mes + ":\n");
    //     Integer i = 0;
    //     for (Map.Entry<String, Double> entry : hashMapOrdenado.entrySet()) {
    //         if (i == 5) {
    //             break;
    //         }
    //         result.append(i + 1)
    //                 .append(". ")
    //                 .append(entry.getKey())
    //                 .append(" - Valor: ")
    //                 .append(entry.getValue())
    //                 .append("\n");
    //         i++;
    //     }

    //     return result.toString();
    // }

}
