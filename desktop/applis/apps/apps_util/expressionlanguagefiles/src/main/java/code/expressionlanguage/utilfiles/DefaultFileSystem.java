package code.expressionlanguage.utilfiles;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.filenames.PathUtil;
import code.expressionlanguage.utilcompo.AbstractFileSystem;
import code.expressionlanguage.utilcompo.ExecutingOptions;
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
    public void build(ExecutingOptions _opt, ReadBinFiles _readBin) {
        String bf_ = _opt.getBaseFiles();
        base = bf_;
        simpleMkdirs(ExecutingOptions.adjustPath(bf_));
    }

    @Override
    public String changeDir(String _file, String _rCont) {
        String abs_ = absolutePath(_file, _rCont);
        String normal_ = StringUtil.replaceBackSlashDot(abs_);
        if (!normal_.startsWith(base)) {
            return _rCont;
        }
        if (!isDirectory(normal_,_rCont)) {
            return _rCont;
        }
        return normal_;
    }

    @Override
    public String contentsOfFile(String _file, String _rCont) {
        String file_ = prefix(_file, _rCont);
        return StreamTextFile.contentsOfFile(file_, uniformingString, streams);
    }

    @Override
    public boolean saveTextFile(String _file, String _content, String _rCont) {
        if (koName(_file)) {
            return false;
        }
        String file_ = prefix(_file, _rCont);
        return StreamTextFile.saveTextFile(file_,_content,streams);
    }

    @Override
    public BytesInfo loadFile(String _file, String _rCont) {
        String file_ = prefix(_file, _rCont);
        return StreamBinaryFile.loadFile(file_, streams);
    }

    @Override
    public boolean writeFile(String _file, byte[] _content, String _rCont) {
        if (koName(_file)) {
            return false;
        }
        String file_ = prefix(_file, _rCont);
        return StreamBinaryFile.writeFile(file_,_content,streams);
    }

    @Override
    public boolean delete(String _file, String _rCont) {
        if (koName(_file)) {
            return false;
        }
        String file_ = prefix(_file, _rCont);
        return fileCoreStream.newFile(file_).delete();
    }

    @Override
    public boolean rename(String _origin, String _dest, String _rCont) {
        if (koName(_origin) || koName(_dest)) {
            return false;
        }
        String origin_ = prefix(_origin, _rCont);
        String dest_ = prefix(_dest, _rCont);
        return fileCoreStream.newFile(origin_).renameTo(fileCoreStream.newFile(dest_));
    }

    @Override
    public boolean logToFile(String _file, String _content, String _rCont) {
        if (koName(_file)) {
            return false;
        }
        String file_ = prefix(_file, _rCont);
        return StreamTextFile.logToFile(file_,_content,streams);
    }

    @Override
    public String absolutePath(String _file, String _rCont) {
        if (isAbsoluteFct(_file)) {
            return StringUtil.replaceBackSlash(_file);
        }
        return PathUtil.transform(_rCont,_file);
    }

    @Override
    public long length(String _file, String _rCont) {
        String file_ = prefix(_file, _rCont);
        return fileCoreStream.newFile(file_).length();
    }

    @Override
    public String getName(String _file, String _rCont) {
        String file_ = prefix(_file, _rCont);
        return fileCoreStream.newFile(file_).getName();
    }

    @Override
    public String getParentPath(String _file, String _rCont) {
        String file_ = prefix(_file, _rCont);
        String parent_ = fileCoreStream.newFile(file_).getParent();
        return StringUtil.replaceBackSlash(parent_);
    }

    @Override
    public boolean isDirectory(String _file, String _rCont) {
        String file_ = prefix(_file, _rCont);
        return isDirectory(file_);
    }

    private boolean isDirectory(String _file) {
        AbstractFile info_ = fileCoreStream.newFile(_file);
        return dir(info_);
    }

    public static boolean dir(AbstractFile _i) {
        return _i.exists() && _i.isDirectory();
    }

    @Override
    public boolean isFile(String _file, String _rCont) {
        String file_ = prefix(_file, _rCont);
        AbstractFile info_ = fileCoreStream.newFile(file_);
        return file(info_);
    }

    public static boolean file(AbstractFile _i) {
        return _i.exists() && !_i.isDirectory();
    }

    @Override
    public StringList getRoots() {
        StringList roots_ = PathsUtil.listRootsAbPath(fileCoreStream);
        roots_.sort();
        return roots_;
    }

    @Override
    public boolean isAbsoluteFct(String _file) {
        return isAbsolute(_file);
    }

    private boolean isAbsolute(String _file) {
        return PathsUtil.isAbsolute(_file,fileCoreStream);
    }

    @Override
    public boolean mkdirs(String _file, String _rCont) {
        String file_ = ExecutingOptions.adjustPath(_file);
        if (koName(file_)) {
            return false;
        }
        String pref_ = prefix(file_, _rCont);
        return simpleMkdirs(pref_);
    }

    @Override
    public long lastModified(String _file, String _rCont) {
        String file_ = prefix(_file, _rCont);
        AbstractFile info_ = fileCoreStream.newFile(file_);
        return info_.lastModified();
    }

    @Override
    public StringList getFiles(String _file, String _rCont) {
        String file_ = prefix(_file, _rCont);
        AbstractFile info_ = fileCoreStream.newFile(file_);
        FileListInfo files_ = PathsUtil.abs(info_,fileCoreStream);
        if (files_.isNul()) {
            return null;
        }
        StringList filesList_ = new StringList();
        for (AbstractFile f: files_.getNames()) {
            if (file(f)) {
                filesList_.add(StringUtil.replaceBackSlash(f.getAbsolutePath()));
            }
        }
        filesList_.sort();
        return filesList_;
    }

    @Override
    public StringList getFolders(String _file, String _rCont) {
        String file_ = prefix(_file, _rCont);
        AbstractFile info_ = fileCoreStream.newFile(file_);
        FileListInfo files_ = PathsUtil.abs(info_,fileCoreStream);
        if (files_.isNul()) {
            return null;
        }
        StringList filesList_ = new StringList();
        for (AbstractFile f: files_.getNames()) {
            if (dir(f)) {
                filesList_.add(StringUtil.replaceBackSlash(f.getAbsolutePath()));
            }
        }
        filesList_.sort();
        return filesList_;
    }

    private boolean simpleMkdirs(String _modified) {
        return StreamFolderFile.makeParent(_modified+"/",fileCoreStream);
    }
    private boolean koName(String _file) {
        String normal_ = StringUtil.replaceBackSlash(_file);
        String ext_ = normal_;
        if (isAbsoluteFct(normal_)) {
            if (!normal_.startsWith(base)) {
                return true;
            }
            ext_ = normal_.substring(base.length());
        }
        return !nameValidating.okPath(ext_, '/', '\\');
    }

    private String prefix(String _file, String _rCont) {
        String chg_ = StringUtil.replaceBackSlash(_file);
        String file_ = chg_;
        if (!isAbsoluteFct(chg_)) {
            file_ = _rCont+chg_;
        }
        return file_;
    }

}
