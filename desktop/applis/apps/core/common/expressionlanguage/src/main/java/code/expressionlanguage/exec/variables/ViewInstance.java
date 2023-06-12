package code.expressionlanguage.exec.variables;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class ViewInstance {
    private final String className;
    private final Struct eval;
    public ViewInstance(ContextEl _r, AbstractPageEl _page) {
        Struct gl_ = _page.getContentEx().getGlobalStruct();
        eval = gl_;
        if (gl_ == NullStruct.NULL_VALUE) {
            className = _page.getGlobalClass().getFormatted();
        } else {
            className = eval.getClassName(_r);
        }
    }

    public Struct getEval() {
        return eval;
    }

    public String getClassName() {
        return className;
    }
}
