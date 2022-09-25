package code.gui;

import code.stream.AbstractFile;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class FileComparator implements Comparing<AbstractFile> {

    private boolean increasing;

    private int indexOfSorted;

    private String folder;

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
                long lg_ = _o1.lastModified() - _o2.lastModified();
                if (lg_ > SortConstants.EQ_CMP) {
                    return SortConstants.SWAP_SORT;
                }
                if (lg_ < SortConstants.EQ_CMP) {
                    return SortConstants.NO_SWAP_SORT;
                }
                return SortConstants.EQ_CMP;
            }
            long lg_ = _o2.lastModified() - _o1.lastModified();
            if (lg_ > SortConstants.EQ_CMP) {
                return SortConstants.SWAP_SORT;
            }
            if (lg_ < SortConstants.EQ_CMP) {
                return SortConstants.NO_SWAP_SORT;
            }
            return SortConstants.EQ_CMP;
        }
        if (indexOfSorted == FileTable.SIZE_INDEX) {
            if (increasing) {
                long lg_ = _o1.length() - _o2.length();
                if (lg_ > SortConstants.EQ_CMP) {
                    return SortConstants.SWAP_SORT;
                }
                if (lg_ < SortConstants.EQ_CMP) {
                    return SortConstants.NO_SWAP_SORT;
                }
                return SortConstants.EQ_CMP;
            }
            long lg_ = _o2.length() - _o1.length();
            if (lg_ > SortConstants.EQ_CMP) {
                return SortConstants.SWAP_SORT;
            }
            if (lg_ < SortConstants.EQ_CMP) {
                return SortConstants.NO_SWAP_SORT;
            }
            return SortConstants.EQ_CMP;
        }
        if (indexOfSorted == FileTable.PATH_INDEX) {
            if (increasing) {
                String returnOne_ = _o1.getAbsolutePath();
                returnOne_ = returnOne_.substring(folder.length());
                returnOne_ = StringUtil.replaceBackSlash(returnOne_);
                StringList pathOne_ = StringUtil.splitStrings(returnOne_, StreamTextFile.SEPARATEUR);
                String returnTwo_ = _o2.getAbsolutePath();
                returnTwo_ = returnTwo_.substring(folder.length());
                returnTwo_ = StringUtil.replaceBackSlash(returnTwo_);
                StringList pathTwo_ = StringUtil.splitStrings(returnTwo_, StreamTextFile.SEPARATEUR);
                int min_ = NumberUtil.min(pathOne_.size(), pathTwo_.size());
                for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
                    int res_ = StringUtil.compareStrings(pathOne_.get(i),pathTwo_.get(i));
                    if (res_ != SortConstants.EQ_CMP) {
                        return res_;
                    }
                }
                return pathOne_.size() - pathTwo_.size();
            }
            String returnOne_ = _o1.getAbsolutePath();
            returnOne_ = returnOne_.substring(folder.length());
            returnOne_ = StringUtil.replaceBackSlash(returnOne_);
            StringList pathOne_ = StringUtil.splitStrings(returnOne_,StreamTextFile.SEPARATEUR);
            String returnTwo_ = _o2.getAbsolutePath();
            returnTwo_ = returnTwo_.substring(folder.length());
            returnTwo_ = StringUtil.replaceBackSlash(returnTwo_);
            StringList pathTwo_ = StringUtil.splitStrings(returnTwo_,StreamTextFile.SEPARATEUR);
            int min_ = NumberUtil.min(pathOne_.size(), pathTwo_.size());
            for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
                int res_ = StringUtil.compareStrings(pathTwo_.get(i),pathOne_.get(i));
                if (res_ != SortConstants.EQ_CMP) {
                    return res_;
                }
            }
            return pathTwo_.size() - pathOne_.size();
        }
        return SortConstants.EQ_CMP;
    }

}
