package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.fwd.opers.AnaVariableContent;
import code.util.core.IndexConstants;

public final class FinalVariableOperation extends LeafOperation {

    private final AnaVariableContent variableContent;
    private String realVariableName = EMPTY_STRING;
    private final int delta;
    private int afterOper;
    private int ref;

    public FinalVariableOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        variableContent = new AnaVariableContent(relativeOff_);
        delta = _op.getDelta();
        ref = 0;
        variableContent.setDeep(-1);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
        afterOper = StringExpUtil.getOffset(originalStr_);
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ variableContent.getOff(), _page);
        int deep_ = -1;
        String shortStr_ = str_;
        AnaLoopVariable val_ = _page.getLoopsVars().getVal(str_);
        if (val_ == null) {
            deep_ = StringExpUtil.countPrefix(str_, '#');
            shortStr_ = StringExpUtil.skipPrefix(str_);
            AnaLoopVariable loc_ = _page.getLoopsVars().getVal(shortStr_);
            if (loc_ != null) {
                deep_--;
            }
            val_ = _page.getCache().getLoopVar(shortStr_,deep_);
        }
        if (val_ != null) {
            variableContent.setDeep(deep_);
            ref = val_.getRef();
            variableContent.setVariableName(shortStr_);
            realVariableName = str_;
            setResultClass(new AnaClassArgumentMatching(val_.getIndexClassName(), _page.getPrimitiveTypes()));
            return;
        }
        variableContent.setVariableName(str_);
        realVariableName = str_;
        FoundErrorInterpret und_ = new FoundErrorInterpret();
        und_.setFile(_page.getCurrentFile());
        und_.setIndexFile(_page);
        //variable name len
        und_.buildError(_page.getAnalysisMessages().getUndefinedVariable(),
                variableContent.getVariableName());
        _page.getLocalizer().addError(und_);
        addErr(und_.getBuiltError());
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
    }

    public String getRealVariableName() {
        return realVariableName;
    }

    public int getOff() {
        return variableContent.getOff();
    }

    public int getRef() {
        return ref;
    }

    public int getDelta() {
        return delta;
    }

    public int getAfterOper() {
        return afterOper;
    }

    public AnaVariableContent getVariableContent() {
        return variableContent;
    }
}
