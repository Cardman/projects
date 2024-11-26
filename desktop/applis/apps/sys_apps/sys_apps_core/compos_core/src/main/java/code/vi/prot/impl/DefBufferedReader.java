package code.vi.prot.impl;

import code.gui.initialize.*;
import code.stream.*;
import code.stream.core.*;

import java.io.*;

public final class DefBufferedReader implements AbstractBufferedReader, AbstractBinStreamIn {

    private InputStream reader;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    public DefBufferedReader() {
    }
    public DefBufferedReader(InputStream _read) {
        this.reader = _read;
    }
    @Override
    public int close() {
        out.reset();
        return StreamCoreUtil.close(reader);
    }
    @Override
    public int read() {
        try {
            int read_ = reader.read();
            byte[] arr_ = StreamBinaryFile.wrap(read_);
            out.write(arr_,0,arr_.length);
            return arr_.length;
        } catch (Exception e) {
            return -2;
        }
    }

    @Override
    public String readLine() {
        try {
            out.reset();
            return StreamBinaryFile.readLine(this);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public byte[] getBytes() {
        return out.toByteArray();
    }
}
