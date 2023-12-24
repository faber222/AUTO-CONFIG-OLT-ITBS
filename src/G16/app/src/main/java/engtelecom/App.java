package engtelecom;

import engtelecom.product.OltG16;

/**
 * Classe principal que inicia a aplicação OltG16.
 */
public class App {

    /**
     * Método principal que cria uma instância da classe OltG16 e inicia a aplicação.
     *
     * @param args Argumentos da linha de comando (não utilizados neste momento).
     */
    public static void main(String[] args) {
        OltG16 oltG16 = new OltG16();
        oltG16.start();
    }
}
