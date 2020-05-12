package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class MutableLoopVariableOperation extends LeafOperation implements SettableElResult {

    private boolean variable;

    private boolean catString;

    private String variableName = EMPTY_STRING;
    private int off;
    private String className;

    public MutableLoopVariableOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        this(_indexInEl, _indexChild, _m, _op, EMPTY_STRING);
    }

    public MutableLoopVariableOperation(int _indexInEl, int _indexChild,
                                        MethodOperation _m, OperationsSequence _op, String _className) {
        super(_indexInEl, _indexChild, _m, _op);
        off = _op.getOffset();
        className = _className;
    }
    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _conf);
        if (ElUtil.isDeclaringLoopVariable(this, _conf)) {
            AnalyzedPageEl page_ = _conf.getAnalyzing();
            if (_conf.getAnalyzing().containsMutableLoopVar(str_) || _conf.getAnalyzing().containsVar(str_)) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(_conf.getCurrentFileName());
                d_.setIndexFile(page_.getTraceIndex());
                //variable name len
                d_.buildError(_conf.getContextEl().getAnalysisMessages().getBadVariableName(),
                        str_);
                _conf.addError(d_);
                setResultClass(new ClassArgumentMatching(_conf.getAnalyzing().getCurrentVarSetting()));
                return;
            }
            if (!_conf.isValidSingleToken(str_)) {
                FoundErrorInterpret b_ = new FoundErrorInterpret();
                b_.setFileName(_conf.getCurrentFileName());
                b_.setIndexFile(page_.getTraceIndex());
                //variable name len
                b_.buildError(_conf.getContextEl().getAnalysisMessages().getBadVariableName(),
                        str_);
                _conf.addError(b_);
            }
            String c_ = _conf.getAnalyzing().getCurrentVarSetting();
            KeyWords keyWords_ = _conf.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringList.quickEq(c_, keyWordVar_)) {
                _conf.getAnalyzing().putMutableLoopVar(str_);
                _conf.getAnalyzing().getVariablesNamesLoopToInfer().add(str_);
            }
            LoopVariable lv_ = new LoopVariable();
            String indexClassName_ = _conf.getIndexClassName();
            if (StringList.quickEq(c_, keyWordVar_)) {
                lv_.setClassName(_conf.getStandards().getAliasObject());
            } else {
                lv_.setClassName(c_);
            }
            lv_.setIndexClassName(indexClassName_);
            lv_.setFinalVariable(_conf.getAnalyzing().isFinalVariable());
            page_.putMutableLoopVar(str_, lv_);
            page_.getVariablesNames().add(str_);
            variableName = str_;
            setResultClass(new ClassArgumentMatching(_conf.getAnalyzing().getCurrentVarSetting()));
            return;
        }
        variableName = str_;
        setResultClass(new ClassArgumentMatching(className));
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getAnalyzing().getCurrentBlock();
        AssignedVariables vars_ = _conf.getContextEl().getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<StringMap<AssignmentBefore>> assB_ = vars_.getVariablesBefore().getVal(this);
        CustList<StringMap<AssignmentBefore>> assM_ = vars_.getMutableLoopBefore().getVal(this);
        StringMap<AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(this);
        CustList<StringMap<Assignment>> ass_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> assAfM_ = new CustList<StringMap<Assignment>>();
        StringMap<Assignment> assA_ = new StringMap<Assignment>();
        if (ElUtil.isDeclaringLoopVariable(this, _conf)) {
            if (variableName.isEmpty()) {
                analyzeNotBoolAssignmentAfter(_conf);
                return;
            }
            boolean isBool_;
            isBool_ = getResultClass().isBoolType(_conf);
            ass_.addAllElts(AssignmentsUtil.assignAfter(isBool_,assB_));
            assAfM_.addAllElts(AssignmentsUtil.assignAfter(isBool_,assM_));
            AssignmentBefore asBe_ = new AssignmentBefore();
            asBe_.setUnassignedBefore(true);
            assAfM_.last().put(variableName, asBe_.assignAfter(isBool_));
            assA_.putAllMap(AssignmentsUtil.assignAfter(isBool_,assF_));
            vars_.getVariables().put(this, ass_);
            vars_.getMutableLoop().put(this, assAfM_);
            vars_.getFields().put(this, assA_);
            return;
        }

        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf);
        String varName_ = variableName;
        if (getParent() instanceof AffectationOperation && getParent().getFirstChild() == this) {
            varName_ = EMPTY_STRING;
        }
        
        for (StringMap<AssignmentBefore> s: assM_) {
            for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                if (StringList.quickEq(e.getKey(), varName_)) {
                    if (!e.getValue().isAssignedBefore()) {
                        //errors
                        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_conf.getCurrentFileName());
                        un_.setIndexFile(_conf.getCurrentLocationIndex());
                        un_.buildError(_conf.getContextEl().getAnalysisMessages().getFinalField(),
                                varName_);
                        _conf.addError(un_);
                    }
                }
            }
        }
        assAfM_.addAllElts(AssignmentsUtil.assignAfter(isBool_,assM_));
        ass_.addAllElts(AssignmentsUtil.assignAfter(isBool_,assB_));
        assA_.putAllMap(AssignmentsUtil.assignAfter(isBool_,assF_));
        vars_.getVariables().put(this, ass_);
        vars_.getMutableLoop().put(this, assAfM_);
        vars_.getFields().put(this, assA_);
    }

    @Override
    public void setVariable(boolean _variable) {
        variable = _variable;
    }

    @Override
    public void setCatenizeStrings() {
        catString = true;
    }

    public String getVariableName() {
        return variableName;
    }
    public int getOff() {
        return off;
    }

    public boolean isVariable() {
        return variable;
    }

    public boolean isCatString() {
        return catString;
    }
    
}
