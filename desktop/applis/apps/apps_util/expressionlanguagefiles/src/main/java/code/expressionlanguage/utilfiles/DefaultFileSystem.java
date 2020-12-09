package code.expressionlanguage.utilfiles;

import code.expressionlanguage.utilcompo.AbstractFileSystem;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.stream.StreamBinaryFile;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;

import java.io.File;

public final class DefaultFileSystem implements AbstractFileSystem {

    private final UniformingString uniformingString;

    public DefaultFileSystem(UniformingString _uniformingString) {
        uniformingString = _uniformingString;
    }

    @Override
    public String contentsOfFile(String _file, RunnableContextEl _rCont) {
        return StreamTextFile.contentsOfFile(_file, uniformingString);
    }

    @Override
    public boolean saveTextFile(String _file, String _content, RunnableContextEl _rCont) {
        return StreamTextFile.saveTextFile(_file,_content);
    }

    @Override
    public byte[] loadFile(String _file, RunnableContextEl _rCont) {
        return StreamBinaryFile.loadFile(_file);
    }

    @Override
    public boolean writeFile(String _file, byte[] _content, RunnableContextEl _rCont) {
        return StreamBinaryFile.writeFile(_file,_content);
    }

    @Override
    public boolean delete(String _file, RunnableContextEl _rCont) {
        return new File(_file).delete();
    }

    @Override
    public boolean rename(String _origin, String _dest, RunnableContextEl _rCont) {
        return new File(_origin).renameTo(new File(_dest));
    }

    @Override
    public boolean logToFile(String _file, String _content, RunnableContextEl _rCont) {
        return StreamTextFile.logToFile(_file,_content);
    }

    @Override
    public String absolutePath(String _file, RunnableContextEl _rCont) {
        return StringUtil.replaceBackSlash(new File(_file).getAbsolutePath());
    }

    @Override
    public long length(String _file, RunnableContextEl _rCont) {
        return new File(_file).length();
    }

    @Override
    public String getName(String _file, RunnableContextEl _rCont) {
        return new File(_file).getName();
    }

    @Override
    public String getParentPath(String _file, RunnableContextEl _rCont) {
        return StringUtil.replaceBackSlash(new File(_file).getParentFile().getAbsolutePath());
    }

    @Override
    public boolean isDirectory(String _file, RunnableContextEl _rCont) {
        File info_ = new File(_file);
        return info_.exists()&&info_.isDirectory();
    }

    @Override
    public boolean isFile(String _file, RunnableContextEl _rCont) {
        File info_ = new File(_file);
        return info_.exists()&&!info_.isDirectory();
    }

    @Override
    public boolean isAbsolute(String _file, RunnableContextEl _rCont) {
        return StreamFolderFile.isAbsolute(_file);
    }

    @Override
    public boolean mkdirs(String _file, RunnableContextEl _rCont) {
        File info_ = new File(_file);
        return info_.mkdirs();
    }

    @Override
    public long lastModified(String _file, RunnableContextEl _rCont) {
        File info_ = new File(_file);
        return info_.lastModified();
    }

    @Override
    public StringList getFiles(String _file, RunnableContextEl _rCont) {
        File info_ = new File(_file);
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
        return filesList_;
    }

    @Override
    public StringList getFolders(String _file, RunnableContextEl _rCont) {
        File info_ = new File(_file);
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
        return filesList_;
    }
}
