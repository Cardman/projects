package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrayInstancingContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.ArrayStruct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecArrayElementOperation extends
        ExecAbstractArrayInstancingOperation {

    public ExecArrayElementOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecArrayInstancingContent _arrayInstancingContent) {
        super(_opCont, _intermediateDottedOperation, _arrayInstancingContent);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        int off_ = getMethodName();
        setRelOffsetPossibleLastPage(off_, _stack);
        String className_ = _stack.formatVarType(getClassName());

        ArrayStruct arr_ = ArrayStruct.instance(StringExpUtil.getPrettyArrayType(className_), arguments_);
        ExecArrayTemplates.checkedElements(arr_, _conf, _stack);
        Argument res_ = new Argument(arr_);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

}
