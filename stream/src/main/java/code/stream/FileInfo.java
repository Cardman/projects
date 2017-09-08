package code.stream;
import java.io.File;

import code.stream.comparators.FileNameComparator;
import code.util.CustList;

public final class FileInfo {

    private File info;

    private CustList<File> children;

    private int index;

    private FileInfo parent;

    public FileInfo(File _info) {
        info = _info;
        File[] files_ = info.listFiles();
        if (files_ != null) {
            children = new CustList<File>(files_);
            children.sortElts(new FileNameComparator());
        }
    }

    public FileInfo(File _info, int _index, FileInfo _parent) {
        this(_info);
        parent = _parent;
        index = _index;
    }

    public FileInfo getFirstChild() {
        if (children == null || children.isEmpty()) {
            return null;
        }
        File f_ = children.first();
        return new FileInfo(f_, CustList.FIRST_INDEX, this);
    }

    public FileInfo getNextSibling() {
        FileInfo p_ = getParent();
        if (p_ == null) {
            return null;
        }
        CustList<File> children_ = p_.children;
        if (index + 1 >= children_.size()) {
            return null;
        }
        File f_ = children_.get(index + 1);
        return new FileInfo(f_, index + 1, p_);
    }

    public FileInfo getParent() {
        return parent;
    }

    public File getInfo() {
        return info;
    }
}
