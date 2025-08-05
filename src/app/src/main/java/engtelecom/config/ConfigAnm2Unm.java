package engtelecom.config;

// import com.google.common.collect.Table.Cell;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// import com.google.common.collect.Table.Cell;

@SuppressWarnings("unused")
public class ConfigAnm2Unm {

    // main para testar
    public static void main(final String[] args) throws Exception {
        final Path anmPath = Paths.get("entrada.csv");
        final Path unmPath = Paths.get("entrada.xls");

        final ConfigAnm2Unm config = new ConfigAnm2Unm(anmPath, unmPath);
        config.processar();
    }
    private final Path anmPath;

    private final Path unmPath;

    public ConfigAnm2Unm(final Path anmPath, final Path unmPath) {
        this.anmPath = anmPath;
        this.unmPath = unmPath;
    }

    public void processar() throws Exception {
        final List<Map<String, String>> anmData = lerCsvAnm(anmPath);
        final List<String> unmData = lerXlsUnm(unmPath);

        salvarAnmTxt(anmData, "anm_output.txt");
        salvarUnmTxt(unmData, "unm_output.txt");

        // // Exemplo: salvar valores de Physical Address que existem nos dois arquivos
        // Set<String> comum = new HashSet<>();
        // for (Map<String, String> linha : anmData) {
        //     String physAddr = linha.get("Physical Address");
        //     if (unmData.contains(physAddr)) {
        //         comum.add(physAddr);
        //     }
        // }
        // salvarComum(comum, "comparacao_output.txt");
    }

    private List<Map<String, String>> lerCsvAnm(final Path path) throws IOException {
        final List<Map<String, String>> resultado = new ArrayList<>();
        try (
            BufferedReader reader = Files.newBufferedReader(
                path,
                StandardCharsets.ISO_8859_1
            )
        ) {
            String headerLine = null;
            int linhaAtual = 0;

            while ((headerLine = reader.readLine()) != null) {
                linhaAtual++;
                if (linhaAtual == 6) break;
            }

            if (headerLine == null) return resultado;
            final String[] headers = headerLine.split(",");

            int idxObjectName = -1;
            int idxPhysAddress = -1;
            for (int i = 0; i < headers.length; i++) {
                if (
                    headers[i].trim().equalsIgnoreCase("Object Name")
                ) idxObjectName = i;
                if (
                    headers[i].trim().equalsIgnoreCase("Physical Address")
                ) idxPhysAddress = i;
            }

            if (idxObjectName == -1 || idxPhysAddress == -1) return resultado;

            String linha;
            while ((linha = reader.readLine()) != null) {
                final String[] partes = linha.split(",");
                if (
                    partes.length <= Math.max(idxObjectName, idxPhysAddress)
                ) continue;

                final Map<String, String> mapa = new HashMap<>();
                mapa.put("Object Name", partes[idxObjectName].trim());
                mapa.put("Physical Address", partes[idxPhysAddress].trim());
                resultado.add(mapa);
            }
        }
        return resultado;
    }

    @SuppressWarnings("deprecation")
	private List<String> lerXlsUnm(final Path path) throws IOException {
        final List<String> resultado = new ArrayList<>();
        try (
            InputStream inp = Files.newInputStream(path);
            Workbook workbook = new XSSFWorkbook(Files.newInputStream(unmPath));
        ) {
            final Sheet sheet = workbook.getSheetAt(0);
            final Row headerRow = sheet.getRow(4); // linha 5 (indexada em 0)

            int idxPhysAddress = -1;
            for (final org.apache.poi.ss.usermodel.Cell cell : headerRow) {
                if (
                    cell
                        .getStringCellValue()
                        .trim()
                        .equalsIgnoreCase("Physic Address")
                ) {
                    idxPhysAddress = cell.getColumnIndex();
                    break;
                }
            }

            if (idxPhysAddress == -1) return resultado;

            for (int i = 5; i <= sheet.getLastRowNum(); i++) {
                // a partir da linha 6
                final Row row = sheet.getRow(i);
                if (row == null) continue;

                final Cell cell = (Cell) row.getCell(idxPhysAddress);
                if (cell != null) {
                    ((org.apache.poi.ss.usermodel.Cell) cell).setCellType(
                        CellType.STRING
                    );
                    resultado.add(
                        ((org.apache.poi.ss.usermodel.Cell) cell).getStringCellValue().trim()
                    );
                }
            }
        }
        return resultado;
    }

    private void salvarAnmTxt(
        final List<Map<String, String>> dados,
        final String nomeArquivo
    ) throws IOException {
        try (
            BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(nomeArquivo)
            )
        ) {
            for (final Map<String, String> linha : dados) {
                writer.write(
                    linha.get("Object Name") +
                    " ; " +
                    linha.get("Physical Address")
                );
                writer.newLine();
            }
        }
    }

    private void salvarUnmTxt(final List<String> dados, final String nomeArquivo)
        throws IOException {
        Files.write(Paths.get(nomeArquivo), dados);
    }

    @SuppressWarnings("unused")
	private void salvarComum(final Set<String> comum, final String nomeArquivo)
        throws IOException {
        Files.write(Paths.get(nomeArquivo), comum);
    }
}
