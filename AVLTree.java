import java.util.ArrayList;

public class AVLTree extends BST {

    // Variável global da árvore para contar o total de rotacões feitas
    private int totalRotacoes = 0;

    public int getTotalRotacoes() {
        return this.totalRotacoes;
    }

    // Método para resetar os valor do total de rotações
    public void resetTotalRotacoes() {
        this.totalRotacoes = 0;
    }

    @Override
    protected No inserirRecursivo(No no, Resultado resultado) {
        // Chama a inserção da classe pai (BST), da qual extendemos o comportamento da AVLTree
        no = super.inserirRecursivo(no, resultado);

        // Atualiza a altura da árvore
        no.setAltura(1 + Math.max(altura(no.getEsquerda()), altura(no.getDireita())));

        return balancear(no);
    }

    // Retorna a altura do nó
    private int altura(No N) {
        if (N == null)
            return 0;

        return N.getAltura();
    }

    // Calcula o fator de balanceamento do nó
    private int getFatorBalanceamento(No N) {
        if (N == null) return 0;

        return altura(N.getEsquerda()) - altura(N.getDireita());
    }

    private No balancear(No no) {
        int fb = getFatorBalanceamento(no);

        // Esquerda pesada
        if (fb > 1) {

            // Caso Esquerda-Direita (LR) -> Rotação dupla
            if (getFatorBalanceamento(no.getEsquerda()) < 0) {
                no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
            }

            // Caso Esquerda-Esquerda (LL)
            return rotacaoDireita(no);
        }

        // Direita Pesada
        if (fb < -1) {
            // Caso Direita-Esquerda (RL) -> Rotação Dupla
            if (getFatorBalanceamento(no.getDireita()) > 0) {
                no.setDireita(rotacaoDireita(no.getDireita()));
            }

            // Caso Direita-Direita (RR) -> Rotação Simples
            return rotacaoEsquerda(no);
        }

        return no;
    }


    // Realiza rotação à direita (simples)
    private No rotacaoDireita(No N) {
        this.totalRotacoes++; // Incrementa o total de rotações feitas

        No x = N.getEsquerda();
        No y = x.getDireita();

        // Realiza a rotação
        x.setDireita(N);
        N.setEsquerda(y);

        // Atualiza as alturas dos nós
        N.setAltura(Math.max(altura(N.getEsquerda()), altura(N.getDireita())) + 1);
        x.setAltura(Math.max(altura(x.getEsquerda()), altura(x.getDireita())) + 1);

        return x;
    }

    private No rotacaoEsquerda(No x) {
        this.totalRotacoes++; // Incrementa o total de rotações feitas

        No y = x.getDireita();
        No T2 = y.getEsquerda();

        // Rotação
        y.setEsquerda(x);
        x.setDireita(T2);

        // Atualiza as alturas dos nós
        x.setAltura(Math.max(altura(x.getEsquerda()), altura(x.getDireita())) + 1);
        y.setAltura(Math.max(altura(y.getEsquerda()), altura(y.getDireita())) + 1);

        return y;
    }

    public ArrayList<String> exibirMaiores(double similaridade, Integer topK) {
        int contador = 0;

        ArrayList<String> pares = new ArrayList<>();
        this.exibirMaioresRecursivo(this.raiz, similaridade, contador, topK, pares);

        return pares;
    }

    private void exibirMaioresRecursivo(No no, double similaridade, int contador, Integer topK, ArrayList<String> pares) {
        if (no == null) {
            return;
        }

        if (no.getChave() >= similaridade && (topK == null || topK > 0)) {
            contador++;

            if (topK != null)
                topK--;

            exibirMaioresRecursivo(no.getDireita(), similaridade, contador, topK, pares);
            pares.addAll(recuperarPares(no));
            exibirMaioresRecursivo(no.getEsquerda(), similaridade, contador, topK, pares);
        } else {
            exibirMaioresRecursivo(no.getDireita(), similaridade, contador, topK, pares);
        }
    }

    public ArrayList<String> exibirMenores(double similaridade) {
        ArrayList<String> pares = new ArrayList<>();
        exibirMenoresRecursivo(this.raiz, similaridade, pares);
        return pares;
    }


    private void exibirMenoresRecursivo(No no, double similaridade, ArrayList<String> pares) {
        if (no == null) {
            return;
        }

        if (no.getChave() < similaridade) {
            exibirMenoresRecursivo(no.getDireita(), similaridade, pares);
            pares.addAll(recuperarPares(no));
            exibirMenoresRecursivo(no.getEsquerda(), similaridade, pares);
        } else {
            exibirMenoresRecursivo(no.getEsquerda(), similaridade, pares);
        }
    }

    private ArrayList<String> recuperarPares(No no) {
        ArrayList<String> paresFormados = new ArrayList<String>();
        for (Resultado par : no.getPares()) {
            paresFormados.add(par.toString());
        }

        return paresFormados;
    }
}

