package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ConstType;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.UndefinedVariableError;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.LongStruct;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class FinalVariableOperation extends LeafOperation {

    private String variableName = EMPTY_STRING;

    public FinalVariableOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        if (op_.getConstType() == ConstType.PARAM) {
            variableName = str_;
            LocalVariable locVar_ = _conf.getParameters().getVal(variableName);
            if (locVar_ != null) {
                String paramType_ = locVar_.getClassName();
                if (paramType_.endsWith(VARARG_SUFFIX)) {
                    paramType_ = StringList.replace(paramType_, VARARG_SUFFIX, EMPTY_STRING);
                    paramType_ = PrimitiveTypeUtil.getPrettyArrayType(paramType_);
                }
                setResultClass(new ClassArgumentMatching(paramType_));
                return;
            }
            UndefinedVariableError und_ = new UndefinedVariableError();
            und_.setId(variableName);
            und_.setFileName(_conf.getCurrentFileName());
            und_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(und_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (op_.getConstType() == ConstType.CATCH_VAR) {
            variableName = str_;
            LocalVariable locVar_ = _conf.getCatchVar(variableName);
            if (locVar_ != null) {
                setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
                return;
            }
            UndefinedVariableError und_ = new UndefinedVariableError();
            und_.setId(variableName);
            und_.setFileName(_conf.getCurrentFileName());
            und_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(und_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (op_.getConstType() == ConstType.LOOP_INDEX) {
            variableName = str_;
            LoopVariable locVar_ = _conf.getVar(variableName);
            if (locVar_ != null) {
                setResultClass(new ClassArgumentMatching(locVar_.getIndexClassName()));
                return;
            }
            UndefinedVariableError und_ = new UndefinedVariableError();
            und_.setId(variableName);
            und_.setFileName(_conf.getCurrentFileName());
            und_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(und_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        variableName = str_;
        LoopVariable locVar_ = _conf.getVar(variableName);
        if (locVar_ != null) {
            setResultClass(new ClassArgumentMatching(locVar_.getClassName()));
            return;
        }
        UndefinedVariableError und_ = new UndefinedVariableError();
        und_.setId(variableName);
        und_.setFileName(_conf.getCurrentFileName());
        und_.setRc(_conf.getCurrentLocation());
        _conf.getClasses().getErrorsDet().add(und_);
        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<StringMap<AssignmentBefore>> assB_ = vars_.getVariablesBefore().getVal(this);
        ObjectMap<ClassField,AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(this);
        CustList<StringMap<Assignment>> ass_ = new CustList<StringMap<Assignment>>();
        ObjectMap<ClassField,Assignment> assA_ = new ObjectMap<ClassField,Assignment>();

        LgNames lgNames_ = _conf.getStandards();
        String aliasBoolean_ = lgNames_.getAliasBoolean();
        boolean isBool_;
        isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(aliasBoolean_, getResultClass().getName(), _conf);
        
        for (StringMap<AssignmentBefore> s: assB_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                AssignmentBefore bf_ = e.getValue();
                sm_.put(e.getKey(), bf_.assignAfter(isBool_));
            }
            ass_.add(sm_);
        }
        for (EntryCust<ClassField, AssignmentBefore> e: assF_.entryList()) {
            AssignmentBefore bf_ = e.getValue();
            assA_.put(e.getKey(), bf_.assignAfter(isBool_));
        }
        vars_.getVariables().put(this, ass_);
        vars_.getFields().put(this, assA_);
    }

    @Override
    public void tryCalculateNode(ContextEl _conf,
            EqList<SortedClassField> _list, SortedClassField _current) {
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        ArgumentCall argres_ = getCommonArgument(_conf);
        if (_conf.getException() != null) {
            return;
        }
        Argument arg_ = argres_.getArgument();
        if (arg_ == null) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        ArgumentCall argres_ = getCommonArgument(_conf);
        Argument arg_ = argres_.getArgument();
        if (_conf.getException() != null) {
            return arg_;
        }
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }
    ArgumentCall getCommonArgument(ExecutableCode _conf) {
        Argument a_ = new Argument();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        PageEl ip_ = _conf.getOperationPageEl();
        OperationsSequence op_ = getOperations();
        if (op_.getConstType() == ConstType.PARAM) {
            LocalVariable locVar_ = ip_.getParameters().getVal(variableName);
            a_ = new Argument();
            a_.setStruct(locVar_.getStruct());
            return ArgumentCall.newArgument(a_);
        }
        if (op_.getConstType() == ConstType.CATCH_VAR) {
            LocalVariable locVar_ = ip_.getCatchVars().getVal(variableName);
            a_ = new Argument();
            a_.setStruct(locVar_.getStruct());
            return ArgumentCall.newArgument(a_);
        }
        if (op_.getConstType() == ConstType.LOOP_INDEX) {
            LoopVariable locVar_ = ip_.getVars().getVal(variableName);
            a_ = new Argument();
            ClassArgumentMatching clArg_ = new ClassArgumentMatching(locVar_.getIndexClassName());
            a_.setStruct(PrimitiveTypeUtil.convertObject(clArg_, new LongStruct(locVar_.getIndex()), _conf));
            return ArgumentCall.newArgument(a_);
        }
        LoopVariable locVar_ = ip_.getVars().getVal(variableName);
        a_ = new Argument();
        a_.setStruct(locVar_.getStruct());
        return ArgumentCall.newArgument(a_);
    }
    @Override
    public boolean isCalculated(IdMap<OperationNode, ArgumentsPair> _nodes) {
        OperationNode op_ = this;
        while (op_ != null) {
            if (_nodes.getVal(op_).getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public boolean isCalculated() {
        OperationNode op_ = this;
        while (op_ != null) {
            if (op_.getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

}
