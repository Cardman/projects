package code.vi.prot.impl;

import code.stream.core.AbstractZipStreamOut;

import java.io.ByteArrayOutputStream;
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
    public boolean putNextEntry(String _key, long _lastModifTime, byte[] _content) {
        try {
            ZipEntry e_ = entry(_key, _lastModifTime);
            e_.setSize(_content.length);
            zipOut.putNextEntry(e_);
            zipOut.write(_content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean putNextEmptyEntry(String _key, long _lastModifTime) {
        try {
            ZipEntry e_ = entry(_key, _lastModifTime);
            zipOut.putNextEntry(e_);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private ZipEntry entry(String _key, long _lastModifTime) {
        ZipEntry e_ = new ZipEntry(_key);
        e_.setTime(_lastModifTime);
        return e_;
    }

    @Override
    public byte[] byteArray() {
        return baos.toByteArray();
    }
}
