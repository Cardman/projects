package code.gui.initialize;

public interface AbstractNetworkInterfaceList {
    int size();
    AbstractAddressList list(int _index);
    int size(int _index);

    String getName(int _index);
    boolean isVirtual(int _index);
}
