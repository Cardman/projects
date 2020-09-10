package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.analyze.opers.CurrentInvokingConstructor;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCurrentInvokingConstructor extends ExecAbstractInvokingConstructor {

    public ExecCurrentInvokingConstructor(CurrentInvokingConstructor _current, ContextEl _context) {
        super(_current,_context);
    }


    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument res_ = getArgument(_nodes, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = getOffsetOper();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);

        PageEl page_ = _conf.getLastPage();
        String gl_ = page_.getGlobalClass();
        CustList<Argument> firstArgs_;
        String lastType_ = getLastType();
        lastType_ = page_.formatVarType(lastType_, _conf);
        int natvararg_ = getNaturalVararg();
        CustList<Argument> first_ = listNamedArguments(_nodes, chidren_).getArguments();
        firstArgs_ = listArguments(chidren_, natvararg_, lastType_, first_);
        checkParametersCtors(_conf, gl_, getRootBlock(),getCtor(), firstArgs_, InstancingStep.USING_THIS);
        return Argument.createVoid();
    }

}
