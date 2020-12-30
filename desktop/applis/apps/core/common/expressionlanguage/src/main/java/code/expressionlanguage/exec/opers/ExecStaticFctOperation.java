package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
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
        Argument res_ = getArgument(_nodes, _conf, _stack);
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(res_, _conf, _nodes, _stack);
            return;
        }
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }
    Argument getArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stackCall) {
        int off_ = StringUtil.getFirstPrintableCharIndex(staticFctContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stackCall);
        String classNameFound_ = ClassMethodId.formatType(staticFctContent.getClassName(), staticFctContent.getKind(), _stackCall);
        if (_conf.getExiting().hasToExit(_stackCall, classNameFound_)) {
            return Argument.createVoid();
        }
        Argument prev_ = new Argument();
        return callPrepare(_conf.getExiting(), _conf, classNameFound_, pair, prev_,null, getArgs(_nodes, classNameFound_), null, staticFctContent.getKind(), "", _stackCall);
    }

    private ArgumentListCall getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String _classNameFound) {
        String lastType_ = ClassMethodId.formatType(pair.getType(),_classNameFound, staticFctContent.getLastType(), staticFctContent.getKind());
        return fectchArgs(_nodes,lastType_, staticFctContent.getNaturalVararg());
    }


}
