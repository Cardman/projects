package code.bean.nat.analyze.opers;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.bean.nat.fwd.opers.NatAnaVariableContent;
import code.util.core.IndexConstants;

public final class FinalVariableNatOperation extends LeafNatOperation {

    private final NatAnaVariableContent variableContent;
    private final String className;
    private final boolean varIndex;

    public FinalVariableNatOperation(int _indexInEl, int _indexChild,
                                     MethodNatOperation _m, NatOperationsSequence _op, boolean _varIndex) {
        this(_indexInEl, _indexChild, _m, _op,EMPTY_STRING, _varIndex);
    }

    public FinalVariableNatOperation(int _indexInEl, int _indexChild,
                                     MethodNatOperation _m, NatOperationsSequence _op, String _className, boolean _varIndex) {
        super(_indexInEl, _indexChild, _m, _op);
        variableContent = new NatAnaVariableContent();
        className = _className;
        varIndex = _varIndex;
    }

    @Override
    public void analyze(NatAnalyzedCode _page) {
        NatOperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValNat().getValue(IndexConstants.FIRST_INDEX);
        String str_ = originalStr_.trim();
        if (!className.isEmpty()) {
            variableContent.setVariableName(str_);
            setResultClass(className);
            return;
        }
        variableContent.setVariableName(str_);
        setResultClass("$int");
    }

    public boolean isVarIndex() {
        return varIndex;
    }

    public NatAnaVariableContent getVariableContent() {
        return variableContent;
    }
}
