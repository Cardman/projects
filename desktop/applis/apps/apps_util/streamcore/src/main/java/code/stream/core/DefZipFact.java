package code.stream.core;

import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

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
        while (zis_.hasNextEntry()) {
            String name_ = StringUtil.replaceBackSlash(zis_.getName());
            long time_ = zis_.getTime();
            if (zis_.isDirectory()) {
                byte[] bytes_ = new byte[0];
                ContentTime content_ = new ContentTime(bytes_, time_);
                files_.put(name_, content_);
                zis_.closeEntry();
                continue;
            }
            byte[] bytes_ = new byte[1];
            while (true) {
                int read_ = zis_.read(bytes_, 0, bytes_.length);
                if (read_ <= 0) {
                    break;
                }
            }
            ContentTime content_ = new ContentTime(zis_.getReadBytes(), time_);
            files_.put(name_, content_);
            zis_.closeEntry();
        }
        zis_.close();
        return files_;
    }

    @Override
    public byte[] zipBinFiles(StringMap<ContentTime> _files) {
        AbstractZipStreamOut zos_ = zipFactory.buildOut();
        for (EntryCust<String, ContentTime> n : _files.entryList()) {
            ContentTime file_ = n.getValue();
            byte[] content_ = file_.getContent();
            if (content_ != null) {
                if (!n.getKey().endsWith("/")) {
                    zos_.putNextEntry(n.getKey(),file_.getLastModifTime(),content_);
                } else {
                    zos_.putNextEmptyEntry(n.getKey(),file_.getLastModifTime());
                }
            } else {
                if (n.getKey().endsWith("/")) {
                    zos_.putNextEmptyEntry(n.getKey(),file_.getLastModifTime());
                } else {
                    zos_.putNextEmptyEntry(n.getKey() + "/",file_.getLastModifTime());
                }
            }
            zos_.closeEntry();
        }
        // remember close it
        byte[] bytes_ = zos_.byteArray();
        zos_.close();
        return bytes_;
    }

}
