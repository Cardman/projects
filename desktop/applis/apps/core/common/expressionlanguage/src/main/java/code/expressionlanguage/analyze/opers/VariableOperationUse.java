package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.FoundVariable;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fwd.opers.AnaVariableContent;
import code.util.core.IndexConstants;

public final class VariableOperationUse extends LeafOperation implements
        SettableElResult {

    private final AnaVariableContent variableContent;
    private final boolean keyWord;

    private String realVariableName = EMPTY_STRING;

    private String className = EMPTY_STRING;

    private final int ref;
    private final boolean finalVariable;

    public VariableOperationUse(int _indexInEl, int _indexChild,
                                MethodOperation _m, OperationsSequence _op,
                                FoundVariable _foundVar) {
        super(_indexInEl, _indexChild, _m, _op);
        AnaLocalVariable val_ = _foundVar.getVal();
        int relativeOff_ = _op.getOffset();
        variableContent = new AnaVariableContent(relativeOff_);
        className = val_.getClassName();
        ref = val_.getRef();
        variableContent.setDeep(_foundVar.getDeep());
        finalVariable = val_.isFinalVariable();
        keyWord = val_.isKeyWord();
    }

    @Override
    public void setVariable(boolean _variable) {
        variableContent.setVariable(_variable);
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

    public String getVariableName() {
        return variableContent.getVariableName();
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

    public boolean isKeyWord() {
        return keyWord;
    }

    public boolean isFinalVariable() {
        return finalVariable;
    }

    public int getDeep() {
        return variableContent.getDeep();
    }

    public AnaVariableContent getVariableContent() {
        return variableContent;
    }
}
