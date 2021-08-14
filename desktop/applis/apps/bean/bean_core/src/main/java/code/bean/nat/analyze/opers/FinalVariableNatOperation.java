package code.bean.nat.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.ConstType;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.bean.nat.fwd.opers.NatAnaVariableContent;
import code.util.core.IndexConstants;

public final class FinalVariableNatOperation extends LeafNatOperation {

    private final NatAnaVariableContent variableContent;
    private final ConstType type;
    private final String className;

    public FinalVariableNatOperation(int _indexInEl, int _indexChild,
                                     MethodNatOperation _m, NatOperationsSequence _op) {
        this(_indexInEl, _indexChild, _m, _op,EMPTY_STRING);
    }

    public FinalVariableNatOperation(int _indexInEl, int _indexChild,
                                     MethodNatOperation _m, NatOperationsSequence _op, String _className) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        variableContent = new NatAnaVariableContent(relativeOff_);
        type = _op.getConstType();
        className = _className;
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        NatOperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ variableContent.getOff(), _page);
        if (!className.isEmpty()) {
            variableContent.setVariableName(str_);
            setResultClass(className);
            return;
        }
        variableContent.setVariableName(str_);
        setResultClass("$int");
    }

    public ConstType getType() {
        return type;
    }

    public NatAnaVariableContent getVariableContent() {
        return variableContent;
    }
}
