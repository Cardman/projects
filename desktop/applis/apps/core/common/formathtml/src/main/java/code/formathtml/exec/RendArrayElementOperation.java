package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.analyze.opers.AbstractArrayInstancingOperation;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class RendArrayElementOperation extends
        RendAbstractArrayInstancingOperation {

    public RendArrayElementOperation(AbstractArrayInstancingOperation _abs) {
        super(_abs);
    }

    @Override
    Argument getArgument(CustList<Argument> _arguments, Configuration _conf) {
        String me_ = getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(me_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String cl_ = getClassName();
        String className_;
        PageEl page_ = _conf.getPageEl();
        className_ = page_.formatVarType(cl_, _conf.getContext());

        int nbCh_ = _arguments.size();

        Ints dims_;
        dims_ = new Ints();
        dims_.add(nbCh_);
        Struct str_ = ExecTemplates.newCustomArray(className_, dims_, _conf.getContext());
        ExecTemplates.setCheckedElements(_arguments,str_,_conf.getContext());
        return new Argument(str_);
    }
}
