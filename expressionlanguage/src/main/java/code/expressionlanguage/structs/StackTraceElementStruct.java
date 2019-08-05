package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;
import code.util.*;
import code.util.StringList;

public final class StackTraceElementStruct implements DisplayableStruct {

    private final String fileName;
    private final int row;
    private final int col;
    private final int indexFileType;
    private final String currentClassName;
    private final String signature;

    public StackTraceElementStruct(String _fileName, int _row, int _col, int _indexFileType, String _currentClassName, String _signature) {
        fileName = _fileName;
        row = _row;
        col = _col;
        indexFileType = _indexFileType;
        currentClassName = _currentClassName;
        signature = _signature;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return _contextEl.getStandards().getAliasStackTraceElement();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof StackTraceElementStruct)) {
            return false;
        }
        StackTraceElementStruct other_ = (StackTraceElementStruct) _other;
        if (!StringList.quickEq(fileName, other_.fileName)) {
            return false;
        }
        return Numbers.eq(indexFileType, other_.indexFileType);
    }

    @Override
    public StringStruct getDisplayedString(Analyzable _an) {
        return new StringStruct(getStringRep());
    }

    String getStringRep() {
        StringBuilder str_ = new StringBuilder();
        str_.append(fileName);
        str_.append(":");
        str_.append(Integer.toString(row));
        str_.append(",");
        str_.append(Integer.toString(col));
        str_.append(":");
        str_.append(Integer.toString(indexFileType));
        str_.append("\n");
        str_.append(currentClassName);
        str_.append(".");
        str_.append(signature);
        return str_.toString();
    }
}
