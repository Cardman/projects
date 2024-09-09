package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.util.ArgumentList;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstancingDirContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecDirectStandardInstancingOperation extends
        ExecSettableCallFctOperation  implements StdParamsOperable{

    private final ExecInstancingDirContent instancingCommonContent;

    public ExecDirectStandardInstancingOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstancingDirContent _instancingCommonContent) {
        super(_opCont, _intermediateDottedOperation,new ExecArrContent(false));
        instancingCommonContent = _instancingCommonContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingCommonContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        Struct res_ = ParamCheckerUtil.instancePrepareStd(_conf, instancingCommonContent.getConstructor(), instancingCommonContent.getConstId(), fectchArgs(instancingCommonContent.getLastType(), instancingCommonContent.getNaturalVararg(), _conf,_stack, buildInfos(_nodes)), _stack).getValue();
        setCheckedResult(res_, _conf, _nodes, _stack);
    }
    @Override
    public ArgumentList args(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String _alias) {
        return args(instancingCommonContent.getLastType(), instancingCommonContent.getNaturalVararg(),_nodes);
    }

    @Override
    public StandardNamedFunction fct() {
        return instancingCommonContent.getConstructor();
    }

    @Override
    public Struct instance(IdMap<ExecOperationNode, ArgumentsPair> _nodes, AbstractPageEl _stack) {
        return NullStruct.NULL_VALUE;
    }
}