package code.stream;

import code.stream.core.*;
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
        for (File f: File.listRoots()) {
            l_.add(StringUtil.replaceBackSlashDot(f.getAbsolutePath()));
        }
        return l_;
    }

    public static boolean mkdirs(String _folder) {
        return new File(_folder).mkdirs();
    }
    public static ReadFiles getFiles(String _archiveOrFolder, UniformingString _app) {
        File file_ = new File(_archiveOrFolder);
        if (file_.isDirectory()) {
            StringMap<String> zipFiles_ = new StringMap<String>();
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
            return new ReadFiles(zipFiles_, OutputType.FOLDER);
        }
        byte[] bytes_ = StreamBinaryFile.loadFile(_archiveOrFolder);
        return StreamZipFile.getZippedFiles(_app, bytes_);
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
        return StreamZipFile.getZippedBinFiles(bytes_);
    }

}
