
public class Cliente {

	private String nome;
	private String id;
	private Veiculo[] veiculos;

	public Cliente(String nome, String id) {
		this.nome = nome;
		this.id = id;
		this.Veiculo = new Veiculo[0];

	}

	public void addVeiculo(Veiculo veiculo) {
		int tamanhoAtual = veiculos.length;
		Veiculo[] novoArray = new Veiculo[tamanhoAtual + 1];
		for (int i = 0; i < tamanhoAtual; i++) {
			novoArray[i] = veiculos[i];

		}
		novoArray[tamanhoAtual] = veiculo;
		veiculos = novoArray;
	}

	public Veiculo possuiVeiculo(String placa) {
		for (Veiculo veiculo : veiculos) {
			if (veiculo.getPlaca().equals(placa)) {
				return veiculo;
			}
		}
		return null;
	}

	public int totalDeUsos() {
		return veiculos.length;

	}

	public double arrecadadoPorVeiculo(String placa) {
		double arrecadacaoTotal = 0.0;
		for (Veiculo veiculo : veiculos) {
			if (veiculo.getPlaca().equals(placa)) {
				arrecadacaoTotal += veiculo.getArrecadacao();
				return arrecadacaoTotal;
			}			
		}
		return 0.0;
	}


	public double arrecadadoNoMes(int mes) {
		double arrecadacaoNoMes = 0.0;
		for (Veiculo veiculo : veiculos) {
			arrecadacaoNoMes += veiculo.arrecadadoNoMes(mes);
		}
		return arrecadacaoNoMes;
	}

}
