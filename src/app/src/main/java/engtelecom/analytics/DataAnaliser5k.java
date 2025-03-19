package engtelecom.analytics;

import engtelecom.filters.DataBandwidthFilter;
import engtelecom.filters.DataNgnFilter;
import engtelecom.filters.DataPortEthFilter;
import engtelecom.filters.DataVeipFilter;
import engtelecom.filters.DataVlanUpFilter;
import engtelecom.filters.DataWanServicePPPFilter;
import engtelecom.filters.DataWanServiceWifiFilter;
import engtelecom.filters.DataWhitelistFilter;

public class DataAnaliser5k {
    private final DataVlanUpFilter dataVlanUpFilter;
    private final DataWhitelistFilter dataWhitelistFilter;
    private final DataWanServicePPPFilter dataWanServiceFilter;
    private final DataWanServiceWifiFilter dataWanServiceWifiFilter;
    private final DataVeipFilter dataVeipFilter;
    private final DataPortEthFilter dataPortEthFilter;
    private final DataBandwidthFilter dataBandwidthFilter;
    private final DataNgnFilter dataNgnFilter;

    private final String fileName;

    public DataAnaliser5k(final String fileName) {
        this.fileName = fileName;
        this.dataVlanUpFilter = new DataVlanUpFilter(fileName);
        this.dataWhitelistFilter = new DataWhitelistFilter(fileName);
        this.dataWanServiceFilter = new DataWanServicePPPFilter(fileName);
        this.dataWanServiceWifiFilter = new DataWanServiceWifiFilter(fileName);
        this.dataVeipFilter = new DataVeipFilter(fileName);
        this.dataPortEthFilter = new DataPortEthFilter(fileName);
        this.dataBandwidthFilter = new DataBandwidthFilter(fileName);
        this.dataNgnFilter = new DataNgnFilter(fileName);
    }

    public DataVlanUpFilter getDataVlanUpFilter() {
        return dataVlanUpFilter;
    }

    public DataWhitelistFilter getDataWhitelistFilter() {
        return dataWhitelistFilter;
    }

    public DataWanServicePPPFilter getDataWanServiceFilter() {
        return dataWanServiceFilter;
    }

    public DataWanServiceWifiFilter getDataWanServiceWifiFilter() {
        return dataWanServiceWifiFilter;
    }

    public DataVeipFilter getDataVeipFilter() {
        return dataVeipFilter;
    }

    public DataPortEthFilter getDataPortEthFilter() {
        return dataPortEthFilter;
    }

    public DataBandwidthFilter getDataBandwidthFilter() {
        return dataBandwidthFilter;
    }

    public DataNgnFilter getDataNgnFilter() {
        return dataNgnFilter;
    }

    public void start() {
        System.out.println(this.fileName);

        // Faz a coleta da vlan
        dataVlanUpFilter.start();

        // Faz a coleta do whitelist
        // dataWhitelistFilter.start();

        // Faz a coleta da config do wan-service
        // Faz a coleta do pppoe
        // dataWanServiceFilter.start();

        // Fazer a coleta do wifi com senha
        // Fazer a coleta do wifi com radius
        // dataWanServiceWifiFilter.start();

        // Fazer a coleta do veip
        // dataVeipFilter.start();

        // Fazer a coleta do portEth
        // dataPortEthFilter.start();

        // Fazer a coleta do bandwidth
        // dataBandwidthFilter.start();

        // Fazer a coleta do ngn
        // dataNgnFilter.start();

        // TO-DO
        // Fazer a coleta do qinq
    }

}
