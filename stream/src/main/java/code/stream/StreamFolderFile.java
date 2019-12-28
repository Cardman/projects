package code.stream;

import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

import java.io.File;

public final class StreamFolderFile {
    private StreamFolderFile() {
    }

    public static boolean isAbsolute(String _path) {
        File file_ = new File(_path);
        String absPath_ = StringList.replaceBackSlash(file_.getAbsolutePath());
        String path_ = StringList.replaceBackSlash(_path);
        return StringList.quickEq(absPath_,path_);
    }
    public static StringMap<String> getFiles(String _archiveOrFolder) {
        StringMap<String> zipFiles_ = new StringMap<String>();
        File file_ = new File(_archiveOrFolder);
        if (file_.isDirectory()) {
            String abs_ = file_.getAbsolutePath();
            for (String f: StreamTextFile.allSortedFiles(_archiveOrFolder)) {
                if (new File(f).isDirectory()) {
                    continue;
                }
                String contentOfFile_ = StreamTextFile.contentsOfFile(f);
                if (contentOfFile_ == null) {
                    continue;
                }
                zipFiles_.addEntry(f.substring(abs_.length()+1),contentOfFile_);
            }
        } else {
            StringMap<byte[]> zip_ =  StreamZipFile.zippedBinaryFiles(StreamBinaryFile.loadFile(_archiveOrFolder));
            if (zip_ == null) {
                return zipFiles_;
            }
            for (EntryCust<String,byte[]> e: zip_.entryList()) {
                String key_ = e.getKey();
                if (key_.endsWith("/")) {
                    continue;
                }
                String dec_ = StringList.decode(e.getValue());
                if (dec_ == null) {
                    continue;
                }
                zipFiles_.addEntry(key_,StringList.removeChars(dec_,'\r'));
            }
        }
        return zipFiles_;
    }
    public static StringMap<byte[]> getBinFiles(String _archiveOrFolder) {
        StringMap<byte[]> zipFiles_ = new StringMap<byte[]>();
        File file_ = new File(_archiveOrFolder);
        if (file_.isDirectory()) {
            String abs_ = file_.getAbsolutePath();
            for (String f: StreamTextFile.allSortedFiles(_archiveOrFolder)) {
                if (new File(f).isDirectory()) {
                    continue;
                }
                byte[] bytes_ = StreamBinaryFile.loadFile(f);
                if (bytes_ == null) {
                    continue;
                }
                zipFiles_.addEntry(f.substring(abs_.length()+1), bytes_);
            }
        } else {
            StringMap<byte[]> zip_ =  StreamZipFile.zippedBinaryFiles(StreamBinaryFile.loadFile(_archiveOrFolder));
            if (zip_ == null) {
                return zipFiles_;
            }
            for (EntryCust<String,byte[]> e: zip_.entryList()) {
                String key_ = e.getKey();
                if (key_.endsWith("/")) {
                    continue;
                }
                zipFiles_.addEntry(key_,e.getValue());
            }
        }
        return zipFiles_;
    }
}
