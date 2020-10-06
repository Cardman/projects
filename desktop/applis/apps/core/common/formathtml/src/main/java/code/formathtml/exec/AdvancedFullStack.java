package code.formathtml.exec;

import code.expressionlanguage.AbstractFullStack;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.StackTraceElementStruct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;

public final class AdvancedFullStack implements AbstractFullStack {
    private final Configuration context;
    private final ContextEl cont;

    public AdvancedFullStack(Configuration _conf, ContextEl _context) {
        this.context = _conf;
        cont = _context;
    }

    @Override
    public ArrayStruct newStackTraceElementArray() {
        return newStackTraceElementArray(context, cont);
    }

    private static ArrayStruct newStackTraceElementArray(Configuration _configuration, ContextEl _context) {
        int count_ = _configuration.getImporting().size();
        int lenArrCtx_ = _context.nbPages();
        String cl_ = _context.getStandards().getContent().getStackElt().getAliasStackTraceElement();
        cl_ = StringExpUtil.getPrettyArrayType(cl_);
        ArrayStruct array_ = new ArrayStruct(count_+ lenArrCtx_, cl_);
        for (int i = 0; i < count_; i++) {
            array_.set(i, newStackTraceElement(_configuration.getImporting().get(i), _context));
        }
        for (int i = 0; i < lenArrCtx_; i++) {
            array_.set(i+count_, ExecutingUtil.newStackTraceElement(_context,i));
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
