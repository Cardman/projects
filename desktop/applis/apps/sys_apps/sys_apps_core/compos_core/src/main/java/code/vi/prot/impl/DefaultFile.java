package code.vi.prot.impl;

import code.stream.AbstractFile;
import code.util.core.StringUtil;

import java.io.File;

public final class DefaultFile implements AbstractFile {
    private final File file;

    public DefaultFile(String _name) {
        file = newFile(_name);
    }

    public static File newFile(String _name) {
        return new File(StringUtil.nullToEmpty(_name));
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
    public String[] list() {
        return file.list();
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
