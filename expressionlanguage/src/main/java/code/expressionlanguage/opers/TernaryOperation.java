package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.methods.Block;
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
            OperationNode _nextSibling, OperationNode _previous) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        ObjectMap<ClassField,AssignmentBefore> fieldsBefore_ = new ObjectMap<ClassField,AssignmentBefore>();
        CustList<StringMap<AssignmentBefore>> variablesBefore_ = new CustList<StringMap<AssignmentBefore>>();
        OperationNode firstChild_ = getFirstChild();
        ObjectMap<ClassField,Assignment> fieldsAfterFirst_ = vars_.getFields().getVal(firstChild_);
        CustList<StringMap<Assignment>> variablesAfterFirst_ = vars_.getVariables().getVal(firstChild_);
        if (firstChild_ == _previous) {
            for (EntryCust<ClassField, Assignment> e: fieldsAfterFirst_.entryList()) {
                BooleanAssignment b_ = e.getValue().toBoolAssign();
                AssignmentBefore a_ = b_.copyWhenTrue();
                fieldsBefore_.put(e.getKey(), a_);
            }
            for (StringMap<Assignment> s: variablesAfterFirst_) {
                StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    BooleanAssignment b_ = e.getValue().toBoolAssign();
                    AssignmentBefore a_ = b_.copyWhenTrue();
                    sm_.put(e.getKey(), a_);
                }
                variablesBefore_.add(sm_);
            }
        } else {
            for (EntryCust<ClassField, Assignment> e: fieldsAfterFirst_.entryList()) {
                BooleanAssignment b_ = e.getValue().toBoolAssign();
                AssignmentBefore a_ = b_.copyWhenFalse();
                fieldsBefore_.put(e.getKey(), a_);
            }
            for (StringMap<Assignment> s: variablesAfterFirst_) {
                StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    BooleanAssignment b_ = e.getValue().toBoolAssign();
                    AssignmentBefore a_ = b_.copyWhenFalse();
                    sm_.put(e.getKey(), a_);
                }
                variablesBefore_.add(sm_);
            }
        }
        vars_.getFieldsBefore().put(_nextSibling, fieldsBefore_);
        vars_.getVariablesBefore().put(_nextSibling, variablesBefore_);
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
    public void analyze(Analyzable _conf) {
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
            _conf.getClasses().addError(badNb_);
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
                _conf.getClasses().addError(un_);
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
            _conf.getClasses().addError(un_);
        }
        setResultClass(clMatchTwo_);
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        OperationNode last_ = children_.last();
        ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);

        OperationNode befLast_ = children_.get(children_.size() - 2);
        ObjectMap<ClassField,Assignment> fieldsAfterBefLast_ = vars_.getFields().getVal(befLast_);
        CustList<StringMap<Assignment>> variablesAfterBefLast_ = vars_.getVariables().getVal(befLast_);
        if (getResultClass().isBoolType(_conf)) {
            for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
                BooleanAssignment b_ = e.getValue().toBoolAssign();
                BooleanAssignment p_ = fieldsAfterBefLast_.getVal(e.getKey()).toBoolAssign();
                BooleanAssignment r_ = b_.ternary(p_);
                fieldsAfter_.put(e.getKey(), r_);
            }
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = variablesAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    BooleanAssignment b_ = e.getValue().toBoolAssign();
                    BooleanAssignment p_ = variablesAfterBefLast_.get(index_).getVal(e.getKey()).toBoolAssign();
                    BooleanAssignment r_ = b_.ternary(p_);
                    sm_.put(e.getKey(), r_);
                }
                variablesAfter_.add(sm_);
            }
        } else {
            for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
                Assignment b_ = e.getValue();
                Assignment p_ = fieldsAfterBefLast_.getVal(e.getKey());
                SimpleAssignment r_ = b_.ternarySimple(p_);
                fieldsAfter_.put(e.getKey(), r_);
            }
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = variablesAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    Assignment b_ = e.getValue();
                    Assignment p_ = variablesAfterBefLast_.get(index_).getVal(e.getKey());
                    SimpleAssignment r_ = b_.ternarySimple(p_);
                    sm_.put(e.getKey(), r_);
                }
                variablesAfter_.add(sm_);
            }
        }
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument res_ = getArgument(arguments_, _conf);
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
        Argument res_ = getArgument(arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
        return res_;
    }
    Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offsetLocal, _conf);
        Boolean obj_ = (Boolean) _arguments.first().getObject();
        Argument arg_;
        if (obj_) {
            arg_ = _arguments.get(CustList.SECOND_INDEX);
        } else {
            arg_ = _arguments.last();
        }
        return arg_;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

}
