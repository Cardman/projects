package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendStaticInfoOperation extends RendLeafOperation implements RendCalculableOperation {

    private final String className;
    public RendStaticInfoOperation(ExecOperationContent _content, String _className) {
        super(_content);
        className = _className;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        String classStr_ = _rendStack.formatVarType(className);
        Struct a_ = ClassMetaInfo.getClassMetaInfo(_context,classStr_);
        setSimpleArgument(a_, _nodes, _context, _rendStack);
    }
}
