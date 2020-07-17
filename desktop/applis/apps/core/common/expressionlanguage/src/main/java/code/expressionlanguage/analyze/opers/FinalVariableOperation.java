package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;

public final class FinalVariableOperation extends LeafOperation {

    private String variableName = EMPTY_STRING;
    private int off;
    private int delta;
    private ConstType type;
    private String className = EMPTY_STRING;
    private int ref;

    public FinalVariableOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        delta = _op.getDelta();
        off = relativeOff_;
        type = _op.getConstType();
    }

    public FinalVariableOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op, String _className, int _ref) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        delta = _op.getDelta();
        off = relativeOff_;
        type = _op.getConstType();
        className = _className;
        ref = _ref;
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
        AnaLoopVariable val_ = _conf.getAnalyzing().getLoopsVars().getVal(str_);
        if (val_ != null) {
            ref = val_.getRef();
            variableName = str_;
            setResultClass(new ClassArgumentMatching(val_.getIndexClassName()));
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
        getErrs().add(und_.getBuiltError());
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

    public int getRef() {
        return ref;
    }

    public int getDelta() {
        return delta;
    }
}
