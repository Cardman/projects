package code.expressionlanguage;
import code.expressionlanguage.exceptions.BadIndexException;
import code.expressionlanguage.exceptions.CustomFoundConstructorException;
import code.expressionlanguage.exceptions.CustomFoundMethodException;
import code.expressionlanguage.exceptions.DivideZeroException;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.exceptions.InvokeRedinedMethException;
import code.expressionlanguage.exceptions.NegativeSizeException;
import code.expressionlanguage.exceptions.NotInitializedClassException;
import code.expressionlanguage.exceptions.PrimitiveTypeException;
import code.expressionlanguage.exceptions.UnwrappingException;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.ExpLanguages;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ComparatorOrder;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.SettableElResult;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringList;
import code.util.exceptions.NullObjectException;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class ElUtil {

    private static final String ATTRIBUTE_LEFT = "left";
    private static final String ATTRIBUTE_OPER = "oper";
    private static final String ATTRIBUTE_RIGHT = "right";
    private static final String EMPTY_STRING = "";
    private ElUtil() {
    }

    public static ExpLanguages analyzeAffect(String _left, String _right, String _oper, ContextEl _conf, boolean _staticContext) {
        PageEl page_ = _conf.getLastPage();
        page_.setOffset(0);
        page_.setProcessingAttribute(ATTRIBUTE_LEFT);
        Delimiters dLeft_ = ElResolver.checkSyntax(_left, _conf, CustList.FIRST_INDEX);
        ElResolver.secondCheckSyntax(_left, _conf, dLeft_);
        OperationsSequence opTwoLeft_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _left, _conf, dLeft_);
        OperationNode opLeft_ = OperationNode.createOperationNode(_left, CustList.FIRST_INDEX, _conf, CustList.FIRST_INDEX, null, opTwoLeft_);
        CustList<OperationNode> allLeft_ = getOperationNodes(opLeft_);
        for (OperationNode o: allLeft_) {
            o.setConf(null);
        }
        page_.setOffset(0);
        page_.setProcessingAttribute(ATTRIBUTE_RIGHT);
        Delimiters dRight_ = ElResolver.checkSyntax(_right, _conf, CustList.FIRST_INDEX);
        ElResolver.secondCheckSyntax(_right, _conf, dLeft_);
        OperationsSequence opTwoRight_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _right, _conf, dRight_);
        OperationNode opRight_ = OperationNode.createOperationNode(_right, CustList.FIRST_INDEX, _conf, CustList.FIRST_INDEX, null, opTwoRight_);
        CustList<OperationNode> allRight_ = getOperationNodes(opRight_);
        for (OperationNode o: allRight_) {
            o.setConf(null);
        }
        page_.setOffset(0);
        page_.setProcessingAttribute(ATTRIBUTE_LEFT);
        analyzeLeft(allLeft_, _conf, _staticContext, false, _oper);
        page_.setOffset(0);
        page_.setProcessingAttribute(ATTRIBUTE_RIGHT);
        analyzeRight(allRight_, _conf, _staticContext, false, _oper);
        page_.setOffset(0);
        page_.setProcessingAttribute(ATTRIBUTE_LEFT);
        ClassArgumentMatching clMatchRight_ = opRight_.getResultClass();
        ClassArgumentMatching clMatchLeft_ = opLeft_.getResultClass();
        page_.setOffset(0);
        page_.setProcessingAttribute(ATTRIBUTE_OPER);
        if (_oper.length() == 2) {
            if (StringList.quickEq(_oper, Block.EQ_PLUS) || StringList.quickEq(_oper, Block.PLUS_EQ)) {
                if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_)) {
                    if (!clMatchLeft_.matchClass(String.class)) {
                        throw new DynamicCastClassException(_conf.joinPages());
                    }
                } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchRight_)) {
                    throw new DynamicCastClassException(_conf.joinPages());
                }
            } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_)) {
                throw new DynamicCastClassException(_conf.joinPages());
            } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchRight_)) {
                throw new DynamicCastClassException(_conf.joinPages());
            }
        } else {
            if (clMatchRight_.isVariable()) {
                if (!clMatchLeft_.isPrimitive()) {
                    return new ExpLanguages(new ExpressionLanguage(allLeft_), new ExpressionLanguage(allRight_));
                }
                throw new PrimitiveTypeException(_conf.joinPages());
            }
            if (!PrimitiveTypeUtil.canBeUseAsArgument(clMatchLeft_.getName(), clMatchRight_.getName(), _conf.getClasses())) {
                throw new DynamicCastClassException(_conf.joinPages());
            }
        }
        return new ExpLanguages(new ExpressionLanguage(allLeft_), new ExpressionLanguage(allRight_));
    }

    public static Argument tryToCalculate(ContextEl _conf, ExpressionLanguage _right) {
        if (_right.isFinished()) {
            return _right.getArgument();
        }
        IdMap<OperationNode, ArgumentsPair> allRight_ = _right.getArguments();
        calculateRight(allRight_, _right, _conf, EMPTY_STRING);
        _right.finish();
        Argument arg_ = _right.getArgument();
        return arg_;
    }
    public static void tryToCalculateLeftAffect(ExpressionLanguage _left, ContextEl _conf, String _op) {
        if (_left.isFinished()) {
            return;
        }
        IdMap<OperationNode, ArgumentsPair> allLeft_ = _left.getArguments();
        calculateLeft(allLeft_, _left, _conf, _op);
        _left.finish();   
    }
    public static void tryToCalculateRightAffect(ExpressionLanguage _right, ContextEl _conf, String _op) {
        if (_right.isFinished()) {
            return;
        }
        IdMap<OperationNode, ArgumentsPair> allRight_ = _right.getArguments();
        calculateRight(allRight_, _right, _conf, _op);
        _right.finish();
        _conf.getLastPage().setRightArgument(_right.getArgument());
    }
    public static void tryToCalculateAllAffect(ExpressionLanguage _left, ContextEl _conf, String _op) {
        IdMap<OperationNode, ArgumentsPair> allLeft_ = _left.getArguments();
        SettableElResult settable_ = _left.getSettable();
        OperationNode op_ = (OperationNode) settable_;
        ArgumentsPair a_ = allLeft_.getVal(op_);
        Argument arg_ = _conf.getLastPage().getGlobalArgument();
        if (op_.isNeedPrevious() && a_.getPreviousArgument() == null) {
            a_.setPreviousArgument(arg_);
        }
        try {
            a_.setArgument(settable_.calculateSetting(allLeft_, _conf, _op));
        } catch (RuntimeException _0) {
            _left.setCurrentOper(null);
            _conf.getLastPage().getCurrentEls().clear();
            throw _0;
        } catch (Error _0) {
            _left.setCurrentOper(null);
            _conf.getLastPage().getCurrentEls().clear();
            throw _0;
        }
        _conf.getLastPage().setRightArgument(null);
    }
    public static void tryToCalculateAffect(ExpressionLanguage _left, ContextEl _conf, ExpressionLanguage _right, String _op) {
        CustList<OperationNode> allLeft_ = _left.getOperations();
        _conf.getLastPage().setCurrentEls(new CustList<ExpressionLanguage>(_left));
        calculateLeft(allLeft_ , _left, _conf, _op);
        CustList<OperationNode> allRight_ = _right.getOperations();
        _conf.getLastPage().getCurrentEls().add(_right);
        calculateRight(allRight_, _right,_conf, _op);
        _conf.getLastPage().setRightArgument(_right.getRoot().getArgument());
        SettableElResult settable_ = _left.getSettable();
        OperationNode op_ = (OperationNode) settable_;
        Argument arg_ = _conf.getLastPage().getGlobalArgument();
        if (op_.isNeedPrevious() && op_.getPreviousArgument() == null) {
            op_.setPreviousArgument(arg_);
        }
        try {
            settable_.calculateSetting(allLeft_, _conf, _op);
        } catch (RuntimeException _0) {
            _left.setCurrentOper(null);
            _conf.getLastPage().getCurrentEls().clear();
            throw _0;
        } catch (Error _0) {
            _left.setCurrentOper(null);
            _conf.getLastPage().getCurrentEls().clear();
            throw _0;
        }
        _conf.getLastPage().setRightArgument(null);
    }
    public static void processAffect(String _left, String _right, String _oper, ContextEl _conf, boolean _staticContext) {
        Delimiters dLeft_ = ElResolver.checkSyntax(_left, _conf, CustList.FIRST_INDEX);
        ElResolver.secondCheckSyntax(_left, _conf, dLeft_);
        OperationsSequence opTwoLeft_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _left, _conf, dLeft_);
        OperationNode opLeft_ = OperationNode.createOperationNode(_left, CustList.FIRST_INDEX, _conf, CustList.FIRST_INDEX, null, opTwoLeft_);
        CustList<OperationNode> allLeft_ = getOperationNodes(opLeft_);
        for (OperationNode o: allLeft_) {
            o.setConf(null);
        }
        Delimiters dRight_ = ElResolver.checkSyntax(_right, _conf, CustList.FIRST_INDEX);
        ElResolver.secondCheckSyntax(_right, _conf, dLeft_);
        OperationsSequence opTwoRight_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _right, _conf, dRight_);
        OperationNode opRight_ = OperationNode.createOperationNode(_right, CustList.FIRST_INDEX, _conf, CustList.FIRST_INDEX, null, opTwoRight_);
        CustList<OperationNode> allRight_ = getOperationNodes(opRight_);
        for (OperationNode o: allRight_) {
            o.setConf(null);
        }
        analyzeLeft(allLeft_, _conf, _staticContext, false, _oper);
        analyzeRight(allRight_, _conf, _staticContext, false, _oper);
        ClassArgumentMatching clMatchRight_ = opRight_.getResultClass();
        ClassArgumentMatching clMatchLeft_ = opLeft_.getResultClass();
        if (_oper.length() == 2) {
            if (StringList.quickEq(_oper, Block.EQ_PLUS) || StringList.quickEq(_oper, Block.PLUS_EQ)) {
                if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_)) {
                    if (!clMatchLeft_.matchClass(String.class)) {
                        throw new DynamicCastClassException(_conf.joinPages());
                    }
                } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchRight_)) {
                    throw new DynamicCastClassException(_conf.joinPages());
                }
            } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_)) {
                throw new DynamicCastClassException(_conf.joinPages());
            } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchRight_)) {
                throw new DynamicCastClassException(_conf.joinPages());
            }
        } else if (!PrimitiveTypeUtil.canBeUseAsArgument(clMatchLeft_.getName(), clMatchRight_.getName(), _conf.getClasses())) {
            throw new DynamicCastClassException(_conf.joinPages());
        }
        calculateLeft(allLeft_, _conf, _oper);
        calculateRight(allRight_, _conf, _oper);
        _conf.getLastPage().setRightArgument(opRight_.getArgument());
        SettableElResult settable_ = null;
        if (allLeft_.last() instanceof SettableElResult) {
            settable_ = (SettableElResult) allLeft_.last();
        } else if (allLeft_.size() > 1){
            OperationNode beforeLast_ = allLeft_.getPrev(allLeft_.getLastIndex());
            if (beforeLast_ instanceof SettableElResult) {
                settable_ = (SettableElResult) beforeLast_;
            }
        }
        OperationNode op_ = (OperationNode) settable_;
        Argument arg_ = _conf.getLastPage().getGlobalArgument();
        if (op_.isNeedPrevious() && op_.getPreviousArgument() == null) {
            op_.setPreviousArgument(arg_);
        }
        settable_.calculateSetting(allLeft_, _conf, _oper);
        _conf.getLastPage().setRightArgument(null);
        for (OperationNode o: allLeft_) {
            o.resetArguments();
        }
        for (OperationNode o: allRight_) {
            o.resetArguments();
        }
    }

    public static Argument processEl(String _el, ContextEl _conf, int _minIndex, char _begin, char _end) {
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(_el, _conf, _minIndex, _begin, _end);
        String el_ = _el.substring(d_.getIndexBegin(), d_.getIndexEnd()+1);
        _conf.setNextIndex(d_.getIndexEnd()+2);
        ElResolver.secondCheckSyntax(el_, _conf, d_);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, el_, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(el_, CustList.FIRST_INDEX, _conf, CustList.FIRST_INDEX, null, opTwo_);
        CustList<OperationNode> all_ = getOperationNodes(op_);
        for (OperationNode o: all_) {
            o.setConf(null);
        }
        if (!_conf.isEmptyPages()) {
            _conf.getLastPage().setOffset(d_.getIndexBegin());
        }
        analyzeRight(all_, _conf);
        if (!_conf.isEmptyPages()) {
            _conf.getLastPage().setOffset(d_.getIndexBegin());
        }
        calculateRight(all_, _conf, EMPTY_STRING);
        Argument arg_ = op_.getArgument();
        return arg_;
    }

    public static CustList<OperationNode> getAnalyzedOperations(String _el, ContextEl _conf, Calculation _calcul) {
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, CustList.FIRST_INDEX);
        ElResolver.secondCheckSyntax(_el, _conf, d_);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _el, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(_el, CustList.FIRST_INDEX, _conf, CustList.FIRST_INDEX, null, opTwo_);
        CustList<OperationNode> all_ = getOperationNodes(op_);
        for (OperationNode o: all_) {
            o.setConf(null);
        }
        if (!_conf.isEmptyPages()) {
            _conf.getLastPage().setOffset(d_.getIndexBegin());
        }
        boolean staticContext_ = _calcul.isStaticAcces();
        boolean enumContext_ = _calcul.isEnumAcces();
        String oper_ = _calcul.getOper();
        if (_calcul.isLeftStep()) {
            analyzeLeft(all_, _conf, staticContext_, enumContext_, oper_);
        } else {
            analyzeRight(all_, _conf, staticContext_, enumContext_, oper_);
        }
        return all_;
    }

    public static Argument processEl(String _el, int _index, ContextEl _conf) {
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, _index);
        String el_ = _el.substring(_index);
        ElResolver.secondCheckSyntax(el_, _conf, d_);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, el_, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(el_, CustList.FIRST_INDEX, _conf, CustList.FIRST_INDEX, null, opTwo_);
        CustList<OperationNode> all_ = getOperationNodes(op_);
        for (OperationNode o: all_) {
            o.setConf(null);
        }
        if (!_conf.isEmptyPages()) {
            _conf.getLastPage().setOffset(d_.getIndexBegin());
        }
        analyzeRight(all_, _conf);
        if (!_conf.isEmptyPages()) {
            _conf.getLastPage().setOffset(d_.getIndexBegin());
        }
        calculateRight(all_, _conf, EMPTY_STRING);
        Argument arg_  = op_.getArgument();
        return arg_;
    }

    static CustList<OperationNode> getOperationNodes(OperationNode _root) {
        CustList<OperationNode> all_ = new CustList<OperationNode>();
        for (OperationNode s: getSortedDescNodes(_root)) {
            all_.add(s);
        }
        int order_ = 0;
        while (true) {
            CustList<OperationNode> next_ = new CustList<OperationNode>();
            for (OperationNode e: all_) {
                if (e.getOrder() > CustList.INDEX_NOT_FOUND_ELT) {
                    continue;
                }
                OperationNode cur_ = e;
                boolean tonumber_ = true;
                while (cur_ != null) {
                    int index_ = cur_.getIndexChild() - 1;
                    if (index_ >= CustList.FIRST_INDEX) {
                        CustList<OperationNode> sn_ = getDirectChildren(cur_.getParent());
                        OperationNode s_ = sn_.get(index_);
                        OperationNode prev_ = (OperationNode) s_;
                        if (prev_.getOrder() == CustList.INDEX_NOT_FOUND_ELT) {
                            tonumber_ = false;
                            break;
                        }
                    }
                    cur_ = cur_.getParent();
                }
                if (!tonumber_) {
                    continue;
                }
                CustList<OperationNode> list_ = getDirectChildren(e);
                if (!list_.isEmpty()) {
                    OperationNode op_ = (OperationNode) list_.last();
                    if (op_.getOrder() == CustList.INDEX_NOT_FOUND_ELT) {
                        continue;
                    }
                }
                next_.add(e);
            }
            if (next_.isEmpty()) {
                break;
            }
            for (OperationNode o: next_) {
                o.setOrder(order_);
                order_++;
            }
        }
        all_.sortElts(new ComparatorOrder());
        return all_;
    }
    public static CustList<OperationNode> getSortedDescNodes(OperationNode _root) {
        CustList<OperationNode> list_ = new CustList<OperationNode>();
        if (_root == null) {
            return list_;
        }
        OperationNode c_ = _root;
        while (true) {
            if (c_ == null) {
                break;
            }
            list_.add(c_);
            c_ = getNext(c_, _root);
        }
        return list_;
    }

    public static OperationNode getNext(OperationNode _current, OperationNode _root) {
        OperationNode n_ = _current.getFirstChild();
        if (n_ != null) {
            return n_;
        }
        n_ = _current.getNextSibling();
        if (n_ != null) {
            return n_;
        }
        n_ = _current.getParent();
        if (n_ == _root) {
            return null;
        }
        if (n_ != null) {
            OperationNode next_ = n_.getNextSibling();
            while (next_ == null) {
                OperationNode par_ = n_.getParent();
                if (par_ == _root) {
                    break;
                }
                if (par_ == null) {
                    break;
                }
                next_ = par_.getNextSibling();
                n_ = par_;
            }
            if (next_ != null) {
                return next_;
            }
        }
        return null;
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

    static void analyzeLeft(CustList<OperationNode> _nodes, ContextEl _context, boolean _staticContext, boolean _isEnumContext, String _op) {
        PageEl page_ = _context.getLastPage();
        for (OperationNode e: _nodes) {
            if (e.getPreviousResultClass() == null) {
                e.setPreviousResultClass(new ClassArgumentMatching(page_.getGlobalClass()), _staticContext);
            }
            e.analyzeLeft(_nodes, _context, _isEnumContext, _op);
        }
    }
    static void analyzeRight(CustList<OperationNode> _nodes, ContextEl _context, boolean _staticContext, boolean _isEnumContext, String _op) {
        PageEl page_ = _context.getLastPage();
        for (OperationNode e: _nodes) {
            if (e.getPreviousResultClass() == null) {
                e.setPreviousResultClass(new ClassArgumentMatching(page_.getGlobalClass()), _staticContext);
            }
            e.analyzeRight(_nodes, _context, _isEnumContext, _op);
        }
    }

    static void analyzeRight(CustList<OperationNode> _nodes, ContextEl _context) {
        PageEl page_ = _context.getLastPage();
        Argument arg_ = page_.getGlobalArgument();
        boolean static_ = arg_ == null || arg_.isNull();
        for (OperationNode e: _nodes) {
            if (e.getPreviousResultClass() == null && arg_ != null) {
                e.setPreviousResultClass(new ClassArgumentMatching(page_.getGlobalClass()), static_);
            }
            e.analyzeRight(_nodes, _context, false, EMPTY_STRING);
        }
    }
    /**@throws InvokeRedinedMethException
    @throws DivideZeroException
    @throws BadIndexException
    @throws NegativeSizeException
    @throws ErrorCausingException
    @throws DynamicCastClassException
    @throws RuntimeClassNotFoundException
    @throws NullObjectException
    @throws InvokeException
    @throws UnwrappingException*/
    static void calculateLeft(CustList<OperationNode> _nodes, ContextEl _context, String _op) {
        Argument arg_ = _context.getLastPage().getGlobalArgument();
        for (OperationNode e: _nodes) {
            if (!e.isCalculated()) {
                if (e.isNeedPrevious() && e.getPreviousArgument() == null) {
                    e.setPreviousArgument(arg_);
                }
                e.calculateLeft(_nodes, _context, _op);
            }
        }
    }
    static void calculateRight(CustList<OperationNode> _nodes, ContextEl _context, String _op) {
        Argument arg_ = _context.getLastPage().getGlobalArgument();
        for (OperationNode e: _nodes) {
            if (!e.isCalculated()) {
                if (e.isNeedPrevious() && e.getPreviousArgument() == null) {
                    e.setPreviousArgument(arg_);
                }
                e.calculateRight(_nodes, _context, _op);
            }
        }
    }
    /**@throws InvokeRedinedMethException
    @throws CustomFoundMethodException
    @throws DivideZeroException
    @throws BadIndexException
    @throws NegativeSizeException
    @throws ErrorCausingException
    @throws DynamicCastClassException
    @throws RuntimeClassNotFoundException
    @throws NullObjectException
    @throws InvokeException
    @throws UnwrappingException*/
    static void calculateLeft(CustList<OperationNode> _nodes, ExpressionLanguage _el, ContextEl _context, String _op) {
        Argument arg_ = _context.getLastPage().getGlobalArgument();
        for (OperationNode e: _nodes) {
            if (!e.isCalculated()) {
                if (e.isNeedPrevious() && e.getPreviousArgument() == null) {
                    e.setPreviousArgument(arg_);
                }
                try {
                    e.calculateLeft(_nodes, _context, _op);
                } catch (NotInitializedClassException _0) {
                    throw _0;
                } catch (CustomFoundConstructorException _0) {
                    _el.setCurrentOper(e);
                    throw _0;
                } catch (CustomFoundMethodException _0) {
                    _el.setCurrentOper(e);
                    throw _0;
                } catch (RuntimeException _0) {
                    _el.setCurrentOper(null);
                    _context.getLastPage().getCurrentEls().clear();
                    throw _0;
                } catch (Error _0) {
                    _el.setCurrentOper(null);
                    _context.getLastPage().getCurrentEls().clear();
                    throw _0;
                }
            }
        }
    }
    static void calculateRight(CustList<OperationNode> _nodes, ExpressionLanguage _el, ContextEl _context, String _op) {
        Argument arg_ = _context.getLastPage().getGlobalArgument();
        for (OperationNode e: _nodes) {
            if (!e.isCalculated()) {
                if (e.isNeedPrevious() && e.getPreviousArgument() == null) {
                    e.setPreviousArgument(arg_);
                }
                try {
                    e.calculateRight(_nodes, _context, _op);
                } catch (NotInitializedClassException _0) {
                    throw _0;
                } catch (CustomFoundConstructorException _0) {
                    _el.setCurrentOper(e);
                    throw _0;
                } catch (CustomFoundMethodException _0) {
                    _el.setCurrentOper(e);
                    throw _0;
                } catch (RuntimeException _0) {
                    _el.setCurrentOper(null);
                    _context.getLastPage().getCurrentEls().clear();
                    throw _0;
                } catch (Error _0) {
                    _el.setCurrentOper(null);
                    _context.getLastPage().getCurrentEls().clear();
                    throw _0;
                }
            }
        }
    }
    /**@throws InvokeRedinedMethException
    @throws CustomFoundMethodException
    @throws DivideZeroException
    @throws BadIndexException
    @throws NegativeSizeException
    @throws ErrorCausingException
    @throws DynamicCastClassException
    @throws RuntimeClassNotFoundException
    @throws NullObjectException
    @throws InvokeException
    @throws UnwrappingException*/

    static void calculateLeft(IdMap<OperationNode,ArgumentsPair> _nodes, ExpressionLanguage _el, ContextEl _context, String _op) {
        Argument arg_ = _context.getLastPage().getGlobalArgument();
        for (EntryCust<OperationNode,ArgumentsPair> e: _nodes.entryList()) {
            OperationNode o = e.getKey();
            if (!o.isCalculated(_nodes)) {
                ArgumentsPair a_ = e.getValue();
                if (o.isNeedPrevious() && a_.getPreviousArgument() == null) {
                    a_.setPreviousArgument(arg_);
                }
                try {
                    a_.setArgument(o.calculateLeft(_nodes, _context, _op));
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
                    _el.setCurrentOper(null);
                    _context.getLastPage().getCurrentEls().clear();
                    throw _0;
                } catch (Error _0) {
                    _el.setCurrentOper(null);
                    _context.getLastPage().getCurrentEls().clear();
                    throw _0;
                }
            }
        }
    }

    static void calculateRight(IdMap<OperationNode,ArgumentsPair> _nodes, ExpressionLanguage _el, ContextEl _context, String _op) {
        Argument arg_ = _context.getLastPage().getGlobalArgument();
        for (EntryCust<OperationNode,ArgumentsPair> e: _nodes.entryList()) {
            OperationNode o = e.getKey();
            if (!o.isCalculated(_nodes)) {
                ArgumentsPair a_ = e.getValue();
                if (o.isNeedPrevious() && a_.getPreviousArgument() == null) {
                    a_.setPreviousArgument(arg_);
                }
                try {
                    a_.setArgument(o.calculateRight(_nodes, _context, _op));
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
                    _el.setCurrentOper(null);
                    _context.getLastPage().getCurrentEls().clear();
                    throw _0;
                } catch (Error _0) {
                    _el.setCurrentOper(null);
                    _context.getLastPage().getCurrentEls().clear();
                    throw _0;
                }
            }
        }
    }
}
