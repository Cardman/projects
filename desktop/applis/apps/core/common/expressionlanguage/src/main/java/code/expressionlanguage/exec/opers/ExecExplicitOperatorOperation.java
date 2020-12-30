package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
import code.util.IdMap;

public final class ExecExplicitOperatorOperation extends ExecSettableCallFctOperation {

    private final ExecTypeFunction pair;
    private final ExecStaticFctContent staticFctContent;

    private final int offsetOper;

    public ExecExplicitOperatorOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecStaticFctContent _staticFctContent, int _offsetOper, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ExecArrContent _exArr) {
        super(_opCont, _intermediateDottedOperation,_exArr);
        staticFctContent = _staticFctContent;
        offsetOper = _offsetOper;
        pair = new ExecTypeFunction(_rootBlock,_named);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        int off_ = getOffsetOper();
        setRelOffsetPossibleLastPage(off_, _stack);
        checkParametersOperators(_conf.getExiting(),_conf, pair, getArgs(_nodes, _stack), staticFctContent.getClassName(), staticFctContent.getKind(), _stack);
    }

    public ArgumentListCall getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, StackCall _stackCall) {
        String classNameFound_ = ClassMethodId.formatType(staticFctContent.getClassName(), staticFctContent.getKind(), _stackCall);
        String lastType_ = ClassMethodId.formatType(pair.getType(),classNameFound_, staticFctContent.getLastType(), staticFctContent.getKind());
        return fectchArgs(_nodes,lastType_, staticFctContent.getNaturalVararg());
    }

    public int getOffsetOper() {
        return offsetOper;
    }

}
