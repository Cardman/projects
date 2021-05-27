package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctCommonContent;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecEnumMethOperation extends ExecSettableCallFctOperation {

    private final ExecStaticFctCommonContent staticFctContent;

    private final ExecRootBlock type;

    public ExecEnumMethOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecStaticFctCommonContent _staticFctContent, ExecArrContent _arrContent, ExecRootBlock _type) {
        super(_opCont, _intermediateDottedOperation,_arrContent);
        staticFctContent = _staticFctContent;
        type = _type;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        int off_ = StringUtil.getFirstPrintableCharIndex(staticFctContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        String lastType_ = staticFctContent.getLastType();
        Argument res_ = processEnums(_conf.getExiting(), _conf, fectchArgs(lastType_, staticFctContent.getNaturalVararg(),null,_conf,_stack, buildInfos(_nodes)), _stack, type);
        setResult(res_, _conf, _nodes, _stack);
    }
}
