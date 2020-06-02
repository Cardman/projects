package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.calls.util.InstancingStep;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.SuperInvokingConstructor;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;

public final class ExecSuperInvokingConstructor extends ExecAbstractInvokingConstructor {

    public ExecSuperInvokingConstructor(SuperInvokingConstructor _super) {
        super(_super);
    }

    @Override
    Argument getArgument(CustList<Argument> _arguments, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = getOffsetOper();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);

        PageEl page_ = _conf.getLastPage();
        Argument arg_ = page_.getGlobalArgument();
        CustList<Argument> firstArgs_;
        String calledCtorTemp_;
        String superClass_ = page_.formatVarType(getClassFromName(), _conf);
        String lastType_ = getLastType();
        lastType_ = Templates.quickFormat(superClass_, lastType_, _conf);
        int natvararg_ = getNaturalVararg();
        ConstructorId ctorId_ = getConstId();
        firstArgs_ = listArguments(chidren_, natvararg_, lastType_, _arguments, _conf);
        calledCtorTemp_ = superClass_;
        checkParametersCtors(_conf, calledCtorTemp_, ctorId_, arg_, firstArgs_, InstancingStep.USING_SUPER,null);
        return Argument.createVoid();
    }

}
