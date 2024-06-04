package code.gui.files;

import code.stream.AbstractFile;
import code.stream.PathsUtil;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class FileComparator implements Comparing<AbstractFile> {

    private final boolean increasing;

    private final int indexOfSorted;

    private final String folder;

    public FileComparator(boolean _increasing, int _indexOfSorted,
            String _folder) {
        increasing = _increasing;
        indexOfSorted = _indexOfSorted;
        folder = _folder;
    }

    @Override
    public int compare(AbstractFile _o1, AbstractFile _o2) {
        if (indexOfSorted == FileTable.NAME_INDEX) {
            if (increasing) {
                return StringUtil.compareStrings(_o1.getName(),_o2.getName());
            }
            return StringUtil.compareStrings(_o2.getName(),_o1.getName());
        }
        if (indexOfSorted == FileTable.DATE_INDEX) {
            if (increasing) {
                return NumberUtil.compareLg(_o1.lastModified(),_o2.lastModified());
            }
            return NumberUtil.compareLg(_o2.lastModified(),_o1.lastModified());
        }
        if (indexOfSorted == FileTable.SIZE_INDEX) {
            if (increasing) {
                return NumberUtil.compareLg(_o1.length(),_o2.length());
            }
            return NumberUtil.compareLg(_o2.length(),_o1.length());
        }
        if (increasing) {
            return sortPath(_o1.getAbsolutePath(), _o2.getAbsolutePath());
        }
        return sortPath(_o2.getAbsolutePath(), _o1.getAbsolutePath());
    }

    private int sortPath(String _one, String _two) {
        return sortPath(adaptList(_one), adaptList(_two));
    }

    private int sortPath(StringList _eltOne, StringList _eltTwo) {
        int min_ = NumberUtil.min(_eltTwo.size(), _eltOne.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            int res_ = StringUtil.compareStrings(_eltOne.get(i), _eltTwo.get(i));
            if (res_ != SortConstants.EQ_CMP) {
                return res_;
            }
        }
        return _eltOne.size() - _eltTwo.size();
    }

    private StringList adaptList(String _path) {
        String returnOne_ = adapt(_path);
        return StringUtil.splitStrings(returnOne_, PathsUtil.SEPARATEUR);
    }

    private String adapt(String _path) {
        String returnOne_ = _path;
        returnOne_ = returnOne_.substring(folder.length());
        returnOne_ = StringUtil.replaceBackSlash(returnOne_);
        return returnOne_;
    }

}
