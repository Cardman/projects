package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.RangeStruct;
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
                          ContextEl _conf, StackCall _stack) {
        setRelativeOffsetPossibleLastPage(_stack);
        Argument a_ = null;
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int max_ = chidren_.size();
        if (resultCanBeSet()) {
            max_--;
        }
        Struct array_ = getPreviousArgument(_nodes, this).getStruct();
        for (int i = IndexConstants.FIRST_INDEX; i < max_; i++) {
            ExecOperationNode op_ = chidren_.get(i);
            Struct o_ = getArgument(_nodes,op_).getStruct();
            int indexEl_ = op_.getIndexInEl();
            setRelativeOffsetPossibleLastPage(indexEl_, _stack);
            if (o_ instanceof RangeStruct) {
                array_ = ExecTemplates.getRange(array_, o_, _conf, _stack);
            } else {
                array_ = ExecTemplates.getElement(array_, o_, _conf, _stack);
            }
            if (_conf.callsOrException(_stack)) {
                a_ = new Argument();
                break;
            }
        }
        if (a_ == null) {
            a_ = new Argument(array_);
        }
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(a_, _conf, _nodes, _stack);
        } else {
            setSimpleArgument(a_, _conf, _nodes, _stack);
        }
    }

    public boolean resultCanBeSet() {
        return arrContent.isVariable();
    }

    @Override
    public Argument calculateSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            Argument _right, StackCall _stack) {
        return commonSetting(_nodes, _conf, _right, _stack);
    }

    @Override
    public Argument calculateCompoundString(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right, StackCall _stack) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = ExecCatOperation.localSumDiff(left_, _right, _conf);
        return commonSetting(_nodes, _conf, res_, _stack);
    }

    @Override
    public Argument calculateCompoundSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, StackCall _stack) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, _cl.getNames(), _cast, _stack);
        return commonSetting(_nodes, _conf, res_, _stack);
    }

    @Override
    public Argument calculateSemiSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post, byte _cast, StackCall _stack) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        commonSetting(_nodes,_conf,res_,_stack);
        Argument out_ = ExecSemiAffectationOperation.getPrePost(_post, left_, res_);
        return new Argument(out_.getStruct());
    }

    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        return commonSetting(_nodes, _conf, _right, _stack);
    }

    @Override
    public Argument endCalculate(ContextEl _conf,
                                 IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post,
                                 Argument _stored, Argument _right, StackCall _stack) {
        commonSetting(_nodes,_conf,_right,_stack);
        return ExecSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private Argument commonSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right, StackCall _stack) {
        if (_conf.callsOrException(_stack)) {
            return _right;
        }
        Struct array_ = getPreviousArgument(_nodes, this).getStruct();
        Argument index_ = getLastArgument(_nodes, this);
        Struct o_ = index_.getStruct();
        ExecTemplates.setElement(array_, o_, _right.getStruct(), _conf, _stack);
        return _right;
    }

}
