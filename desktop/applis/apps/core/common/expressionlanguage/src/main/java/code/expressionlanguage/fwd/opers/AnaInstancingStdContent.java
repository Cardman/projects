package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.util.CustList;

public final class AnaInstancingStdContent {
    private String fieldName = "";
    private int blockIndex = -1;
    private final CustList<AnaNamedFieldContent> namedFields = new CustList<AnaNamedFieldContent>();
    private final CustList<AnaFormattedRootBlock> sups = new CustList<AnaFormattedRootBlock>();

    public String getFieldName() {
        return fieldName;
    }

    public void setBlockIndex(int _blockIndex) {
        this.blockIndex = _blockIndex;
    }

    public int getBlockIndex() {
        return blockIndex;
    }

    public void setFieldName(String _fieldName) {
        this.fieldName = _fieldName;
    }

    public CustList<AnaNamedFieldContent> getNamedFields() {
        return namedFields;
    }

    public CustList<AnaFormattedRootBlock> getSups() {
        return sups;
    }
}
