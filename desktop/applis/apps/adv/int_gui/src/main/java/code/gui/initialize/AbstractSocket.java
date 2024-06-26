package code.gui.initialize;

public interface AbstractSocket {
    AbstractBufferedReader getInput();

    String println(String _string);

    void close();

    boolean isKo();

    String inetAddress();
    String localAddress();
    String localSocketAddress();
    String remoteSocketAddress();
}
