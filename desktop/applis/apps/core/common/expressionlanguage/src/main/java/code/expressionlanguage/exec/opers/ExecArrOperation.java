package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.RangeStruct;
import code.expressionlanguage.structs.Struct;
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
        Struct a_ = null;
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int max_ = chidren_.size();
        if (resultCanBeSet()) {
            max_--;
        }
        Struct previousArgument_ = getPreviousArgument(_nodes, this);
        Struct array_ = previousArgument_;
        for (int i = IndexConstants.FIRST_INDEX; i < max_; i++) {
            ExecOperationNode op_ = chidren_.get(i);
            Struct o_ = getArgument(_nodes,op_);
            op_.setRelativeOffsetPossibleLastPage(_stack);
            if (o_ instanceof RangeStruct) {
                array_ = ExecArrayTemplates.getRange(array_, o_, _conf, _stack);
            } else {
                array_ = ExecArrayTemplates.getElement(array_, o_, _conf, _stack);
            }
            if (_conf.callsOrException(_stack)) {
                a_ = NullStruct.NULL_VALUE;
                break;
            }
        }
        if (a_ == null) {
            a_ = array_;
        }
        setRelativeOffsetPossibleLastPage(_stack);
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(a_, _conf, _nodes, _stack);
        } else {
            ExecHelper.getArgumentPair(_nodes,this).setArgumentParent(previousArgument_);
            setSimpleArgument(a_, _conf, _nodes, _stack);
        }
    }

    public boolean resultCanBeSet() {
        return arrContent.isVariable();
    }

    @Override
    public Struct calculateSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            Struct _right, StackCall _stack) {
        return commonSetting(_nodes, _conf, _right, _stack);
    }

    private Struct commonSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _right, StackCall _stack) {
        if (_conf.callsOrException(_stack)) {
            return _right;
        }
        Struct array_ = getPreviousArgument(_nodes, this);
        Struct index_ = getLastArgument(_nodes, this);
        setParts(_conf, _right, _stack, array_, index_);
        return _right;
    }

    public static void setParts(ContextEl _conf, Struct _right, StackCall _stack, Struct _array, Struct _index) {
        if (_index instanceof RangeStruct) {
            ExecArrayTemplates.setRange(_array, _index, _right, _conf, _stack);
        } else {
            ExecArrayTemplates.setElement(_array, _index, _right, _conf, _stack);
        }
    }

}
