package code.expressionlanguage.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;

public final class FinalVariableOperation extends LeafOperation {

    private String variableName = EMPTY_STRING;
    private int off;
    private int delta;
    private ConstType type;
    private String className = EMPTY_STRING;

    public FinalVariableOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        delta = _op.getDelta();
        off = relativeOff_;
        type = _op.getConstType();
    }

    public FinalVariableOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op, String _className) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        delta = _op.getDelta();
        off = relativeOff_;
        type = _op.getConstType();
        className = _className;
    }

    @Override
    public void analyze(ContextEl _conf) {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off, _conf);
        LgNames stds_ = _conf.getStandards();
        if (!className.isEmpty()) {
            variableName = str_;
            setResultClass(new ClassArgumentMatching(className));
            return;
        }
        AnaLoopVariable loopVar_ = _conf.getAnalyzing().getVar(str_);
        if (loopVar_ != null) {
            variableName = str_;
            setResultClass(new ClassArgumentMatching(loopVar_.getIndexClassName()));
            return;
        }
        loopVar_ = _conf.getAnalyzing().getMutableLoopVar(str_);
        if (loopVar_ != null) {
            variableName = str_;
            setResultClass(new ClassArgumentMatching(loopVar_.getIndexClassName()));
            return;
        }
        variableName = str_;
        FoundErrorInterpret und_ = new FoundErrorInterpret();
        und_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
        und_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
        //variable name len
        und_.buildError(_conf.getAnalysisMessages().getUndefinedVariable(),
                variableName);
        _conf.getAnalyzing().getLocalizer().addError(und_);
        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
    }

    public String getVariableName() {
        return variableName;
    }

    public ConstType getType() {
        return type;
    }

    public int getOff() {
        return off;
    }

    public int getDelta() {
        return delta;
    }
}
