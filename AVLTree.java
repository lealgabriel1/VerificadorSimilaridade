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
        if(N == null)
            return 0;

        return N.getAltura();
    }

    // Calcula o fator de balanceamento do nó
    private int getFatorBalanceamento(No N) {
        if(N == null) return 0;

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

    public void exibirMaiores(double similaridade) {
        this.exibirMaioresRecursivo(this.raiz, similaridade);
    }

    private void exibirMaioresRecursivo(No no, double similaridade) {
        if(no == null) {
            return;
        }

        if(no.getChave() >= similaridade) {

            exibirMaioresRecursivo(no.getDireita(), similaridade);
            imprimirPares(no);
            exibirMaioresRecursivo(no.getEsquerda(), similaridade);
        }
        else {
            exibirMaioresRecursivo(no.getDireita(), similaridade);
        }
    }

    public void exibirMenores(double similaridade) {
        exibirMenoresRecursivo(this.raiz, similaridade);
    }


    private void exibirMenoresRecursivo(No no, double similaridade) {
        if(no == null) {
            return;
        }

        if(no.getChave() < similaridade) {
            exibirMenoresRecursivo(no.getDireita(), similaridade);
            imprimirPares(no);
            exibirMenoresRecursivo(no.getEsquerda(), similaridade);
        }
        else {
            exibirMaioresRecursivo(no.getEsquerda(), similaridade);
        }
    }

    private void imprimirPares(No no) {
        ArrayList<Resultado> pares = no.getPares();

        for (Resultado par : pares) {
            String s = String.format("%s = %.2f", par.toString(), no.getChave());
            System.out.println(s);
        }
    }

}
