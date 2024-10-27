package code.gui.initialize;

public interface AbstractServerSocket {
    int close();

    boolean isOk();
    void setOk(boolean _o);
    boolean isClosed();

    AbstractSocket accept();
}
