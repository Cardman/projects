package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.analyze.blocks.EnumBlock;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.types.AnaResultPartTypeDtoInt;
import code.util.CustList;
import code.util.Ints;
import code.util.core.StringUtil;

public final class AnaElementContent {
    private static final String PAR_LEFT = "(";
    private static final String PAR_RIGHT = ")";
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
    private final CustList<AnaResultPartTypeDtoInt> partOffsets = new CustList<AnaResultPartTypeDtoInt>();
    private int infoBlockNb;
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
    public String buildVirtualCreate(String _newKeyWord) {
        return StringUtil.concat(getFieldName(),"=", _newKeyWord, PAR_LEFT, getValue(), PAR_RIGHT);
    }
    public int retrieveTr(String _newKeyWord) {
        return getValueOffest() - getFieldNameOffest() -getFieldName().length() - 1 - _newKeyWord.length() - 1;
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

    public CustList<AnaResultPartTypeDtoInt> getPartOffsets() {
        return partOffsets;
    }

    public int getInfoBlockNb() {
        return infoBlockNb;
    }

    public void setInfoBlockNb(int _i) {
        this.infoBlockNb = _i;
    }
}
