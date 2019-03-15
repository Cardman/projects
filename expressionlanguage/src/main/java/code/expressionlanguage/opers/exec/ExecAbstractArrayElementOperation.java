package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.AbstractArrayElementOperation;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

public abstract class ExecAbstractArrayElementOperation extends
        ExecAbstractArrayInstancingOperation {

    protected ExecAbstractArrayElementOperation(AbstractArrayElementOperation _abs) {
        super(_abs);
    }

    @Override
    final Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        String me_ = getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(me_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String cl_ = getClassName();
        String className_;
        PageEl page_ = _conf.getOperationPageEl();
        className_ = page_.formatVarType(cl_, _conf);

        int nbCh_ = _arguments.size();
        Argument a_ = new Argument();

        Numbers<Integer> dims_;
        dims_ = new Numbers<Integer>();
        dims_.add(nbCh_);
        Struct str_ = PrimitiveTypeUtil.newCustomArray(className_, dims_, _conf);
        Templates.setCheckedElements(_arguments,str_,_conf);
        a_.setStruct(str_);
        return a_;
    }
}
