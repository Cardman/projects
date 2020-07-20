package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ArrOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecArrOperation extends ExecInvokingOperation implements ExecSettableElResult {

    private boolean variable;

    private boolean catString;

    public ExecArrOperation(ArrOperation _arr) {
        super(_arr);
        variable = _arr.isVariable();
        catString = _arr.isCatString();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        int max_ = chidren_.size();
        if (resultCanBeSet()) {
            max_--;
        }
        Argument a_ = getArgument(_nodes, max_, _conf);
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(a_, _conf, _nodes);
        } else {
            setSimpleArgument(a_, _conf, _nodes);
        }
    }

    Argument getArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            int _maxIndexChildren, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        Argument a_ = new Argument();
        for (int i = CustList.FIRST_INDEX; i < _maxIndexChildren; i++) {
            ExecOperationNode op_ = chidren_.get(i);
            Struct o_ = getArgument(_nodes,op_).getStruct();
            int indexEl_ = chidren_.get(i).getIndexInEl();
            setRelativeOffsetPossibleLastPage(indexEl_, _conf);
            array_ = ExecInvokingOperation.getElement(array_, o_, _conf);
            if (_conf.callsOrException()) {
                return a_;
            }
        }
        a_.setStruct(array_);
        return a_;
    }

    public boolean resultCanBeSet() {
        return variable;
    }

    @Override
    public Argument calculateSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            Argument _right) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = new Argument();
        ExecOperationNode lastElement_ = chidren_.last();
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        Argument lastArg_ = getArgument(_nodes, lastElement_);
        a_.setStruct(affectArray(array_, lastArg_, lastElement_.getIndexInEl(), _right, _conf));
        return a_;
    }

    @Override
    public Argument calculateCompoundSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, Argument _right) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        ExecOperationNode lastElement_ = chidren_.last();
        Struct array_;
        array_ = getPreviousArgument(_nodes, this).getStruct();
        Argument lastArg_ = getArgument(_nodes, lastElement_);
        Argument o_ = new Argument();
        o_.setStruct(compoundAffectArray(array_, store_, lastArg_, lastElement_.getIndexInEl(), _op,_right, _conf,getResultClass()));
        return o_;
    }

    @Override
    public Argument calculateSemiSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        ExecOperationNode lastElement_ = chidren_.last();
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        Argument lastArg_ = getArgument(_nodes, lastElement_);
        Argument o_ = new Argument();
        o_.setStruct(semiAffectArray(array_, store_, lastArg_, _op, _post, _conf));
        return o_;
    }

    Struct affectArray(Struct _array,Argument _index, int _indexEl, Argument _right, ContextEl _conf) {
        Struct o_ = _index.getStruct();
        ExecInvokingOperation.setElement(_array, o_, _right.getStruct(), _conf);
        return _right.getStruct();
    }

    Struct compoundAffectArray(Struct _array,Struct _stored,Argument _index, int _indexEl, String _op, Argument _right, ContextEl _conf, ClassArgumentMatching _arg) {
        Struct o_ = _index.getStruct();
        Argument left_ = new Argument();
        left_.setStruct(_stored);
        Argument res_;
        res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, _arg);
        if (_conf.callsOrException()) {
            return _stored;
        }
        ExecInvokingOperation.setElement(_array, o_, res_.getStruct(), _conf);
        return res_.getStruct();
    }
    Struct semiAffectArray(Struct _array, Struct _stored, Argument _index, String _op, boolean _post, ContextEl _conf) {
        Struct o_ = _index.getStruct();
        Argument left_ = new Argument();
        left_.setStruct(_stored);
        ClassArgumentMatching clArg_ = getResultClass();
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _conf, _op, clArg_);
        ExecInvokingOperation.setElement(_array, o_, res_.getStruct(), _conf);
        Argument out_ = ExecSemiAffectationOperation.getPrePost(_post, left_, res_);
        return out_.getStruct();
    }

    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        Struct array_;
        array_ = getPreviousArgument(_nodes, this).getStruct();
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode lastElement_ = chidren_.last();
        Argument index_ = getArgument(_nodes, lastElement_);
        ExecInvokingOperation.setElement(array_, index_.getStruct(), _right.getStruct(), _conf);
        return _right;
    }

    @Override
    public Argument endCalculate(ContextEl _conf,
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post,
            Argument _stored, Argument _right) {
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode lastElement_ = chidren_.last();
        Argument index_ = getArgument(_nodes, lastElement_);
        ExecInvokingOperation.setElement(array_, index_.getStruct(), _right.getStruct(), _conf);
        return ExecSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }
}
