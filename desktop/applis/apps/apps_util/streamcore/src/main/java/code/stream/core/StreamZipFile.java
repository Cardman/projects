package code.stream.core;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import code.util.StringMap;

public final class StreamZipFile {

    private StreamZipFile() {
    }

    public static StringMap<byte[]> zippedBinaryFiles(byte[] _bytes) {
        if (_bytes == null) {
            return null;
        }
        try {
            StringMap<byte[]> files_ = new StringMap<byte[]>();
            ByteArrayInputStream bais_ = new ByteArrayInputStream(_bytes);
            ZipInputStream zis_ = new ZipInputStream(bais_);
            while (true) {
                ZipEntry e_ = zis_.getNextEntry();
                if (e_ == null) {
                    break;
                }
                if (e_.isDirectory()) {
                    byte[] bytes_ = new byte[0];
                    files_.put(e_.getName(), bytes_);
                    zis_.closeEntry();
                    continue;
                }
                byte[] bytes_ = new byte[(int) e_.getSize()];
                int i = 0;
                while (i < bytes_.length) {
                    i += zis_.read(bytes_, i, bytes_.length - i);
                }
                files_.put(e_.getName(), bytes_);
                zis_.closeEntry();
            }
            zis_.close();
            return files_;
        } catch (IOException e) {
            return null;
        }

    }

    public static byte[] zipBinFiles(StringMap<byte[]> _files) {
        try {
            ByteArrayOutputStream baos_ = new ByteArrayOutputStream();
            ZipOutputStream zos_ = new ZipOutputStream(baos_);
            for (String n : _files.getKeys()) {
                byte[] file_ = _files.getVal(n);
                ZipEntry ze_ = new ZipEntry(n);
                zos_.putNextEntry(ze_);
                zos_.write(file_);
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
