package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecNamedFieldContent;
import code.util.CustList;

public final class CustomFoundRecordConstructor implements CallingState {

    private final Argument argument;
    private final ExecFormattedRootBlock className;
    private final ExecTypeFunction pair;

    private final CustList<ExecNamedFieldContent> namedFields;
    private final String fieldName;
    private final int childIndex;

    private final CustList<Argument> arguments;

    public CustomFoundRecordConstructor(Argument _argument,ExecFormattedRootBlock _className,
                                        ExecTypeFunction _pair,
                                        CustList<ExecNamedFieldContent> _named,
                                        CustList<Argument> _arguments) {
        this(_argument,_className,_pair, _named,"",-1,_arguments);
    }

    public CustomFoundRecordConstructor(Argument _argument,ExecFormattedRootBlock _className,
                                        ExecTypeFunction _pair,
                                        CustList<ExecNamedFieldContent> _named,
                                        String _fieldName, int _childIndex,
                                        CustList<Argument> _arguments) {
        argument = _argument;
        className = _className;
        pair = _pair;
        namedFields = _named;
        fieldName = _fieldName;
        childIndex = _childIndex;
        arguments = _arguments;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stack) {
        return ExecutingUtil.createRecordInstancing(_context,this);
    }

    public Argument getArgument() {
        return argument;
    }

    public CustList<ExecNamedFieldContent> getNamedFields() {
        return namedFields;
    }

    public int getChildIndex() {
        return childIndex;
    }

    public String getFieldName() {
        return fieldName;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

    public ExecFormattedRootBlock getClassName() {
        return className;
    }

    public CustList<Argument> getArguments() {
        return arguments;
    }

}
