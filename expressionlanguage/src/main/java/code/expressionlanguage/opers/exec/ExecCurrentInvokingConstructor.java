package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.calls.util.InstancingStep;
import code.expressionlanguage.opers.CurrentInvokingConstructor;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;

public final class ExecCurrentInvokingConstructor extends ExecAbstractInvokingConstructor {

    public ExecCurrentInvokingConstructor(CurrentInvokingConstructor _current) {
        super(_current);
    }

    @Override
    Argument getArgument(CustList<Argument> _arguments, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = getOffsetOper();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);

        PageEl page_ = _conf.getLastPage();
        Argument arg_ = page_.getGlobalArgument();
        String gl_ = page_.getGlobalClass();
        CustList<Argument> firstArgs_;
        String lastType_ = getLastType();
        lastType_ = page_.formatVarType(lastType_, _conf);
        int natvararg_ = getNaturalVararg();
        ConstructorId ctorId_ = getConstId();
        firstArgs_ = listArguments(chidren_, natvararg_, lastType_, _arguments, _conf);
        checkParameters(_conf, gl_, ctorId_, arg_, firstArgs_, true,false,InstancingStep.USING_THIS,null);
        return Argument.createVoid();
    }

}
