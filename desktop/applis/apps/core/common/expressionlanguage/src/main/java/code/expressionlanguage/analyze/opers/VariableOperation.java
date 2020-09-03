package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.StringList;

public final class VariableOperation extends LeafOperation implements
        SettableElResult {

    private boolean variable;

    private boolean catString;

    private String variableName = EMPTY_STRING;
    private String realVariableName = EMPTY_STRING;
    private int off;
    private String className = EMPTY_STRING;

    private final StringList nameErrors = new StringList();

    private int ref;
    private boolean declare;
    private boolean finalVariable;
    private int deep;
    public VariableOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        this(_indexInEl, _indexChild, _m, _op,EMPTY_STRING,0,-1,false);
    }

    public VariableOperation(int _indexInEl, int _indexChild,
                             MethodOperation _m, OperationsSequence _op,
                             String _className, int _ref, int _deep, boolean _finalVariable) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        String originalStr_ = _op.getValues().getValue(CustList.FIRST_INDEX);
        off = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        className = _className;
        ref = _ref;
        deep = _deep;
        finalVariable = _finalVariable;
    }

    @Override
    public void setVariable(boolean _variable) {
        variable = _variable;
    }

    @Override
    public void setCatenizeStrings() {
        catString = true;
    }

    @Override
    public void analyze(ContextEl _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _conf);
        if (ElUtil.isDeclaringVariable(this, _conf)) {
            declare = true;
            AnalyzedPageEl page_ = _conf.getAnalyzing();
            TokenErrorMessage res_ = page_.getTokenValidation().isValidSingleToken(str_);
            variableName = str_;
            realVariableName = str_;
            if (res_.isError()) {
                page_.setVariableIssue(true);
                FoundErrorInterpret b_ = new FoundErrorInterpret();
                b_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                b_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //variable name len
                b_.setBuiltError(res_.getMessage());
                _conf.getAnalyzing().getLocalizer().addError(b_);
                nameErrors.add(b_.getBuiltError());
                return;
            }
            ref = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
            String c_ = _conf.getAnalyzing().getCurrentVarSetting();
            KeyWords keyWords_ = _conf.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringList.quickEq(c_, keyWordVar_)) {
                _conf.getAnalyzing().getVariablesNamesToInfer().add(str_);
            }
            AnaLocalVariable lv_ = new AnaLocalVariable();
            if (StringList.quickEq(c_, keyWordVar_)) {
                lv_.setClassName(_conf.getStandards().getAliasObject());
            } else {
                lv_.setClassName(c_);
            }
            lv_.setRef(ref);
            lv_.setConstType(ConstType.LOC_VAR);
            finalVariable = _conf.getAnalyzing().isFinalVariable();
            lv_.setFinalVariable(_conf.getAnalyzing().isFinalVariable());
            page_.getInfosVars().put(str_, lv_);
            page_.getVariablesNames().add(str_);
            setResultClass(new ClassArgumentMatching(_conf.getAnalyzing().getCurrentVarSetting()));
            return;
        }
        variableName = StringExpUtil.skipPrefix(str_);
        realVariableName = str_;
        setResultClass(new ClassArgumentMatching(className));
    }

    public boolean isDeclare() {
        return declare;
    }

    public String getVariableName() {
        return variableName;
    }

    public String getRealVariableName() {
        return realVariableName;
    }

    public boolean isVariable() {
        return variable;
    }

    public boolean isCatString() {
        return catString;
    }

    public int getOff() {
        return off;
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
        return deep;
    }
}
