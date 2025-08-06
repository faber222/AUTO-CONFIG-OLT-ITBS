package engtelecom.swingType.anm2Unm;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import engtelecom.config.ConfigAnm2Unm;

public class BatchAnmUnmWorker extends SwingWorker<Void, Integer> {

    private final String anmDirPath;
    private final String unmDirPath;
    private final JProgressBar progressBar;
    private final JLabel percentLabel;

    public BatchAnmUnmWorker(String anmDirPath, String unmDirPath, JProgressBar progressBar, JLabel percentLabel) {
        this.anmDirPath = anmDirPath;
        this.unmDirPath = unmDirPath;
        this.progressBar = progressBar;
        this.percentLabel = percentLabel;
    }

    @Override
    protected Void doInBackground() throws Exception {
        File dirAnm = new File(anmDirPath);
        File dirUnm = new File(unmDirPath);

        File[] anmFiles = dirAnm.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));
        File[] unmFiles = dirUnm.listFiles((dir, name) -> name.toLowerCase().endsWith(".xls") || name.toLowerCase().endsWith(".xlsx"));

        if (anmFiles == null || unmFiles == null) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar arquivos.");
            return null;
        }

        Arrays.sort(anmFiles);
        Arrays.sort(unmFiles);

        int total = Math.min(anmFiles.length, unmFiles.length);
        progressBar.setMaximum(total);

        for (int i = 0; i < total; i++) {
            File anmFile = anmFiles[i];
            File unmFile = unmFiles[i];
            String nomeSaida = "COMPARACAO-" + (i + 1) + ".txt";

            ConfigAnm2Unm comparador = new ConfigAnm2Unm(anmFile.toPath(), unmFile.toPath());
            comparador.processar(nomeSaida);

            publish(i + 1);
        }

        return null;
    }

    @Override
    protected void process(List<Integer> chunks) {
        int progressoAtual = chunks.get(chunks.size() - 1);
        progressBar.setValue(progressoAtual);

        int total = progressBar.getMaximum();
        int porcentagem = (int) (((double) progressoAtual / total) * 100);
        percentLabel.setText(porcentagem + "%");
    }

    @Override
    protected void done() {
        JOptionPane.showMessageDialog(null, "Processamento finalizado com sucesso!");
        progressBar.setValue(0);
        percentLabel.setText("0%");
    }
}
