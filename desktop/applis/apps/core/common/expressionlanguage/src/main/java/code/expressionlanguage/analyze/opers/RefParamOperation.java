package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fwd.opers.AnaVariableContent;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class RefParamOperation extends LeafOperation implements
        SettableElResult{

    private final AnaVariableContent variableContent;

    private String realVariableName = EMPTY_STRING;

    private final String className;

    private final int ref;

    public RefParamOperation(int _indexInEl, int _indexChild, String _className, int _ref, MethodOperation _m, OperationsSequence _op, int _deep) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        String originalStr_ = _op.getValues().getValue(IndexConstants.FIRST_INDEX);
        variableContent = new AnaVariableContent(StringUtil.getFirstPrintableCharIndex(originalStr_)+relativeOff_);
        className = _className;
        ref = _ref;
        variableContent.setDeep(_deep);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        variableContent.setVariableName(StringExpUtil.skipPrefix(str_));
        realVariableName = str_;
        setResultClass(new AnaClassArgumentMatching(className, _page.getPrimitiveTypes()));
    }

    @Override
    public void setVariable(boolean _variable) {
        variableContent.setVariable(_variable);
    }

    @Override
    public void setCatenizeStrings() {
        variableContent.setCatString(true);
    }

    public int getRef() {
        return ref;
    }

    public int getOff() {
        return variableContent.getOff();
    }
    public String getRealVariableName() {
        return realVariableName;
    }

    public AnaVariableContent getVariableContent() {
        return variableContent;
    }
}
