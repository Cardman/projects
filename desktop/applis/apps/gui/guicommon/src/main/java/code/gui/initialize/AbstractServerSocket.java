package code.gui.initialize;

public interface AbstractServerSocket {
    boolean close();

    boolean isOk();
    boolean isClosed();

    AbstractSocket accept();
}
