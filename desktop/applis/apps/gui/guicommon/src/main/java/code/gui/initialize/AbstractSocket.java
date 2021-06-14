package code.gui.initialize;

import java.io.Closeable;

public interface AbstractSocket {
    AbstractBufferedReader getInput();

    void println(String _string);

    Closeable getClos();
}
