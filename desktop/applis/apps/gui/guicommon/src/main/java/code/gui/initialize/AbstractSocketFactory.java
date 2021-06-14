package code.gui.initialize;

public interface AbstractSocketFactory {
    AbstractSocket newSocket(int _port, String _address);
    AbstractServerSocket newServerSocket();
    AbstractNetworkInterfaceList newList();
    AbstractAddressList newAddr(String _host);
}
