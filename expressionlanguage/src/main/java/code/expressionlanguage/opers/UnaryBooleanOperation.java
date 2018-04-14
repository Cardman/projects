package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.exceptions.NullObjectException;

public final class UnaryBooleanOperation extends PrimitiveBoolOperation {

    public UnaryBooleanOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(CustList<OperationNode> _nodes, Analyzable _conf,
            String _fieldName, String _op) {
        analyzeCommon(_nodes, _conf, _op);
    }

    void analyzeCommon(CustList<OperationNode> _nodes, Analyzable _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        ClassArgumentMatching clMatch_;
        clMatch_ = chidren_.first().getResultClass();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        LgNames stds_ = _conf.getStandards();
        String booleanPrimType_ = stds_.getAliasPrimBoolean();
        String booleanType_ = stds_.getAliasBoolean();
        if (!clMatch_.matchClass(booleanPrimType_)) {
            if (!clMatch_.matchClass(booleanType_)) {
                ClassArgumentMatching cl_ = chidren_.first().getResultClass();
                UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                un_.setRc(_conf.getCurrentLocation());
                un_.setFileName(_conf.getCurrentFileName());
                un_.setExpectedResult(booleanType_);
                un_.setOperands(new StringList(cl_.getName()));
                _conf.getClasses().getErrorsDet().add(un_);
            }
        }
        setResultClass(new ClassArgumentMatching(booleanPrimType_));
    }

    @Override
    public Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }

    Argument calculateCommon(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = _nodes.getVal(chidren_.first()).getArgument();
        Object o_ = arg_.getObject();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        LgNames stds_ = _conf.getStandards();
        String null_;
        null_ = stds_.getAliasNullPe();
        if (o_ == null) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        Boolean b_ = (Boolean) o_;
        b_ = !b_;
        Argument a_ = new Argument();
        a_.setObject(b_);
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    /**@throws NullObjectException*/

    @Override
    public void calculate(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateCommon(_nodes, _conf, _op);
    }

    void calculateCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Object o_ = arg_.getObject();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        LgNames stds_ = _conf.getStandards();
        String null_;
        null_ = stds_.getAliasNullPe();
        if (o_ == null) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
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
}
