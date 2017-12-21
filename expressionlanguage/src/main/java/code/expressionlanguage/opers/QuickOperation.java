package code.expressionlanguage.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.exceptions.BadNumberValuesException;
import code.expressionlanguage.exceptions.NotBooleanException;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.exceptions.NullObjectException;


public abstract class QuickOperation extends PrimitiveBoolOperation {

    public QuickOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public final void analyze(CustList<OperationNode> _nodes, ContextEl _conf,
            String _fieldName, String _op) {
        analyzeCommon(_nodes, _conf, _op);
    }

    final void analyzeCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.size() < 2) {
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            throw new BadNumberValuesException(_conf.joinPages());
        }
        LgNames stds_ = _conf.getStandards();
        String booleanPrimType_ = stds_.getAliasPrimBoolean();
        String booleanType_ = stds_.getAliasBoolean();
        for (OperationNode o: chidren_) {
            ClassArgumentMatching clMatch_;
            clMatch_ = o.getResultClass();
            setRelativeOffsetPossibleLastPage(o.getIndexInEl(), _conf);
            if (!clMatch_.matchClass(booleanPrimType_)) {
                if (!clMatch_.matchClass(booleanType_)) {
                    ClassArgumentMatching cl_ = o.getResultClass();
                    throw new NotBooleanException(cl_+RETURN_LINE+_conf.joinPages());
                }
            }
        }
        setResultClass(chidren_.last().getResultClass());
    }

    @Override
    public final Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf, String _op) {
        return calculateCommon(_nodes, _conf, _op);
    }

    final Argument calculateCommon(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode last_ = chidren_.last();
        setRelativeOffsetPossibleLastPage(last_.getIndexInEl(), _conf);
        Argument a_ = _nodes.getVal(last_).getArgument();
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    /**@throws NullObjectException*/
    @Override
    public final void calculate(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        calculateCommon(_nodes, _conf, _op);
    }

    final void calculateCommon(CustList<OperationNode> _nodes, ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
        Argument a_ = chidren_.last().getArgument();
        setSimpleArgument(a_, _conf);
    }

    abstract boolean absorbingValue();
}
