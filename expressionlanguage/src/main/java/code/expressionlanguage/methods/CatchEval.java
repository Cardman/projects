package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.exceptions.AlreadyDefinedVarException;
import code.expressionlanguage.methods.exceptions.BadTryException;
import code.expressionlanguage.stacks.TryBlockStack;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class CatchEval extends BracedStack implements Eval, IncrCurrentGroup, IncrNextGroup {

    private final String className;

    private int classNameOffset;

    private final String variableName;

    private int variableNameOffset;

    public CatchEval(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        className = _el.getAttribute(ATTRIBUTE_CLASS);
        variableName = _el.getAttribute(ATTRIBUTE_VAR);
    }

    public CatchEval(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetStringInfo _className, OffsetStringInfo _variable, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        variableNameOffset = _variable.getOffset();
    }

    public int getClassNameOffset() {
        return classNameOffset;
    }
    public int getVariableNameOffset() {
        return variableNameOffset;
    }
    public String getClassName() {
        return className;
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public NatTreeMap<String,String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        tr_.put(ATTRIBUTE_CLASS, className);
        return tr_;
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        Block next_ = getPreviousSibling();
        boolean existTry_ = false;
        while (next_ != null) {
            if (next_ instanceof TryEval) {
                existTry_ = true;
                break;
            }
            if (!(next_ instanceof CatchEval)) {
                break;
            }
            next_ = next_.getPreviousSibling();
        }
        if (!existTry_) {
            PageEl page_ = _cont.getLastPage();
            page_.setProcessingAttribute(EMPTY_STRING);
            page_.setOffset(0);
            throw new BadTryException(_cont.joinPages());
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(ATTRIBUTE_CLASS);
        page_.setOffset(0);
        page_.setProcessingAttribute(ATTRIBUTE_VAR);
        page_.setOffset(0);
        if (_cont.getLastPage().getCatchVars().contains(variableName)) {
            throw new AlreadyDefinedVarException(StringList.concat(variableName,RETURN_LINE,_cont.joinPages()));
        }
        if (getFirstChild() == null) {
            return;
        }
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(className);
        _cont.getLastPage().getCatchVars().put(variableName, lv_);
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return true;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        Block next_ = getNextSibling();
        return next_ instanceof CatchEval || next_ instanceof FinallyEval;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return true;
    }

    @Override
    public void checkCallConstructor(ContextEl _cont) {
    }

    @Override
    public String getTagName() {
        return TAG_CATCH;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        TryBlockStack ts_ = (TryBlockStack) ip_.getLastStack();
        ts_.setThrownException(null);
        if (ts_.getLastCatchBlock() == this) {
            ip_.removeLastBlock();
            processBlock(_cont);
        } else {
            ts_.setVisitedCatch(getIndexInGroup()-1);
            rw_.setBlock(getNextSibling());
        }
    }

    @Override
    public void exitStack(ContextEl _context) {
        PageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        TryBlockStack tryStack_ = (TryBlockStack) ip_.getLastStack();
        CatchEval catch_ = (CatchEval) tryStack_.getCurrentCatchBlock();
        String var_ = catch_.getVariableName();
        StringMap<LocalVariable> vars_ = ip_.getCatchVars();
        vars_.removeKey(var_);
        rw_.setBlock(catch_);
    }

}
