package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.ArrOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecArrOperation extends ExecReflectableInvokingOperation implements ExecSettableElResult {

    private boolean variable;

    private boolean catString;

    public ExecArrOperation(ArrOperation _arr) {
        super(_arr);
        variable = _arr.resultCanBeSet();
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
            setQuickSimpleArgument(a_, _conf, _nodes);
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
            NumberStruct o_ = (NumberStruct) getArgument(_nodes,op_).getStruct();
            int indexEl_ = chidren_.get(i).getIndexInEl();
            setRelativeOffsetPossibleLastPage(indexEl_, _conf);
            array_ = ExecInvokingOperation.getElement(array_, o_, _conf);
            if (_conf.hasExceptionOrFailInit()) {
                return a_;
            }
        }
        a_.setStruct(array_);
        return a_;
    }

    static void setCheckedElement(Struct _array,NumberStruct _index, Argument _element, ExecutableCode _conf) {
        ExecInvokingOperation.setElement(_array, _index, _element.getStruct(), _conf);
    }

    public boolean resultCanBeSet() {
        return variable;
    }

    @Override
    public Argument calculateSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            Argument _right) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument(_nodes,this);
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        ExecOperationNode lastElement_ = chidren_.last();
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        Argument lastArg_ = getArgument(_nodes, lastElement_);
        a_.setStruct(affectArray(array_, lastArg_, lastElement_.getIndexInEl(), _right, _conf));
        setSimpleArgument(a_, _conf, _nodes);
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
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        ExecOperationNode lastElement_ = chidren_.last();
        Struct array_;
        array_ = getPreviousArgument(_nodes, this).getStruct();
        Argument lastArg_ = getArgument(_nodes, lastElement_);
        a_.setStruct(compoundAffectArray(array_, store_, lastArg_, lastElement_.getIndexInEl(), _op,_right, _conf));
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }

    @Override
    public Argument calculateSemiSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        ExecOperationNode lastElement_ = chidren_.last();
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        Argument lastArg_ = getArgument(_nodes, lastElement_);
        a_.setStruct(semiAffectArray(array_, store_, lastArg_, lastElement_.getIndexInEl(), _op, _post, _conf));
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }

    Struct affectArray(Struct _array,Argument _index, int _indexEl, Argument _right, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        NumberStruct o_ = (NumberStruct)_index.getStruct();
        ExecInvokingOperation.setElement(_array, o_, _right.getStruct(), _conf);
        return _right.getStruct();
    }

    Struct compoundAffectArray(Struct _array,Struct _stored,Argument _index, int _indexEl, String _op, Argument _right, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        NumberStruct o_ = (NumberStruct)_index.getStruct();
        Argument left_ = new Argument();
        left_.setStruct(_stored);
        ClassArgumentMatching clArg_ = getResultClass();
        Argument res_;
        res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, clArg_);
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return _stored;
        }
        ExecInvokingOperation.setElement(_array, o_, res_.getStruct(), _conf);
        return res_.getStruct();
    }
    Struct semiAffectArray(Struct _array,Struct _stored,Argument _index, int _indexEl, String _op, boolean _post, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        NumberStruct o_ = (NumberStruct)_index.getStruct();
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
        return endCalculate(_conf, _nodes, false, null, _right);
    }

    @Override
    public Argument endCalculate(ContextEl _conf,
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post,
            Argument _stored, Argument _right) {
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        ExecOperationNode lastElement_ = chidren_.last();
        Argument index_ = getArgument(_nodes, lastElement_);
        ExecInvokingOperation.setElement(array_, (NumberStruct)index_.getStruct(), _right.getStruct(), _conf);
        Argument out_ = ExecSemiAffectationOperation.getPrePost(_post, _stored, _right);
        setSimpleArgument(out_, _conf, _nodes);
        return out_;
    }
}
