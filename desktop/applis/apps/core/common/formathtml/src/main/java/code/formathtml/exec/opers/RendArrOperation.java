package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendArrOperation extends RendInvokingOperation implements RendCalculableOperation,RendSettableElResult {

    private ExecArrContent arrContent;

    public RendArrOperation(boolean _intermediate, ExecOperationContent _content, ExecArrContent _arrContent) {
        super(_content,_intermediate);
        arrContent = _arrContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        int max_ = chidren_.size();
        if (resultCanBeSet()) {
            max_--;
        }
        Argument a_ = getArgument(_nodes,max_, _conf, _context);
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(a_, _nodes, _context);
        } else {
            setSimpleArgument(a_, _conf,_nodes, _context);
        }
    }

    Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, int _maxIndexChildren, Configuration _conf, ContextEl _context) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        for (int i = CustList.FIRST_INDEX; i < _maxIndexChildren; i++) {
            Struct o_ = getArgument(_nodes, chidren_.get(i)).getStruct();
            int indexEl_ = chidren_.get(i).getIndexInEl();
            setRelativeOffsetPossibleLastPage(indexEl_, _conf);
            array_ = ExecTemplates.getElement(array_, o_, _context);
            if (_context.callsOrException()) {
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
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        RendDynOperationNode lastElement_ = chidren_.last();
        Argument last_ = getArgument(_nodes,lastElement_);
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        return new Argument(affectArray(array_, last_, lastElement_.getIndexInEl(), _right, _conf, _context));
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, BeanLgNames _advStandards, ContextEl _context) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        RendDynOperationNode lastElement_ = chidren_.last();
        Argument last_ = getArgument(_nodes,lastElement_);
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        return new Argument(compoundAffectArray(array_, store_, last_, lastElement_.getIndexInEl(), _op, _right, _conf, _cl, _cast, _context));
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _store, byte _cast, BeanLgNames _advStandards, ContextEl _context) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        Struct store_;
        store_ = _store.getStruct();
        RendDynOperationNode lastElement_ = chidren_.last();
        Argument last_ = getArgument(_nodes,lastElement_);
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        return new Argument(semiAffectArray(array_, store_, last_, lastElement_.getIndexInEl(), _op, _post, _conf, _cast, _context));
    }

    private Struct affectArray(Struct _array, Argument _index, int _indexEl, Argument _right, Configuration _conf, ContextEl _context) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        Struct o_ = _index.getStruct();
        ExecTemplates.setElement(_array, o_, _right.getStruct(), _context);
        return _right.getStruct();
    }

    private Struct compoundAffectArray(Struct _array, Struct _stored, Argument _index, int _indexEl, String _op, Argument _right, Configuration _conf, ExecClassArgumentMatching _cl, byte _cast, ContextEl _context) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        Struct o_ = _index.getStruct();
        Argument left_ = new Argument(_stored);
        Argument res_;
        res_ = RendNumericOperation.calculateAffect(left_, _conf, _right, _op, arrContent.isCatString(), _cl.getNames(), _cast, _context);
        if (_context.callsOrException()) {
            return _stored;
        }
        ExecTemplates.setElement(_array, o_, res_.getStruct(), _context);
        return res_.getStruct();
    }
    private Struct semiAffectArray(Struct _array, Struct _stored, Argument _index, int _indexEl, String _op, boolean _post, Configuration _conf, byte _cast, ContextEl _context) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        Struct o_ = _index.getStruct();
        Argument left_ = new Argument(_stored);
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        ExecTemplates.setElement(_array, o_, res_.getStruct(), _context);
        Argument out_ = RendSemiAffectationOperation.getPrePost(_post, left_, res_);
        return out_.getStruct();
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        processArray(_nodes, _conf, _right, _context);
        return _right;
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        processArray(_nodes, _conf, _right, _context);
        return RendSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private void processArray(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, ContextEl _context) {
        Struct array_;
        array_ = getPreviousArgument(_nodes,this).getStruct();
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        RendDynOperationNode lastElement_ = chidren_.last();
        Argument index_ = getArgument(_nodes,lastElement_);
        ExecTemplates.setElement(array_, index_.getStruct(), _right.getStruct(), _context);
    }

}
