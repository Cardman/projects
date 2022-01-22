package code.expressionlanguage.fwd.opers;

import code.util.CustList;

public final class ExecInstancingStdContent {
    private final String fieldName;
    private final int blockIndex;
    private final CustList<ExecNamedFieldContent> namedFields;

    public ExecInstancingStdContent(AnaInstancingStdContent _cont, CustList<ExecNamedFieldContent> _namedFields) {
        fieldName = _cont.getFieldName();
        blockIndex = _cont.getBlockIndex();
        namedFields = _namedFields;
    }
    public String getFieldName() {
        return fieldName;
    }

    public int getBlockIndex() {
        return blockIndex;
    }

    public CustList<ExecNamedFieldContent> getNamedFields() {
        return namedFields;
    }
}
