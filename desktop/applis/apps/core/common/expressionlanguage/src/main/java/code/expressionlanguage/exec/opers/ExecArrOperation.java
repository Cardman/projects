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
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;
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
            op_.setRelativeOffsetPossibleLastPage(_stack);
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
        setRelativeOffsetPossibleLastPage(_stack);
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
    public Argument calculateCompoundSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op, Argument _right, byte _cast, StackCall _stack) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, _cast, _stack);
        return commonSetting(_nodes, _conf, res_, _stack);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right, StringList _cl, StackCall _stack) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _cl, _stack);
        return commonSetting(_nodes, _conf, res_, _stack);
    }

    private Argument commonSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right, StackCall _stack) {
        if (_conf.callsOrException(_stack)) {
            return _right;
        }
        Struct array_ = getPreviousArgument(_nodes, this).getStruct();
        Argument index_ = getLastArgument(_nodes, this);
        setParts(_conf, _right, _stack, array_, index_);
        return _right;
    }

    public static void setParts(ContextEl _conf, Argument _right, StackCall _stack, Struct _array, Argument _index) {
        Struct o_ = _index.getStruct();
        if (o_ instanceof RangeStruct) {
            ExecTemplates.setRange(_array, (RangeStruct) o_, _right.getStruct(), _conf, _stack);
        } else {
            ExecTemplates.setElement(_array, o_, _right.getStruct(), _conf, _stack);
        }
    }

}
