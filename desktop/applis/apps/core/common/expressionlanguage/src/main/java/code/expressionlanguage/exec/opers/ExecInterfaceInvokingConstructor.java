package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecInvokingConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public final class ExecInterfaceInvokingConstructor extends ExecAbstractInvokingConstructor {

    public ExecInterfaceInvokingConstructor(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInvokingConstructorContent _invokingConstructorContent, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _ctor) {
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

        String superClass_ = _conf.formatVarType(getClassFromName());
        CustList<Argument> firstArgs_ = getArgs(_nodes, superClass_);
        checkParametersCtors(_conf, superClass_, getRootBlock(),getCtor(),  firstArgs_, InstancingStep.USING_SUPER);
        return Argument.createVoid();
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String _superClass) {
        String lastType_ = ExecTemplates.quickFormat(getRootBlock(),_superClass, getLastType());
        return fectchArgs(_nodes,lastType_,getNaturalVararg());
    }

}
