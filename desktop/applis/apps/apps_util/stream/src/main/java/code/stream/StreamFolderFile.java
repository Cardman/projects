package code.stream;

import code.stream.core.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;

public final class StreamFolderFile {
    private StreamFolderFile() {
    }

    public static String getCurrentPath(AbstractFileCoreStream _list) {
        return StringUtil.replaceBackSlashDot(_list.newFile(".").getAbsolutePath());
    }

    public static boolean isAbsolute(String _path,AbstractFileCoreStream _list) {
        AbstractFile file_ = _list.newFile(_path);
        String absPath_ = StringUtil.replaceBackSlashDot(file_.getAbsolutePath());
        String path_ = StringUtil.replaceBackSlashDot(_path);
        return StringUtil.quickEq(absPath_,path_);
    }
    public static StringMap<String> getFiles(String _archiveOrFolder,AbstractFileCoreStream _fact,TechStreams _zip) {
        return getFiles(_archiveOrFolder,new DefaultUniformingString(),_fact,_zip).getZipFiles();
    }

    public static String getRelativeRootPath(String _absolute,AbstractFileCoreStream _list) {
        for (String r: listRootsAbPath(_list)) {
            if (_absolute.startsWith(r)) {
                return _absolute.substring(r.length());
            }
        }
        return _absolute;
    }

    public static StringList listRootsAbPath(AbstractFileCoreStream _list) {
        AbstractListRoot roots_ = _list.newFileList();
        StringList l_ = new StringList();
        int length_ = roots_.length();
        for (int i = 0; i< length_; i++) {
            l_.add(StringUtil.replaceBackSlashDot(roots_.path(i)));
        }
        return l_;
    }

    public static boolean makeParent(String _pathname,AbstractFileCoreStream _list) {
        StringList parts_ = StringUtil.splitChars(_pathname, '/', '\\');
        int nbElements_ = parts_.size() - 1;
        if (nbElements_ <= 0) {
            return false;
        }
        return mkdirs(StringUtil.join(parts_.left(nbElements_),'/'),_list);
    }

    public static boolean mkdirs(String _folder,AbstractFileCoreStream _list) {
        return _list.newFile(_folder).mkdirs();
    }
    public static ReadFiles getFiles(String _archiveOrFolder, UniformingString _app,AbstractFileCoreStream _fact,TechStreams _zip) {
        AbstractFile file_ = _fact.newFile(_archiveOrFolder);
        if (file_.isDirectory()) {
            StringMap<String> zipFiles_ = new StringMap<String>();
            String abs_ = file_.getAbsolutePath();
            for (String f: StreamTextFile.allSortedFiles(_archiveOrFolder,_fact)) {
                if (_fact.newFile(f).isDirectory()) {
                    continue;
                }
                String contentOfFile_ = StreamTextFile.contentsOfFile(f, _app,_fact,_zip);
                if (contentOfFile_ == null) {
                    continue;
                }
                zipFiles_.addEntry(f.substring(abs_.length()+1),contentOfFile_);
            }
            return new ReadFiles(zipFiles_, OutputType.FOLDER);
        }
        byte[] bytes_ = StreamBinaryFile.loadFile(_archiveOrFolder,_fact,_zip);
        return StreamZipFile.getZippedFiles(_app, bytes_, _zip.getZipFact());
    }

    public static ReadBinFiles getBinFiles(String _archiveOrFolder,AbstractFileCoreStream _fact, TechStreams _zip) {
        AbstractFile file_ = _fact.newFile(_archiveOrFolder);
        if (file_.isDirectory()) {
            StringMap<ContentTime> zipFiles_ = new StringMap<ContentTime>();
            StringMap<ContentTime> zipFolders_ = new StringMap<ContentTime>();
            String abs_ = file_.getAbsolutePath();
            for (String f: StreamTextFile.allSortedFiles(_archiveOrFolder,_fact)) {
                AbstractFile info_ = _fact.newFile(f);
                if (info_.isDirectory()) {
                    if (abs_.length()+1 <= f.length()) {
                        zipFolders_.addEntry(f.substring(abs_.length()+1),new ContentTime(null,info_.lastModified()));
                    }
                    continue;
                }
                byte[] contentOfFile_ = StreamBinaryFile.loadFile(f,_fact,_zip);
                if (contentOfFile_ == null) {
                    continue;
                }
                zipFiles_.addEntry(f.substring(abs_.length()+1),new ContentTime(contentOfFile_,info_.lastModified()));
            }
            return new ReadBinFiles(zipFiles_,zipFolders_, OutputType.FOLDER);
        }
        byte[] bytes_ = StreamBinaryFile.loadFile(_archiveOrFolder,_fact,_zip);
        return StreamZipFile.getZippedBinFiles(bytes_, _zip.getZipFact());
    }

}
