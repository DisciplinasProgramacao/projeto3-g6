import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe ClienteDAO implementa um Data Access Object para manipulação de objetos Cliente em um arquivo.
 */
public class ClienteDAO implements DAO<Cliente> {
    private String nomeArquivo;
    private Scanner leitorArquivo;
    private FileWriter escritorArquivo;

    /**
     * Construtor da classe ClienteDAO.
     * @param nomeArquivo O nome do arquivo a ser manipulado pelo DAO.
     */
    public ClienteDAO(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.escritorArquivo = null;
        this.leitorArquivo = null;
    }

    /**
     * Abre o arquivo para leitura.
     * @throws IOException Exceção lançada em caso de erro na abertura do arquivo.
     */
    public void abrirLeitura() throws IOException {
        if (escritorArquivo != null) {
            escritorArquivo.close();
            escritorArquivo = null;
        }
        leitorArquivo = new Scanner(new File(nomeArquivo), Charset.forName("UTF-8"));
    }

    /**
     * Abre o arquivo para escrita.
     * @throws IOException Exceção lançada em caso de erro na abertura do arquivo.
     */
    public void abrirEscrita() throws IOException {
        if (leitorArquivo != null) {
            leitorArquivo.close();
            leitorArquivo = null;
        }
        escritorArquivo = new FileWriter(nomeArquivo, Charset.forName("UTF-8"), true);
    }

    /**
     * Fecha os recursos de leitura e escrita do arquivo.
     */
    public void fechar() {
        try {
            if (escritorArquivo != null) {
                escritorArquivo.close();
            }
            if (leitorArquivo != null) {
                leitorArquivo.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            escritorArquivo = null;
            leitorArquivo = null;
        }
    }

    /**
     * Obtém o próximo registro do arquivo e cria um objeto Cliente com os dados lidos.
     * @return Um objeto Cliente lido do arquivo, ou null se não houver mais registros.
     */
    public Cliente getNext() {
        if (leitorArquivo != null && leitorArquivo.hasNext()) {
            String[] linha = leitorArquivo.nextLine().split(" ");
            String id = linha[0];
            String nome = linha[1].toLowerCase();
            TipoCliente tipoCliente = TipoCliente.valueOf(linha[2].toUpperCase());

            return new Cliente(nome, id, tipoCliente);
        }
        return null;
    }


    /**
     * Adiciona um objeto Cliente ao arquivo.
     * @param cliente O objeto Cliente a ser adicionado ao arquivo.
     * @throws IOException Exceção lançada em caso de erro na escrita do arquivo.
     */
    public void add(Cliente cliente) throws IOException {
        abrirEscrita();
        escritorArquivo.append(cliente.dataToText()).append("\n");
        fechar();
    }

    /**
     * Obtém todos os registros do arquivo e os retorna em um array de Clientes.
     * @return Um array de Clientes contendo todos os registros do arquivo.
     */
    public Cliente[] getAll() {       
        List<Cliente> dados = new ArrayList<>();
        
        try {
            fechar();
            abrirLeitura();
            
            while (leitorArquivo != null && leitorArquivo.hasNext()) {
                Cliente cliente = this.getNext();
                if (cliente != null) {
                    dados.add(cliente);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            fechar();
        }
        
        return dados.toArray(new Cliente[0]);
    }

    /**
     * Adiciona vários objetos Cliente ao arquivo.
     * @param clientes Um array de Clientes a ser adicionado ao arquivo.
     */
    public void addAll(Cliente[] clientes) {
        try {
            fechar();
            abrirEscrita();
            for (Cliente cliente : clientes) {
                if (cliente != null) {
                    escritorArquivo.append(cliente.dataToText()).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fechar();
        }
    }
}
