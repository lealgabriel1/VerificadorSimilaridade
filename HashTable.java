import java.util.LinkedList;
/* Implementacao manual de uma hash table generica.
 *  
 * A classe deve gerenciar um array interno e usar 2 funcoes de dispersao.
 *  
 * O tratamento de colisoes utilizado foi endereçamento fechado com arrays.
 * 
 * TODO -> (explicação da escolha do tratamento de colisões)
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
    
    /* TODO:
     * 1. hash() wrapper
     * 2. funções hash
     * 3. put(k, v)
     * 4. get(k)
     * 
     *  **ADCIONANDO STUBS**
     */
    private int hash(K key) {
        
        return 0;
    }

    private int hashFunction1(K key) {
        //
        return key.hashCode();
    }
   
    private int hashFunction2(K key) {
        //
        return key.hashCode();
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