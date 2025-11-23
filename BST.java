public class BST {
    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

    protected No raiz;

    public void inserir(double chave, Resultado resultado) {
        raiz = inserirRecursivo(raiz, resultado);
    }

    protected No inserirRecursivo(No no, Resultado resultado) {
        if(no == null) {
            return new No(resultado);
        }

        if(resultado.getSimilaridade() < no.getChave()) {
            No novoNo = inserirRecursivo(no.getEsquerda(), resultado);
            no.setEsquerda(novoNo);
        }
        else if(resultado.getSimilaridade() > no.getChave()) {
            No novoNo = inserirRecursivo(no.getDireita(), resultado);
            no.setDireita(novoNo.getEsquerda());
        }

        return no;
    }
}
