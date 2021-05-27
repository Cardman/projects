package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.util.*;

public abstract class AnnotationInstanceOperation extends InvokingOperation implements PreAnalyzableOperation {

    private final CustList<PartOffset> partOffsetsErr = new CustList<PartOffset>();
    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private final CustList<PartOffset> partOffsetsErrPar = new CustList<PartOffset>();

    protected AnnotationInstanceOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public CustList<PartOffset> getPartOffsetsErr() {
        return partOffsetsErr;
    }

    public CustList<PartOffset> getPartOffsetsErrPar() {
        return partOffsetsErrPar;
    }

}
