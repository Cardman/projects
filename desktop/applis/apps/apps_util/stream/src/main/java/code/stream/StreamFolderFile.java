package code.stream;

import code.stream.core.StreamZipFile;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;

import java.io.File;

public final class StreamFolderFile {
    private StreamFolderFile() {
    }

    public static String getCurrentPath() {
        return StringUtil.replaceBackSlashDot(new File(".").getAbsolutePath());
    }

    public static boolean isAbsolute(String _path) {
        File file_ = new File(_path);
        String absPath_ = StringUtil.replaceBackSlash(file_.getAbsolutePath());
        String path_ = StringUtil.replaceBackSlash(_path);
        return StringUtil.quickEq(absPath_,path_);
    }
    public static StringMap<String> getFiles(String _archiveOrFolder) {
        return getFiles(_archiveOrFolder,new DefaultUniformingString());
    }
    public static StringMap<String> getFiles(String _archiveOrFolder, UniformingString _app) {
        StringMap<String> zipFiles_ = new StringMap<String>();
        File file_ = new File(_archiveOrFolder);
        if (file_.isDirectory()) {
            String abs_ = file_.getAbsolutePath();
            for (String f: StreamTextFile.allSortedFiles(_archiveOrFolder)) {
                if (new File(f).isDirectory()) {
                    continue;
                }
                String contentOfFile_ = StreamTextFile.contentsOfFile(f, _app);
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
                String dec_ = StringUtil.decode(e.getValue());
                if (dec_ == null) {
                    continue;
                }
                zipFiles_.addEntry(key_, _app.apply(dec_));
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
