package code.expressionlanguage.fwd.opers;

import code.util.StringMap;

public final class AnaInstancingStdContent {
    private String fieldName = "";
    private int blockIndex = -1;
    private final StringMap<String> infos = new StringMap<String>();

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

    public StringMap<String> getInfos() {
        return infos;
    }
}
