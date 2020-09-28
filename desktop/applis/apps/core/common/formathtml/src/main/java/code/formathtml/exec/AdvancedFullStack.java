package code.formathtml.exec;

import code.expressionlanguage.AbstractFullStack;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.StackTraceElementStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;

public final class AdvancedFullStack implements AbstractFullStack {
    private final Configuration context;

    public AdvancedFullStack(Configuration context) {
        this.context = context;
    }

    @Override
    public ArrayStruct newStackTraceElementArray() {
        return newStackTraceElementArray(context);
    }

    private static ArrayStruct newStackTraceElementArray(Configuration _configuration) {
        int count_ = _configuration.getImporting().size();
        int lenArrCtx_ = _configuration.getContext().nbPages();
        Struct[] arr_ = new Struct[count_+ lenArrCtx_];
        for (int i = 0; i < count_; i++) {
            arr_[i] = newStackTraceElement(_configuration.getImporting().get(i));
        }
        for (int i = 0; i < lenArrCtx_; i++) {
            arr_[i+count_] = ExecutingUtil.newStackTraceElement(_configuration.getContext(),i);
        }
        String cl_ = _configuration.getContext().getStandards().getAliasStackTraceElement();
        cl_ = StringExpUtil.getPrettyArrayType(cl_);
        return new ArrayStruct(arr_, cl_);
    }

    private static StackTraceElementStruct newStackTraceElement(ImportingPage _page) {
        int indexFileType_ = _page.getSum();
        int row_ = _page.getRowFile(indexFileType_);
        int col_ = _page.getColFile(indexFileType_,row_);
        String fileName_ = _page.getReadUrl();
        String currentClassName_ = _page.getGlobalClass();
        return new StackTraceElementStruct(fileName_,row_,col_,indexFileType_,currentClassName_,"");
    }

}
