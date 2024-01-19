package engtelecom.swingType;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

public class DarkThemeExample {

    public static void main(String[] args) {
        try {
            // Define o tema como HiFi (um tema escuro do JTattoo)
            UIManager.setLookAndFeel(new HiFiLookAndFeel());

            // Cria a interface gráfica
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Dark Theme Example");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 300);
                frame.setLocationRelativeTo(null);

                // Adiciona alguns componentes à janela
                JLabel label = new JLabel("Olá, este é um exemplo de tema escuro!");
                label.setHorizontalAlignment(JLabel.CENTER);
                frame.getContentPane().add(label, BorderLayout.CENTER);

                JButton button = new JButton("Clique aqui");
                frame.getContentPane().add(button, BorderLayout.SOUTH);

                frame.setVisible(true);
            });
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}