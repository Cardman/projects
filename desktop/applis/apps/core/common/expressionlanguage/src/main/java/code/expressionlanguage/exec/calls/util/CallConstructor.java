package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.functionid.ConstructorId;

public final class CallConstructor {

    private String fieldName;
    private int childIndex;

    private ExecNamedFunctionBlock id;

    private Argument argument;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String _fieldName) {
        fieldName = _fieldName;
    }

    public int getChildIndex() {
        return childIndex;
    }

    public void setChildIndex(int _childIndex) {
        childIndex = _childIndex;
    }

    public ExecNamedFunctionBlock getId() {
        return id;
    }

    public void setId(ExecNamedFunctionBlock _id) {
        id = _id;
    }

    public Argument getArgument() {
        return argument;
    }

    public void setArgument(Argument _argument) {
        argument = _argument;
    }

}
