package code.stream.core;

import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;

public final class StreamZipFile {

    private StreamZipFile() {
    }

    public static ReadFiles getZippedFiles(UniformingString _app, byte[] _bytes, AbstractZipFact _zipFact) {
        StringMap<ContentTime> zip_ = _zipFact.zippedBinaryFiles(_bytes);
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
            if (dec_ != null) {
                zipFiles_.addEntry(key_, _app.apply(dec_));
            }
        }
        return new ReadFiles(zipFiles_, OutputType.ZIP);
    }

    public static ReadBinFiles getZippedBinFiles(byte[] _bytes, AbstractZipFact _zipFact) {
        StringMap<ContentTime> zipFiles_ = new StringMap<ContentTime>();
        StringMap<ContentTime> zipFolders_ = new StringMap<ContentTime>();
        StringMap<ContentTime> zip_ = _zipFact.zippedBinaryFiles(_bytes);
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
