package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.fwd.opers.ExecArrayInstancingContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.Ints;
import code.util.core.StringUtil;

public final class RendArrayElementOperation extends
        RendAbstractArrayInstancingOperation {

    public RendArrayElementOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecArrayInstancingContent _arrayInstancingContent) {
        super(_content, _intermediateDottedOperation, _arrayInstancingContent);
    }

    @Override
    Argument getArgument(CustList<Argument> _arguments, Configuration _conf, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        String me_ = getMethodName();
        int off_ = StringUtil.getFirstPrintableCharIndex(me_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _rendStack);
        String className_ = getClassName();

        int nbCh_ = _arguments.size();

        Ints dims_ = new Ints();
        dims_.add(nbCh_);
        Struct str_ = ExecTemplates.newCustomArray(className_, dims_, _ctx);
        ExecTemplates.setCheckedElements(_arguments,str_,_ctx, _stack);
        return new Argument(str_);
    }
}
