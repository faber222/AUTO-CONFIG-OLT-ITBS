package engtelecom.analytics;

import java.util.ArrayList;
import java.util.List;

import engtelecom.filters.DataVlanUpFilter;
import engtelecom.filters.DataWanServicePPPFilter;
import engtelecom.filters.DataWanServiceWifiFilter;
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

        // Faz a coleta da vlan
        DataVlanUpFilter dataVlanUpFilter = new DataVlanUpFilter(fileName);
        // dataVlanUpFilter.start();

        // Faz a coleta do whitelist
        DataWhitelistFilter dataWhitelistFilter = new DataWhitelistFilter(fileName);
        // dataWhitelistFilter.start();

        // Faz a coleta da config do wan-service
        // Faz a coleta do pppoe
        DataWanServicePPPFilter dataWanServiceFilter = new DataWanServicePPPFilter(fileName);
        // dataWanServiceFilter.start();

        // Fazer a coleta do wifi com senha
        // Fazer a coleta do wifi com radius
        DataWanServiceWifiFilter dataWanServiceWifiFilter = new DataWanServiceWifiFilter(fileName);
        dataWanServiceWifiFilter.start();

        // TO-DO
        // Fazer a coleta do veip
        // Fazer a coleta do portEth
        // Fazer a coleta do ngn
        // Fazer a coleta do qinq

        // Criar um arrayList de tudo, e enviar para o gerador
    }

    public ArrayList<String> getData() {
        return data;
    }

}
