package code.expressionlanguage.utilfiles;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.AbstractFileSystem;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.stream.StreamBinaryFile;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.stream.core.ReadBinFiles;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;

import java.io.File;

public final class DefaultFileSystem implements AbstractFileSystem {

    private final UniformingString uniformingString;
    private final AbstractNameValidating nameValidating;
    private String base = "";

    public DefaultFileSystem(UniformingString _uniformingString, AbstractNameValidating _nameValidating) {
        uniformingString = _uniformingString;
        nameValidating = _nameValidating;
    }

    @Override
    public void build(String _base, ReadBinFiles _readBin) {
        base = _base;
        mkdirs(_base);
    }

    @Override
    public void changeDir(String _file, RunnableContextEl _rCont) {
        String normal_ = StringUtil.replaceBackSlashDot(_file);
        if (isAbsolute(_file,_rCont)) {
            if (!normal_.startsWith(base)) {
                return;
            }
            if (!isDirectory(_file)) {
                return;
            }
            _rCont.setCurrentDir(normal_);
        }
        String currentDir_ = _rCont.getCurrentDir();
        String candidate_ = currentDir_ + normal_;
        if (!isDirectory(candidate_)) {
            return;
        }
        _rCont.setCurrentDir(candidate_);
    }

    @Override
    public String currentDir(RunnableContextEl _rCont) {
        return _rCont.getCurrentDir();
    }

    @Override
    public String contentsOfFile(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        return StreamTextFile.contentsOfFile(file_, uniformingString);
    }

    @Override
    public boolean saveTextFile(String _file, String _content, RunnableContextEl _rCont) {
        if (koName(_file, _rCont)) {
            return false;
        }
        String file_ = prefix(_file, _rCont);
        return StreamTextFile.saveTextFile(file_,_content);
    }

    @Override
    public byte[] loadFile(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        return StreamBinaryFile.loadFile(file_);
    }

    @Override
    public boolean writeFile(String _file, byte[] _content, RunnableContextEl _rCont) {
        if (koName(_file, _rCont)) {
            return false;
        }
        String file_ = prefix(_file, _rCont);
        return StreamBinaryFile.writeFile(file_,_content);
    }

    @Override
    public boolean delete(String _file, RunnableContextEl _rCont) {
        if (koName(_file, _rCont)) {
            return false;
        }
        String file_ = prefix(_file, _rCont);
        return new File(file_).delete();
    }

    @Override
    public boolean rename(String _origin, String _dest, RunnableContextEl _rCont) {
        if (koName(_origin, _rCont) || koName(_dest, _rCont)) {
            return false;
        }
        String origin_ = prefix(_origin, _rCont);
        String dest_ = prefix(_dest, _rCont);
        return new File(origin_).renameTo(new File(dest_));
    }

    @Override
    public boolean logToFile(String _file, String _content, RunnableContextEl _rCont) {
        if (koName(_file, _rCont)) {
            return false;
        }
        String file_ = prefix(_file, _rCont);
        return StreamTextFile.logToFile(file_,_content);
    }

    @Override
    public String absolutePath(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        return StringUtil.replaceBackSlash(new File(file_).getAbsolutePath());
    }

    @Override
    public long length(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        return new File(file_).length();
    }

    @Override
    public String getName(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        return new File(file_).getName();
    }

    @Override
    public String getParentPath(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        return StringUtil.replaceBackSlash(new File(file_).getParentFile().getAbsolutePath());
    }

    @Override
    public boolean isDirectory(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        return isDirectory(file_);
    }

    private boolean isDirectory(String _file) {
        File info_ = new File(_file);
        return info_.exists()&&info_.isDirectory();
    }

    @Override
    public boolean isFile(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        File info_ = new File(file_);
        return info_.exists()&&!info_.isDirectory();
    }

    @Override
    public StringList getRoots(RunnableContextEl _rCont) {
        StringList roots_ = StreamFolderFile.listRootsAbPath();
        roots_.sort();
        return roots_;
    }

    @Override
    public boolean isAbsolute(String _file, RunnableContextEl _rCont) {
        return isAbsolute(_file);
    }

    private static boolean isAbsolute(String _file) {
        return StreamFolderFile.isAbsolute(_file);
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
        File info_ = new File(file_);
        return info_.lastModified();
    }

    @Override
    public StringList getFiles(String _file, RunnableContextEl _rCont) {
        String file_ = prefix(_file, _rCont);
        File info_ = new File(file_);
        File[] files_ = info_.listFiles();
        if (files_ == null) {
            return null;
        }
        StringList filesList_ = new StringList();
        for (File f: files_) {
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
        File info_ = new File(file_);
        File[] files_ = info_.listFiles();
        if (files_ == null) {
            return null;
        }
        StringList filesList_ = new StringList();
        for (File f: files_) {
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

    private static boolean simpleMkdirs(String _modified) {
        return StreamFolderFile.mkdirs(_modified);
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
