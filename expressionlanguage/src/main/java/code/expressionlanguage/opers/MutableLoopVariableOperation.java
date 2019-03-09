package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.BadVariableName;
import code.expressionlanguage.errors.custom.DuplicateVariable;
import code.expressionlanguage.errors.custom.UnexpectedOperationAffect;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class MutableLoopVariableOperation extends VariableLeafOperation implements SettableElResult {

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
        int relativeOff_ = _op.getOffset();
        String originalStr_ = _op.getValues().getValue(CustList.FIRST_INDEX);
        off = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        className = _className;
    }
    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        if (ElUtil.isDeclaringLoopVariable(this, _conf)) {
            AnalyzedPageEl page_ = _conf.getAnalyzing();
            if (_conf.containsMutableLoopVar(str_) || _conf.getAnalyzing().containsVar(str_)) {
                DuplicateVariable d_ = new DuplicateVariable();
                d_.setId(str_);
                d_.setFileName(page_.getCurrentBlock().getFile().getFileName());
                d_.setIndexFile(page_.getTraceIndex());
                _conf.getClasses().addError(d_);
                setResultClass(new ClassArgumentMatching(_conf.getCurrentVarSetting()));
                return;
            }
            if (!_conf.isValidSingleToken(str_)) {
                BadVariableName b_ = new BadVariableName();
                b_.setFileName(page_.getCurrentBlock().getFile().getFileName());
                b_.setIndexFile(page_.getTraceIndex());
                b_.setVarName(str_);
                _conf.getClasses().addError(b_);
            }
            String c_ = _conf.getCurrentVarSetting();
            KeyWords keyWords_ = _conf.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringList.quickEq(c_, keyWordVar_)) {
                if (!(getParent() instanceof AffectationOperation)) {
                    UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                    un_.setFileName(_conf.getCurrentFileName());
                    un_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(un_);
                }
                _conf.putMutableLoopVar(str_);
            }
            LoopVariable lv_ = new LoopVariable();
            if (StringList.quickEq(c_, keyWordVar_)) {
                lv_.setClassName(_conf.getStandards().getAliasObject());
            } else {
                lv_.setClassName(c_);
            }
            lv_.setFinalVariable(_conf.isFinalVariable());
            _conf.putMutableLoopVar(str_, lv_);
            _conf.getVariablesNames().add(str_);
            variableName = str_;
            setResultClass(new ClassArgumentMatching(_conf.getCurrentVarSetting()));
            return;
        }
        variableName = str_;
        setResultClass(new ClassArgumentMatching(className));
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
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
                        UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                        un_.setFileName(_conf.getCurrentFileName());
                        un_.setIndexFile(_conf.getCurrentLocationIndex());
                        _conf.getClasses().addError(un_);
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
