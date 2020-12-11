package code.stream.core;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import code.util.StringMap;

public final class StreamZipFile {

    private static final int MAX = Integer.MAX_VALUE/2;
    private StreamZipFile() {
    }

    public static StringMap<ContentTime> zippedBinaryFiles(byte[] _bytes) {
        if (_bytes == null) {
            return null;
        }
        try {
            StringMap<ContentTime> files_ = new StringMap<ContentTime>();
            ByteArrayInputStream bais_ = new ByteArrayInputStream(_bytes);
            ZipInputStream zis_ = new ZipInputStream(bais_);
            while (true) {
                ZipEntry e_ = zis_.getNextEntry();
                if (e_ == null) {
                    break;
                }
                if (e_.isDirectory()) {
                    byte[] bytes_ = new byte[0];
                    ContentTime content_ = new ContentTime(bytes_,e_.getTime());
                    files_.put(e_.getName(), content_);
                    zis_.closeEntry();
                    continue;
                }
                long size_ = e_.getSize();
                if (size_ < 0) {
                    size_ = MAX;
                } else {
                    size_ = Math.min(MAX,size_);
                }
                byte[] bytes_ = new byte[Math.max((int) size_,0)];
                int i = 0;
                while (i < bytes_.length) {
                    int read_ = zis_.read(bytes_, i, bytes_.length - i);
                    if (read_ <= 0) {
                        break;
                    }
                    i += read_;
                }
                byte[] copy_ = new byte[i];
                for (int j = 0; j < i; j++) {
                    set(bytes_, copy_, j);
                }
                ContentTime content_ = new ContentTime(copy_,e_.getTime());
                files_.put(e_.getName(), content_);
                zis_.closeEntry();
            }
            zis_.close();
            return files_;
        } catch (IOException e) {
            return null;
        }

    }

    private static void set(byte[] _bytes, byte[] _copy, int _j) {
        _copy[_j] = _bytes[_j];
    }

    public static byte[] zipBinFiles(StringMap<ContentTime> _files) {
        try {
            ByteArrayOutputStream baos_ = new ByteArrayOutputStream();
            ZipOutputStream zos_ = new ZipOutputStream(baos_);
            for (String n : _files.getKeys()) {
                ContentTime file_ = _files.getVal(n);
                ZipEntry ze_ = new ZipEntry(n);
                ze_.setTime(file_.getLastModifTime());
                zos_.putNextEntry(ze_);
                zos_.write(file_.getContent());
            }
            zos_.closeEntry();
            // remember close it
            zos_.close();
            return baos_.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }
}
