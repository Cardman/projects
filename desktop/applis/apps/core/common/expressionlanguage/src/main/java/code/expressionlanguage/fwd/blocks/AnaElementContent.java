package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.analyze.blocks.EnumBlock;
import code.expressionlanguage.analyze.files.OffsetStringInfo;

public final class AnaElementContent {

    private final String fieldName;

    private final int fieldNameOffest;

    private final String value;

    private final int valueOffest;

    private final String tempClass;

    private final int tempClassOffset;
    private final EnumBlock parentEnum;
    public AnaElementContent(EnumBlock _m, OffsetStringInfo _fieldName,
                             OffsetStringInfo _type,
                             OffsetStringInfo _value) {
        parentEnum = _m;
        fieldName = _fieldName.getInfo();
        fieldNameOffest = _fieldName.getOffset();
        tempClass = _type.getInfo();
        tempClassOffset = _type.getOffset();
        value = _value.getInfo();
        valueOffest = _value.getOffset();
    }

    public int diffTr(String _newKeyWord) {
        return -getFieldName().length() - 1 - _newKeyWord.length() - 1;
    }
    public String getFieldName() {
        return fieldName;
    }

    public int getFieldNameOffest() {
        return fieldNameOffest;
    }

    public String getValue() {
        return value;
    }

    public int getValueOffest() {
        return valueOffest;
    }

    public String getTempClass() {
        return tempClass;
    }

    public int getTempClassOffset() {
        return tempClassOffset;
    }

    public EnumBlock getParentEnum() {
        return parentEnum;
    }
}
