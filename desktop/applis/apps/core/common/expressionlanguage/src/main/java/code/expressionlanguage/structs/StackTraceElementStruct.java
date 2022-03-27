package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.RowColumnIndex;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class StackTraceElementStruct extends WithoutParentStruct implements DisplayableStruct {

    private final String fileName;
    private final int row;
    private final int col;
    private final int indexFileType;
    private final String currentClassName;
    private final String signature;

    public StackTraceElementStruct(String _fileName, RowColumnIndex _rci, String _currentClassName, String _signature) {
        this(_fileName,_rci.getRow(),_rci.getColumn(),_rci.getIndex(),_currentClassName,_signature);
    }
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
        return _contextEl.getStandards().getContent().getStackElt().getAliasStackTraceElement();
    }

    @Override
    public boolean sameReference(Struct _other) {
        StackTraceElementStruct other_ = NumParsers.getStack(_other);
        if (!(_other instanceof StackTraceElementStruct)) {
            return false;
        }
        if (!StringUtil.quickEq(fileName, other_.fileName)) {
            return false;
        }
        return NumberUtil.eq(indexFileType, other_.indexFileType);
    }

    @Override
    public long randCode() {
        long r_ = NumParsers.mergeRandCode(1,NumParsers.randCode(fileName));
        r_ = NumParsers.mergeRandCode(r_,NumParsers.randCode(indexFileType));
        return r_;
    }
    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(getStringRep());
    }

    String getStringRep() {
        StringBuilder str_ = new StringBuilder();
        str_.append(fileName);
        str_.append(":");
        str_.append(Long.toString(row));
        str_.append(",");
        str_.append(Long.toString(col));
        str_.append(":");
        str_.append(Long.toString(indexFileType));
        str_.append("\n");
        str_.append(currentClassName);
        str_.append(".");
        str_.append(signature);
        return str_.toString();
    }
}
