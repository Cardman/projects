package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ConstType;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.EmptyPartError;
import code.expressionlanguage.methods.util.UndefinedVariableError;
import code.expressionlanguage.methods.util.UnexpectedOperationAffect;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LocalVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class VariableOperation extends LeafOperation implements
        SettableElResult {

    private boolean variable;

    private boolean excVar;

    private boolean catString;

    private String variableName = EMPTY_STRING;

    public VariableOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
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
    public boolean resultCanBeSet() {
        return variable;
    }

    @Override
    public void analyze(Analyzable _conf, String _fieldName) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        String argClName_;
        if (str_.isEmpty()) {
            EmptyPartError emptyPart_ = new EmptyPartError();
            emptyPart_.setFileName(_conf.getCurrentFileName());
            emptyPart_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(emptyPart_);
            argClName_ = _conf.getStandards().getAliasObject();
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        LgNames stds_ = _conf.getStandards();
        String stringType_;
        stringType_ = stds_.getAliasString();
        str_ = StringList.removeAllSpaces(str_);
        if (getParent() instanceof AffectationOperation && getParent().getParent() == null && _conf.isMerged()) {
            excVar = true;
        }
        variableName = str_;
        if (excVar) {
            setResultClass(new ClassArgumentMatching(_conf.getCurrentVarSetting()));
            return;
        }
        LocalVariable locVar_ = _conf.getLocalVar(variableName);
        if (locVar_ != null) {
            String c_ = locVar_.getClassName();
            if (StringList.quickEq(c_, stringType_)) {
                catString = true;
            }
            setResultClass(new ClassArgumentMatching(c_));
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
        String varName_ = EMPTY_STRING;
        OperationsSequence op_ = getOperations();
        if (op_.getConstType() == ConstType.LOC_VAR) {
            String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
            varName_ = originalStr_.trim();
        }
        if (getParent() instanceof AffectationOperation && getParent().getFirstChild() == this) {
            AffectationOperation aff_ = (AffectationOperation) getParent();
            if (StringList.quickEq(aff_.getOperations().getOperators().firstValue(), "=")) {
                varName_ = EMPTY_STRING;
            }
        }
        
        for (StringMap<AssignmentBefore> s: assB_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                if (StringList.quickEq(e.getKey(), varName_)) {
                    if (!e.getValue().isAssignedBefore()) {
                        //errors
                        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
                        UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                        un_.setFileName(_conf.getCurrentFileName());
                        un_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().getErrorsDet().add(un_);
                    }
                }
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
        if (resultCanBeSet()) {
            return ArgumentCall.newArgument(Argument.createVoid());
        }
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
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

    @Override
    public Argument calculateSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post) {
        Argument arg_ = getCommonSetting(_conf, _op, _post);
        if (_conf.getException() == null) {
            setSimpleArgument(arg_, _conf, _nodes);
        }
        return arg_;
    }

    @Override
    public void calculateSetting(ExecutableCode _conf, String _op, boolean _post) {
        Argument arg_ = getCommonSetting(_conf, _op, _post);
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }
    Argument getCommonSetting(ExecutableCode _conf, String _op, boolean _post) {
        PageEl ip_ = _conf.getOperationPageEl();
        LgNames stds_ = _conf.getStandards();
        String null_;
        String cast_;
        null_ = stds_.getAliasNullPe();
        cast_ = stds_.getAliasCast();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        Argument left_ = new Argument();
        left_.setStruct(locVar_.getStruct());
        Argument right_ = ip_.getRightArgument();
        Argument check_ = left_;
        if (getParent() instanceof AffectationOperation || _conf.isCheckAffectation()) {
            check_ = right_;
        }
        String formattedClassVar_ = locVar_.getClassName();
        formattedClassVar_ = _conf.getOperationPageEl().formatVarType(formattedClassVar_, _conf);
        if (PrimitiveTypeUtil.primitiveTypeNullObject(formattedClassVar_, check_.getStruct(), _conf)) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return Argument.createVoid();
        }
        if (!check_.isNull() && !NumericOperation.convert(_op)) {
            Mapping mapping_ = new Mapping();
            String base_ = check_.getObjectClassName(_conf.getContextEl());
            mapping_.setArg(base_);
            mapping_.setParam(formattedClassVar_);
            if (!Templates.isCorrect(mapping_, _conf)) {
                _conf.setException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,formattedClassVar_,RETURN_LINE,_conf.joinPages())),cast_));
                return Argument.createVoid();
            }
        }
        Argument res_;
        res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
        if (_conf.getException() != null) {
            return res_;
        }
        if (res_.getStruct() instanceof NumberStruct || res_.getStruct() instanceof CharStruct) {
            ClassArgumentMatching cl_ = new ClassArgumentMatching(locVar_.getClassName());
            res_.setStruct(PrimitiveTypeUtil.convertObject(cl_, res_.getStruct(), _conf));
        }
        locVar_.setStruct(res_.getStruct());
        if (_post) {
            return left_;
        }
        return res_;
    }

}
