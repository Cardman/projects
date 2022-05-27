package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.analyze.blocks.EnumBlock;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.util.CustList;
import code.util.Ints;

public final class AnaElementContent {

    private final String fieldName;

    private final int fieldNameOffest;

    private final String value;

    private final int valueOffest;

    private final String tempClass;

    private final int tempClassOffset;
    private final EnumBlock parentEnum;
    private int trOffset;
    private final Ints lastBadIndexes = new Ints();

    private boolean koTy;
    private final CustList<AnaResultPartType> partOffsets = new CustList<AnaResultPartType>();

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

    public int getTrOffset() {
        return trOffset;
    }

    public void setTrOffset(int _tr) {
        this.trOffset = _tr;
    }

    public Ints getLastBadIndexes() {
        return lastBadIndexes;
    }

    public boolean isKoTy() {
        return koTy;
    }

    public void setKoTy(boolean _ko) {
        this.koTy = _ko;
    }

    public CustList<AnaResultPartType> getPartOffsets() {
        return partOffsets;
    }
}
