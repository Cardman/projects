package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.CustomFoundMethod;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;

public final class UnaryOperation extends AbstractUnaryOperation {
    private ClassMethodId classMethodId;

    public UnaryOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyzeUnary(Analyzable _conf) {
        OperationNode child_ = getFirstChild();
        LgNames stds_ = _conf.getStandards();
        ClassArgumentMatching clMatch_ = child_.getResultClass();
        String oper_ = getOperations().getOperators().firstValue();
        ClassMethodIdReturn cust_ = getOperator(_conf, oper_, clMatch_);
        if (cust_.isFoundMethod()) {
            setResultClass(new ClassArgumentMatching(cust_.getReturnType()));
            String foundClass_ = cust_.getRealClass();
            foundClass_ = Templates.getIdFromAllTypes(foundClass_);
            MethodId id_ = cust_.getRealId();
            classMethodId = new ClassMethodId(foundClass_, id_);
            MethodId realId_ = cust_.getRealId();
            CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
            firstArgs_.add(child_.getResultClass());
            CustList<OperationNode> children_ = new CustList<OperationNode>(child_);
            InvokingOperation.unwrapArgsFct(children_, realId_, -1, EMPTY_STRING, firstArgs_, _conf);
            return;
        }
        ClassArgumentMatching cl_ = PrimitiveTypeUtil.toPrimitive(clMatch_, true, _conf);
        if (child_ instanceof ConstantOperation) {
            Argument arg_ = ((ConstantOperation) child_).getArgument();
            Object instance_ = arg_.getObject();
            if (instance_ instanceof Byte) {
                clMatch_.setUnwrapObject(cl_);
                setResultClass(cl_);
                return;
            }
            if (instance_ instanceof Short) {
                clMatch_.setUnwrapObject(cl_);
                setResultClass(cl_);
                return;
            }
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        if (cl_ == null) {
            _conf.setOkNumOp(false);
            String exp_ = _conf.getStandards().getAliasNumber();
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(exp_);
            un_.setOperands(clMatch_);
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
        if (classMethodId != null) {
            CustList<Argument> arguments_ = new CustList<Argument>();
            for (OperationNode o: chidren_) {
                arguments_.add(_nodes.getVal(o).getArgument());
            }
            CustList<Argument> firstArgs_ = InvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
            String classNameFound_ = classMethodId.getClassName();
            MethodId id_ = classMethodId.getConstraints();
            _conf.getContextEl().setCallMethod(new CustomFoundMethod(Argument.createVoid(), classNameFound_, id_, firstArgs_));
            return Argument.createVoid();
        }
        OperationNode op_ = chidren_.first();
        Argument arg_ = _nodes.getVal(op_).getArgument();
        Argument a_ = getArgument(_conf, arg_);
        if (!_conf.hasExceptionOrFailInit()) {
            setSimpleArgument(a_, _conf, _nodes);
        }
        return a_;
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        if (classMethodId != null || !_conf.isOkNumOp()) {
            return;
        }
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Argument out_ = new Argument();
        if (arg_.isNull()) {
            return;
        }
        String oper_ = getOperations().getOperators().firstValue();
        if (StringList.quickEq(oper_, PLUS)) {
            out_.setStruct(arg_.getStruct());
        } else {
            Number b_ = arg_.getNumber();
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
        if (classMethodId != null) {
            CustList<Argument> arguments_ = new CustList<Argument>();
            for (OperationNode o: chidren_) {
                arguments_.add(o.getArgument());
            }
            CustList<Argument> firstArgs_ = InvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
            String classNameFound_ = classMethodId.getClassName();
            MethodId id_ = classMethodId.getConstraints();
            Argument res_;
            res_ = ProcessMethod.calculateArgument(Argument.createVoid(), classNameFound_, id_, firstArgs_, _conf.getContextEl());
            setSimpleArgument(res_, _conf);
            return;
        }
        Argument arg_ = chidren_.first().getArgument();
        Argument a_ = getArgument(_conf, arg_);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(a_, _conf);
    }

    Argument getArgument(ExecutableCode _conf,
            Argument _in) {
        Argument out_ = new Argument();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        String oper_ = getOperations().getOperators().firstValue();
        if (StringList.quickEq(oper_, PLUS)) {
            out_.setStruct(_in.getStruct());
        } else {
            Number b_ = _in.getNumber();
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
        analyzeStdAssignmentAfter(_conf);
    }
}
