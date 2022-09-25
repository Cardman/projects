package code.vi.sys.impl;

import code.stream.core.AbstractBinStreamIn;
import code.util.core.NumberUtil;
import code.vi.prot.impl.StreamCoreUtil;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public abstract class AbstractBinStreamInImpl implements AbstractBinStreamIn {
    private final InputStream reader;
    private final byte[] arr = new byte[1024];
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    protected AbstractBinStreamInImpl(InputStream _read) {
        this.reader = _read;
    }
    @Override
    public boolean close() {
        out.reset();
        return StreamCoreUtil.close(reader);
    }


    @Override
    public int read() {
        try {
            int read_ = reader.read(arr);
            out.write(arr,0, NumberUtil.max(0,read_));
            return read_;
        } catch (Exception e) {
            return -2;
        }
    }

    @Override
    public byte[] getBytes() {
        return out.toByteArray();
    }
}
