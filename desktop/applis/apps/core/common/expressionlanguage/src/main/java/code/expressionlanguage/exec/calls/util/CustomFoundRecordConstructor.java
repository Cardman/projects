package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.util.CustList;
import code.util.StringMap;

public final class CustomFoundRecordConstructor implements CallingState {

    private final String className;
    private final ExecTypeFunction pair;

    private final StringMap<String> id;
    private final String fieldName;
    private final int childIndex;

    private final CustList<Argument> arguments;

    public CustomFoundRecordConstructor(String _className,
                                        ExecTypeFunction _pair,
                                        StringMap<String> _id,
                                        String _fieldName, int _childIndex,
                                        CustList<Argument> _arguments) {
        className = _className;
        pair = _pair;
        id = _id;
        fieldName = _fieldName;
        childIndex = _childIndex;
        arguments = _arguments;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context) {
        return ExecutingUtil.createRecordInstancing(_context,this);
    }

    public StringMap<String> getId() {
        return id;
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

    public String getClassName() {
        return className;
    }

    public CustList<Argument> getArguments() {
        return arguments;
    }

}
