package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AnnotationTypeInfo;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.util.CustList;
import code.util.StringMap;

public final class CustomFoundAnnotation implements CallingState {

    private final ExecFormattedRootBlock className;
    private final ExecRootBlock type;

    private final StringMap<AnnotationTypeInfo> id;

    private final CustList<Argument> arguments;

    public CustomFoundAnnotation(ExecFormattedRootBlock _className,ExecRootBlock _type,
                                 StringMap<AnnotationTypeInfo> _id,
                                 CustList<Argument> _arguments) {
        className = _className;
        type = _type;
        id = _id;
        arguments = _arguments;
    }
    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return ExecutingUtil.createAnnotation(_context,getClassName(),getType(), getId(), getArguments());
    }
    public ExecFormattedRootBlock getClassName() {
        return className;
    }

    public ExecRootBlock getType() {
        return type;
    }

    public StringMap<AnnotationTypeInfo> getId() {
        return id;
    }

    public CustList<Argument> getArguments() {
        return arguments;
    }
}
