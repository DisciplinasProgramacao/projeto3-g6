# Correção

  - tipoCliente está misturando duas regras: preços e horários. (ISP)
  - Observer do estacionamento usando de maneira incorreta o map. Olha a complicação deste código:
```
        List<Map.Entry<String, Double>> listaEntradas = new ArrayList<>(this.clientesMes.get(mes).entrySet());
        listaEntradas.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));
        Map<String, Double> hashMapOrdenado = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : listaEntradas) {
            hashMapOrdenado.put(entry.getKey(), entry.getValue());
        }
```
E ele continua refazendo o top5 em momento separado da observação.  Vejam o código que deixei
  - cliente não muda os veículos de plano: precisa passar o parâmetro a cada chamada
  - criando usoHorista dentro do uso turno... 

## Nota base 15

# Colaborações

  - Brendon
  - Davi 
  - Lara
  - ~~Larissa Felipe~~
  - ~~Lukas Maciel~~
  - Otavio Salomão
	
Requisitos:
  - Gerais: 4/6 pontos
    - cliente mudar de plano 😐 
    - estacionar e cálculo de preço ✔️😐
    - relatórios estacionamento ✔️
  - Padrões de projeto: 4/6 pontos
    - Observer 😐
  - App: 4/5 pontos
    - funcional ✔️ 
    - carga de teste 😐
    - robustez ✔️
  - Documentação: 3/3 pontos
    - diagrama ✔️
    - javadoc ✔️
	
	
	✔️  😐 ❌ ⚠️
