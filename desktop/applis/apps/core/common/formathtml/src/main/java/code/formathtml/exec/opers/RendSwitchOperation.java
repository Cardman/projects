package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendSwitchOperation extends RendSettableCallFctOperation implements RendCalculableOperation  {
    private final ExecAbstractSwitchMethod switchMethod;
    public RendSwitchOperation(ExecOperationContent _content, ExecArrContent _arrContent, ExecAbstractSwitchMethod _sw) {
        super(_content, false, _arrContent);
        switchMethod = _sw;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        RendDynOperationNode o_ = getFirstChild();
        Struct value_ = getArgument(_nodes,o_);
        ExecTemplates.okArgsSetSwCall(switchMethod,_context,_rendStack.getStackCall(),value_, _rendStack.getLastPage().getGlobalClass(), _rendStack.getLastPage().getContent());
        ArgumentWrapper argres_ = processCall(NullStruct.NULL_VALUE,_context,_rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }
}
