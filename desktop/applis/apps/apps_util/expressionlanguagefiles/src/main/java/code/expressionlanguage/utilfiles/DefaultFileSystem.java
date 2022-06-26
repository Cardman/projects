package code.expressionlanguage.utilfiles;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.filenames.PathUtil;
import code.expressionlanguage.utilcompo.AbstractFileSystem;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.stream.*;
import code.stream.core.ReadBinFiles;
import code.stream.core.TechStreams;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;

public final class DefaultFileSystem implements AbstractFileSystem {

    private final UniformingString uniformingString;
    private final AbstractNameValidating nameValidating;
    private final AbstractFileCoreStream fileCoreStream;
    private final TechStreams streams;
    private String base = "";

    public DefaultFileSystem(UniformingString _uniformingString, AbstractNameValidating _nameValidating, AbstractFileCoreStream _fileCoreStream, TechStreams _streams) {
        uniformingString = _uniformingString;
        nameValidating = _nameValidating;
        fileCoreStream = _fileCoreStream;
        streams = _streams;
    }

    @Override
    public void build(String _base, ReadBinFiles _readBin) {
        base = _base;
        mkdirs(_base);
    }

    @Override
    public void changeDir(String _file, RunnableContextEl _rCont) {
        String abs_ = absolutePath(_file, _rCont);
        String normal_ = StringUtil.replaceBackSlashDot(abs_);
        if (!normal_.startsWith(base)) {
            return;
        }
        if (!isDirectory(normal_,_rCont)) {
            return;
        }
        _rCont.setCurrentDir(normal_);
    }

    @Override
    public String currentDir(RunnableContextEl _rCont) {
        return _rCont.getCurrentDir();
    }

    @Override
    public String contentsOfFile(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        return StreamTextFile.contentsOfFile(file_, uniformingString,fileCoreStream,streams);
    }

    @Override
    public boolean saveTextFile(String _file, String _content, RunnableContextEl _rCont) {
        if (koName(_file, _rCont)) {
            return false;
        }
        String file_ = prefix(_file, _rCont);
        return StreamTextFile.saveTextFile(file_,_content,streams);
    }

    @Override
    public byte[] loadFile(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        return StreamBinaryFile.loadFile(file_, streams);
    }

    @Override
    public boolean writeFile(String _file, byte[] _content, RunnableContextEl _rCont) {
        if (koName(_file, _rCont)) {
            return false;
        }
        String file_ = prefix(_file, _rCont);
        return StreamBinaryFile.writeFile(file_,_content,streams);
    }

    @Override
    public boolean delete(String _file, RunnableContextEl _rCont) {
        if (koName(_file, _rCont)) {
            return false;
        }
        String file_ = prefix(_file, _rCont);
        return fileCoreStream.newFile(file_).delete();
    }

    @Override
    public boolean rename(String _origin, String _dest, RunnableContextEl _rCont) {
        if (koName(_origin, _rCont) || koName(_dest, _rCont)) {
            return false;
        }
        String origin_ = prefix(_origin, _rCont);
        String dest_ = prefix(_dest, _rCont);
        return fileCoreStream.newFile(origin_).renameTo(fileCoreStream.newFile(dest_));
    }

    @Override
    public boolean logToFile(String _file, String _content, RunnableContextEl _rCont) {
        if (koName(_file, _rCont)) {
            return false;
        }
        String file_ = prefix(_file, _rCont);
        return StreamTextFile.logToFile(file_,_content,streams);
    }

    @Override
    public String absolutePath(String _file, RunnableContextEl _rCont) {
        if (isAbsolute(_file, _rCont)) {
            return StringUtil.replaceBackSlash(_file);
        }
        String abs_ = _rCont.getCurrentDir();
        return PathUtil.transform(abs_,_file);
    }

    @Override
    public long length(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        return fileCoreStream.newFile(file_).length();
    }

    @Override
    public String getName(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        return fileCoreStream.newFile(file_).getName();
    }

    @Override
    public String getParentPath(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        String parent_ = fileCoreStream.newFile(file_).getParent();
        if (parent_ == null) {
            return "";
        }
        return StringUtil.replaceBackSlash(parent_);
    }

    @Override
    public boolean isDirectory(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        return isDirectory(file_);
    }

    private boolean isDirectory(String _file) {
        AbstractFile info_ = fileCoreStream.newFile(_file);
        return info_.exists()&&info_.isDirectory();
    }

    @Override
    public boolean isFile(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        AbstractFile info_ = fileCoreStream.newFile(file_);
        return info_.exists()&&!info_.isDirectory();
    }

    @Override
    public StringList getRoots(RunnableContextEl _rCont) {
        StringList roots_ = StreamFolderFile.listRootsAbPath(fileCoreStream);
        roots_.sort();
        return roots_;
    }

    @Override
    public boolean isAbsolute(String _file, RunnableContextEl _rCont) {
        return isAbsolute(_file);
    }

    private boolean isAbsolute(String _file) {
        return StreamFolderFile.isAbsolute(_file,fileCoreStream);
    }

    @Override
    public boolean mkdirs(String _file, RunnableContextEl _rCont) {
        String file_ = _file;
        if (endsSep(file_)) {
            file_ = file_.substring(0,file_.length()-1);
        }
        if (koName(file_, _rCont)) {
            return false;
        }
        String pref_ = prefix(file_, _rCont);
        return simpleMkdirs(pref_);
    }

    @Override
    public long lastModified(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        AbstractFile info_ = fileCoreStream.newFile(file_);
        return info_.lastModified();
    }

    @Override
    public StringList getFiles(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        AbstractFile info_ = fileCoreStream.newFile(file_);
        FileListInfo files_ = PathsUtil.abs(info_,fileCoreStream);
        if (files_.isNul()) {
            return null;
        }
        StringList filesList_ = new StringList();
        for (AbstractFile f: files_.getNames()) {
            if (!f.exists()) {
                continue;
            }
            if (f.isDirectory()) {
                continue;
            }
            filesList_.add(StringUtil.replaceBackSlash(f.getAbsolutePath()));
        }
        filesList_.sort();
        return filesList_;
    }

    @Override
    public StringList getFolders(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        AbstractFile info_ = fileCoreStream.newFile(file_);
        FileListInfo files_ = PathsUtil.abs(info_,fileCoreStream);
        if (files_.isNul()) {
            return null;
        }
        StringList filesList_ = new StringList();
        for (AbstractFile f: files_.getNames()) {
            if (!f.exists()) {
                continue;
            }
            if (!f.isDirectory()) {
                continue;
            }
            filesList_.add(StringUtil.replaceBackSlash(f.getAbsolutePath()));
        }
        filesList_.sort();
        return filesList_;
    }
    private void mkdirs(String _file) {
        String file_ = _file;
        if (!_file.startsWith(base)) {
            return;
        }
        if (endsSep(file_)) {
            file_ = file_.substring(0,file_.length()-1);
        }
        simpleMkdirs(file_);
    }

    private static boolean endsSep(String _file) {
        return _file.endsWith("/") || _file.endsWith("\\");
    }

    private boolean simpleMkdirs(String _modified) {
        return StreamFolderFile.makeParent(_modified+"/",fileCoreStream);
    }
    private boolean koName(String _file, RunnableContextEl _rCont) {
        String normal_ = StringUtil.replaceBackSlash(_file);
        String ext_ = normal_;
        if (isAbsolute(normal_, _rCont)) {
            if (!normal_.startsWith(base)) {
                return true;
            }
            ext_ = normal_.substring(base.length());
        }
        return !nameValidating.okPath(ext_, '/', '\\');
    }

    private String prefix(String _file, RunnableContextEl _rCont) {
        String chg_ = StringUtil.replaceBackSlash(_file);
        String file_ = chg_;
        if (!isAbsolute(chg_, _rCont)) {
            file_ = _rCont.getCurrentDir()+chg_;
        }
        return file_;
    }

}
