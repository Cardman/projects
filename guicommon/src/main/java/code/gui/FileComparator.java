package code.gui;
import java.io.File;
import java.util.Comparator;

import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.StringList;
import code.util.consts.ConstFiles;

public final class FileComparator implements Comparator<File> {

    private static final boolean SENSITIVE = ConstFiles.filesAreCaseSensitive();

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
    public int compare(File _o1, File _o2) {
        if (indexOfSorted == FileTable.NAME_INDEX) {
            if (increasing) {
                if (!SENSITIVE) {
                    return String.CASE_INSENSITIVE_ORDER.compare(_o1.getName(), _o2.getName());
                }
                return _o1.getName().compareTo(_o2.getName());
            }
            if (!SENSITIVE) {
                return String.CASE_INSENSITIVE_ORDER.compare(_o2.getName(), _o1.getName());
            }
            return _o2.getName().compareTo(_o1.getName());
        }
        if (indexOfSorted == FileTable.DATE_INDEX) {
            if (increasing) {
                long lg_ = _o1.lastModified() - _o2.lastModified();
                if (lg_ > CustList.EQ_CMP) {
                    return CustList.SWAP_SORT;
                }
                if (lg_ < CustList.EQ_CMP) {
                    return CustList.NO_SWAP_SORT;
                }
                return CustList.EQ_CMP;
            }
            long lg_ = _o2.lastModified() - _o1.lastModified();
            if (lg_ > CustList.EQ_CMP) {
                return CustList.SWAP_SORT;
            }
            if (lg_ < CustList.EQ_CMP) {
                return CustList.NO_SWAP_SORT;
            }
            return CustList.EQ_CMP;
        }
        if (indexOfSorted == FileTable.SIZE_INDEX) {
            if (increasing) {
                long lg_ = _o1.length() - _o2.length();
                if (lg_ > CustList.EQ_CMP) {
                    return CustList.SWAP_SORT;
                }
                if (lg_ < CustList.EQ_CMP) {
                    return CustList.NO_SWAP_SORT;
                }
                return CustList.EQ_CMP;
            }
            long lg_ = _o2.length() - _o1.length();
            if (lg_ > CustList.EQ_CMP) {
                return CustList.SWAP_SORT;
            }
            if (lg_ < CustList.EQ_CMP) {
                return CustList.NO_SWAP_SORT;
            }
            return CustList.EQ_CMP;
        }
        if (indexOfSorted == FileTable.PATH_INDEX) {
            if (increasing) {
                String returnOne_ = _o1.getAbsolutePath();
                returnOne_ = returnOne_.substring(folder.length());
                returnOne_ = StringList.replaceBackSlash(returnOne_);
                StringList pathOne_ = StringList.splitStrings(returnOne_, StreamTextFile.SEPARATEUR);
                String returnTwo_ = _o2.getAbsolutePath();
                returnTwo_ = returnTwo_.substring(folder.length());
                returnTwo_ = StringList.replaceBackSlash(returnTwo_);
                StringList pathTwo_ = StringList.splitStrings(returnTwo_, StreamTextFile.SEPARATEUR);
                int min_ = Math.min(pathOne_.size(), pathTwo_.size());
                if (!SENSITIVE) {
                    for (int i = CustList.FIRST_INDEX; i < min_; i++) {
                        int res_ = String.CASE_INSENSITIVE_ORDER.compare(pathOne_.get(i),pathTwo_.get(i));
                        if (res_ != CustList.EQ_CMP) {
                            return res_;
                        }
                    }
                } else {
                    for (int i = CustList.FIRST_INDEX; i < min_; i++) {
                        int res_ = pathOne_.get(i).compareTo(pathTwo_.get(i));
                        if (res_ != CustList.EQ_CMP) {
                            return res_;
                        }
                    }
                }
                return pathOne_.size() - pathTwo_.size();
            }
            String returnOne_ = _o1.getAbsolutePath();
            returnOne_ = returnOne_.substring(folder.length());
            returnOne_ = StringList.replaceBackSlash(returnOne_);
            StringList pathOne_ = StringList.splitStrings(returnOne_,StreamTextFile.SEPARATEUR);
            String returnTwo_ = _o2.getAbsolutePath();
            returnTwo_ = returnTwo_.substring(folder.length());
            returnTwo_ = StringList.replaceBackSlash(returnTwo_);
            StringList pathTwo_ = StringList.splitStrings(returnTwo_,StreamTextFile.SEPARATEUR);
            int min_ = Math.min(pathOne_.size(), pathTwo_.size());
            if (!SENSITIVE) {
                for (int i = CustList.FIRST_INDEX; i < min_; i++) {
                    int res_ = String.CASE_INSENSITIVE_ORDER.compare(pathTwo_.get(i), pathOne_.get(i));
                    if (res_ != CustList.EQ_CMP) {
                        return res_;
                    }
                }
            } else {
                for (int i = CustList.FIRST_INDEX; i < min_; i++) {
                    int res_ = pathTwo_.get(i).compareTo(pathOne_.get(i));
                    if (res_ != CustList.EQ_CMP) {
                        return res_;
                    }
                }
            }
            return pathTwo_.size() - pathOne_.size();
        }
        return CustList.EQ_CMP;
    }

}
