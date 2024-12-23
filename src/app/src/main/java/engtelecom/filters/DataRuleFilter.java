package engtelecom.filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataRuleFilter {

    private HashMap<Integer, ArrayList<String>> dataMap;
    private final String path;

    public DataRuleFilter(String fileName) {
        this.dataMap = new HashMap<>();
        // this.path = "dadosRules.txt";
        this.path = fileName;
    }

    public void start() {
        // Hashtable onde a chave é o "line" e o valor é uma lista de strings no formato
        // "XXXX-FFFFFFFF;0/x/y"

        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line;
            String aim = null; // Variável para armazenar o valor do aim
            while ((line = br.readLine()) != null) {
                // Regex para capturar o aim no formato 0/x/y
                final Pattern aimPattern = Pattern.compile("aim\\s(\\d+/\\d+/\\d+)");
                final Matcher aimMatcher = aimPattern.matcher(line);
                if (aimMatcher.find()) {
                    aim = aimMatcher.group(1); // Captura o aim encontrado
                }

                // Regex para capturar o string-hex genérico e o valor do line
                final Pattern hexPattern = Pattern
                        .compile("([A-Za-z0-9]{4}-[0-9a-fA-F]{8})\\s+line\\s+(\\d+)");
                final Matcher hexMatcher = hexPattern.matcher(line);
                if (hexMatcher.find()) {
                    String stringHex = hexMatcher.group(1).replace("-", ""); // Captura o valor do string-hex
                                                                             // (XXXX-XXXXXXXX)

                    // Separa o prefixo (primeiros 4 caracteres) e a parte em hexadecimal
                    String prefix = stringHex.substring(0, 3);
                    String hexPart = stringHex.substring(4).toLowerCase(); // Torna os caracteres hexadecimais
                                                                           // minúsculos

                    // Reconstrói a string com a parte hexadecimal convertida para minúsculas
                    stringHex = prefix + hexPart;

                    final int lineKey = Integer.parseInt(hexMatcher.group(2)); // Captura o valor do line como chave

                    // Formata o valor como "XXXX-XXXXXXXX;0/x/y"
                    final String value = stringHex + ";" + aim;

                    // Adiciona o valor na hashMap, agrupando por chave (line)
                    this.dataMap.computeIfAbsent(lineKey, k -> new ArrayList<>()).add(value);
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, ArrayList<String>> getDataMap() {
        return dataMap;
    }

    public void setDataMap(final HashMap<Integer, ArrayList<String>> dataMap) {
        this.dataMap = dataMap;
    }

}
