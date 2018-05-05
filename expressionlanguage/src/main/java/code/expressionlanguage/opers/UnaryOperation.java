package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class UnaryOperation extends PrimitiveBoolOperation {

    public UnaryOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf,
            String _fieldName) {
        analyzeCommon(_conf);
    }

    void analyzeCommon(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
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
            _conf.getClasses().getErrorsDet().add(un_);
            ClassArgumentMatching arg_ = new ClassArgumentMatching(exp_);
            setResultClass(arg_);
            return;
        }
        LgNames stds_ = _conf.getStandards();
        int intOrder_ = PrimitiveTypeUtil.getOrderClass(stds_.getAliasPrimInteger(), _conf);
        if (PrimitiveTypeUtil.getOrderClass(cl_, _conf) < intOrder_) {
            cl_ = new ClassArgumentMatching(stds_.getAliasPrimInteger());
        }
        setResultClass(cl_);
    }

    @Override
    public Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        return calculateCommon(_nodes, _conf);
    }

    Argument calculateCommon(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
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
    public void quickCalculate(ContextEl _conf) {
        calculateCommon(_conf);
    }
    @Override
    public void calculate(ContextEl _conf) {
        calculateCommon(_conf);
    }

    void calculateCommon(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Argument a_ = getArgument(_conf, arg_);
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(a_, _conf);
    }

    Argument getArgument(ContextEl _conf,
            Argument _in) {
        Argument out_ = new Argument();
        Object o_ = _in.getObject();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        LgNames stds_ = _conf.getStandards();
        String null_;
        null_ = stds_.getAliasNullPe();
        if (o_ == null) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return out_;
        }
        String oper_ = getOperations().getOperators().firstValue();
        if (StringList.quickEq(oper_, PLUS)) {
            if (o_ instanceof Character) {
                out_.setObject(((Character)o_));
            } else {
                Number b_ = (Number) o_;
                if (b_ instanceof Integer) {
                    out_.setObject(((Integer)b_));
                } else if (b_ instanceof Long) {
                    out_.setObject(((Long)b_));
                } else if (b_ instanceof Byte) {
                    out_.setObject(((Byte)b_));
                } else if (b_ instanceof Short) {
                    out_.setObject(((Short)b_));
                } else if (b_ instanceof Double) {
                    out_.setObject(((Double)b_));
                } else if (b_ instanceof Float) {
                    out_.setObject(((Float)b_));
                }
            }
        } else if (o_ instanceof Character) {
            out_.setObject(-((Character)o_));
        } else {
            Number b_ = (Number) o_;
            if (b_ instanceof Integer) {
                out_.setObject(-((Integer)b_));
            } else if (b_ instanceof Long) {
                out_.setObject(-((Long)b_));
            } else if (b_ instanceof Byte) {
                out_.setObject(-((Byte)b_));
            } else if (b_ instanceof Short) {
                out_.setObject(-((Short)b_));
            } else if (b_ instanceof Double) {
                out_.setObject(-((Double)b_));
            } else if (b_ instanceof Float) {
                out_.setObject(-((Float)b_));
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
        OperationNode last_ = children_.last();
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
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
            OperationNode _firstChild, OperationNode _previous) {
        
    }
}
