package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class UnaryOperation extends AbstractUnaryOperation {

    public UnaryOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        LgNames stds_ = _conf.getStandards();
        if (chidren_.isEmpty()) {
            String exp_ = _conf.getStandards().getAliasNumber();
            setResultClass(new ClassArgumentMatching(exp_));
            return;
        }
        ClassArgumentMatching clMatch_ = chidren_.first().getResultClass();
        ClassArgumentMatching cl_ = PrimitiveTypeUtil.toPrimitive(clMatch_, true, _conf);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        if (cl_ == null) {
            String exp_ = _conf.getStandards().getAliasNumber();
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(exp_);
            un_.setOperands(new StringList(clMatch_.getName()));
            _conf.getClasses().addError(un_);
            ClassArgumentMatching arg_ = new ClassArgumentMatching(exp_);
            setResultClass(arg_);
            return;
        }
        int intOrder_ = PrimitiveTypeUtil.getOrderClass(stds_.getAliasPrimInteger(), _conf);
        if (PrimitiveTypeUtil.getOrderClass(cl_, _conf) < intOrder_) {
            cl_ = new ClassArgumentMatching(stds_.getAliasPrimInteger());
        }
        clMatch_.setUnwrapObject(cl_);
        setResultClass(cl_);
    }

    @Override
    public Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode op_ = chidren_.first();
        Argument arg_ = _nodes.getVal(op_).getArgument();
        Argument a_ = getArgument(_conf, arg_);
        if (_conf.getException() == null) {
            setSimpleArgument(a_, _conf, _nodes);
        }
        return a_;
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Argument out_ = new Argument();
        Object o_ = arg_.getObject();
        if (o_ == null) {
            return;
        }
        String oper_ = getOperations().getOperators().firstValue();
        if (StringList.quickEq(oper_, PLUS)) {
            out_.setStruct(arg_.getStruct());
        } else if (o_ instanceof Character) {
            out_.setObject(-((Character)o_));
        } else {
            Number b_ = (Number) o_;
            int order_ = PrimitiveTypeUtil.getOrderClass(getResultClass(), _conf);
            String longPrim_ = _conf.getStandards().getAliasPrimLong();
            if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _conf)) {
                out_.setObject(-b_.longValue());
            } else {
                out_.setObject(-b_.doubleValue());
            }
        }
        ClassArgumentMatching res_ = getResultClass();
        out_.setStruct(PrimitiveTypeUtil.convertObject(res_, out_.getStruct(), _conf));
        setSimpleArgumentAna(out_, _conf);
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Argument a_ = getArgument(_conf, arg_);
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(a_, _conf);
    }

    Argument getArgument(ExecutableCode _conf,
            Argument _in) {
        Argument out_ = new Argument();
        Object o_ = _in.getObject();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        String oper_ = getOperations().getOperators().firstValue();
        if (StringList.quickEq(oper_, PLUS)) {
            out_.setStruct(_in.getStruct());
        } else if (o_ instanceof Character) {
            out_.setObject(-((Character)o_));
        } else {
            Number b_ = (Number) o_;
            int order_ = PrimitiveTypeUtil.getOrderClass(getResultClass(), _conf);
            String longPrim_ = _conf.getStandards().getAliasPrimLong();
            if (order_ <= PrimitiveTypeUtil.getOrderClass(longPrim_, _conf)) {
                out_.setObject(-b_.longValue());
            } else {
                out_.setObject(-b_.doubleValue());
            }
        }
        ClassArgumentMatching res_ = getResultClass();
        out_.setStruct(PrimitiveTypeUtil.convertObject(res_, out_.getStruct(), _conf));
        return out_;
    }
    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }
    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        if (children_.isEmpty()) {
            CustList<StringMap<AssignmentBefore>> variablesAfterLast_ = vars_.getVariablesRootBefore();
            for (StringMap<AssignmentBefore> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    SimpleAssignment s_ = new SimpleAssignment();
                    s_.setAssignedAfter(true);
                    s_.setUnassignedAfter(true);
                    sm_.put(e.getKey(), s_);
                }
                variablesAfter_.add(sm_);
            }
            vars_.getVariables().put(this, variablesAfter_);
            ObjectMap<ClassField,AssignmentBefore> fieldsAfterLast_ = vars_.getFieldsRootBefore();
            for (EntryCust<ClassField, AssignmentBefore> e: fieldsAfterLast_.entryList()) {
                SimpleAssignment s_ = new SimpleAssignment();
                s_.setAssignedAfter(true);
                s_.setUnassignedAfter(true);
                fieldsAfter_.put(e.getKey(), s_);
            }
            vars_.getFields().put(this, fieldsAfter_);
            return;
        }
        OperationNode last_ = children_.last();
        ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);
        for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsAfter_.put(e.getKey(), b_.assign(false));
        }
        vars_.getFields().put(this, fieldsAfter_);
        for (StringMap<Assignment> s: variablesAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assign(false));
            }
            variablesAfter_.add(sm_);
        }
        vars_.getVariables().put(this, variablesAfter_);
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        
    }
}
