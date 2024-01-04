package engtelecom.product;

import java.util.List;

import javax.swing.ImageIcon;

public interface OltInterface {
    public abstract String getIp();

    public abstract void setIp(String ip);

    public abstract int getPort();

    public abstract void setPort(int port);

    public abstract String getPasswd();

    public abstract void setPasswd(String passwd);

    public abstract String getUser();

    public abstract void setUser(String user);

    public abstract int getSlotLength();

    public abstract void setSlotLength(int slotLength);

    public abstract void start();

    public abstract void saida(ImageIcon saidaIcon);

    public abstract boolean isValidIPv4Address(String ipAddress);

    public abstract void getIpFromUser(ImageIcon saidaIcon, ImageIcon erroIcon);

    public abstract void getPortFromUser(ImageIcon saidaIcon, ImageIcon erroIcon);

    public abstract void getUserAndPwd(ImageIcon saidaIcon, ImageIcon erroIcon);

    public abstract List<String> getVlanClient(ImageIcon equipamentoIcon, ImageIcon saidaIcon,
            ImageIcon erroIcon, int range);

    public abstract String getDefaultCpeType(ImageIcon equipamentoIcon, ImageIcon saidaIcon,
            ImageIcon erroIcon, String[] defaultCpeType);

    public abstract String getModelConfiguration(ImageIcon equipamentoIcon, ImageIcon saidaIcon,
            ImageIcon erroIcon, String[] modelConfiguration);

    public abstract String getInterfaceEth(ImageIcon equipamentoIcon, ImageIcon saidaIcon,
            ImageIcon erroIcon, String[] interfaceEth);
}
