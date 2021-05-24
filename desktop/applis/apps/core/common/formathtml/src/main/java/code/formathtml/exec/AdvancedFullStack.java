package code.formathtml.exec;

import code.expressionlanguage.AbstractFullStack;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.MetaInfoUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.StackTraceElementStruct;
import code.formathtml.ImportingPage;

public final class AdvancedFullStack implements AbstractFullStack {
    private final ContextEl cont;
    private final RendStackCall rendStackCall;

    public AdvancedFullStack(ContextEl _context, RendStackCall _rendStackCall) {
        cont = _context;
        rendStackCall = _rendStackCall;
    }

    @Override
    public ArrayStruct newStackTraceElementArray(StackCall _stack) {
        return newStackTraceElementArray(cont, rendStackCall);
    }

    private static ArrayStruct newStackTraceElementArray(ContextEl _context, RendStackCall _rendStackCall) {
        int count_ = _rendStackCall.getImporting().size();
        int lenArrCtx_ = _rendStackCall.getStackCall().nbPages();
        String cl_ = _context.getStandards().getContent().getStackElt().getAliasStackTraceElement();
        cl_ = StringExpUtil.getPrettyArrayType(cl_);
        ArrayStruct array_ = new ArrayStruct(count_+ lenArrCtx_, cl_);
        for (int i = 0; i < count_; i++) {
            array_.set(i, newStackTraceElement(_rendStackCall.getImporting().get(i), _context));
        }
        for (int i = 0; i < lenArrCtx_; i++) {
            array_.set(i+count_, MetaInfoUtil.newStackTraceElement(_context,i, _rendStackCall.getStackCall()));
        }
        return array_;
    }

    private static StackTraceElementStruct newStackTraceElement(ImportingPage _page, ContextEl _context) {
        int indexFileType_ = _page.getSum();
        int row_ = _page.getRowFile(indexFileType_);
        int col_ = _page.getColFile(indexFileType_,row_);
        String fileName_ = _page.getReadUrl();
        String currentClassName_ = _page.getGlobalArgument().getStruct().getClassName(_context);
        return new StackTraceElementStruct(fileName_,row_,col_,indexFileType_,currentClassName_,"");
    }

}
