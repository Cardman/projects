package code.expressionlanguage.exec.blocks;

public final class ExecEnumCaseCondition extends ExecAbstractCaseCondition {

    private final String value;

    public ExecEnumCaseCondition(String _value, int _valueOffset, int _offsetTrim) {
        super(_valueOffset, _offsetTrim);
        value = _value;
    }

    public String getValue() {
        return value;
    }
}
