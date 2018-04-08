package code.expressionlanguage;
import code.expressionlanguage.exceptions.BadExpressionLanguageException;
import code.expressionlanguage.exceptions.BadIndexException;
import code.expressionlanguage.exceptions.CustomFoundConstructorException;
import code.expressionlanguage.exceptions.CustomFoundMethodException;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.FinalMemberException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.exceptions.InvokeRedinedMethException;
import code.expressionlanguage.exceptions.NegativeSizeException;
import code.expressionlanguage.exceptions.NotInitializedClassException;
import code.expressionlanguage.exceptions.PrimitiveTypeException;
import code.expressionlanguage.exceptions.SettingMemberException;
import code.expressionlanguage.exceptions.UnwrappingException;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.exceptions.AnalyzingErrorsException;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.ExpLanguages;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ConstantOperation;
import code.expressionlanguage.opers.DotOperation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.MethodOperation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.SettableElResult;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.exceptions.NullObjectException;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class ElUtil {

    private static final String RETURN_LINE = "\n";
    private static final String EMPTY_STRING = "";
    private ElUtil() {
    }
    
    public static void tryToCalculateAffect(CustList<OperationNode> _left, ContextEl _conf, CustList<OperationNode> _right, String _op) {
        CustList<OperationNode> allLeft_ = _left;
        calculate(allLeft_ , _conf, _op);
        CustList<OperationNode> allRight_ = _right;
        calculate(allRight_, _conf, _op);
        _conf.getLastPage().setRightArgument(_right.last().getArgument());
        SettableElResult settable_ =  ExpressionLanguage.getSettable(_left);
        settable_.calculateSetting(allLeft_, _conf, _op);
        _conf.getLastPage().setRightArgument(null);
    }
    public static ExpLanguages getAnalyzedAffectation(int _attrOp, int _attrLeft, int _attrRight,
            String _left, String _right, String _oper, ContextEl _conf, boolean _staticContext, boolean _hiddenVarTypes) {
        PageEl page_ = _conf.getLastPage();
        page_.setOffset(0);
        page_.setGlobalOffset(_attrLeft);
        Delimiters dLeft_ = ElResolver.checkSyntax(_left, _conf, CustList.FIRST_INDEX);
        if (dLeft_.getBadOffset() >= 0) {
            _conf.getLastPage().setOffset(dLeft_.getBadOffset());
            throw new BadExpressionLanguageException(StringList.concat(Integer.toString(dLeft_.getBadOffset()),RETURN_LINE,_left,RETURN_LINE,_conf.joinPages()));
        }
        OperationsSequence opTwoLeft_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _left, _conf, dLeft_);
        OperationNode opLeft_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwoLeft_);
        if (opLeft_ == null) {
            throw new BadExpressionLanguageException(StringList.concat(_left,RETURN_LINE,_conf.joinPages()));
        }
        CustList<OperationNode> allLeft_ = getSortedDescNodes(opLeft_, _conf);
        page_.setOffset(0);
        page_.setGlobalOffset(_attrRight);
        Delimiters dRight_ = ElResolver.checkSyntax(_right, _conf, CustList.FIRST_INDEX);
        if (dRight_.getBadOffset() >= 0) {
            _conf.getLastPage().setOffset(dRight_.getBadOffset());
            throw new BadExpressionLanguageException(StringList.concat(Integer.toString(dRight_.getBadOffset()),RETURN_LINE,_right,RETURN_LINE,_conf.joinPages()));
        }
        OperationsSequence opTwoRight_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _right, _conf, dRight_);
        OperationNode opRight_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwoRight_);
        if (opRight_ == null) {
            throw new BadExpressionLanguageException(StringList.concat(_right,RETURN_LINE,_conf.joinPages()));
        }
        CustList<OperationNode> allRight_ = getSortedDescNodes(opRight_, _conf);
        page_.setOffset(0);
        page_.setGlobalOffset(_attrLeft);
        analyzeSetting(true, allLeft_, _conf);
        analyze(allLeft_, _conf, _staticContext, _hiddenVarTypes, EMPTY_STRING, _oper);
        analyzeSetting(false, allLeft_, _conf);
        page_.setOffset(0);
        page_.setGlobalOffset(_attrRight);
        analyze(allRight_, _conf, _staticContext, _hiddenVarTypes, EMPTY_STRING, _oper);
        page_.setOffset(0);
        page_.setGlobalOffset(_attrLeft);
        ClassArgumentMatching clMatchRight_ = opRight_.getResultClass();
        ClassArgumentMatching clMatchLeft_ = opLeft_.getResultClass();
        page_.setOffset(0);
        if (_attrOp >= 0) {
            page_.setGlobalOffset(_attrOp);
        }
        if (_oper.length() == 2) {
            if (StringList.quickEq(_oper, Block.EQ_PLUS) || StringList.quickEq(_oper, Block.PLUS_EQ)) {
                if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_, _conf)) {
                    if (!clMatchLeft_.matchClass(_conf.getStandards().getAliasString())) {
                        throw new DynamicCastClassException(_conf.joinPages());
                    }
                } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchRight_, _conf)) {
                    throw new DynamicCastClassException(_conf.joinPages());
                }
            } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_, _conf)) {
                throw new DynamicCastClassException(_conf.joinPages());
            } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchRight_, _conf)) {
                throw new DynamicCastClassException(_conf.joinPages());
            }
        } else {
            if (clMatchRight_.isVariable()) {
                if (!clMatchLeft_.isPrimitive(_conf)) {
                    return new ExpLanguages(allLeft_, allRight_);
                }
                throw new PrimitiveTypeException(_conf.joinPages());
            }
            StringMap<StringList> vars_ = new StringMap<StringList>();
            boolean buildMap_ = true;
            if (_staticContext) {
                buildMap_ = false;
            } else if (page_.getGlobalClass() == null) {
                buildMap_ = false;
            }
            if (buildMap_) {
                for (TypeVar t: Templates.getConstraints(page_.getGlobalClass(), _conf)) {
                    vars_.put(t.getName(), t.getConstraints());
                }
            }
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(vars_);
            mapping_.setArg(clMatchRight_.getName());
            mapping_.setParam(clMatchLeft_.getName());
            if (!Templates.isCorrect(mapping_, _conf)) {
                throw new DynamicCastClassException(_conf.joinPages());
            }
        }
        return new ExpLanguages(allLeft_, allRight_);
    }
    public static Argument tryToCalculate(ContextEl _conf, ExpressionLanguage _right, int _offset) {
        if (_right.isFinished()) {
            return _right.getArgument();
        }
        IdMap<OperationNode, ArgumentsPair> allRight_ = _right.getArguments();
        calculate(allRight_, _right, _conf, EMPTY_STRING, _offset);
        _right.finish();
        Argument arg_ = _right.getArgument();
        return arg_;
    }
    public static void tryToCalculateLeftAffect(ExpressionLanguage _left, ContextEl _conf, String _op) {
        if (_left.isFinished()) {
            return;
        }
        IdMap<OperationNode, ArgumentsPair> allLeft_ = _left.getArguments();
        calculate(allLeft_, _left, _conf, _op, 0);
        _left.finish();
    }
    public static void tryToCalculateRightAffect(ExpressionLanguage _right, ContextEl _conf, String _op) {
        if (_right.isFinished()) {
            return;
        }
        IdMap<OperationNode, ArgumentsPair> allRight_ = _right.getArguments();
        calculate(allRight_, _right, _conf, _op, 0);
        _right.finish();
        _conf.getLastPage().setRightArgument(_right.getArgument());
    }
    public static void tryToCalculateAllAffect(ExpressionLanguage _left, ContextEl _conf, String _op) {
        IdMap<OperationNode, ArgumentsPair> allLeft_ = _left.getArguments();
        SettableElResult settable_ = _left.getSettable();
        OperationNode op_ = (OperationNode) settable_;
        ArgumentsPair a_ = allLeft_.getVal(op_);
        try {
            a_.setArgument(settable_.calculateSetting(allLeft_, _conf, _op));
        } catch (RuntimeException _0) {
            _left.setCurrentOper(null);
            _conf.getLastPage().clearCurrentEls();
            throw _0;
        } catch (Error _0) {
            _left.setCurrentOper(null);
            _conf.getLastPage().clearCurrentEls();
            throw _0;
        }
        _conf.getLastPage().setRightArgument(null);
    }

    public static Argument processEl(String _el, ContextEl _conf, int _minIndex, char _begin, char _end) {
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(_el, _conf, _minIndex, _begin, _end);
        if (d_.getBadOffset() >= 0) {
            _conf.getLastPage().setOffset(d_.getBadOffset());
            throw new BadExpressionLanguageException(StringList.concat(Integer.toString(d_.getBadOffset()),RETURN_LINE,_el,RETURN_LINE,_conf.joinPages()));
        }
        String el_ = _el.substring(d_.getIndexBegin(), d_.getIndexEnd()+1);
        _conf.setNextIndex(d_.getIndexEnd()+2);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(_minIndex, el_, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(_minIndex, CustList.FIRST_INDEX, null, opTwo_);
        if (op_ == null) {
            throw new BadExpressionLanguageException(StringList.concat(_el,RETURN_LINE,_conf.joinPages()));
        }
        CustList<OperationNode> all_ = getSortedDescNodes(op_, _conf);
        analyze(all_, _conf);
        if (!_conf.getClasses().getErrorsDet().isEmpty()) {
            throw new AnalyzingErrorsException(_conf.getClasses().getErrorsDet());
        }
        calculate(all_, _conf, EMPTY_STRING);
        Argument arg_ = op_.getArgument();
        return arg_;
    }

    public static CustList<OperationNode> getAnalyzedOperations(String _el, ContextEl _conf, Calculation _calcul) {
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, CustList.FIRST_INDEX);
        if (d_.getBadOffset() >= 0) {
            _conf.getLastPage().setOffset(d_.getBadOffset());
            throw new BadExpressionLanguageException(StringList.concat(Integer.toString(d_.getBadOffset()),RETURN_LINE,_el,RETURN_LINE,_conf.joinPages()));
        }
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _el, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwo_);
        if (op_ == null) {
            throw new BadExpressionLanguageException(StringList.concat(_el,RETURN_LINE,_conf.joinPages()));
        }
        CustList<OperationNode> all_ = getSortedDescNodes(op_, _conf);
        boolean staticContext_ = _calcul.isStaticAcces();
        boolean hiddenVarTypes_ = _calcul.isStaticBlock();
        String fieldName_ = _calcul.getFieldName();
        String oper_ = _calcul.getOper();
        if (_calcul.isLeftStep()) {
            analyzeSetting(true, all_, _conf);
            analyze(all_, _conf, staticContext_, hiddenVarTypes_, fieldName_, oper_);
            analyzeSetting(false, all_, _conf);
        } else {
            analyze(all_, _conf, staticContext_, hiddenVarTypes_, fieldName_, oper_);
        }
        return all_;
    }

    public static Argument processEl(String _el, int _index, ContextEl _conf) {
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, _index);
        if (d_.getBadOffset() >= 0) {
            _conf.getLastPage().setOffset(d_.getBadOffset());
            throw new BadExpressionLanguageException(StringList.concat(Integer.toString(d_.getBadOffset()),RETURN_LINE,_el,RETURN_LINE,_conf.joinPages()));
        }
        String el_ = _el.substring(_index);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(_index, el_, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(_index, CustList.FIRST_INDEX, null, opTwo_);
        if (op_ == null) {
            throw new BadExpressionLanguageException(StringList.concat(_el,RETURN_LINE,_conf.joinPages()));
        }
        CustList<OperationNode> all_ = getSortedDescNodes(op_, _conf);
        analyze(all_, _conf);
        if (!_conf.getClasses().getErrorsDet().isEmpty()) {
            throw new AnalyzingErrorsException(_conf.getClasses().getErrorsDet());
        }
        calculate(all_, _conf, EMPTY_STRING);
        Argument arg_  = op_.getArgument();
        return arg_;
    }

    public static CustList<OperationNode> getSortedDescNodes(OperationNode _root, ContextEl _context) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        OperationNode c_ = _root;
        while (true) {
            if (c_ == null) {
                break;
            }
            c_ = getNext(c_, _root, list_, _context);
        }
        return list_;
    }

    private static OperationNode getNext(OperationNode _current, OperationNode _root, CustList<OperationNode> _sortedNodes, ContextEl _context) {
        OperationNode next_ = createFirstChild(_current, _context);
        if (next_ != null) {
            ((MethodOperation) _current).appendChild(next_);
            return next_;
        }
        _sortedNodes.add(_current);
        next_ = createNextSibling(_current, _context);
        if (next_ != null) {
            next_.getParent().appendChild(next_);
            return next_;
        }
        next_ = _current.getParent();
        if (next_ == _root) {
            _sortedNodes.add(next_);
            return null;
        }
        if (next_ != null) {
            _sortedNodes.add(next_);
            OperationNode nextAfter_ = createNextSibling(next_, _context);
            while (nextAfter_ == null) {
                OperationNode par_ = next_.getParent();
                if (par_ == _root) {
                    _sortedNodes.add(par_);
                    break;
                }
                if (par_ == null) {
                    break;
                }
                _sortedNodes.add(par_);
                nextAfter_ = createNextSibling(par_, _context);
                next_ = par_;
            }
            if (nextAfter_ != null) {
                nextAfter_.getParent().appendChild(nextAfter_);
                return nextAfter_;
            }
        }
        return null;
    }
    private static OperationNode createFirstChild(OperationNode _block, ContextEl _context) {
        if (!(_block instanceof MethodOperation)) {
            return null;
        }
        MethodOperation block_ = (MethodOperation) _block;
        if (block_.getChildren() == null || block_.getChildren().isEmpty()) {
            return null;
        }
        String value_ = block_.getChildren().getValue(0);
        Delimiters d_ = block_.getOperations().getDelimiter();
        int curKey_ = block_.getChildren().getKey(0);
        d_.setChildOffest(curKey_);
        int offset_ = block_.getIndexInEl()+curKey_;
        OperationsSequence r_ = ElResolver.getOperationsSequence(offset_, value_, _context, d_);
        OperationNode op_ = OperationNode.createOperationNode(offset_, CustList.FIRST_INDEX, block_, r_);
        if (op_ == null) {
            throw new BadExpressionLanguageException(StringList.concat(value_,RETURN_LINE,_context.joinPages()));
        }
        return op_;
    }

    private static OperationNode createNextSibling(OperationNode _block, ContextEl _context) {
        MethodOperation p_ = _block.getParent();
        if (p_ == null) {
            return null;
        }
        NatTreeMap<Integer,String> children_ = p_.getChildren();
        if (_block.getIndexChild() + 1 >= children_.size()) {
            return null;
        }
        String value_ = children_.getValue(_block.getIndexChild() + 1);
        Delimiters d_ = _block.getOperations().getDelimiter();
        int curKey_ = children_.getKey(_block.getIndexChild() + 1);
        d_.setChildOffest(curKey_);
        int offset_ = p_.getIndexInEl()+curKey_;
        OperationsSequence r_ = ElResolver.getOperationsSequence(offset_, value_, _context, d_);
        OperationNode op_ = OperationNode.createOperationNode(offset_, _block.getIndexChild() + 1, p_, r_);
        if (op_ == null) {
            throw new BadExpressionLanguageException(StringList.concat(value_,RETURN_LINE,_context.joinPages()));
        }
        return op_;
    }
    public static CustList<OperationNode> getDirectChildren(OperationNode _element) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        if (_element == null) {
            return list_;
        }
        OperationNode firstChild_ = _element.getFirstChild();
        OperationNode elt_ = firstChild_;
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

    public static void analyze(CustList<OperationNode> _nodes, ContextEl _context, boolean _staticContext, boolean _staticBlock,String _fieldName, String _op) {
        PageEl page_ = _context.getLastPage();
        page_.setStaticContext(_staticContext);
        for (OperationNode e: _nodes) {
            e.setStaticBlock(_staticBlock);
            e.analyze(_nodes, _context, _fieldName, _op);
        }
    }

    static void analyze(CustList<OperationNode> _nodes, ContextEl _context) {
        PageEl page_ = _context.getLastPage();
        Argument arg_ = page_.getGlobalArgument();
        boolean static_ = arg_ == null || arg_.isNull();
        page_.setStaticContext(static_);
        for (OperationNode e: _nodes) {
            e.setStaticBlock(static_);
            e.analyze(_nodes, _context, EMPTY_STRING, EMPTY_STRING);
        }
    }
    /**@throws InvokeRedinedMethException
    @throws BadIndexException
    @throws NegativeSizeException
    @throws ErrorCausingException
    @throws DynamicCastClassException
    @throws RuntimeClassNotFoundException
    @throws NullObjectException
    @throws InvokeException
    @throws UnwrappingException*/
    static void calculate(CustList<OperationNode> _nodes, ContextEl _context, String _op) {
        for (OperationNode e: _nodes) {
            if (!e.isCalculated()) {
                e.calculate(_nodes, _context, _op);
            }
        }
    }
    /**@throws InvokeRedinedMethException
    @throws CustomFoundMethodException
    @throws BadIndexException
    @throws NegativeSizeException
    @throws ErrorCausingException
    @throws DynamicCastClassException
    @throws RuntimeClassNotFoundException
    @throws NullObjectException
    @throws InvokeException
    @throws UnwrappingException*/

    static void calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ExpressionLanguage _el, ContextEl _context, String _op, int _offset) {
        _context.getLastPage().setTranslatedOffset(_offset);
        for (EntryCust<OperationNode,ArgumentsPair> e: _nodes.entryList()) {
            OperationNode o = e.getKey();
            if (!o.isCalculated(_nodes)) {
                ArgumentsPair a_ = e.getValue();
                try {
                    a_.setArgument(o.calculate(_nodes, _context, _op));
                } catch (NotInitializedClassException _0) {
                    throw _0;
                } catch (CustomFoundMethodException _0) {
                    _el.setCurrentOper(o);
                    throw _0;
                } catch (CustomFoundConstructorException _0) {
                    if (_0.getCall().getInstancingStep() != InstancingStep.USING_SUPER) {
                        _el.setCurrentOper(o);
                    } else {
                        _el.setCurrentOper(null);
                    }
                    throw _0;
                } catch (RuntimeException _0) {
                    _context.getLastPage().setTranslatedOffset(0);
                    _el.setCurrentOper(null);
                    _context.getLastPage().clearCurrentEls();
                    throw _0;
                } catch (Error _0) {
                    _context.getLastPage().setTranslatedOffset(0);
                    _el.setCurrentOper(null);
                    _context.getLastPage().clearCurrentEls();
                    throw _0;
                }
            }
        }
        _context.getLastPage().setTranslatedOffset(0);
    }
    public static void analyzeSetting(boolean _setVar,CustList<OperationNode> _nodes, ContextEl _conf) {
        OperationNode root_ = _nodes.last();
        SettableElResult elt_ = null;
        boolean ok_ = true;
        if (_nodes.size() == CustList.ONE_ELEMENT) {
            if (!(root_ instanceof SettableElResult)) {
                ok_ = false;
            } else {
                elt_ = (SettableElResult) root_;
            }
        } else {
            OperationNode beforeLast_ = _nodes.getPrev(_nodes.getLastIndex());
            if (!(root_ instanceof DotOperation)) {
                if (!(root_ instanceof SettableElResult)) {
                    ok_ = false;
                } else {
                    elt_ = (SettableElResult) root_;
                }
            } else if (!(beforeLast_ instanceof SettableElResult)){
                ok_ = false;
            } else {
                elt_ = (SettableElResult) beforeLast_;
            }
        }
        if (!ok_) {
            root_.setRelativeOffsetPossibleLastPage(root_.getIndexInEl(), _conf);
            throw new SettingMemberException(_conf.joinPages());
        }
        if (_setVar) {
            elt_.setVariable();
        } else if (elt_ instanceof ConstantOperation) {
            if (((ConstantOperation)elt_).isImmutablePart()) {
                root_.setRelativeOffsetPossibleLastPage(root_.getIndexInEl(), _conf);
                throw new SettingMemberException(_conf.joinPages());
            }
            if (((ConstantOperation)elt_).isFinalField()) {
                root_.setRelativeOffsetPossibleLastPage(root_.getIndexInEl(), _conf);
                throw new FinalMemberException(_conf.joinPages());
            }
        }
    }
}
