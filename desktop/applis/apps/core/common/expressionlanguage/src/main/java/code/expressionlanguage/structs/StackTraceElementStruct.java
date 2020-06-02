package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.util.*;
import code.util.StringList;

public final class StackTraceElementStruct extends WithoutParentStruct implements DisplayableStruct {

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
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getAliasStackTraceElement();
    }

    @Override
    public boolean sameReference(Struct _other) {
        StackTraceElementStruct other_ = getStack(_other);
        if (!(_other instanceof StackTraceElementStruct)) {
            return false;
        }
        if (!StringList.quickEq(fileName, other_.fileName)) {
            return false;
        }
        return Numbers.eq(indexFileType, other_.indexFileType);
    }

    public static StackTraceElementStruct getStack(Struct _str) {
        if (_str instanceof StackTraceElementStruct) {
            return (StackTraceElementStruct) _str;
        }
        return new StackTraceElementStruct("",-1,-1,-1,"","");
    }
    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
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
