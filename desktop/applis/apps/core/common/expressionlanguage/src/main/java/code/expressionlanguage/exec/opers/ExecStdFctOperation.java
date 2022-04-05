package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStdFctContent;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecStdFctOperation extends ExecSettableCallFctOperation {

    private final ExecStdFctContent stdFctContent;

    public ExecStdFctOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecStdFctContent _stdFctContent, ExecArrContent _arrContent) {
        super(_opCont, _intermediateDottedOperation,_arrContent);
        stdFctContent = _stdFctContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        int off_ = StringUtil.getFirstPrintableCharIndex(stdFctContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        Argument res_ = ParamCheckerUtil.prep(_conf, _stack, previous_, buildInfos(_nodes), stdFctContent);
        setResult(res_, _conf, _nodes, _stack);
    }

}
