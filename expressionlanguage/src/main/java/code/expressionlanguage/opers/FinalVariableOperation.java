package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.ConstType;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.StringList;

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
        String originalStr_ = _op.getValues().getValue(CustList.FIRST_INDEX);
        delta = _op.getDelta();
        off = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        type = _op.getConstType();
    }

    public FinalVariableOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op, String _className) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        String originalStr_ = _op.getValues().getValue(CustList.FIRST_INDEX);
        delta = _op.getDelta();
        off = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        type = _op.getConstType();
        className = _className;
    }

    @Override
    public void analyze(Analyzable _conf) {
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
        LoopVariable loopVar_ = _conf.getVar(str_);
        if (loopVar_ != null) {
            variableName = str_;
            setResultClass(new ClassArgumentMatching(loopVar_.getIndexClassName()));
            return;
        }
        loopVar_ = _conf.getMutableLoopVar(str_);
        if (loopVar_ != null) {
            variableName = str_;
            setResultClass(new ClassArgumentMatching(loopVar_.getIndexClassName()));
            return;
        }
        variableName = str_;
        FoundErrorInterpret und_ = new FoundErrorInterpret();
        und_.setFileName(_conf.getCurrentFileName());
        und_.setIndexFile(_conf.getCurrentLocationIndex());
        //variable name len
        und_.buildError(_conf.getContextEl().getAnalysisMessages().getUndefinedVariable(),
                variableName);
        _conf.addError(und_);
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
