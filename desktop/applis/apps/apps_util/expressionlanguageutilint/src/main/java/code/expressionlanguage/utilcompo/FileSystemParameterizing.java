package code.expressionlanguage.utilcompo;

import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.filenames.DefaultNameValidating;
import code.util.*;
import code.util.core.StringUtil;

public final class FileSystemParameterizing {
    private final String folderPrefix;
    private final String filePrefix;
    public FileSystemParameterizing(String _defFolder,StringBuilder _folder, Ints _folderIndex, String _defFile, StringBuilder _file, Ints _fileIndex, StringMap<String> _messages) {
        if (_folderIndex.isEmpty() && _fileIndex.isEmpty()) {
            folderPrefix = feedFirst(_defFolder,_defFile);
            filePrefix = feedFirst(_defFile,_defFolder);
            return;
        }
        String filPref_ = improve(_file.length(),ParseLinesArgUtil.parseValue(_messages,_file.toString()),_defFile);
        String folPref_ = improve(_folder.length(),ParseLinesArgUtil.parseValue(_messages,_folder.toString()),_defFolder);
        if (fixFolder(_folderIndex, _fileIndex)) {
            if (StringUtil.quickEq(folPref_, filPref_)) {
                folderPrefix = distinct(folPref_);
                filePrefix = folPref_;
                return;
            }
            filePrefix = feedFirst(filPref_, folPref_);
            folderPrefix = feedFirst(folPref_,filPref_);
            return;
        }
        if (StringUtil.quickEq(filPref_, folPref_)) {
            filePrefix = distinct(filPref_);
            folderPrefix = filPref_;
            return;
        }
        folderPrefix = feedFirst(folPref_, filPref_);
        filePrefix = feedFirst(filPref_,folPref_);
    }
    private static String distinct(String _other) {
        if (StringUtil.quickEq(_other, "_")) {
            return "-";
        }
        return "_";
    }
    private static boolean fixFolder(Ints _folderIndex, Ints _fileIndex) {
        if (_folderIndex.isEmpty()) {
            return false;
        }
        if (_fileIndex.isEmpty()) {
            return true;
        }
        long mFolder_ = _folderIndex.getMinimum(0);
        long mFile_ = _fileIndex.getMinimum(0);
        return mFolder_ < mFile_;
    }
    private static String feedFirst(String _one, String _two) {
        int addedCount_ = _two.length() - _one.length();
        if (addedCount_ <= 0) {
            return _one;
        }
        String res_ = complete(_one, '-', addedCount_);
        if (!StringUtil.quickEq(res_, _two)) {
            return res_;
        }
        return complete(_one, '_', addedCount_);
    }
    private static String complete(String _str, char _ch, int _count) {
        StringBuilder str_ = new StringBuilder(_str.length()+_count);
        str_.append(_str);
        for (int i = 0; i < _count; i++) {
            str_.append(_ch);
        }
        return str_.toString();
    }
    private static String improve(int _len,String _prefix, String _defValue) {
        if (_len == 0) {
            return _defValue;
        }
        if (!new DefaultNameValidating(new StringList()).ok(_prefix + "._")) {
            return _defValue;
        }
        return _prefix;
    }
    public static void addIndex(String _line, String _prefix, int _current, StringBuilder _row, Ints _indexes) {
        if (_line.startsWith(_prefix)) {
            _indexes.add(_current);
            _row.append(_line.substring(_prefix.length()));
        }
    }

    public String getFilePrefix() {
        return filePrefix;
    }

    public String getFolderPrefix() {
        return folderPrefix;
    }
}
