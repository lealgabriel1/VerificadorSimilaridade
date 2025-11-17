import java.io.IOException;

/**
 * Responsabilidades da classe Doc:
 *  1. Ler um arquivo de texto;
 *  2. Normalizar o conteúdo (stop words, remover pontuação, tokenizar e etc. que o professor citou);
 *  3. Popular uma HashTable<> com as frequências das palavras.
 */

public class Doc {

    //nome simples do arquivo (ex: "doc1.txt")
    private String nomeArquivo;

    //tabela de frequências das palavras deste documento
    private HashTable<String, Integer> tabelaFrequencias;

    //lista de stopwords
    private static final String[] STOPWORDS = {
        "a", "o", "os", "as", "um", "uma", "uns", "umas",
        "de", "do", "da", "dos", "das",
        "em", "no", "na", "nos", "nas",
        "para", "por", "com", "sem",
        "e", "é", "ser", "são", "foi", "era",
        "que", "se", "ao", "à", "às", "àquele", "àquela",
        "ou", "mas", "como", "também",
        "este", "esta", "esse", "essa", "isso", "isto", "aquele", "aquela",
        "depois", "antes", "muito", "muita", "muitos", "muitas"
    };

    public Doc(String caminhoArquivo, int hashFunctionChoice, int capacidadeHash) throws IOException {
        this.nomeArquivo = extrairNomeArquivo(caminhoArquivo);
        this.tabelaFrequencias = new HashTable<>(capacidadeHash, hashFunctionChoice);
        processarArquivo(caminhoArquivo);
    }

    public Doc(String caminhoArquivo, int hashFunctionChoice) throws IOException {
        this(caminhoArquivo, hashFunctionChoice, 1024);
    }

    public String getNomeArquivo() {
        return this.nomeArquivo;
    }

    public HashTable<String, Integer> getTabelaFrequencias() {
        return this.tabelaFrequencias;
    }

    //retorna a frequência de uma palavra aplicando a mesma normalizacao usada durante a leitura
    public int getFrequencia(String palavra) {
        if (palavra == null) {
            return 0;
        }

        //normalizacao real vai ser implementada no TODO
        String normalizada = normalizarPalavra(palavra);

        if (normalizada.isEmpty() || isStopWord(normalizada)) {
            return 0;
        }

        Integer freq = tabelaFrequencias.get(normalizada);
        return (freq == null) ? 0 : freq;
    }
    
    //TODO -> implementar metodo (private void processarArquivo(String caminhoArquivo) throws IOException)

    //TODO -> implementar metodo (private void processarLinha(String linha))

    //TODO -> implementar metodo (private String normalizarPalavra(String palavra))

    //TODO -> implementar metodo (private static boolean isStopWord(String palavra))

    //TODO -> implementar metodo (private static String extrairNomeArquivo(String caminho))

}
