package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecStaticFctOperation extends ExecSettableCallFctOperation {

    private final ExecStaticFctContent staticFctContent;

    private final ExecTypeFunction pair;
    public ExecStaticFctOperation(ExecTypeFunction _pair, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecStaticFctContent _staticFctContent, ExecArrContent _arrContent) {
        super(_opCont, _intermediateDottedOperation,_arrContent);
        staticFctContent = _staticFctContent;
        pair = _pair;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        int off_ = StringUtil.getFirstPrintableCharIndex(staticFctContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        String classNameFound_ = ClassMethodId.formatType(staticFctContent.getClassName(), staticFctContent.getKind(), _stack);
        Argument res_;
        if (_conf.getExiting().hasToExit(_stack, classNameFound_)) {
            res_ = Argument.createVoid();
        } else {
            Argument prev_ = new Argument();
            String lastType_ = ClassMethodId.formatType(pair.getType(), classNameFound_, staticFctContent.getLastType(), staticFctContent.getKind());
            res_ = callPrepare(_conf, classNameFound_, pair, prev_, null, fectchArgs(_nodes, lastType_, staticFctContent.getNaturalVararg()), null, staticFctContent.getKind(), "", _stack);
        }
        setResult(res_, _conf, _nodes, _stack);
    }


}
