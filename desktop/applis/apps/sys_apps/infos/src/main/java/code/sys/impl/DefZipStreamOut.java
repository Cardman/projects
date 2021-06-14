package code.sys.impl;

import code.stream.core.AbstractZipStreamOut;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class DefZipStreamOut implements AbstractZipStreamOut {
    private final ByteArrayOutputStream baos;
    private final ZipOutputStream zipOut;
    public DefZipStreamOut() {
        baos = new ByteArrayOutputStream();
        zipOut = new ZipOutputStream(baos);
    }
    @Override
    public ZipEntry buildEntry(String _name, long _time) {
        ZipEntry e_ = new ZipEntry(_name);
        e_.setTime(_time);
        return e_;
    }

    @Override
    public boolean write(byte[] _array) {
        try {
            zipOut.write(_array);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean closeEntry() {
        try {
            zipOut.closeEntry();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public void close() {
        StreamCoreUtil.close(zipOut);
    }

    @Override
    public boolean putNextEntry(ZipEntry _entry) {
        try {
            zipOut.putNextEntry(_entry);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public byte[] byteArray() {
        return baos.toByteArray();
    }
}
