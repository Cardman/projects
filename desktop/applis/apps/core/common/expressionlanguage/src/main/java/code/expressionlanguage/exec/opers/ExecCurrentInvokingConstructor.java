package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInvokingConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCurrentInvokingConstructor extends ExecAbstractInvokingConstructor {

    public ExecCurrentInvokingConstructor(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInvokingConstructorContent _invokingConstructorContent, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _ctor) {
        super(_opCont, _intermediateDottedOperation, _invokingConstructorContent, _rootBlock, _ctor);
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
        String lastType_ = _conf.formatVarType(getLastType());
        return fectchArgs(_nodes,lastType_,getNaturalVararg());
    }

}
