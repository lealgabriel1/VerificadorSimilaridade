/*
 * Alunos: ...; Giovanna Borges Coelho - RA10756784; ...
*/

import java.io.File;

public class Main {

    public static void main(String[] args) {

        // Validação dos argumentos de entrada
        if(args.length < 3) {
            System.out.println("Número de argumentos informados inferior ao esperado! Uso correto: java Main <diretorio_documentos> <limiar> <modo> [argumentos_opcionais].");
            return;
        }

        String diretorioDocumentos = args[0]; // Primeiro argumento referente ao diretório em que estão os arquivos

        File caminho = new File(diretorioDocumentos);

        // Validação do argumento <diretorio_documento> informado
        if(!caminho.exists() || !caminho.isDirectory()) {
            System.out.println("Caminho não encontrado ou não é um diretório!");
            return;
        }

        // Validação do limiar informado
        double limiar = 0.0;
        try {
            limiar = Double.parseDouble(args[1]);
        }
        catch (NumberFormatException ex) {
            System.out.println("Erro! Limiar deve ser um número decimal");
            ex.printStackTrace();
            return;
        }

        String modo = args[2];

        Configuracao configuracao = new Configuracao(caminho, limiar, args);

        try {
            switch (modo) {
                case "lista": executarModoLista(configuracao); break;
                case "topK": executarModoTopK(configuracao); break;
                case "busca": executarModoBusca(configuracao); break;
            }
        }
        catch (Exception ex) {
            System.out.println("Ocorreu um erro inesperado!");
            ex.printStackTrace();
        }
    }

    public static void executarModoLista(Configuracao configuracao) {

    }

    public static void executarModoTopK(Configuracao configuracao) {
        int topK = 0;

        try {
            topK = Integer.parseInt(configuracao.getArg(3));
        }
        catch (NumberFormatException ex) {
            System.out.println("Ocorreu um erro ao obter o valor de topK! Um número inteiro deve ser passado como argumento!");
            return;
        }


    }

    public static void executarModoBusca(Configuracao configuracao) {

        if (configuracao.getArgs().length < 5) {
            System.out.println("Erro: Modo busca precisa de dois arquivos.");
            return;
        }

        String documentoA = configuracao.getArg(3);
        String documentoB = configuracao.getArg(4);

    }


}
