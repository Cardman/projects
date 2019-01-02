package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.FileBlock;
import code.expressionlanguage.methods.FunctionBlock;
import code.expressionlanguage.methods.NamedFunctionBlock;
import code.expressionlanguage.opers.util.Identifiable;
import code.util.Numbers;
import code.util.StringList;

public final class StackTraceElementStruct implements DisplayableStruct {

    private final String fileName;
    private final int row;
    private final int col;
    private final int indexFileType;
    private final String currentClassName;
    private final String signature;

    private StackTraceElementStruct(AbstractPageEl _page) {
        indexFileType = _page.getTraceIndex();
        FileBlock f_ = _page.getFile();
        if (f_ != null) {
            fileName = f_.getFileName();
            row = f_.getRowFile(indexFileType);
            col = f_.getColFile(indexFileType,row);
        } else {
            fileName = "";
            row = 0;
            col = 0;
        }
        String r_ = _page.getGlobalClass();
        if (r_ != null) {
            currentClassName = r_;
        } else {
            currentClassName = "";
        }
        Block bl_ = _page.getBlockRoot();
        if (bl_ != null) {
            FunctionBlock fct_ = bl_.getFunction();
            if (fct_ instanceof NamedFunctionBlock) {
                Identifiable id_ = ((NamedFunctionBlock)fct_).getId();
                signature =id_.getSignature();
                return;
            }
        }
        signature = "";
    }

    public static ArrayStruct newStackTraceElementArray(ContextEl _context) {
        int count_ = _context.nbPages();
        Struct[] arr_ = new Struct[count_];
        for (int i = 0; i < count_; i++) {
            AbstractPageEl call_ = _context.getCall(i);
            arr_[i] = new StackTraceElementStruct(call_);
        }
        String cl_ = _context.getStandards().getAliasStackTraceElement();
        cl_ = PrimitiveTypeUtil.getPrettyArrayType(cl_);
        return new ArrayStruct(arr_, cl_);
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
