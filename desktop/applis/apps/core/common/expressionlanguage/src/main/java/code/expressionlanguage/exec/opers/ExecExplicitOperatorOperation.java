package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
import code.util.IdMap;

public final class ExecExplicitOperatorOperation extends ExecSettableCallFctOperation {

    private final ExecTypeFunction pair;
    private final ExecStaticFctContent staticFctContent;

    private final int offsetOper;
    public ExecExplicitOperatorOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecStaticFctContent _staticFctContent, int _offsetOper, ExecTypeFunction _pair, ExecArrContent _exArr) {
        super(_opCont, _intermediateDottedOperation,_exArr);
        staticFctContent = _staticFctContent;
        offsetOper = _offsetOper;
        pair = _pair;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        int off_ = getOffsetOper();
        setRelOffsetPossibleLastPage(off_, _stack);
        checkParametersOperatorsFormatted(this,pair,staticFctContent,_nodes, _conf, _stack);
    }

    static void checkParametersOperatorsFormatted(ExecMethodOperation _curr, ExecTypeFunction _pair, ExecStaticFctContent _elt,IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        ExecFormattedRootBlock classNameFound_ = ExecFormattedRootBlock.formatType(_elt.getElts(), _stack);
        String lastType_ = ExecFormattedRootBlock.formatLastType(classNameFound_, _elt);
        checkParametersOperatorsFormatted(_conf.getExiting(), _conf, _pair, fectchArgs(lastType_, _elt.getNaturalVararg(),null, _conf, _stack, _curr.buildInfos(_nodes)), classNameFound_, _elt.getKind(), _stack);
    }

    public int getOffsetOper() {
        return offsetOper;
    }

}
