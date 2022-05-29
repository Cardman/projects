package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.instr.OperationsSequence;

public abstract class AnnotationInstanceOperation extends InvokingOperation implements PreAnalyzableOperation {

    private InfoErrorDto partOffsetsErr = new InfoErrorDto("");
    private InfoErrorDto partOffsetsErrPar = new InfoErrorDto("");

    protected AnnotationInstanceOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    public InfoErrorDto getPartOffsetsErr() {
        return partOffsetsErr;
    }

    public void setPartOffsetsErr(InfoErrorDto _partOffsetsErr) {
        this.partOffsetsErr = _partOffsetsErr;
    }

    public InfoErrorDto getPartOffsetsErrPar() {
        return partOffsetsErrPar;
    }

    public void setPartOffsetsErrPar(InfoErrorDto _partOffsetsErrPar) {
        partOffsetsErrPar = _partOffsetsErrPar;
    }
}
