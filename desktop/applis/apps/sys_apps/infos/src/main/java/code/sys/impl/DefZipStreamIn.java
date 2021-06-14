package code.sys.impl;

import code.stream.core.AbstractZipStreamIn;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class DefZipStreamIn implements AbstractZipStreamIn {
    private final ZipInputStream zipIn;
    public DefZipStreamIn(byte[] _bytes) {
        ByteArrayInputStream bais_ = new ByteArrayInputStream(_bytes);
        zipIn = new ZipInputStream(bais_);
    }
    @Override
    public ZipEntry getNextEntry() {
        try {
            return zipIn.getNextEntry();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean closeEntry() {
        try {
            zipIn.closeEntry();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public void close() {
        StreamCoreUtil.close(zipIn);
    }

    @Override
    public int read(byte[] _array, int _off, int _len) {
        try {
            return zipIn.read(_array,_off,_len);
        } catch (IOException e) {
            return -2;
        }
    }
}
