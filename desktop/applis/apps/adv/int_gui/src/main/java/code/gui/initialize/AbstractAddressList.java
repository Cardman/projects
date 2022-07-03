package code.gui.initialize;
public interface AbstractAddressList {
    int size();

    String getHost(int _index);
    boolean isLoopbackAddress(int _index);
    boolean isIpFour(int _index);
    boolean isIpSix( int _index);
}
