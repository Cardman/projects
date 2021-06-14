package code.gui.initialize;

import java.io.Closeable;

public interface AbstractServerSocket {
    Closeable getClos();

    boolean isClosed();

    AbstractServerSocket bind(String _ip, int _port);

    AbstractSocket accept();
}
