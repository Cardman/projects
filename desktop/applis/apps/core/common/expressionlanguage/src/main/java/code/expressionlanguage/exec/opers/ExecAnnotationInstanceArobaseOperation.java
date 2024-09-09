package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstancingAnnotContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecAnnotationInstanceArobaseOperation extends ExecSettableCallFctOperation {

    private final ExecInstancingAnnotContent instancingAnnotContent;

    public ExecAnnotationInstanceArobaseOperation(
            ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstancingAnnotContent _instancingAnnotContent) {
        super(_opCont, _intermediateDottedOperation,new ExecArrContent(false));
        instancingAnnotContent = _instancingAnnotContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        CustList<Struct> arguments_ = getArguments(_nodes, this);
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingAnnotContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        ParamCheckerUtil.redirectAnnotation(_conf, _stack, arguments_, instancingAnnotContent);
        setResult(NullStruct.NULL_VALUE, _conf, _nodes, _stack);
    }

}
