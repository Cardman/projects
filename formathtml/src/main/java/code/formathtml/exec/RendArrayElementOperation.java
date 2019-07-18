package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.AbstractArrayInstancingOperation;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class RendArrayElementOperation extends
        RendAbstractArrayInstancingOperation {

    public RendArrayElementOperation(AbstractArrayInstancingOperation _abs) {
        super(_abs);
    }

    @Override
    Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        String me_ = getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(me_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String cl_ = getClassName();
        String className_;
        PageEl page_ = _conf.getOperationPageEl();
        className_ = page_.formatVarType(cl_, _conf);

        int nbCh_ = _arguments.size();
        Argument a_ = new Argument();

        Ints dims_;
        dims_ = new Ints();
        dims_.add(nbCh_);
        Struct str_ = PrimitiveTypeUtil.newCustomArray(className_, dims_, _conf);
        Templates.setCheckedElements(_arguments,str_,_conf);
        a_.setStruct(str_);
        return a_;
    }
}
