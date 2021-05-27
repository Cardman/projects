package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecExplicitCommonContent;
import code.expressionlanguage.fwd.opers.ExecExplicitContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public final class ExecImplicitOperation extends ExecAbstractUnaryOperation {
    private final ExecExplicitCommonContent explicitContent;

    public ExecImplicitOperation(ExecOperationContent _opCont, ExecExplicitCommonContent _explicitContent) {
        super(_opCont);
        explicitContent = _explicitContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(explicitContent.getOffset(), _stack);
        ArgumentListCall list_ = listNamedArguments(buildInfos(_nodes)).getArguments();
        Argument argres_ = getArgument(_stack.formatVarType(explicitContent.getClassName()), _conf, _stack, list_);
        setSimpleArgument(argres_, _conf, _nodes, _stack);
    }

    public static Argument getArgument(String _paramName, ContextEl _conf, StackCall _stackCall, ArgumentListCall _list) {
        Argument objArg_ = new Argument(ArgumentWrapper.helpArg(ExecHelper.getFirstArgumentWrapper(_list.getArgumentWrappers())).getStruct());
        ExecTemplates.checkObject(_paramName, objArg_, _conf, _stackCall);
        return objArg_;
    }
}
