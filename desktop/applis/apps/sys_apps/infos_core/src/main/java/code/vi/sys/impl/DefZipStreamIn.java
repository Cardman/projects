package code.vi.sys.impl;

import code.stream.core.AbstractZipStreamIn;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class DefZipStreamIn implements AbstractZipStreamIn {
    private final ZipInputStream zipIn;
    private String name = "";
    private long time;
    private long size;
    private boolean directory;
    public DefZipStreamIn(byte[] _bytes) {
        ByteArrayInputStream bais_ = new ByteArrayInputStream(_bytes);
        zipIn = new ZipInputStream(bais_);
    }
    @Override
    public boolean hasNextEntry() {
        return has(next());
    }

    private boolean has(ZipEntry _current) {
        try {
            name = name(_current);
            time = time(_current);
            size = size(_current);
            directory = directory(_current);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private ZipEntry next() {
        try {
            return zipIn.getNextEntry();
        } catch (Exception e) {
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
    public String getName() {
        return name;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public boolean isDirectory() {
        return directory;
    }

    @Override
    public int read(byte[] _array, int _off, int _len) {
        try {
            return zipIn.read(_array,_off,_len);
        } catch (IOException e) {
            return -2;
        }
    }

    private static boolean directory(ZipEntry _current) {
        return _current.isDirectory();
    }

    private static long size(ZipEntry _current) {
        return _current.getSize();
    }

    private static long time(ZipEntry _current) {
        return _current.getTime();
    }

    private static String name(ZipEntry _current) {
        return _current.getName();
    }
}
