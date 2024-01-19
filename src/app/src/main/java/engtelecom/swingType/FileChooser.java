/**
 * ARQUIVO BASE, CRIADO POR CHATGPT
 */

package engtelecom.swingType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileChooser {

    public static void main(String[] args) {
        JFrame frame = new JFrame("File Chooser Example");
        JButton button = new JButton("Escolher Arquivo");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria um seletor de arquivo
                JFileChooser fileChooser = new JFileChooser();

                // Exibe o seletor de arquivo e obtém a resposta do usuário
                int returnValue = fileChooser.showOpenDialog(null);

                // Verifica se o usuário escolheu um arquivo
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Obtém o arquivo selecionado
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Arquivo selecionado: " + selectedFile.getAbsolutePath());
                } else {
                    System.out.println("Nenhum arquivo selecionado.");
                }
            }
        });

        frame.getContentPane().add(button);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}