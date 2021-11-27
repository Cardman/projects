package code.vi.sys.impl;

import code.stream.AbstractFile;
import code.stream.AbstractFileCoreStream;
import code.stream.FileListInfo;
import code.stream.PathsUtil;
import code.util.core.StringUtil;

import java.io.File;

public final class DefaultFile implements AbstractFile {
    private final File file;

    public DefaultFile(String _name) {
        file = new File(StringUtil.nullToEmpty(_name));
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public String getAbsolutePath() {
        return file.getAbsolutePath();
    }

    @Override
    public FileListInfo listAbsolute(AbstractFileCoreStream _fac) {
        return PathsUtil.abs(_fac,StringUtil.replaceBackSlashDot(file.getAbsolutePath()), file.list());
    }

    @Override
    public boolean isDirectory() {
        return file.isDirectory();
    }

    @Override
    public boolean exists() {
        return file.exists();
    }

    @Override
    public long length() {
        return file.length();
    }

    @Override
    public long lastModified() {
        return file.lastModified();
    }

    @Override
    public boolean renameTo(AbstractFile _newFile) {
        return file.renameTo(((DefaultFile)_newFile).file);
    }

    @Override
    public boolean delete() {
        return file.delete();
    }

    @Override
    public String getParent() {
        return file.getParent();
    }

    @Override
    public boolean mkdirs() {
        return file.mkdirs();
    }
}
