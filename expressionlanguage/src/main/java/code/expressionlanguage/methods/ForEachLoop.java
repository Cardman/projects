package code.expressionlanguage.methods;
import java.lang.reflect.Array;
import java.util.Iterator;

import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.methods.exceptions.AlreadyDefinedVarException;
import code.expressionlanguage.methods.exceptions.BadConstructorCall;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringMap;
import code.util.exceptions.NullObjectException;
import code.util.ints.Listable;

public final class ForEachLoop extends BracedStack implements ForLoop {

    private final String className;

    private final String classIndexName;

    private final String variableName;

    private final String expression;

    private CustList<OperationNode> opList;

    public ForEachLoop(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        className = _el.getAttribute(ATTRIBUTE_CLASS);
        variableName = _el.getAttribute(ATTRIBUTE_VAR);
        expression = _el.getAttribute(ATTRIBUTE_EXPRESSION);
        String classIndex_ = _el.getAttribute(ATTRIBUTE_CLASS_INDEX);
        if (classIndex_.isEmpty()) {
            classIndex_ = Long.class.getName();
        }
        classIndexName = classIndex_;
        setAlwaysSkipped(true);
    }

    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        tr_.put(ATTRIBUTE_CLASS, className);
        tr_.put(ATTRIBUTE_CLASS_INDEX, classIndexName);
        return tr_;
    }

    @Override
    public String getClassIndexName() {
        return classIndexName;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getVariableName() {
        return variableName;
    }

    public String getExpression() {
        return expression;
    }

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opList);
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(classIndexName)) {
            throw new DynamicCastClassException(classIndexName+RETURN_LINE+_cont.joinPages());
        }
        if (_cont.getLastPage().getVars().contains(variableName)) {
            throw new AlreadyDefinedVarException(variableName+RETURN_LINE+_cont.joinPages());
        }
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        page_.setOffset(0);
        opList = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        OperationNode el_ = opList.last();
        Classes classes_ = _cont.getClasses();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(Iterable.class.getName(), el_.getResultClass().getName(), classes_)) {
            if (!el_.getResultClass().isArray()) {
                String str_ = el_.getResultClass().getName();
                throw new DynamicCastClassException(str_+RETURN_LINE+_cont.joinPages());
            }
        }
        LoopVariable lv_ = new LoopVariable();
        lv_.setClassName(className);
        _cont.getLastPage().getVars().put(variableName, lv_);
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public void checkCallConstructor(ContextEl _cont) {
        PageEl p_ = _cont.getLastPage();
        p_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        for (OperationNode o: opList) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                throw new BadConstructorCall(_cont.joinPages());
            }
        }
    }

    @Override
    public String getTagName() {
        return TAG_FOREACH;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        LoopBlockStack c_ = null;
        if (!ip_.noBlock() && ip_.getLastStack() instanceof LoopBlockStack) {
            c_ = (LoopBlockStack) ip_.getLastStack();
        }
        if (c_ != null && c_.getBlock() == this) {
            if (c_.isFinished()) {
                removeVarAndLoop(ip_);
                processBlock(_cont);
                return;
            }
            ip_.getReadWrite().setBlock(getFirstChild());
            return;
        }
        processLoop(_cont);
        c_ = (LoopBlockStack) ip_.getLastStack();
        if (c_.isFinished()) {
            ip_.removeLastBlock();
            processBlock(_cont);
            return;
        } else {
            ip_.getReadWrite().setBlock(getFirstChild());
            return;
        }
    }

    void processLoop(ContextEl _conf) {
        PageEl ip_ = _conf.getLastPage();
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        Object iterable_ = null;
        String var_ = getVariableName();

        ip_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        ip_.setOffset(0);
        ExpressionLanguage el_;
        if (!ip_.getCurrentEls().isEmpty()) {
            el_ = ip_.getCurrentEls().last();
        } else {
            el_ = getEl();
            ip_.setCurrentBlock(this);
            ip_.setCurrentEls(new CustList<ExpressionLanguage>(el_));
        }
        Object ito_ = el_.calculateMember(_conf).getObject();
        ip_.getCurrentEls().clear();
        if (ito_ == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        iterable_ = ito_;
        Iterator<?> it_ = null;
        long length_ = CustList.INDEX_NOT_FOUND_ELT;
        boolean finished_ = false;
        if (iterable_.getClass().isArray()) {
            length_ = Array.getLength(iterable_);
            if (length_ == CustList.SIZE_EMPTY) {
                finished_ = true;
            }
        } else {
            it_ = ProcessXmlMethod.iterator(_conf, (Iterable<?>) iterable_);
            if (!ProcessXmlMethod.hasNext(_conf, it_)) {
                finished_ = true;
            }
        }
        if (getFirstChild() == null) {
            finished_ = true;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setFinished(finished_);
        l_.setBlock(this);
        l_.setIterator(it_, length_);
        ip_.addBlock(l_);
        if (finished_) {
            return;
        }
        Object int_;
        if (iterable_.getClass().isArray()) {
            int_ = Array.get(iterable_, CustList.FIRST_INDEX);
        } else {
            int_ = ProcessXmlMethod.next(_conf, it_);
        }
        String indexClassName_;
        indexClassName_ = getClassIndexName();
        String className_;
        LoopVariable lv_ = new LoopVariable();
        className_ = getClassName();
        lv_.setClassName(className_);
        lv_.setIndexClassName(indexClassName_);
        lv_.setElement(int_);
        if (iterable_.getClass().isArray()) {
            lv_.setArray(iterable_);
        } else {
            lv_.setList((Listable<?>)iterable_);
        }
        lv_.setExtendedExpression(EMPTY_STRING);
        varsLoop_.put(var_, lv_);
    }

    @Override
    public void exitStack(ContextEl _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void removeVarAndLoop(PageEl _ip) {
        super.removeVarAndLoop(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        String var_ = getVariableName();
        v_.removeKey(var_);
    }


    @Override
    public void processLastElementLoop(ContextEl _conf) {
        PageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        Block forLoopLoc_ = l_.getBlock();
        rw_.setBlock(forLoopLoc_);
        if (keepLoop(_conf)) {
            incrementLoop(_conf, l_, vars_);
            return;
        }
        l_.setFinished(true);
    }

    @Override
    public void incrementLoop(ContextEl _conf, LoopBlockStack _l,
            StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);

        _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VAR);
        _conf.getLastPage().setOffset(0);
        String var_ = getVariableName();
        LoopVariable lv_ = _vars.getVal(var_);
        Iterator<?> iterator_ = _l.getIterator();
        if (iterator_ != null) {
            lv_.setElement(ProcessXmlMethod.next(_conf, iterator_));
        } else {
            lv_.setElement(Array.get(lv_.getArray(), (int) _l.getIndex()));
        }
        lv_.setIndex(lv_.getIndex() + 1);
    }

    @Override
    public boolean keepLoop(ContextEl _conf) {
        PageEl ip_ = _conf.getLastPage();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        _conf.getLastPage().setProcessingAttribute(EMPTY_STRING);
        _conf.getLastPage().setOffset(0);
        return l_.hasNext(_conf);
    }

}
