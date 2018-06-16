package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
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
import code.expressionlanguage.opers.util.Struct;
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
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
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

        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf);
        String varName_ = variableName;
        if (getParent() instanceof AffectationOperation && getParent().getFirstChild() == this) {
            varName_ = EMPTY_STRING;
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

    public String getVariableName() {
        return variableName;
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
        Argument arg_ = getCommonArgument(_conf);
        if (_conf.getException() != null) {
            return;
        }
        if (resultCanBeSet()) {
            setQuickSimpleArgument(arg_, _conf);
        } else {
            setSimpleArgument(arg_, _conf);
        }
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        Argument arg_ = getCommonArgument(_conf);
        if (_conf.getException() != null) {
            return arg_;
        }
        if (resultCanBeSet()) {
            setQuickSimpleArgument(arg_, _conf, _nodes);
        } else {
            setSimpleArgument(arg_, _conf, _nodes);
        }
        return arg_;
    }
    Argument getCommonArgument(ExecutableCode _conf) {
        Argument a_ = new Argument();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        PageEl ip_ = _conf.getOperationPageEl();
        if (resultCanBeSet()) {
            return Argument.createVoid();
        }
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        a_ = new Argument();
        a_.setStruct(locVar_.getStruct());
        return a_;
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
            Argument _right) {
        Argument arg_ = getCommonSetting(_conf, _right);
        if (_conf.getException() == null) {
            setSimpleArgument(arg_, _conf, _nodes);
        }
        return arg_;
    }

    @Override
    public void calculateSetting(ExecutableCode _conf, Argument _right) {
        Argument arg_ = getCommonSetting(_conf, _right);
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }

    @Override
    public Argument calculateCompoundSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, Argument _right) {
        Argument a_ = _nodes.getVal(this).getArgument();
        Struct store_;
        store_ = a_.getStruct();
        Argument arg_ = getCommonCompoundSetting(_conf, store_, _op, _right);
        if (_conf.getException() == null) {
            setSimpleArgument(arg_, _conf, _nodes);
        }
        return arg_;
    }

    @Override
    public void calculateCompoundSetting(ExecutableCode _conf, String _op,
            Argument _right) {
        Argument a_ = getArgument();
        Struct store_;
        store_ = a_.getStruct();
        Argument arg_ = getCommonCompoundSetting(_conf, store_, _op, _right);
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }

    @Override
    public Argument calculateSemiSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post) {
        Argument a_ = _nodes.getVal(this).getArgument();
        Struct store_;
        store_ = a_.getStruct();
        Argument arg_ = getCommonSemiSetting(_conf, store_, _op, _post);
        if (_conf.getException() == null) {
            setSimpleArgument(arg_, _conf, _nodes);
        }
        return arg_;
    }

    @Override
    public void calculateSemiSetting(ExecutableCode _conf, String _op,
            boolean _post) {
        Argument a_ = getArgument();
        Struct store_;
        store_ = a_.getStruct();
        Argument arg_ = getCommonSemiSetting(_conf, store_, _op, _post);
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }
    Argument getCommonSetting(ExecutableCode _conf, Argument _right) {
        PageEl ip_ = _conf.getOperationPageEl();
        LgNames stds_ = _conf.getStandards();
        String cast_;
        cast_ = stds_.getAliasCast();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        Argument left_ = new Argument();
        String formattedClassVar_ = locVar_.getClassName();
        formattedClassVar_ = _conf.getOperationPageEl().formatVarType(formattedClassVar_, _conf);
        left_.setStruct(locVar_.getStruct());
        if (!_right.isNull()) {
            Mapping mapping_ = new Mapping();
            String base_ = _right.getObjectClassName(_conf.getContextEl());
            mapping_.setArg(base_);
            mapping_.setParam(formattedClassVar_);
            if (!Templates.isCorrect(mapping_, _conf)) {
                _conf.setException(new StdStruct(new CustomError(StringList.concat(base_,RETURN_LINE,formattedClassVar_,RETURN_LINE,_conf.joinPages())),cast_));
                return Argument.createVoid();
            }
        }
        ClassArgumentMatching cl_ = new ClassArgumentMatching(formattedClassVar_);
        if (_conf.getException() != null) {
            return _right;
        }
        if (_right.getStruct() instanceof NumberStruct || _right.getStruct() instanceof CharStruct) {
            _right.setStruct(PrimitiveTypeUtil.convertObject(cl_, _right.getStruct(), _conf));
        }
        locVar_.setStruct(_right.getStruct());
        return _right;
    }
    Argument getCommonCompoundSetting(ExecutableCode _conf, Struct _store, String _op, Argument _right) {
        PageEl ip_ = _conf.getOperationPageEl();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        Argument left_ = new Argument();
        String formattedClassVar_ = locVar_.getClassName();
        formattedClassVar_ = _conf.getOperationPageEl().formatVarType(formattedClassVar_, _conf);
        left_.setStruct(_store);
        ClassArgumentMatching cl_ = new ClassArgumentMatching(formattedClassVar_);
        Argument res_;
        res_ = NumericOperation.calculateAffect(left_, _conf, _right, _op, catString, cl_);
        if (_conf.getException() != null) {
            return res_;
        }
        if (res_.getStruct() instanceof NumberStruct || res_.getStruct() instanceof CharStruct) {
            res_.setStruct(PrimitiveTypeUtil.convertObject(cl_, res_.getStruct(), _conf));
        }
        locVar_.setStruct(res_.getStruct());
        return res_;
    }
    Argument getCommonSemiSetting(ExecutableCode _conf, Struct _store, String _op, boolean _post) {
        PageEl ip_ = _conf.getOperationPageEl();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        Argument left_ = new Argument();
        String formattedClassVar_ = locVar_.getClassName();
        formattedClassVar_ = _conf.getOperationPageEl().formatVarType(formattedClassVar_, _conf);
        left_.setStruct(_store);
        ClassArgumentMatching cl_ = new ClassArgumentMatching(formattedClassVar_);
        Argument res_;
        res_ = NumericOperation.calculateIncrDecr(left_, _conf, _op, cl_);
        if (_conf.getException() != null) {
            return res_;
        }
        if (res_.getStruct() instanceof NumberStruct || res_.getStruct() instanceof CharStruct) {
            res_.setStruct(PrimitiveTypeUtil.convertObject(cl_, res_.getStruct(), _conf));
        }
        locVar_.setStruct(res_.getStruct());
        if (_post) {
            return left_;
        }
        return res_;
    }

}
