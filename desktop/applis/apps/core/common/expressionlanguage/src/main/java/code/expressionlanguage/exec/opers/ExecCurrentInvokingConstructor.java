package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.analyze.opers.CurrentInvokingConstructor;
import code.expressionlanguage.exec.util.ArgumentList;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCurrentInvokingConstructor extends ExecAbstractInvokingConstructor {

    public ExecCurrentInvokingConstructor(CurrentInvokingConstructor _current, AnalyzedPageEl _page) {
        super(_current, _page);
    }


    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument res_ = getArgument(_nodes, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        int off_ = getOffsetOper();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);

        CustList<Argument> firstArgs_ = getArgs(_nodes, _conf);
        checkParametersCtors(_conf, _conf.getLastPage().getGlobalClass(), getRootBlock(),getCtor(), firstArgs_, InstancingStep.USING_THIS);
        return Argument.createVoid();
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        PageEl page_ = _conf.getLastPage();
        String lastType_ = page_.formatVarType(getLastType(), _conf);
        return fectchArgs(_nodes,lastType_,getNaturalVararg());
    }

}
