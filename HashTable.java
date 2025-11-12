import java.util.LinkedList;
/* Implementacao manual de uma hash table generica.
 *  
 * A classe deve gerenciar um array interno e usar 2 funcoes de dispersao.
 *  
 * O tratamento de colisoes utilizado foi endereçamento fechado com LinkedLists.
 * 
 */

public class HashTable<K, V> {

    private static class Entry<K, V> {
        final K key;
        V value;
        
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry<K, V>>[] table; // array de 'LinkedLists' que contem 'Entrys'
    private int size; // Numero total de pares chave-valor armazenados
    private final int capacity; // O tamanho do array 'tabela' (número de buckets)
    private final int hashFunctionChoice; // 1 para Função 1, 2 para Função 2

    // Construtor
    public HashTable(int initialCapacity, int hashFunctionChoice) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacidade inicial deve ser maior que zero.");
        }
        this.capacity = initialCapacity;
        this.size = 0;
        this.hashFunctionChoice = hashFunctionChoice;
        this.table = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity];


        /* Esta lógica vem do exemplo de implementação do professor:
         * Precisamos inicializar CADA posição do array com uma lista vazia.
         * Sem isso teremos NullPointerException ao tentar adicionar o primeiro item em um bucket.
         */

        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }

    }
    
    private int hash(K key) {
        int hashCode;

        if (hashFunctionChoice == 1) {
            hashCode = hashFunction1(key);
        } else {
            hashCode = hashFunction2(key);
        }

        return Math.abs(hashCode) % capacity;
    }

    /* FUNÇÃO DE DISPERSÃO 1: Polinomial (Base 31) - A "BOA"
     * Justificativa: Padrao da industria (similar ao Java), rapida e
     * com excelente distribuicao para Strings, pois considera a ORDEM.
     */
    private int hashFunction1(K key) {
        String strKey = (String) key;
        int hash = 0;
        int P = 31;

        for (int i = 0; i < strKey.length(); i++) {
            hash = (P * hash + strKey.charAt(i));
        }
        return hash;
    }
   
    /* FUNÇÃO DE DISPERSÃO 2: Soma Simples - A "RUIM"
     * Justificativa: Implementada para comparação. É uma função de baixa
     * qualidade que NAO considera a ordem (ex: "ato" e "toa" terao o
     * mesmo hash), o que deve gerar muitas colisoes.
     */
    private int hashFunction2(K key) {
        String strKey = (String) key;
        int hash = 0;

        for (int i = 0; i < strKey.length(); i++) {
            hash = hash + strKey.charAt(i);
        }
        return hash;
    }
    
    public void put(K key, V value) {
        //
    }
    
    public V get(K key) {
        //
        return null;
    }
    
    public V remove(K key) {
        //
        return null;
    }

    public int getSize() {
        return size;
    }
    
    // TODO -> Método de estatísticas de colisão
}