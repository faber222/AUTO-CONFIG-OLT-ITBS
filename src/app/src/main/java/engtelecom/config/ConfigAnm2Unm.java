package engtelecom.config;

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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@SuppressWarnings("unused")
public class ConfigAnm2Unm {

    private final Path anmPath;

    private final Path unmPath;

    public ConfigAnm2Unm(final Path anmPath, final Path unmPath) {
        this.anmPath = anmPath;
        this.unmPath = unmPath;
    }

    public void processar(final String nomeArquivoSaida) throws Exception {
        final List<Map<String, String>> anmData = lerCsvAnm(anmPath);
        final List<String> unmData = lerXlsUnm(unmPath);

        final Map<String, String> mapPhysicalToDesc = new LinkedHashMap<>();
        final Set<String> usados = new HashSet<>();

        for (String unm : unmData) {
            String descricao = unm; // Default é o próprio valor do UNM

            Iterator<Map<String, String>> iter = anmData.iterator();
            while (iter.hasNext()) {
                Map<String, String> anm = iter.next();
                String phys = anm.get("Physical Address");
                if (phys.equalsIgnoreCase(unm)) {
                    descricao = anm.get("Object Name");
                    iter.remove(); // Remove para otimizar próximas buscas
                    break;
                }
            }

            mapPhysicalToDesc.put(unm, descricao);
        }
        salvarComparacao(mapPhysicalToDesc, nomeArquivoSaida);
    }

    private void salvarComparacao(
        final Map<String, String> mapa,
        final String nomeArquivo
    ) throws IOException {
        try (
            BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(nomeArquivo)
            )
        ) {
            for (final Map.Entry<String, String> entry : mapa.entrySet()) {
                writer.write(entry.getValue() + ";" + entry.getKey());
                writer.newLine();
            }
        }
    }

    private List<Map<String, String>> lerCsvAnm(final Path path)
        throws IOException {
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
                String objectName = partes[idxObjectName].trim().replaceAll("\\s+", "-");
                mapa.put("Object Name", objectName);
                // mapa.put("Object Name", partes[idxObjectName].trim());
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
}
