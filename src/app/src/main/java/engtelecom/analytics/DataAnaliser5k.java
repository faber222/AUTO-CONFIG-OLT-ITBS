package engtelecom.analytics;

import java.util.ArrayList;
import java.util.List;

import engtelecom.filters.DataVlanUpFilter;
import engtelecom.filters.DataWhitelistFilter;

public class DataAnaliser5k {
    private List<List<String>> configUplinkVlan;
    private List<List<String>> whitelist;
    private List<List<String>> veip;
    private List<List<String>> wanService;
    private List<List<String>> portEth;
    private List<List<String>> ngn;
    private List<List<String>> qinq;

    private String fileName;
    private ArrayList<String> data;

    public DataAnaliser5k(String fileName) {
        this.data = new ArrayList<>();
        this.fileName = fileName;
    }

    public void start() {
        System.out.println(this.fileName);

        // Fazer a coleta da vlan
        DataVlanUpFilter dataVlanUpFilter = new DataVlanUpFilter(fileName);
        dataVlanUpFilter.start();

        // Fazer a coleta do whitelist
        DataWhitelistFilter dataWhitelistFilter = new DataWhitelistFilter(fileName);
        dataWhitelistFilter.start();

        // TO-DO
        // Fazer a coleta do veip
        // Fazer a coleta do wanService{ Fazer a coleta do wifi e pppoe }
        // Fazer a coleta do portEth
        // Fazer a coleta do ngn
        // Fazer a coleta do qinq

        // Criar um arrayList de tudo, e enviar para o gerador
    }

    public ArrayList<String> getData() {
        return data;
    }

}
