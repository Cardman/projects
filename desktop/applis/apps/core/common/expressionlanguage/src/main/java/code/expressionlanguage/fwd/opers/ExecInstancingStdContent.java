package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.util.CustList;

public final class ExecInstancingStdContent {
    private final String fieldName;
    private final int blockIndex;
    private final CustList<ExecNamedFieldContent> namedFields;
    private final CustList<ExecFormattedRootBlock> supInts;

    public ExecInstancingStdContent(AnaInstancingStdContent _cont, CustList<ExecNamedFieldContent> _namedFields, CustList<ExecFormattedRootBlock> _sup) {
        fieldName = _cont.getFieldName();
        blockIndex = _cont.getBlockIndex();
        namedFields = _namedFields;
        supInts = _sup;
        //FetchMemberUtil.fwdFormatType
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

    public CustList<ExecFormattedRootBlock> getSupInts() {
        return supInts;
    }
}
