package code.expressionlanguage;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import code.expressionlanguage.exceptions.BadExpressionLanguageException;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.FinalMemberException;
import code.expressionlanguage.exceptions.PrimitiveTypeException;
import code.expressionlanguage.exceptions.SettingMemberException;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.ComparatorOrder;
import code.expressionlanguage.opers.ConstantOperation;
import code.expressionlanguage.opers.DotOperation;
import code.expressionlanguage.opers.FctOperation;
import code.expressionlanguage.opers.MethodOperation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.PossibleIntermediateDotted;
import code.expressionlanguage.opers.SettableElResult;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.MethodId;
import code.serialize.ConstClasses;
import code.util.BooleanList;
import code.util.CustList;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public class CustElUtil {

    public static final StringMap<BooleanList> GETTERS_SETTERS_FIELDS = new StringMap<BooleanList>();
    public static final EqList<ClassMethodId> CALLS = new EqList<ClassMethodId>();
    public static final StringList CLASSES = new StringList();
    private static final String RETURN_LINE = "\n";
    private static final String EMPTY_STRING = "";

    public static String processAnalyzeEl(String _el, ContextEl _conf, int _minIndex, char _begin, char _end) {
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(_el, _conf, _minIndex, _begin, _end);
        String el_ = _el.substring(d_.getIndexBegin(), d_.getIndexEnd()+1);
        _conf.setNextIndex(d_.getIndexEnd()+2);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(_minIndex, el_, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(_minIndex, CustList.FIRST_INDEX, null, opTwo_);
        CustList<OperationNode> all_ = getOperationNodes(op_, _conf);
        boolean static_ = _conf.getLastPage().getGlobalClass() == null;
        analyze(true, all_, _conf,static_,static_,EMPTY_STRING,EMPTY_STRING);
        return op_.getResultClass().getName();
    }
    static void analyze(boolean _get,CustList<OperationNode> _nodes, ContextEl _context, boolean _staticContext, boolean _staticBlock,String _fieldName, String _op) {
        PageEl page_ = _context.getLastPage();
        page_.setStaticContext(_staticContext);
        OperationNode root_ = _nodes.last();
        SettableElResult elt_ = null;
        if (_nodes.size() == CustList.ONE_ELEMENT) {
            if ((root_ instanceof SettableElResult)) {
               elt_ = (SettableElResult) root_;
            }
        } else {
            OperationNode beforeLast_ = _nodes.getPrev(_nodes.getLastIndex());
            if (!(root_ instanceof DotOperation)) {
                if ((root_ instanceof SettableElResult)) {
                    elt_ = (SettableElResult) root_;
                }
            } else if ((beforeLast_ instanceof SettableElResult)){
                elt_ = (SettableElResult) beforeLast_;
            }
        }
        for (OperationNode e: _nodes) {
            e.setStaticBlock(_staticBlock);
            e.analyze(_nodes, _context, _fieldName, _op);
            if (_context.getClasses() == null) {
                if (e instanceof PossibleIntermediateDotted) {
                    PossibleIntermediateDotted current_ = (PossibleIntermediateDotted)e;
                    ClassArgumentMatching previous_;
                    if (current_.isIntermediateDottedOperation()) {
                        previous_ = current_.getPreviousResultClass();
                    } else {
                        previous_ = new ClassArgumentMatching(_context.getLastPage().getGlobalClass());
                    }
                    boolean write_ = !_get && e == elt_;
                    if (e instanceof ConstantOperation && e.getOperations().getConstType() == ConstType.WORD) {
                        String className_ = previous_.getName();
                        String field_ = e.getOperations().getValues().firstValue();
                        Class<?> cl_ = ConstClasses.classForNameNotInit(className_);
                        while (true) {
                            boolean foundField_ = false;
                            for (Field f: cl_.getDeclaredFields()) {
                                if (StringList.quickEq(f.getName(), field_)) {
                                    foundField_ = true;
                                    break;
                                }
                            }
                            if (foundField_) {
                                break;
                            }
                            cl_ = cl_.getSuperclass();
                        }
                        String key_ = StringList.concat(cl_.getName(),".",field_);
                        if (!CustElUtil.GETTERS_SETTERS_FIELDS.contains(key_)) {
                            CustElUtil.GETTERS_SETTERS_FIELDS.put(key_, new BooleanList(write_));
                        } else {
                            CustElUtil.GETTERS_SETTERS_FIELDS.getVal(key_).addAllElts(new BooleanList(write_));
                            CustElUtil.GETTERS_SETTERS_FIELDS.getVal(key_).removeDuplicates();
                        }
                        CustElUtil.CLASSES.add(className_);
                    }
                    if (e instanceof FctOperation) {
                        String className_ = previous_.getName();
                        String methodName_ = e.getOperations().getFctName();
                        EqList<ClassName> params_ = new EqList<ClassName>();
                        for (OperationNode c: getDirectChildren(e)) {
                            params_.add(new ClassName(c.getResultClass().getName(), false));
                        }
                        MethodId mId_ = new MethodId(false, methodName_, params_);
                        CustElUtil.CLASSES.add(className_);
                        StringList baseClassName_ = StringList.getAllTypes(className_);
                        if (baseClassName_.size() > 1) {
                            CustElUtil.CALLS.add(new ClassMethodId(className_, mId_));
                        } else {
                            Class<?> cl_ = ConstClasses.classForNameNotInit(baseClassName_.first());
                            if (cl_.isInterface()) {
                                CustElUtil.CALLS.add(new ClassMethodId(className_, mId_));
                            } else {
                                while (true) {
                                    boolean foundMethod_ = false;
                                    for (Method m: cl_.getDeclaredMethods()) {
                                        if (StringList.quickEq(m.getName(), methodName_)) {
                                            foundMethod_ = true;
                                            break;
                                        }
                                    }
                                    if (foundMethod_) {
                                        break;
                                    }
                                    cl_ = cl_.getSuperclass();
                                }
                                CustElUtil.CALLS.add(new ClassMethodId(cl_.getName(), mId_));
                            }
                        }
                    }
                    CustElUtil.CLASSES.add(e.getResultClass().getName());
                }
            }
        }
    }
//
//    static void analyze(CustList<OperationNode> _nodes, ContextEl _context) {
//        PageEl page_ = _context.getLastPage();
//        Argument arg_ = page_.getGlobalArgument();
//        boolean static_ = arg_ == null || arg_.isNull();
//        page_.setStaticContext(static_);
//        for (OperationNode e: _nodes) {
//            e.setStaticBlock(static_);
//            e.analyze(_nodes, _context, EMPTY_STRING, EMPTY_STRING);
//        }
//    }

    public static String processAnalyzeEl(String _el, int _index, ContextEl _conf) {
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, _index);
        String el_ = _el.substring(_index);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(_index, el_, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(_index, CustList.FIRST_INDEX, null, opTwo_);
        CustList<OperationNode> all_ = getOperationNodes(op_, _conf);
        boolean static_ = _conf.getLastPage().getGlobalClass() == null;
        analyze(true, all_, _conf,static_,static_,EMPTY_STRING,EMPTY_STRING);
        return op_.getResultClass().getName();
    }

    public static void processAnalyzeAffect(String _attrOp, String _attrLeft, String _attrRight,
            String _left, String _right, String _oper, ContextEl _conf, boolean _staticContext, boolean _hiddentVarTypes) {
        PageEl page_ = _conf.getLastPage();
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrLeft);
        Delimiters dLeft_ = ElResolver.checkSyntax(_left, _conf, CustList.FIRST_INDEX);
        OperationsSequence opTwoLeft_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _left, _conf, dLeft_);
        OperationNode opLeft_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwoLeft_);
        CustList<OperationNode> allLeft_ = getOperationNodes(opLeft_, _conf);
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrRight);
        Delimiters dRight_ = ElResolver.checkSyntax(_right, _conf, CustList.FIRST_INDEX);
        OperationsSequence opTwoRight_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _right, _conf, dRight_);
        OperationNode opRight_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwoRight_);
        CustList<OperationNode> allRight_ = getOperationNodes(opRight_, _conf);
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrLeft);
        analyzeSetting(true, allLeft_, _conf);
        analyze(false, allLeft_, _conf, _staticContext, _hiddentVarTypes, EMPTY_STRING, _oper);
        analyzeSetting(false, allLeft_, _conf);
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrRight);
        analyze(true, allRight_, _conf, _staticContext, _hiddentVarTypes, EMPTY_STRING, _oper);
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
                    return;
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
        String compo_ = PrimitiveTypeUtil.getQuickComponentBaseType(clMatchRight_.getName()).getComponent();
        if (!compo_.contains("$")) {
            if (compo_.contains("<") || ConstClasses.classForNameNotInit(compo_).getTypeParameters().length > 0) {
                System.err.println("\t"+clMatchRight_.getName());
            }
        }
        //System.out.println(opRight_.getResultClass().getName()+" to "+opLeft_.getResultClass().getName());
    }
    private static CustList<OperationNode> getOperationNodes(OperationNode _root, ContextEl _context) {
        CustList<OperationNode> all_ = new CustList<OperationNode>();
        for (OperationNode s: getSortedDescNodes(_root, _context)) {
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
                        OperationNode prev_ = s_;
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
                    OperationNode op_ = list_.last();
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

    private static CustList<OperationNode> getSortedDescNodes(OperationNode _root, ContextEl _context) {
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
            c_ = getNext(c_, _root, _context);
        }
        return list_;
    }

    private static OperationNode getNext(OperationNode _current, OperationNode _root, ContextEl _context) {
        OperationNode next_ = createFirstChild(_current, _context);
        if (next_ != null) {
            ((MethodOperation) _current).appendChild(next_);
            return next_;
        }
        next_ = createNextSibling(_current, _context);
        if (next_ != null) {
            next_.getParent().appendChild(next_);
            return next_;
        }
        next_ = _current.getParent();
        if (next_ == _root) {
            return null;
        }
        if (next_ != null) {
            OperationNode nextAfter_ = createNextSibling(next_, _context);
            while (nextAfter_ == null) {
                OperationNode par_ = next_.getParent();
                if (par_ == _root) {
                    break;
                }
                if (par_ == null) {
                    break;
                }
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
