package code.gui.initialize;

public interface AbstractServerSocket {
    boolean close();

    boolean isOk();
    void setOk(boolean _o);
    boolean isClosed();

    AbstractSocket accept();
}
