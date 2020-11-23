package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
import code.util.CustList;
import code.util.IdMap;

public final class ExecExplicitOperatorOperation extends ExecInvokingOperation {

    private final ExecTypeFunction pair;
    private ExecStaticFctContent staticFctContent;

    private int offsetOper;

    public ExecExplicitOperatorOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecStaticFctContent _staticFctContent, int _offsetOper, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_opCont, _intermediateDottedOperation);
        staticFctContent = _staticFctContent;
        offsetOper = _offsetOper;
        pair = new ExecTypeFunction(_rootBlock,_named);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        int off_ = getOffsetOper();
        setRelOffsetPossibleLastPage(off_, _conf);
        checkParametersOperators(_conf.getExiting(),_conf, pair, getArgs(_nodes, _conf), staticFctContent.getClassName(), staticFctContent.getKind());
    }

    public CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        String classNameFound_ = ClassMethodId.formatType(staticFctContent.getClassName(),_conf, staticFctContent.getKind());
        String lastType_ = ClassMethodId.formatType(pair.getType(),classNameFound_, staticFctContent.getLastType(), staticFctContent.getKind());
        return fectchArgs(_nodes,lastType_, staticFctContent.getNaturalVararg());
    }

    public int getOffsetOper() {
        return offsetOper;
    }

}
