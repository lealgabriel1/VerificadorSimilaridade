import java.util.ArrayList;

public class AVLTree extends BST {

    @Override
    protected No inserirRecursivo(No no, Resultado resultado) {
        no = super.inserirRecursivo(no, resultado);

        no.setAltura(1 + Math.max(altura(no.getEsquerda()), altura(no.getDireita())));

        return balancear(no, resultado);
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

    private No balancear(No no, Resultado resultado) {
        int fb = getFatorBalanceamento(no);

        // Esquerda pesada
        if (fb > 1) {
            if (getFatorBalanceamento(no.getEsquerda()) >= 0) {
                return rotacaoDireita(no);
            } else {
                no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
                return rotacaoDireita(no);
            }
        }

        // Direita Pesada
        if (fb < -1) {
            if (getFatorBalanceamento(no.getDireita()) <= 0) {
                return rotacaoEsquerda(no);
            } else {
                no.setDireita(rotacaoDireita(no.getDireita()));
                return rotacaoEsquerda(no);
            }
        }

        return no;
    }


    // Realiza rotação à direita (simples)
    private No rotacaoDireita(No N) {
        No x = N.getEsquerda();
        No y = x.getDireita();

        x.setDireita(N);
        N.setEsquerda(y);

        N.setAltura(Math.max(altura(N.getEsquerda()), altura(N.getDireita())) + 1);
        x.setAltura(Math.max(altura(x.getEsquerda()), altura(x.getDireita())) + 1);

        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.getDireita();
        No T2 = y.getEsquerda();

        y.setEsquerda(x);
        x.setDireita(T2);

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

