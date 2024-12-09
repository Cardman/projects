package code.vi.prot.impl;

import code.stream.*;
import code.stream.core.*;

import java.io.*;

public final class DefBufferedReader implements AbstractBinStreamIn {

    private final InputStream reader;
    private final byte[] arr = new byte[8192];
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

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
            int read_ = reader.read(arr);
            int n_ = StreamBinaryFile.next(arr, read_);
            byte[] arr_ = StreamBinaryFile.ext(arr,read_);
            out.write(arr_,0,arr_.length);
            return n_;
        } catch (Exception e) {
            return -2;
        }
    }

    @Override
    public byte[] getBytes() {
        return out.toByteArray();
    }
}
