package code.expressionlanguage.methods.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.opers.util.ConstructorId;

public final class CallConstructor {

    private String fieldName;
    private int childIndex;

    private ConstructorId id;

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

    public ConstructorId getId() {
        return id;
    }

    public void setId(ConstructorId _id) {
        id = _id;
    }

    public Argument getArgument() {
        return argument;
    }

    public void setArgument(Argument _argument) {
        argument = _argument;
    }

}
