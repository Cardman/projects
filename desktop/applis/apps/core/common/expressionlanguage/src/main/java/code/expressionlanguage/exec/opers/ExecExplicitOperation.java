package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecExplicitContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public final class ExecExplicitOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
    private final ExecTypeFunction pair;
    private final ExecExplicitContent explicitContent;
    public ExecExplicitOperation(ExecTypeFunction _pair, ExecOperationContent _opCont, ExecExplicitContent _explicitContent) {
        super(_opCont);
        pair = _pair;
        explicitContent = _explicitContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(explicitContent.getOffset(), _stack);
        ArgumentListCall list_ = listNamedArguments(buildInfos(_nodes)).getArguments();
        Argument argres_ =  ParamCheckerUtil.prepare(_conf.getExiting(),pair, explicitContent.getFormattedType(),_conf,_stack, list_);
        setSimpleArgument(argres_, _conf, _nodes, _stack);
    }

    public static boolean direct(ExecTypeFunction _castOpId, String _className) {
        return !StringExpUtil.customCast(_className) || _castOpId == null;
    }


}
