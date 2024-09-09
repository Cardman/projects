package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendDefaultValueOperation extends RendLeafOperation implements RendCalculableOperation {

    private final String className;
    public RendDefaultValueOperation(ExecOperationContent _content, String _className) {
        super(_content);
        className = _className;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        String classStr_ = _rendStack.formatVarType(className);
        Struct a_ = ExecClassArgumentMatching.defaultValue(classStr_,_context);
        setSimpleArgument(a_, _nodes, _context, _rendStack);
    }
}
