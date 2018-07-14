package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class UnaryBooleanOperation extends AbstractUnaryOperation {

    public UnaryBooleanOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        LgNames stds_ = _conf.getStandards();
        String booleanPrimType_ = stds_.getAliasPrimBoolean();
        if (chidren_.isEmpty()) {
            setResultClass(new ClassArgumentMatching(booleanPrimType_));
            return;
        }
        ClassArgumentMatching clMatch_;
        clMatch_ = chidren_.first().getResultClass();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        String booleanType_ = stds_.getAliasBoolean();
        if (!clMatch_.matchClass(booleanPrimType_)) {
            if (!clMatch_.matchClass(booleanType_)) {
                ClassArgumentMatching cl_ = chidren_.first().getResultClass();
                UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                un_.setRc(_conf.getCurrentLocation());
                un_.setFileName(_conf.getCurrentFileName());
                un_.setExpectedResult(booleanType_);
                un_.setOperands(new StringList(cl_.getName()));
                _conf.getClasses().addError(un_);
            }
        }
        clMatch_.setUnwrapObject(booleanPrimType_);
        setResultClass(new ClassArgumentMatching(booleanPrimType_));
    }

    @Override
    public Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = _nodes.getVal(chidren_.first()).getArgument();
        Object o_ = arg_.getObject();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        Boolean b_ = (Boolean) o_;
        b_ = !b_;
        Argument a_ = new Argument();
        a_.setObject(b_);
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Object o_ = arg_.getObject();
        Boolean b_ = (Boolean) o_;
        b_ = !b_;
        Argument a_ = new Argument();
        a_.setObject(b_);
        setSimpleArgumentAna(a_, _conf);
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Object o_ = arg_.getObject();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        Boolean b_ = (Boolean) o_;
        b_ = !b_;
        Argument a_ = new Argument();
        a_.setObject(b_);
        setSimpleArgument(a_, _conf);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        if (children_.isEmpty()) {
            CustList<StringMap<AssignmentBefore>> variablesAfterLast_ = vars_.getVariablesRootBefore();
            for (StringMap<AssignmentBefore> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    BooleanAssignment s_ = new BooleanAssignment();
                    s_.setAssignedAfterWhenFalse(true);
                    s_.setUnassignedAfterWhenFalse(true);
                    s_.setAssignedAfterWhenTrue(true);
                    s_.setUnassignedAfterWhenTrue(true);
                    sm_.put(e.getKey(), s_);
                }
                variablesAfter_.add(sm_);
            }
            vars_.getVariables().put(this, variablesAfter_);
            StringMap<AssignmentBefore> fieldsAfterLast_ = vars_.getFieldsRootBefore();
            for (EntryCust<String, AssignmentBefore> e: fieldsAfterLast_.entryList()) {
                BooleanAssignment s_ = new BooleanAssignment();
                s_.setAssignedAfterWhenFalse(true);
                s_.setUnassignedAfterWhenFalse(true);
                s_.setAssignedAfterWhenTrue(true);
                s_.setUnassignedAfterWhenTrue(true);
                fieldsAfter_.put(e.getKey(), s_);
            }
            vars_.getFields().put(this, fieldsAfter_);
            return;
        }
        OperationNode last_ = children_.last();
        StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);
        for (EntryCust<String, Assignment> e: fieldsAfterLast_.entryList()) {
            BooleanAssignment b_ = e.getValue().toBoolAssign();
            BooleanAssignment r_ = b_.neg();
            fieldsAfter_.put(e.getKey(), r_);
        }
        vars_.getFields().put(this, fieldsAfter_);
        for (StringMap<Assignment> s: variablesAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                BooleanAssignment b_ = e.getValue().toBoolAssign();
                BooleanAssignment r_ = b_.neg();
                sm_.put(e.getKey(), r_);
            }
            variablesAfter_.add(sm_);
        }
        vars_.getVariables().put(this, variablesAfter_);
    }
}
