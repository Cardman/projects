package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.opers.ExecArrOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.RangeStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.IndexConstants;

public final class RendArrOperation extends RendInvokingOperation implements RendCalculableOperation,RendSettableElResult {

    private final ExecArrContent arrContent;

    public RendArrOperation(boolean _intermediate, ExecOperationContent _content, ExecArrContent _arrContent) {
        super(_content,_intermediate);
        arrContent = _arrContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        int max_ = chidren_.size();
        if (resultCanBeSet()) {
            max_--;
        }
        Argument a_ = null;
        Argument previousArgument_ = getPreviousArgument(_nodes, this);
        Struct array_ = previousArgument_.getStruct();
        for (int i = IndexConstants.FIRST_INDEX; i < max_; i++) {
            Struct o_ = getArgument(_nodes, chidren_.get(i)).getStruct();
            chidren_.get(i).setRelativeOffsetPossibleLastPage(_rendStack);
            if (o_ instanceof RangeStruct) {
                array_ = ExecArrayTemplates.getRange(array_, o_, _context, _rendStack.getStackCall());
            } else {
                array_ = ExecArrayTemplates.getElement(array_, o_, _context, _rendStack.getStackCall());
            }
            if (_context.callsOrException(_rendStack.getStackCall())) {
                a_ = new Argument();
                break;
            }
        }
        if (a_ == null) {
            a_ = new Argument(array_);
        }
        setRelativeOffsetPossibleLastPage(_rendStack);
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(a_, _nodes, _context, _rendStack);
        } else {
            getArgumentPair(_nodes,this).setArgumentParent(previousArgument_);
            setSimpleArgument(a_, _nodes, _context, _rendStack);
        }
    }

    @Override
    public boolean resultCanBeSet() {
        return arrContent.isVariable();
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, ContextEl _context, RendStackCall _rendStack) {
        return processArray(_nodes,_right,_context,_rendStack);
    }

    private Argument processArray(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, ContextEl _context, RendStackCall _stackCall) {
        if (_context.callsOrException(_stackCall.getStackCall())) {
            return _right;
        }
        Struct array_ = getPreviousArgument(_nodes,this).getStruct();
        RendDynOperationNode lastElement_ = getLastNode(this);
        Argument index_ = getArgument(_nodes,lastElement_);
        ExecArrOperation.setParts(_context, _right, _stackCall.getStackCall(),array_,index_);
        return _right;
    }

}
