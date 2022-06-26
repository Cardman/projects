package code.stream;

import code.stream.comparators.FileNameComparator;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class FileInfo {

    private final AbstractFile info;

    private final CustList<AbstractFile> children;

    private int index;

    private FileInfo parent;

    public FileInfo(AbstractFile _info,AbstractFileCoreStream _fact) {
        info = _info;
        FileListInfo files_ = PathsUtil.abs(info,_fact);
        if (!files_.isNul()) {
            children = new CustList<AbstractFile>(files_.getNames());
            children.sortElts(new FileNameComparator());
        } else {
            children = new CustList<AbstractFile>(new CollCapacity(0));
        }
    }

    public FileInfo(AbstractFile _info,AbstractFileCoreStream _fact, int _index, FileInfo _parent) {
        this(_info,_fact);
        parent = _parent;
        index = _index;
    }

    public FileInfo getFirstChild(AbstractFileCoreStream _fact) {
        if (children.isEmpty()) {
            return null;
        }
        AbstractFile f_ = children.first();
        return new FileInfo(f_,_fact, IndexConstants.FIRST_INDEX, this);
    }

    public FileInfo getNextSibling(AbstractFileCoreStream _fact) {
        FileInfo p_ = getParent();
        if (p_ == null) {
            return null;
        }
        CustList<AbstractFile> children_ = p_.children;
        if (index + 1 >= children_.size()) {
            return null;
        }
        AbstractFile f_ = children_.get(index + 1);
        return new FileInfo(f_,_fact, index + 1, p_);
    }

    public FileInfo getParent() {
        return parent;
    }

    public AbstractFile getInfo() {
        return info;
    }
}
