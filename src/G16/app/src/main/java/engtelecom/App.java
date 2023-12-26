package engtelecom;

import engtelecom.product.OltG16;

/**
 * Classe principal da aplicação que inicia a OLT G16.
 */
public class App {

    /**
     * Construtor padrão da classe App.
     */
    public App() {
        // Construtor padrão, não há ações específicas aqui no momento.
    }

    /**
     * O método principal que inicia a aplicação.
     *
     * @param args Argumentos da linha de comando (não utilizados neste momento).
     */
    public static void main(final String[] args) {
        final OltG16 OltG16 = new OltG16();
        OltG16.start();
    }
}
