package engtelecom.filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataWhitelistFilter {
    private final String path;

    // Listas para armazenar os dados processados
    private final List<String[]> whitelist;
    private final List<String[]> authoList;

    public DataWhitelistFilter(final String path) {
        this.path = path;
        this.whitelist = new ArrayList<>();
        this.authoList = new ArrayList<>();
    }

    /**
     * [0] phy addr
     * [1] slot
     * [2] pon
     * [3] onu
     * [4] type
     * 
     * @return ArrayList contendo os dados do whitelist
     */
    public List<String[]> getWhitelist() {
        return whitelist;
    }

    /**
     * [0] phy addr
     * [1] slot
     * [2] pon
     * [3] onu
     * [4] type
     * 
     * @return ArrayList contendo os dados do auth
     */
    public List<String[]> getAuthoList() {
        return authoList;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void start() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Regex para capturar as entradas de whitelist
                final Pattern whitePattern = Pattern.compile(
                        "set white phy addr (\\S+) .*? sl (\\d+) p (\\d+) o (\\d+) ty (\\S+)");
                final Matcher whiteMatcher = whitePattern.matcher(line);

                if (whiteMatcher.find()) {
                    final String[] entry = new String[] {
                            whiteMatcher.group(1), // phy addr
                            whiteMatcher.group(2), // slot
                            whiteMatcher.group(3), // pon
                            whiteMatcher.group(4), // onu
                            whiteMatcher.group(5) // type
                    };
                    whitelist.add(entry);
                    continue;
                }

                // Regex para capturar as entradas de autorização
                final Pattern authoPattern = Pattern.compile(
                        "set autho sl (\\d+) p (\\d+) ty (\\S+) o (\\d+) phy (\\S+)");
                final Matcher authoMatcher = authoPattern.matcher(line);

                if (authoMatcher.find()) {
                    final String[] entry = new String[] {
                            authoMatcher.group(5), // phy addr
                            authoMatcher.group(1), // slot
                            authoMatcher.group(2), // pon
                            authoMatcher.group(4), // onu
                            authoMatcher.group(3) // type
                    };
                    authoList.add(entry);
                }
            }

            System.out.println("Whitelist:");
            for (final String[] entry : whitelist) {
                System.out.println(Arrays.toString(entry));
            }

            System.out.println("\nAutorização:");
            for (final String[] entry : authoList) {
                System.out.println(Arrays.toString(entry));
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
