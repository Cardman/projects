package code.vi.prot.impl;

import code.stream.core.AbstractZipStreamIn;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class DefZipStreamIn implements AbstractZipStreamIn {
    private final ZipInputStream zipIn;
    private String name = "";
    private long time;
    private long size;
    private boolean directory;
    private final ByteArrayOutputStream out;
    public DefZipStreamIn(byte[] _bytes) {
        ByteArrayInputStream bais_ = new ByteArrayInputStream(_bytes);
        zipIn = new ZipInputStream(bais_);
        out = new ByteArrayOutputStream();
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
            out.reset();
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
        } catch (Exception e) {
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
            int read_ = zipIn.read(_array, _off, _len);
            out.write(_array,0,Math.max(0,read_));
            System.out.println(out.size());
            return read_;
        } catch (Exception e) {
            return -2;
        }
    }

    @Override
    public byte[] getReadBytes() {
        return out.toByteArray();
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
