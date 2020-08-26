package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.analyze.opers.CurrentInvokingConstructor;
import code.util.CustList;

public final class ExecCurrentInvokingConstructor extends ExecAbstractInvokingConstructor {

    public ExecCurrentInvokingConstructor(CurrentInvokingConstructor _current, ContextEl _context) {
        super(_current,_context);
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
        firstArgs_ = listArguments(chidren_, natvararg_, lastType_, _arguments);
        checkParametersCtors(_conf, gl_, getRootBlock(),getCtor(), arg_, firstArgs_, InstancingStep.USING_THIS,null);
        return Argument.createVoid();
    }

}
