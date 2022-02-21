package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.blocks.AbsLineDeclarator;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.*;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.fwd.opers.AnaVariableContent;
import code.expressionlanguage.options.KeyWords;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class VariableOperation extends LeafOperation implements
        SettableElResult {


    private final AnaVariableContent variableContent;

    private String realVariableName = EMPTY_STRING;

    private String className = EMPTY_STRING;

    private final StringList nameErrors = new StringList();

    private int ref;
    private boolean declare;
    private boolean finalVariable;
    private AbsLineDeclarator lineDeclarator;

    public VariableOperation(int _indexInEl, int _indexChild,
                             MethodOperation _m, OperationsSequence _op, AbsLineDeclarator _decl) {
        this(_indexInEl, _indexChild, _m, _op,EMPTY_STRING,0,-1,false);
        lineDeclarator = _decl;
    }

    public VariableOperation(int _indexInEl, int _indexChild,
                             MethodOperation _m, OperationsSequence _op,
                             String _className, int _ref, int _deep, boolean _finalVariable) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        variableContent = new AnaVariableContent(relativeOff_);
        className = _className;
        ref = _ref;
        variableContent.setDeep(_deep);
        finalVariable = _finalVariable;
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
        if (ElUtil.isDeclaringVariable(this, _page)) {
            declare = true;
            TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(str_, _page);
            variableContent.setVariableName(str_);
            realVariableName = str_;
            if (res_.isError()) {
                _page.setVariableIssue(true);
                FoundErrorInterpret b_ = new FoundErrorInterpret();
                b_.setFileName(_page.getLocalizer().getCurrentFileName());
                b_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //variable name len
                b_.setBuiltError(res_.getMessage());
                _page.getLocalizer().addError(b_);
                nameErrors.add(b_.getBuiltError());
                return;
            }
            ref = _page.getLocalizer().getCurrentLocationIndex();
            String c_ = _page.getCurrentVarSetting();
            KeyWords keyWords_ = _page.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringUtil.quickEq(c_, keyWordVar_)) {
                _page.getVariablesNamesToInfer().add(str_);
            }
            AnaLocalVariable lv_ = new AnaLocalVariable();
            if (StringUtil.quickEq(c_, keyWordVar_)) {
                lv_.setClassName(_page.getAliasObject());
            } else {
                lv_.setClassName(c_);
            }
            lv_.setRef(ref);
            lv_.setConstType(ConstType.LOC_VAR);
            finalVariable = _page.isFinalVariable();
            lv_.setFinalVariable(_page.isFinalVariable());
            _page.getInfosVars().put(str_, lv_);
            _page.getVariablesNames().add(str_);
            setResultClass(new AnaClassArgumentMatching(_page.getCurrentVarSetting(), _page.getPrimitiveTypes()));
            return;
        }
        variableContent.setVariableName(StringExpUtil.skipPrefix(str_));
        realVariableName = str_;
        setResultClass(new AnaClassArgumentMatching(className, _page.getPrimitiveTypes()));
    }

    public AbsLineDeclarator getLineDeclarator() {
        return lineDeclarator;
    }
    public boolean isDeclare() {
        return declare;
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

    public StringList getNameErrors() {
        return nameErrors;
    }

    public int getRef() {
        return ref;
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
