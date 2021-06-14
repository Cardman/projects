package code.gui.initialize;

public interface AbstractNetworkInterfaceList {
    int size();
    int size(int _index);

    boolean isLoopbackAddress(int _first, int _index);
    boolean isIpFour(int _first, int _index);
    boolean isIpSix(int _first, int _index);
    String getHost(int _first, int _index);
    String getName(int _index);
    boolean isVirtual(int _index);
}
