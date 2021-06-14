package code.stream.core;

import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

import java.util.zip.ZipEntry;

//very ugly implementation
public final class DefZipFact implements AbstractZipFact {

    private final AbstractZipFactory zipFactory;

    public DefZipFact(AbstractZipFactory _zipFactory) {
        this.zipFactory = _zipFactory;
    }

    @Override
    public StringMap<ContentTime> zippedBinaryFiles(byte[] _bytes) {
        if (_bytes == null) {
            return null;
        }
        StringMap<ContentTime> files_ = new StringMap<ContentTime>();
        AbstractZipStreamIn zis_ = zipFactory.buildIn(_bytes);
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
            byte[] bytes_ = new byte[Math.max((int) size_,0)];
            int i = 0;
            while (i < bytes_.length) {
                int read_ = zis_.read(bytes_, i, bytes_.length - i);
                if (read_ <= 0) {
                    break;
                }
                i += read_;
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
    }

    private static void set(byte[] _bytes, byte[] _copy, int _j) {
        _copy[_j] = _bytes[_j];
    }
    @Override
    public byte[] zipBinFiles(StringMap<ContentTime> _files) {
        AbstractZipStreamOut zos_ = zipFactory.buildOut();
        for (EntryCust<String, ContentTime> n : _files.entryList()) {
            ContentTime file_ = n.getValue();
            byte[] content_ = file_.getContent();
            if (content_ != null) {
                ZipEntry ze_ = zos_.buildEntry(n.getKey(),file_.getLastModifTime());
                zos_.putNextEntry(ze_);
                if (!n.getKey().endsWith("/")) {
                    zos_.write(content_);
                }
            } else {
                ZipEntry ze_;
                if (n.getKey().endsWith("/")) {
                    ze_ = zos_.buildEntry(n.getKey(),file_.getLastModifTime());
                } else {
                    ze_ = zos_.buildEntry(n.getKey() + "/",file_.getLastModifTime());
                }
                zos_.putNextEntry(ze_);
            }
            zos_.closeEntry();
        }
        // remember close it
        zos_.close();
        return zos_.byteArray();
    }

}
