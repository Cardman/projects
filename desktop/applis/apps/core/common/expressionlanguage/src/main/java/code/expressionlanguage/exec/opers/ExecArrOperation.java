package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.IndexConstants;

public final class ExecArrOperation extends ExecInvokingOperation implements ExecSettableElResult {

    private final ExecArrContent arrContent;

    public ExecArrOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecArrContent _arrContent) {
        super(_opCont, _intermediateDottedOperation);
        arrContent = _arrContent;
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
        for (int i = IndexConstants.FIRST_INDEX; i < _maxIndexChildren; i++) {
            ExecOperationNode op_ = chidren_.get(i);
            Struct o_ = getArgument(_nodes,op_).getStruct();
            int indexEl_ = chidren_.get(i).getIndexInEl();
            setRelativeOffsetPossibleLastPage(indexEl_, _conf);
            array_ = ExecTemplates.getElement(array_, o_, _conf);
            if (_conf.callsOrException()) {
                return new Argument();
            }
        }
        return new Argument(array_);
    }

    public boolean resultCanBeSet() {
        return arrContent.isVariable();
    }

    @Override
    public Argument calculateSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            Argument _right) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode lastElement_ = chidren_.last();
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        Argument lastArg_ = getArgument(_nodes, lastElement_);
        return new Argument(affectArray(array_, lastArg_, _right, _conf));
    }

    @Override
    public Argument calculateCompoundSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        ExecOperationNode lastElement_ = chidren_.last();
        Struct array_;
        array_ = getPreviousArgument(_nodes, this).getStruct();
        Argument lastArg_ = getArgument(_nodes, lastElement_);
        return new Argument(compoundAffectArray(array_, store_, lastArg_, _op,_right, _conf,_cl, _cast));
    }

    @Override
    public Argument calculateSemiSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post, byte _cast) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        ExecOperationNode lastElement_ = chidren_.last();
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        Argument lastArg_ = getArgument(_nodes, lastElement_);
        return new Argument(semiAffectArray(array_, store_, lastArg_, _op, _post, _conf, _cast));
    }

    private Struct affectArray(Struct _array, Argument _index, Argument _right, ContextEl _conf) {
        Struct o_ = _index.getStruct();
        ExecTemplates.setElement(_array, o_, _right.getStruct(), _conf);
        return _right.getStruct();
    }

    private Struct compoundAffectArray(Struct _array, Struct _stored, Argument _index, String _op, Argument _right, ContextEl _conf, ExecClassArgumentMatching _arg, byte _cast) {
        Struct o_ = _index.getStruct();
        Argument left_ = new Argument(_stored);
        Argument res_;
        res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, arrContent.isCatString(), _arg.getNames(), _cast);
        if (_conf.callsOrException()) {
            return _stored;
        }
        ExecTemplates.setElement(_array, o_, res_.getStruct(), _conf);
        return res_.getStruct();
    }
    private Struct semiAffectArray(Struct _array, Struct _stored, Argument _index, String _op, boolean _post, ContextEl _conf, byte _cast) {
        Struct o_ = _index.getStruct();
        Argument left_ = new Argument(_stored);
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        ExecTemplates.setElement(_array, o_, res_.getStruct(), _conf);
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
        ExecTemplates.setElement(array_, index_.getStruct(), _right.getStruct(), _conf);
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
        ExecTemplates.setElement(array_, index_.getStruct(), _right.getStruct(), _conf);
        return ExecSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }
}
