package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrayInstancingContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.ArrayStruct;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;

public final class RendArrayElementOperation extends
        RendAbstractArrayInstancingOperation {

    public RendArrayElementOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecArrayInstancingContent _arrayInstancingContent) {
        super(_content, _intermediateDottedOperation, _arrayInstancingContent);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        int off_ = getMethodName();
        setRelOffsetPossibleLastPage(off_, _rendStack);
        String className_ = _rendStack.formatVarType(getClassName());
        ArrayStruct arr_ = ArrayStruct.instance(StringExpUtil.getPrettyArrayType(className_), arguments_);
        ExecArrayTemplates.checkedElements(arr_, _context, _rendStack.getStackCall());
        Argument res_ = new Argument(arr_);
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }

}
