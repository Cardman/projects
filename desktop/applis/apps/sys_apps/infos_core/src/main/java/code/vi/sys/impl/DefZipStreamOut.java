package code.vi.sys.impl;

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
    public boolean write(byte[] _array) {
        try {
            zipOut.write(_array);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean closeEntry() {
        try {
            zipOut.closeEntry();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void close() {
        StreamCoreUtil.close(zipOut);
    }

    @Override
    public boolean putNextEntry(String _key, long _lastModifTime) {
        try {
            ZipEntry e_ = new ZipEntry(_key);
            e_.setTime(_lastModifTime);
            zipOut.putNextEntry(e_);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public byte[] byteArray() {
        return baos.toByteArray();
    }
}
