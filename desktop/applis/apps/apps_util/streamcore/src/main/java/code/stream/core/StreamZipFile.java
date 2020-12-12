package code.stream.core;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;

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
                String name_ = StringUtil.replaceBackSlash(e_.getName());
                if (e_.isDirectory()) {
                    byte[] bytes_ = new byte[0];
                    ContentTime content_ = new ContentTime(bytes_,e_.getTime());
                    files_.put(name_, content_);
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
                if (i > MAX) {
                    zis_.closeEntry();
                    continue;
                }
                int maxByte_ = Math.min(i,bytes_.length);
                byte[] copy_ = new byte[maxByte_];
                for (int j = 0; j < maxByte_; j++) {
                    set(bytes_, copy_, j);
                }
                ContentTime content_ = new ContentTime(copy_,e_.getTime());
                files_.put(name_, content_);
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
            for (EntryCust<String, ContentTime> n : _files.entryList()) {
                ContentTime file_ = n.getValue();
                byte[] content_ = file_.getContent();
                if (content_ != null) {
                    ZipEntry ze_ = new ZipEntry(n.getKey());
                    ze_.setTime(file_.getLastModifTime());
                    zos_.putNextEntry(ze_);
                    zos_.write(content_);
                } else {
                    ZipEntry ze_ = new ZipEntry(n.getKey()+"/");
                    ze_.setTime(file_.getLastModifTime());
                    zos_.putNextEntry(ze_);
                }
            }
            zos_.closeEntry();
            // remember close it
            zos_.close();
            return baos_.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    public static ReadFiles getZippedFiles(UniformingString _app, byte[] _bytes) {
        StringMap<ContentTime> zip_ =  zippedBinaryFiles(_bytes);
        StringMap<String> zipFiles_ = new StringMap<String>();
        if (zip_ == null) {
            return new ReadFiles(zipFiles_, OutputType.NOTHING);
        }
        for (EntryCust<String,ContentTime> e: zip_.entryList()) {
            String key_ = e.getKey();
            if (key_.endsWith("/")) {
                continue;
            }
            String dec_ = StringUtil.decode(e.getValue().getContent());
            if (dec_ == null) {
                continue;
            }
            zipFiles_.addEntry(key_, _app.apply(dec_));
        }
        return new ReadFiles(zipFiles_, OutputType.ZIP);
    }

    public static ReadBinFiles getZippedBinFiles(byte[] _bytes) {
        StringMap<ContentTime> zipFiles_ = new StringMap<ContentTime>();
        StringMap<ContentTime> zipFolders_ = new StringMap<ContentTime>();
        StringMap<ContentTime> zip_ =  zippedBinaryFiles(_bytes);
        if (zip_ == null) {
            return new ReadBinFiles(zipFiles_,zipFolders_, OutputType.NOTHING);
        }
        for (EntryCust<String,ContentTime> e: zip_.entryList()) {
            String key_ = e.getKey();
            if (key_.endsWith("/")) {
                zipFolders_.addEntry(key_.substring(0,key_.length()-1), e.getValue());
                continue;
            }
            zipFiles_.addEntry(key_, e.getValue());
        }
        return new ReadBinFiles(zipFiles_,zipFolders_, OutputType.ZIP);
    }
}
