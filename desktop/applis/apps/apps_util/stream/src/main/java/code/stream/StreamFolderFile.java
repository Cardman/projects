package code.stream;

import code.gui.initialize.AbstractProgramInfos;
import code.stream.core.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;

public final class StreamFolderFile {
    private StreamFolderFile() {
    }

    public static StringList getFilesNames(AbstractFileCoreStream _list,String[] _args) {
        StringList files_ = new StringList();
        for (String s: _args) {
            files_.add(StringUtil.replaceBackSlash(_list.newFile(s).getAbsolutePath()));
        }
        return files_;
    }

    public static String getCurrentPath(AbstractFileCoreStream _list) {
        return StringUtil.replaceBackSlashDot(_list.newFile(".").getAbsolutePath());
    }

    public static StringMap<String> getFiles(String _archiveOrFolder,AbstractFileCoreStream _fact,TechStreams _zip) {
        return getFiles(_archiveOrFolder,new DefaultUniformingString(),_fact,_zip).getZipFiles();
    }

    public static boolean makeParent(String _pathname,AbstractFileCoreStream _list) {
        StringList parts_ = StringUtil.splitChars(_pathname, '/', '\\');
        int nbElements_ = parts_.size() - 1;
        if (nbElements_ <= 0) {
            return false;
        }
        return PathsUtil.mkdirs(StringUtil.join(parts_.left(nbElements_),'/'),_list);
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
                String contentOfFile_ = StreamTextFile.contentsOfFile(f, _app, _zip);
                if (contentOfFile_ != null) {
                    zipFiles_.addEntry(f.substring(abs_.length() + 1), contentOfFile_);
                }
            }
            return new ReadFiles(zipFiles_, OutputType.FOLDER);
        }
        BytesInfo bytes_ = StreamBinaryFile.loadFile(_archiveOrFolder, _zip);
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
                tryExtract(_zip, zipFiles_, abs_, f, info_);
            }
            return new ReadBinFiles(zipFiles_,zipFolders_, OutputType.FOLDER);
        }
        BytesInfo bytes_ = StreamBinaryFile.loadFile(_archiveOrFolder, _zip);
        return StreamZipFile.getZippedBinFiles(bytes_, _zip.getZipFact());
    }

    static void tryExtract(TechStreams _zip, StringMap<ContentTime> _zipFiles, String _abs, String _f, AbstractFile _info) {
        BytesInfo contentOfFile_ = StreamBinaryFile.loadFile(_f, _zip);
        if (contentOfFile_.isNul()) {
            return;
        }
        _zipFiles.addEntry(_f.substring(_abs.length()+1),new ContentTime(contentOfFile_.getBytes(), _info.lastModified()));
    }

    public static String getTempFolder(AbstractProgramInfos _tmpUserFolderSl, String _folder) {
        makeParent(StringUtil.concat(_tmpUserFolderSl.getTmpUserFolder(),_folder)+"/", _tmpUserFolderSl.getFileCoreStream());
        return StringUtil.concat(_tmpUserFolderSl.getTmpUserFolder(),_folder);
    }
}
