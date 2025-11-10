/* Implementacao manual (sem usar Java HashMap) de uma hash table generica.
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
        Entry<K, V> next; // Ponteiro para o prox. no dentro do mesmo "bucket"

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Entry<K, V>[] tabela; // O array de "buckets" (listas ligadas)
    private int size; // Numero total de pares chave-valor armazenados
    private final int capacity; // O tamanho do array 'tabela' (número de buckets)
    private final int hashFunctionChoice; // 1 para Função 1, 2 para Função 2

    // Construtor
    public HashTable(int initialCapacity, int hashFunctionChoice) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacidade inicial deve ser maior que zero.");
        }
        this.capacity = initialCapacity;

        // TODO -> criar o array de buckets
        // estudar maneira de contornar 'type erasure'
    }
    
    /* TODO:
     * 1. hash() wrapper
     * 2. funções hash
     * 3. put(k, v)
     * 4. get(k)
     */

}