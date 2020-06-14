package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.ArrOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendArrOperation extends RendInvokingOperation implements RendCalculableOperation,RendSettableElResult {

    private boolean variable;

    private boolean catString;
    private ClassArgumentMatching previous;
    public RendArrOperation(ArrOperation _arr) {
        super(_arr);
        variable = _arr.isVariable();
        catString = _arr.isCatString();
        previous = _arr.getPreviousResultClass();
    }
    public RendArrOperation(RendArrOperation _arr,int _indexChild, ClassArgumentMatching _res, int _order,
                                 boolean _intermediate, Argument _previousArgument) {
        super(_indexChild,_res,_order, _intermediate, _previousArgument);
        previous = _arr.previous;
        variable = true;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        int max_ = chidren_.size();
        if (resultCanBeSet()) {
            max_--;
        }
        Argument a_ = getArgument(_nodes,max_, _conf);
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(a_, _conf,_nodes);
        } else {
            setSimpleArgument(a_, _conf,_nodes);
        }
    }

    Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, int _maxIndexChildren, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        Argument a_ = new Argument();
        for (int i = CustList.FIRST_INDEX; i < _maxIndexChildren; i++) {
            Struct o_ = getArgument(_nodes, chidren_.get(i)).getStruct();
            int indexEl_ = chidren_.get(i).getIndexInEl();
            setRelativeOffsetPossibleLastPage(indexEl_, _conf);
            array_ = ExecInvokingOperation.getElement(array_, o_, _conf.getContext());
            if (_conf.getContext().hasException()) {
                return a_;
            }
        }
        a_.setStruct(array_);
        return a_;
    }

    @Override
    public boolean resultCanBeSet() {
        return variable;
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument(_nodes,this);
        RendDynOperationNode lastElement_ = chidren_.last();
        Argument last_ = getArgument(_nodes,lastElement_);
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        a_.setStruct(affectArray(array_, last_, lastElement_.getIndexInEl(), _right, _conf));
        return a_;
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        RendDynOperationNode lastElement_ = chidren_.last();
        Argument last_ = getArgument(_nodes,lastElement_);
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        a_.setStruct(compoundAffectArray(array_, store_, last_, lastElement_.getIndexInEl(), _op, _right, _conf));
        return a_;
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _store) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = new Argument();
        Struct store_;
        store_ = _store.getStruct();
        RendDynOperationNode lastElement_ = chidren_.last();
        Argument last_ = getArgument(_nodes,lastElement_);
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        a_.setStruct(semiAffectArray(array_, store_, last_, lastElement_.getIndexInEl(), _op, _post, _conf));
        return a_;
    }

    private Struct affectArray(Struct _array, Argument _index, int _indexEl, Argument _right, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        Struct o_ = _index.getStruct();
        ExecInvokingOperation.setElement(_array, o_, _right.getStruct(), _conf.getContext());
        return _right.getStruct();
    }

    private Struct compoundAffectArray(Struct _array, Struct _stored, Argument _index, int _indexEl, String _op, Argument _right, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        Struct o_ = _index.getStruct();
        Argument left_ = new Argument();
        left_.setStruct(_stored);
        ClassArgumentMatching clArg_ = getResultClass();
        Argument res_;
        res_ = RendNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, clArg_);
        if (_conf.getContext().hasException()) {
            return _stored;
        }
        ExecInvokingOperation.setElement(_array, o_, res_.getStruct(), _conf.getContext());
        return res_.getStruct();
    }
    private Struct semiAffectArray(Struct _array, Struct _stored, Argument _index, int _indexEl, String _op, boolean _post, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        Struct o_ = _index.getStruct();
        Argument left_ = new Argument();
        left_.setStruct(_stored);
        ClassArgumentMatching clArg_ = getResultClass();
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _conf.getContext(), _op, clArg_);
        ExecInvokingOperation.setElement(_array, o_, res_.getStruct(), _conf.getContext());
        Argument out_ = RendSemiAffectationOperation.getPrePost(_post, left_, res_);
        return out_.getStruct();
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right) {
        processArray(_nodes, _conf, _right);
        return _right;
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right) {
        processArray(_nodes, _conf, _right);
        return RendSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private void processArray(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right) {
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        RendDynOperationNode lastElement_ = chidren_.last();
        Argument index_ = getArgument(_nodes,lastElement_);
        ExecInvokingOperation.setElement(array_, index_.getStruct(), _right.getStruct(), _conf.getContext());
    }

    public ClassArgumentMatching getPrevious() {
        return previous;
    }
}
