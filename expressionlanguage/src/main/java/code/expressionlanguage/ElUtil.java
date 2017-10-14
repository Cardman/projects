package code.expressionlanguage;
import code.expressionlanguage.exceptions.BadIndexException;
import code.expressionlanguage.exceptions.CustomFoundConstructorException;
import code.expressionlanguage.exceptions.CustomFoundMethodException;
import code.expressionlanguage.exceptions.DivideZeroException;
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
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.ExpLanguages;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ComparatorOrder;
import code.expressionlanguage.opers.ConstantOperation;
import code.expressionlanguage.opers.DotOperation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.SettableElResult;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.exceptions.NullObjectException;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class ElUtil {

    private static final String EMPTY_STRING = "";
    private ElUtil() {
    }

    public static ExpLanguages analyzeAffect(String _attrOp, String _attrLeft, String _attrRight,
            String _left, String _right, String _oper, ContextEl _conf, boolean _staticContext) {
        PageEl page_ = _conf.getLastPage();
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrLeft);
        Delimiters dLeft_ = ElResolver.checkSyntax(_left, _conf, CustList.FIRST_INDEX);
        ElResolver.secondCheckSyntax(_left, _conf, dLeft_);
        OperationsSequence opTwoLeft_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _left, _conf, dLeft_);
        OperationNode opLeft_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, _conf, CustList.FIRST_INDEX, null, opTwoLeft_);
        CustList<OperationNode> allLeft_ = getOperationNodes(opLeft_);
        for (OperationNode o: allLeft_) {
            o.setConf(null);
        }
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrRight);
        Delimiters dRight_ = ElResolver.checkSyntax(_right, _conf, CustList.FIRST_INDEX);
        ElResolver.secondCheckSyntax(_right, _conf, dLeft_);
        OperationsSequence opTwoRight_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _right, _conf, dRight_);
        OperationNode opRight_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, _conf, CustList.FIRST_INDEX, null, opTwoRight_);
        CustList<OperationNode> allRight_ = getOperationNodes(opRight_);
        for (OperationNode o: allRight_) {
            o.setConf(null);
        }
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrLeft);
        analyzeSetting(true, allLeft_, _conf);
        analyze(allLeft_, _conf, _staticContext, EMPTY_STRING, _oper);
        analyzeSetting(false, allLeft_, _conf);
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrRight);
        analyze(allRight_, _conf, _staticContext, EMPTY_STRING, _oper);
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrLeft);
        ClassArgumentMatching clMatchRight_ = opRight_.getResultClass();
        ClassArgumentMatching clMatchLeft_ = opLeft_.getResultClass();
        page_.setOffset(0);
        if (!_attrOp.isEmpty()) {
            page_.setProcessingAttribute(_attrOp);
        }
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
            StringMap<StringList> vars_ = new StringMap<StringList>();
            boolean buildMap_ = true;
            if (_staticContext) {
                buildMap_ = false;
            } else if (page_.getGlobalClass() == null) {
                buildMap_ = false;
            }
            if (buildMap_) {
                for (TypeVar t: Templates.getConstraints(page_.getGlobalClass(), _conf.getClasses())) {
                    vars_.put(t.getName(), t.getConstraints());
                }
            }
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(vars_);
            mapping_.setArg(clMatchRight_.getName());
            mapping_.setParam(clMatchLeft_.getName());
            if (!Templates.isCorrect(mapping_, _conf.getClasses())) {
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
        calculate(allRight_, _right, _conf, EMPTY_STRING);
        _right.finish();
        Argument arg_ = _right.getArgument();
        return arg_;
    }
    public static void tryToCalculateLeftAffect(ExpressionLanguage _left, ContextEl _conf, String _op) {
        if (_left.isFinished()) {
            return;
        }
        IdMap<OperationNode, ArgumentsPair> allLeft_ = _left.getArguments();
        calculate(allLeft_, _left, _conf, _op);
        _left.finish();
    }
    public static void tryToCalculateRightAffect(ExpressionLanguage _right, ContextEl _conf, String _op) {
        if (_right.isFinished()) {
            return;
        }
        IdMap<OperationNode, ArgumentsPair> allRight_ = _right.getArguments();
        calculate(allRight_, _right, _conf, _op);
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
    public static void tryToCalculateAffect(ExpressionLanguage _left, ContextEl _conf, ExpressionLanguage _right, String _op) {
        CustList<OperationNode> allLeft_ = _left.getOperations();
        _conf.getLastPage().clearCurrentEls();
        _conf.getLastPage().addCurrentEl(_left);
        calculate(allLeft_ , _left, _conf, _op);
        CustList<OperationNode> allRight_ = _right.getOperations();
        _conf.getLastPage().addCurrentEl(_right);
        calculate(allRight_, _right,_conf, _op);
        _conf.getLastPage().setRightArgument(_right.getRoot().getArgument());
        SettableElResult settable_ = _left.getSettable();
        try {
            settable_.calculateSetting(allLeft_, _conf, _op);
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
    public static void processAffect(String _attrOp, String _attrLeft, String _attrRight,
            String _left, String _right, String _oper, ContextEl _conf) {
        Argument arg_ = _conf.getLastPage().getGlobalArgument();
        boolean staticContext_ = arg_ == null || arg_.isNull();
        processAffect(_attrOp, _attrLeft, _attrRight, _left, _right, _oper, _conf, staticContext_);
    }
    public static void processAffect(String _attrOp, String _attrLeft, String _attrRight,
            String _left, String _right, String _oper, ContextEl _conf, boolean _staticContext) {
        ExpLanguages members_ = analyzeAffect(_attrOp, _attrLeft, _attrRight, _left, _right, _oper, _conf, _staticContext);
        ExpressionLanguage left_ = members_.getLeft();
        ExpressionLanguage right_ = members_.getRight();
        tryToCalculateAffect(left_, _conf, right_, _oper);
    }

    public static Argument processEl(String _el, ContextEl _conf, int _minIndex, char _begin, char _end) {
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(_el, _conf, _minIndex, _begin, _end);
        String el_ = _el.substring(d_.getIndexBegin(), d_.getIndexEnd()+1);
        _conf.setNextIndex(d_.getIndexEnd()+2);
        ElResolver.secondCheckSyntax(el_, _conf, d_);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, el_, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, _conf, CustList.FIRST_INDEX, null, opTwo_);
        CustList<OperationNode> all_ = getOperationNodes(op_);
        for (OperationNode o: all_) {
            o.setConf(null);
        }
        if (!_conf.isEmptyPages()) {
            _conf.getLastPage().setOffset(d_.getIndexBegin());
        }
        analyze(all_, _conf);
        if (!_conf.isEmptyPages()) {
            _conf.getLastPage().setOffset(d_.getIndexBegin());
        }
        calculate(all_, _conf, EMPTY_STRING);
        Argument arg_ = op_.getArgument();
        return arg_;
    }

    public static CustList<OperationNode> getAnalyzedOperations(String _el, ContextEl _conf, Calculation _calcul) {
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, CustList.FIRST_INDEX);
        ElResolver.secondCheckSyntax(_el, _conf, d_);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _el, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, _conf, CustList.FIRST_INDEX, null, opTwo_);
        CustList<OperationNode> all_ = getOperationNodes(op_);
        for (OperationNode o: all_) {
            o.setConf(null);
        }
        if (!_conf.isEmptyPages()) {
            _conf.getLastPage().setOffset(d_.getIndexBegin());
        }
        boolean staticContext_ = _calcul.isStaticAcces();
        String fieldName_ = _calcul.getFieldName();
        String oper_ = _calcul.getOper();
        if (_calcul.isLeftStep()) {
            analyzeSetting(true, all_, _conf);
            analyze(all_, _conf, staticContext_, fieldName_, oper_);
            analyzeSetting(false, all_, _conf);
        } else {
            analyze(all_, _conf, staticContext_, fieldName_, oper_);
        }
        return all_;
    }

    public static Argument processEl(String _el, int _index, ContextEl _conf) {
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, _index);
        String el_ = _el.substring(_index);
        ElResolver.secondCheckSyntax(el_, _conf, d_);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, el_, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, _conf, CustList.FIRST_INDEX, null, opTwo_);
        CustList<OperationNode> all_ = getOperationNodes(op_);
        for (OperationNode o: all_) {
            o.setConf(null);
        }
        if (!_conf.isEmptyPages()) {
            _conf.getLastPage().setOffset(d_.getIndexBegin());
        }
        analyze(all_, _conf);
        if (!_conf.isEmptyPages()) {
            _conf.getLastPage().setOffset(d_.getIndexBegin());
        }
        calculate(all_, _conf, EMPTY_STRING);
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

    static void analyze(CustList<OperationNode> _nodes, ContextEl _context, boolean _staticContext, String _fieldName, String _op) {
        PageEl page_ = _context.getLastPage();
        for (OperationNode e: _nodes) {
            if (e.getPreviousResultClass() == null) {
                e.setPreviousResultClass(new ClassArgumentMatching(page_.getGlobalClass()), _staticContext);
            }
            e.analyze(_nodes, _context, _fieldName, _op);
        }
    }

    static void analyze(CustList<OperationNode> _nodes, ContextEl _context) {
        PageEl page_ = _context.getLastPage();
        Argument arg_ = page_.getGlobalArgument();
        boolean static_ = arg_ == null || arg_.isNull();
        for (OperationNode e: _nodes) {
            if (e.getPreviousResultClass() == null && arg_ != null) {
                e.setPreviousResultClass(new ClassArgumentMatching(page_.getGlobalClass()), static_);
            }
            e.analyze(_nodes, _context, EMPTY_STRING, EMPTY_STRING);
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
    static void calculate(CustList<OperationNode> _nodes, ContextEl _context, String _op) {
        Argument arg_ = _context.getLastPage().getGlobalArgument();
        for (OperationNode e: _nodes) {
            if (!e.isCalculated()) {
                if (e.isNeedGlobalArgument()) {
                    e.setPreviousArgument(arg_);
                }
                e.calculate(_nodes, _context, _op);
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
    static void calculate(CustList<OperationNode> _nodes, ExpressionLanguage _el, ContextEl _context, String _op) {
        Argument arg_ = _context.getLastPage().getGlobalArgument();
        for (OperationNode e: _nodes) {
            if (!e.isCalculated()) {
                if (e.isNeedGlobalArgument()) {
                    e.setPreviousArgument(arg_);
                }
                try {
                    e.calculate(_nodes, _context, _op);
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
                    _context.getLastPage().clearCurrentEls();
                    throw _0;
                } catch (Error _0) {
                    _el.setCurrentOper(null);
                    _context.getLastPage().clearCurrentEls();
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

    static void calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ExpressionLanguage _el, ContextEl _context, String _op) {
        Argument arg_ = _context.getLastPage().getGlobalArgument();
        for (EntryCust<OperationNode,ArgumentsPair> e: _nodes.entryList()) {
            OperationNode o = e.getKey();
            if (!o.isCalculated(_nodes)) {
                ArgumentsPair a_ = e.getValue();
                if (o.isNeedGlobalArgument()) {
                    a_.setPreviousArgument(arg_);
                }
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
                    _el.setCurrentOper(null);
                    _context.getLastPage().clearCurrentEls();
                    throw _0;
                } catch (Error _0) {
                    _el.setCurrentOper(null);
                    _context.getLastPage().clearCurrentEls();
                    throw _0;
                }
            }
        }
    }
    static void analyzeSetting(boolean _setVar,CustList<OperationNode> _nodes, ContextEl _conf) {
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
