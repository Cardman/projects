package code.stream;

import code.stream.core.*;
import code.util.EntryCust;
import code.util.StringList;
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
        return getFiles(_archiveOrFolder,new DefaultUniformingString()).getZipFiles();
    }

    public static String getRelativeRootPath(String _absolute) {
        for (String r: listRootsAbPath()) {
            if (_absolute.startsWith(r)) {
                return _absolute.substring(r.length());
            }
        }
        return _absolute;
    }
    public static StringList listRootsAbPath() {
        StringList l_ = new StringList();
        for (File f: listRoots()) {
            l_.add(StringUtil.replaceBackSlashDot(f.getAbsolutePath()));
        }
        return l_;
    }
    public static File[] listRoots() {
        return File.listRoots();
    }
    public static boolean mkdirs(String _folder) {
        return new File(_folder).mkdirs();
    }
    public static ReadFiles getFiles(String _archiveOrFolder, UniformingString _app) {
        File file_ = new File(_archiveOrFolder);
        if (file_.isDirectory()) {
            StringMap<String> zipFiles_ = new StringMap<String>();
            StringList folders_ = new StringList();
            String abs_ = file_.getAbsolutePath();
            for (String f: StreamTextFile.allSortedFiles(_archiveOrFolder)) {
                if (new File(f).isDirectory()) {
                    if (abs_.length()+1 <= f.length()) {
                        folders_.add(f.substring(abs_.length()+1));
                    }
                    continue;
                }
                String contentOfFile_ = StreamTextFile.contentsOfFile(f, _app);
                if (contentOfFile_ == null) {
                    continue;
                }
                zipFiles_.addEntry(f.substring(abs_.length()+1),contentOfFile_);
            }
            return new ReadFiles(zipFiles_,folders_,OutputType.FOLDER);
        }
        byte[] bytes_ = StreamBinaryFile.loadFile(_archiveOrFolder);
        return getZippedFiles(_app, bytes_);
    }

    public static ReadFiles getZippedFiles(UniformingString _app, byte[] _bytes) {
        StringMap<ContentTime> zip_ =  StreamZipFile.zippedBinaryFiles(_bytes);
        StringMap<String> zipFiles_ = new StringMap<String>();
        StringList folders_ = new StringList();
        if (zip_ == null) {
            return new ReadFiles(zipFiles_,folders_,OutputType.NOTHING);
        }
        for (EntryCust<String,ContentTime> e: zip_.entryList()) {
            String key_ = e.getKey();
            if (key_.endsWith("/")) {
                folders_.add(key_.substring(0,key_.length()-1));
                continue;
            }
            String dec_ = StringUtil.decode(e.getValue().getContent());
            if (dec_ == null) {
                continue;
            }
            zipFiles_.addEntry(key_, _app.apply(dec_));
        }
        return new ReadFiles(zipFiles_,folders_,OutputType.ZIP);
    }

    public static ReadBinFiles getBinFiles(String _archiveOrFolder) {
        File file_ = new File(_archiveOrFolder);
        if (file_.isDirectory()) {
            StringMap<ContentTime> zipFiles_ = new StringMap<ContentTime>();
            StringMap<ContentTime> zipFolders_ = new StringMap<ContentTime>();
            String abs_ = file_.getAbsolutePath();
            for (String f: StreamTextFile.allSortedFiles(_archiveOrFolder)) {
                File info_ = new File(f);
                if (info_.isDirectory()) {
                    if (abs_.length()+1 <= f.length()) {
                        zipFolders_.addEntry(f.substring(abs_.length()+1),new ContentTime(null,info_.lastModified()));
                    }
                    continue;
                }
                byte[] contentOfFile_ = StreamBinaryFile.loadFile(f);
                if (contentOfFile_ == null) {
                    continue;
                }
                zipFiles_.addEntry(f.substring(abs_.length()+1),new ContentTime(contentOfFile_,info_.lastModified()));
            }
            return new ReadBinFiles(zipFiles_,zipFolders_, OutputType.FOLDER);
        }
        byte[] bytes_ = StreamBinaryFile.loadFile(_archiveOrFolder);
        return getZippedBinFiles(bytes_);
    }

    public static ReadBinFiles getZippedBinFiles(byte[] _bytes) {
        StringMap<ContentTime> zipFiles_ = new StringMap<ContentTime>();
        StringMap<ContentTime> zipFolders_ = new StringMap<ContentTime>();
        StringMap<ContentTime> zip_ =  StreamZipFile.zippedBinaryFiles(_bytes);
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
