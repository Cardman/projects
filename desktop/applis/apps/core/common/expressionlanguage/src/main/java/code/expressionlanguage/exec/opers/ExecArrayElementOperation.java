package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.fwd.opers.ExecArrayInstancingContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.Ints;
import code.util.core.StringUtil;

public final class ExecArrayElementOperation extends
        ExecAbstractArrayInstancingOperation {

    public ExecArrayElementOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecArrayInstancingContent _arrayInstancingContent) {
        super(_opCont, _intermediateDottedOperation, _arrayInstancingContent);
    }

    @Override
    Argument getArgument(CustList<Argument> _arguments, ContextEl _conf) {
        String me_ = getMethodName();
        int off_ = StringUtil.getFirstPrintableCharIndex(me_);
        setRelOffsetPossibleLastPage(off_, _conf);
        String cl_ = getClassName();
        String className_ = _conf.formatVarType(cl_);

        int nbCh_ = _arguments.size();

        Ints dims_;
        dims_ = new Ints();
        dims_.add(nbCh_);
        Struct str_ = ExecTemplates.newCustomArray(className_, dims_, _conf);
        ExecTemplates.setCheckedElements(_arguments,str_,_conf);
        return new Argument(str_);
    }
}
