package code.vi.prot.impl;

import code.gui.initialize.AbstractBufferedReader;

import java.io.BufferedReader;

public final class DefBufferedReader implements AbstractBufferedReader {
    private BufferedReader reader;

    public DefBufferedReader() {
    }
    public DefBufferedReader(BufferedReader _reader) {
        reader = _reader;
    }
    @Override
    public String readLine() {
        try {
            return reader.readLine();
        } catch (Exception e) {
            return null;
        }
    }
}
