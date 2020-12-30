package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        int max_ = chidren_.size();
        if (resultCanBeSet()) {
            max_--;
        }
        Argument a_ = getArgument(_nodes,max_, _context, _stack, _rendStack);
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(a_, _nodes, _context, _stack);
        } else {
            setSimpleArgument(a_, _nodes, _context, _stack, _rendStack);
        }
    }

    Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, int _maxIndexChildren, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        for (int i = IndexConstants.FIRST_INDEX; i < _maxIndexChildren; i++) {
            Struct o_ = getArgument(_nodes, chidren_.get(i)).getStruct();
            int indexEl_ = chidren_.get(i).getIndexInEl();
            setRelativeOffsetPossibleLastPage(indexEl_, _rendStackCall);
            array_ = ExecTemplates.getElement(array_, o_, _context, _stackCall);
            if (_context.callsOrException(_stackCall)) {
                return new Argument();
            }
        }
        return new Argument(array_);
    }

    @Override
    public boolean resultCanBeSet() {
        return arrContent.isVariable();
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        RendDynOperationNode lastElement_ = getLastNode(this);
        Argument last_ = getArgument(_nodes,lastElement_);
        Struct array_ = getPreviousArgument(_nodes,this).getStruct();
        return new Argument(affectArray(array_, last_, lastElement_.getIndexInEl(), _right, _context, _stack, _rendStack));
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        RendDynOperationNode lastElement_ = getLastNode(this);
        Argument last_ = getArgument(_nodes,lastElement_);
        Struct array_ = getPreviousArgument(_nodes,this).getStruct();
        return new Argument(compoundAffectArray(array_, store_, last_, lastElement_.getIndexInEl(), _op, _right, _cl, _cast, _context, _stack, _rendStack));
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _store, byte _cast, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Struct store_ = _store.getStruct();
        RendDynOperationNode lastElement_ = getLastNode(this);
        Argument last_ = getArgument(_nodes,lastElement_);
        Struct array_ = getPreviousArgument(_nodes,this).getStruct();
        return new Argument(semiAffectArray(array_, store_, last_, lastElement_.getIndexInEl(), _op, _post, _cast, _context, _stack, _rendStack));
    }

    private Struct affectArray(Struct _array, Argument _index, int _indexEl, Argument _right, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        setRelativeOffsetPossibleLastPage(_indexEl, _rendStackCall);
        Struct o_ = _index.getStruct();
        ExecTemplates.setElement(_array, o_, _right.getStruct(), _context, _stackCall);
        return _right.getStruct();
    }

    private Struct compoundAffectArray(Struct _array, Struct _stored, Argument _index, int _indexEl, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        setRelativeOffsetPossibleLastPage(_indexEl, _rendStackCall);
        Struct o_ = _index.getStruct();
        Argument left_ = new Argument(_stored);
        Argument res_ = RendNumericOperation.calculateAffect(left_, _right, _op, arrContent.isCatString(), _cl.getNames(), _cast, _context, _stackCall);
        if (_context.callsOrException(_stackCall)) {
            return _stored;
        }
        ExecTemplates.setElement(_array, o_, res_.getStruct(), _context, _stackCall);
        return res_.getStruct();
    }
    private Struct semiAffectArray(Struct _array, Struct _stored, Argument _index, int _indexEl, String _op, boolean _post, byte _cast, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        setRelativeOffsetPossibleLastPage(_indexEl, _rendStackCall);
        Struct o_ = _index.getStruct();
        Argument left_ = new Argument(_stored);
        Argument res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        ExecTemplates.setElement(_array, o_, res_.getStruct(), _context, _stackCall);
        Argument out_ = RendSemiAffectationOperation.getPrePost(_post, left_, res_);
        return out_.getStruct();
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        processArray(_nodes, _right, _context, _stack);
        return _right;
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        processArray(_nodes, _right, _context, _stack);
        return RendSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private void processArray(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, ContextEl _context, StackCall _stackCall) {
        Struct array_ = getPreviousArgument(_nodes,this).getStruct();
        RendDynOperationNode lastElement_ = getLastNode(this);
        Argument index_ = getArgument(_nodes,lastElement_);
        ExecTemplates.setElement(array_, index_.getStruct(), _right.getStruct(), _context, _stackCall);
    }

}
