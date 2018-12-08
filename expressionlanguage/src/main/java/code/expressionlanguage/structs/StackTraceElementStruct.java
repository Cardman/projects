package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.FileBlock;
import code.expressionlanguage.methods.FunctionBlock;
import code.expressionlanguage.methods.InitBlock;
import code.expressionlanguage.methods.NamedFunctionBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.Identifiable;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.StringList;

public class StackTraceElementStruct implements DisplayableStruct {

    private String fileName;
    private int row;
    private int col;
    private int indexFileType;
    private String currentClassName;
    private String signature;
    private boolean staticCall;

    private StackTraceElementStruct(AbstractPageEl _page) {
        Block bl_ = _page.getCurrentBlock();
        FileBlock f_ = bl_.getFile();
        fileName = f_.getFileName();
        indexFileType = _page.getTraceIndex();
        row = f_.getRowFile(indexFileType);
        col = f_.getColFile(indexFileType);
        FunctionBlock fct_ = bl_.getFunction();
        staticCall = fct_.isStaticContext();
        RootBlock r_ = bl_.getRooted();
        if (r_ != null) {
            currentClassName = r_.getFullName();
        } else {
            currentClassName = "";
        }
        if (fct_ instanceof NamedFunctionBlock) {
            Identifiable id_ = ((NamedFunctionBlock)fct_).getId();
            signature =id_.getSignature();
        } else if (fct_ instanceof InitBlock) {
            signature = "";
        }
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
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
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
    public Object getInstance() {
        return this;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

    @Override
    public StringStruct getDisplayedString(Analyzable _an) {
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
        return new StringStruct(str_.toString());
    }

}
