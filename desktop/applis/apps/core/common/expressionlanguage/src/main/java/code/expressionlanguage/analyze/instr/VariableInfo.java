package code.expressionlanguage.analyze.instr;


import code.expressionlanguage.analyze.blocks.FieldBlock;
import code.expressionlanguage.analyze.blocks.InfoBlock;
import code.expressionlanguage.common.ConstType;
import code.util.StringList;

public final class VariableInfo {

    private String name;

    private ConstType kind;

    private int firstChar;

    private int lastChar;

    private InfoBlock declaringField;
    private StringList errors = new StringList();

    private boolean affect;

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public ConstType getKind() {
        return kind;
    }

    public void setKind(ConstType _kind) {
        kind = _kind;
    }

    public int getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(int _firstChar) {
        firstChar = _firstChar;
    }

    public int getLastChar() {
        return lastChar;
    }

    public void setLastChar(int _lastChar) {
        lastChar = _lastChar;
    }

    public InfoBlock getDeclaringField() {
        return declaringField;
    }

    public void declaringField(int _fieldNumber,InfoBlock _declaringBlock) {
        this.declaringField = _declaringBlock;
        if (_declaringBlock instanceof FieldBlock && ((FieldBlock)_declaringBlock).getNameErrorsFields().isValidIndex(_fieldNumber)) {
            errors = ((FieldBlock)_declaringBlock).getNameErrorsFields().get(_fieldNumber);
        }
    }

    public StringList getErrors() {
        return errors;
    }

    public boolean isAffect() {
        return affect;
    }

    public void setAffect(boolean _affect) {
        this.affect = _affect;
    }
}
