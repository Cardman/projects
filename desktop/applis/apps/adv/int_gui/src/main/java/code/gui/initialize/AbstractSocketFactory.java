package code.gui.initialize;

public interface AbstractSocketFactory {
    AbstractSocket newSocket(int _port, String _address);
    AbstractSocket newSocket(int _port);
    AbstractServerSocket newServerSocket(int _port);
    AbstractServerSocket newServerSocket(String _ip, int _port);
    AbstractNetworkInterfaceList newList();
    AbstractAddressList newAddr(String _host);
    boolean setOkServer(boolean _ok);
    boolean setKoSocket(boolean _ok);
}
