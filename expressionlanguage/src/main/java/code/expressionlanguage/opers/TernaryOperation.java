package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.InvokingConstructor;
import code.expressionlanguage.InvokingMethod;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.CustomFoundConstructor;
import code.expressionlanguage.methods.CustomFoundMethod;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadOperandsNumber;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class TernaryOperation extends MethodOperation {

    private static final int BOOLEAN_ARGS = 3;

    private int offsetLocal;

    public TernaryOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void tryCalculateNode(ContextEl _conf, EqList<SortedClassField> _list, SortedClassField _current) {
        if (getFirstChild().getArgument() == null) {
            return;
        }
        quickCalculate(_conf);
    }
    @Override
    public void tryCalculateNode(Analyzable _conf) {
        if (getFirstChild().getArgument() == null) {
            return;
        }
        quickCalculate(_conf);
    }
    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        if (arguments_.first().isNull()) {
            return;
        }
        Boolean obj_ = (Boolean) arguments_.first().getObject();
        Argument arg_;
        if (obj_) {
            arg_ = arguments_.get(CustList.SECOND_INDEX);
        } else {
            arg_ = arguments_.last();
        }
        setSimpleArgumentAna(arg_, _conf);
    }
    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _firstChild, OperationNode _previous) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        ObjectMap<ClassField,AssignmentBefore> fieldsBefore_ = new ObjectMap<ClassField,AssignmentBefore>();
        CustList<StringMap<AssignmentBefore>> variablesBefore_ = new CustList<StringMap<AssignmentBefore>>();
        OperationNode firstChild_ = getFirstChild();
        ObjectMap<ClassField,Assignment> fieldsAfterFirst_ = vars_.getFields().getVal(firstChild_);
        CustList<StringMap<Assignment>> variablesAfterFirst_ = vars_.getVariables().getVal(firstChild_);
        if (firstChild_ == _previous) {
            for (EntryCust<ClassField, Assignment> e: fieldsAfterFirst_.entryList()) {
                BooleanAssignment b_ = (BooleanAssignment) e.getValue();
                AssignmentBefore a_ = new AssignmentBefore();
                if (b_.isAssignedAfterWhenTrue()) {
                    a_.setAssignedBefore(true);
                }
                if (b_.isUnassignedAfterWhenTrue()) {
                    a_.setUnassignedBefore(true);
                }
                fieldsBefore_.put(e.getKey(), a_);
            }
            for (StringMap<Assignment> s: variablesAfterFirst_) {
                StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    BooleanAssignment b_ = (BooleanAssignment) e.getValue();
                    AssignmentBefore a_ = new AssignmentBefore();
                    if (b_.isAssignedAfterWhenTrue()) {
                        a_.setAssignedBefore(true);
                    }
                    if (b_.isUnassignedAfterWhenTrue()) {
                        a_.setUnassignedBefore(true);
                    }
                    sm_.put(e.getKey(), a_);
                }
                variablesBefore_.add(sm_);
            }
        } else {
            for (EntryCust<ClassField, Assignment> e: fieldsAfterFirst_.entryList()) {
                BooleanAssignment b_ = (BooleanAssignment) e.getValue();
                AssignmentBefore a_ = new AssignmentBefore();
                if (b_.isAssignedAfterWhenFalse()) {
                    a_.setAssignedBefore(true);
                }
                if (b_.isUnassignedAfterWhenFalse()) {
                    a_.setUnassignedBefore(true);
                }
                fieldsBefore_.put(e.getKey(), a_);
            }
            for (StringMap<Assignment> s: variablesAfterFirst_) {
                StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    BooleanAssignment b_ = (BooleanAssignment) e.getValue();
                    AssignmentBefore a_ = new AssignmentBefore();
                    if (b_.isAssignedAfterWhenFalse()) {
                        a_.setAssignedBefore(true);
                    }
                    if (b_.isUnassignedAfterWhenFalse()) {
                        a_.setUnassignedBefore(true);
                    }
                    sm_.put(e.getKey(), a_);
                }
                variablesBefore_.add(sm_);
            }
        }
        vars_.getFieldsBefore().put(_firstChild, fieldsBefore_);
        vars_.getVariablesBefore().put(_firstChild, variablesBefore_);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
        String fct_ = getOperations().getFctName();
        offsetLocal = StringList.getFirstPrintableCharIndex(fct_);
    }

    @Override
    public void analyze(Analyzable _conf, String _fieldName) {
        Classes classes_ = _conf.getClasses();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offsetLocal, _conf);
        LgNames stds_ = _conf.getStandards();
        String booleanType_ = stds_.getAliasBoolean();
        String booleanPrimType_ = stds_.getAliasPrimBoolean();
        if (chidren_.size() != BOOLEAN_ARGS) {
            BadOperandsNumber badNb_ = new BadOperandsNumber();
            badNb_.setOperandsNumber(chidren_.size());
            badNb_.setFileName(_conf.getCurrentFileName());
            badNb_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(badNb_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        OperationNode opOne_ = chidren_.first();
        ClassArgumentMatching clMatch_ = opOne_.getResultClass();
        if (!clMatch_.matchClass(booleanPrimType_)) {
            if (!clMatch_.matchClass(booleanType_)) {
                setRelativeOffsetPossibleAnalyzable(opOne_.getIndexInEl()+1, _conf);
                ClassArgumentMatching cl_ = chidren_.first().getResultClass();
                UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                un_.setRc(_conf.getCurrentLocation());
                un_.setFileName(_conf.getCurrentFileName());
                un_.setExpectedResult(booleanType_);
                un_.setOperands(new StringList(cl_.getName()));
                _conf.getClasses().getErrorsDet().add(un_);
            }
        }
        opOne_.getResultClass().setUnwrapObject(booleanPrimType_);
        OperationNode opTwo_ = chidren_.get(CustList.SECOND_INDEX);
        OperationNode opThree_ = chidren_.get(CustList.SECOND_INDEX);
        ClassArgumentMatching clMatchTwo_ = opTwo_.getResultClass();
        ClassArgumentMatching clMatchThree_ = opThree_.getResultClass();
        if (!clMatchTwo_.matchClass(clMatchThree_)) {
            setRelativeOffsetPossibleAnalyzable(opTwo_.getIndexInEl()+1, _conf);
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setExpectedResult(clMatchTwo_.getName());
            un_.setOperands(new StringList(clMatchTwo_.getName(),clMatchThree_.getName()));
            un_.setFileName(_conf.getCurrentFileName());
            un_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(un_);
        }
        setResultClass(clMatchTwo_);
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        LgNames lgNames_ = _conf.getStandards();
        String aliasBoolean_ = lgNames_.getAliasBoolean();
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        OperationNode last_ = children_.last();
        ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);

        OperationNode befLast_ = children_.get(children_.size() - 2);
        ObjectMap<ClassField,Assignment> fieldsAfterBefLast_ = vars_.getFields().getVal(befLast_);
        CustList<StringMap<Assignment>> variablesAfterBefLast_ = vars_.getVariables().getVal(befLast_);
        if (PrimitiveTypeUtil.canBeUseAsArgument(aliasBoolean_, getResultClass().getName(), _conf)) {
            for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
                BooleanAssignment b_ = (BooleanAssignment) e.getValue();
                BooleanAssignment p_ = (BooleanAssignment) fieldsAfterBefLast_.getVal(e.getKey());
                BooleanAssignment r_ = new BooleanAssignment();
                if (b_.isAssignedAfterWhenTrue() && p_.isAssignedAfterWhenTrue()) {
                    r_.setAssignedAfterWhenTrue(true);
                }
                if (b_.isUnassignedAfterWhenFalse() && p_.isAssignedAfterWhenFalse()) {
                    r_.setUnassignedAfterWhenFalse(true);
                }
                fieldsAfter_.put(e.getKey(), r_);
            }
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = variablesAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    BooleanAssignment b_ = (BooleanAssignment) e.getValue();
                    BooleanAssignment p_ = (BooleanAssignment) variablesAfterBefLast_.get(index_).getVal(e.getKey());
                    BooleanAssignment r_ = new BooleanAssignment();
                    if (b_.isAssignedAfterWhenTrue() && p_.isAssignedAfterWhenTrue()) {
                        r_.setAssignedAfterWhenTrue(true);
                    }
                    if (b_.isUnassignedAfterWhenFalse() && p_.isAssignedAfterWhenFalse()) {
                        r_.setUnassignedAfterWhenFalse(true);
                    }
                    sm_.put(e.getKey(), r_);
                }
                variablesAfter_.add(sm_);
            }
        } else {
            for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
                Assignment b_ = e.getValue();
                Assignment p_ = fieldsAfterBefLast_.getVal(e.getKey());
                SimpleAssignment r_ = new SimpleAssignment();
                if (b_.isAssignedAfter() && p_.isAssignedAfter()) {
                    r_.setAssignedAfter(true);
                }
                fieldsAfter_.put(e.getKey(), r_);
            }
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = variablesAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    Assignment b_ = e.getValue();
                    Assignment p_ = variablesAfterBefLast_.get(index_).getVal(e.getKey());
                    SimpleAssignment r_ = new SimpleAssignment();
                    if (b_.isAssignedAfter() && p_.isAssignedAfter()) {
                        r_.setAssignedAfter(true);
                    }
                    sm_.put(e.getKey(), r_);
                }
                variablesAfter_.add(sm_);
            }
        }
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
    }

    @Override
    public void calculate(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        ArgumentCall argres_ = getArgument(arguments_, _conf);
        Argument res_;
        if (argres_.isInvokeConstructor()) {
            InvokingConstructor i_ = argres_.getInvokeConstructor();
            res_ = ProcessMethod.instanceArgument(i_.getClassName(), i_.getCurrentObject(), i_.getId(), i_.getArguments(), _conf);
        } else if (argres_.isInvokeMethod()) {
            InvokingMethod i_ = argres_.getInvokeMethod();
            res_ = ProcessMethod.calculateArgument(i_.getGl(), i_.getClassName(), i_.getId(), i_.getArguments(), _conf);
        } else {
            res_ = argres_.getArgument();
        }
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(res_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(_nodes.getVal(o).getArgument());
        }
        ArgumentCall argres_ = getArgument(arguments_, _conf);
        Argument res_ = argres_.getArgument();
        if (argres_.isInitClass()) {
            _conf.setInitClass(new NotInitializedClass(argres_.getInitClass().getClassName()));
        } else if (argres_.isInvokeConstructor()) {
            InvokingConstructor i_ = argres_.getInvokeConstructor();
            _conf.setCallCtor(new CustomFoundConstructor(i_.getClassName(), i_.getFieldName(), i_.getOrdinal(), i_.getCalled(), i_.getId(), i_.getCurrentObject(), i_.getArguments(), i_.getInstanceStep()));
        } else if (argres_.isInvokeMethod()) {
            InvokingMethod i_ = argres_.getInvokeMethod();
            _conf.setCallMethod(new CustomFoundMethod(i_.getGl(), i_.getClassName(), i_.getId(), i_.getArguments()));
        } else {
            setSimpleArgument(res_, _conf, _nodes);
        }
        return res_;
    }
    ArgumentCall getArgument(CustList<Argument> _arguments, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offsetLocal, _conf);
        LgNames stds_ = _conf.getStandards();
        String null_;
        null_ = stds_.getAliasNullPe();
        if (_arguments.first().isNull()) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            Argument a_ = new Argument();
            return ArgumentCall.newArgument(a_);
        }
        Boolean obj_ = (Boolean) _arguments.first().getObject();
        Argument arg_;
        if (obj_) {
            arg_ = _arguments.get(CustList.SECOND_INDEX);
        } else {
            arg_ = _arguments.last();
        }
        return ArgumentCall.newArgument(arg_);
    }
    @Override
    public boolean isPossibleInitClass() {
        return false;
    }

    @Override
    public boolean isSuperConstructorCall() {
        return false;
    }

    @Override
    public boolean isOtherConstructorClass() {
        return false;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

}
