package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.files.OffsetsBlock;

public final class ExecEnumCaseCondition extends ExecAbstractCaseCondition {

    private final String value;

    public ExecEnumCaseCondition(OffsetsBlock _offset, String _value, int _valueOffset) {
        super(_offset,_valueOffset);
        value = _value;
    }

    public String getValue() {
        return value;
    }
}
