package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.dbg.BreakPoint;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.*;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public abstract class AbstractPageEl {

    protected static final String EMPTY_STRING = "";

    private final ExecFormattedRootBlock globalClass;

    private ReadWrite readWrite;
    private ExecBlock execBlock;
    private ExecBlock blockRoot;
    private boolean visited;

    private final CustList<AbstractStask> blockStacks = new CustList<AbstractStask>();

    private final CustList<ExpressionLanguage> currentEls = new CustList<ExpressionLanguage>();

    private int globalOffset;

    private int translatedOffset;

    private int offset;
    private final StringMap<LocalVariable> internVars = new StringMap<LocalVariable>();
    private ExecFileBlock file;

    private AbstractWrapper wrapper;
    private Argument returnedArgument = Argument.createVoid();

    private final PageElContent contentEx = new PageElContent();

    protected AbstractPageEl(ExecFormattedRootBlock _global) {
        globalClass = _global;
    }
    public Struct getGlobalStruct() {
        return contentEx.getGlobalStruct();
    }
    public Argument getGlobalArgument() {
        return contentEx.getGlobalArgument();
    }
    public final void setGlobalArgumentStruct(Struct _obj) {
        contentEx.setGlobalArgumentStruct(_obj);
    }

    public void setGlobalArgument(Argument _globalArgument) {
        contentEx.setGlobalArgument(_globalArgument);
    }

    public StringMap<AbstractWrapper> getRefParams() {
        return contentEx.getRefParams();
    }

    public void putValueVar(String _key, LocalVariable _var) {
        contentEx.getRefParams().put(_key, new VariableWrapper(_var));
    }
    public StringMap<LoopVariable> getVars() {
        return contentEx.getVars();
    }

    public void removeRefVar(String _key) {
        contentEx.removeRefVar(_key);
    }

    public Cache getCache() {
        return contentEx.getCache();
    }

    public void setCache(Cache _cache) {
        this.contentEx.setCache(_cache);
    }

    public PageElContent getContentEx() {
        return contentEx;
    }

    public final void forwardTo(AbstractPageEl _page, ContextEl _context, StackCall _stack) {
        _page.receive(wrapper, returnedArgument, _context, _stack);
        if (_page instanceof AbstractRefectCommonMethodPageEl) {
            int a_ = ((AbstractRefectCommonMethodPageEl) _page).getRef();
            callRefLater(_context, _stack, a_);
        }
        if (_page instanceof AbstractReflectConstructorPageEl) {
            int a_ = ((AbstractReflectConstructorPageEl) _page).getRef();
            callRefLater(_context, _stack, a_);
        }
    }

    private void callRefLater(ContextEl _context, StackCall _stack, int _ref) {
        if (_ref == 2) {
            for (EntryCust<String,AbstractWrapper> a: contentEx.getRefParams().entryList()) {
                if (a.getValue() instanceof ReflectVariableLaterWrapper) {
                    ((ReflectVariableLaterWrapper)a.getValue()).apply(_stack, _context);
                    if (_context.callsOrException(_stack)) {
                        return;
                    }
                }
            }
        }
    }

    public abstract void receive(AbstractWrapper _wrap, Argument _argument, ContextEl _context, StackCall _stack);
    public final String formatVarType(String _varType) {
//        if (getGlobalArgument().isNull()) {
//            return _varType;
//        }
        return ExecInherits.quickFormat(globalClass, _varType);
    }

    protected void basicReceive(AbstractWrapper _wrap, Argument _argument, ContextEl _context, StackCall _stackCall) {
        if (isEmptyEl()) {
            return;
        }
        getLastEl().setArgument(_wrap,_argument, _context, _stackCall);
    }

    public void setTranslatedOffset(int _translatedOffset) {
        translatedOffset = _translatedOffset;
    }

    public int getGlobalOffset() {
        return globalOffset;
    }

    public void setGlobalOffset(int _globalOffset) {
        globalOffset = _globalOffset;
    }

    public void setOffset(int _offset) {
        offset = _offset;
    }

    public int getTraceIndex() {
        return getGlobalOffset() + offset;
    }

    public int getTranslatedOffset() {
        return translatedOffset;
    }

    public abstract void processTagsBase(ContextEl _context, StackCall _stack);

    public ExpressionLanguage getCurrentEl(int _index, CustList<ExecOperationNode> _e, ExecBlock _coveredBlock) {
        ExpressionLanguage el_ = getNullableExp(_index);
        if (el_ == null) {
            el_ = new ExpressionLanguage(_e, _coveredBlock);
            currentEls.add(el_);
            setVisited(false);
        }
        return el_;
    }

    private ExpressionLanguage getNullableExp(int _index) {
        if (_index < currentEls.size()) {
            return currentEls.get(_index);
        }
        return null;
    }
    public boolean noBlock() {
        return blockStacks.isEmpty();
    }

    public LoopBlockStack getLastLoopIfPossible(ExecBlock _bl) {
        AbstractStask last_ = tryGetLastStack();
        if (last_ instanceof LoopBlockStack && ((LoopBlockStack)last_).getExecBlock() == _bl) {
            return (LoopBlockStack) last_;
        }
        return null;
    }
    public boolean matchStatement(ExecBlock _bl) {
        AbstractStask last_ = tryGetLastStack();
        return last_ instanceof ConditionBlockStack && _bl == ((ConditionBlockStack) last_).getBlock();
    }

    public AbstractStask tryGetLastStack() {
        if (noBlock()) {
            return null;
        }
        return blockStacks.last();
    }

    public void addBlock(AbstractStask _b) {
        blockStacks.add(_b);
    }

    public void removeLastBlock() {
        AbstractStask last_ = blockStacks.last();
        last_.getCurrentVisitedBlock().removeAllVars(this);
        blockStacks.removeQuicklyLast();
    }

    protected void commonTageBase(ContextEl _context, StackCall _stack, Argument _arg) {
        //method walk through
        ExecBlock en_ = getBlock();
        if (en_ instanceof ExecAbstractSwitchMethod) {
            setBlock(((ExecAbstractSwitchMethod)en_).processCase(Argument.getNullableValue(_arg),_stack));
            return;
        }
        if (en_ instanceof ExecAbstractExpressionReturnMethod) {
            ((ExecAbstractExpressionReturnMethod)en_).processEl(_context, _stack);
            return;
        }
        if (en_ instanceof MethodCallingFinally) {
            ((MethodCallingFinally)en_).removeBlockFinally(_stack.getLastPage());
            return;
        }
        if (en_ instanceof WithEl) {
            ((WithEl)en_).processEl(_context, _stack);
            return;
        }
        setNullReadWrite();
    }

    public void clearCurrentEls() {
        currentEls.clear();
    }

    public int sizeEl(){
        return currentEls.size();
    }

    public Argument getValue(int _index){
        if (_index >= currentEls.size()) {
            return Argument.createVoid();
        }
        return currentEls.get(_index).getArgument();
    }

    public boolean isEmptyEl() {
        return currentEls.isEmpty();
    }
    public ExecBlock getCoveredBlock() {
        return getLastEl().getCoveredBlock();
    }
    private ExpressionLanguage getLastEl() {
        return currentEls.last();
    }

    public ExecBlock getBlock() {
        return execBlock;
    }

    public void setBlock(ExecBlock _block) {
        execBlock = _block;
        setVisited(false);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean _v) {
        this.visited = _v;
    }

    public ReadWrite getReadWrite() {
        return readWrite;
    }
    public void setNullReadWrite() {
        readWrite = null;
    }

    public void setReadWrite(ReadWrite _readWrite) {
        readWrite = _readWrite;
        execBlock = _readWrite.getBlock();
    }

    public ExecBlock getBlockRoot() {
        return blockRoot;
    }

    public void setBlockRoot(ExecBlock _execBlockRoot) {
        blockRoot = _execBlockRoot;
    }
    public void globalOffset(int _offset) {
        setOffset(0);
        setGlobalOffset(_offset);
    }

    public StringMap<LocalVariable> getInternVars() {
        return internVars;
    }
    public void putInternVars(String _key, Struct _struct, ContextEl _context) {
        internVars.put(StringUtil.nullToEmpty(_key),LocalVariable.newLocalVariable(_struct,_context));
    }

    public ExecFileBlock getFile() {
        return file;
    }

    public void setFile(ExecFileBlock _execFile) {
        file = _execFile;
    }

    public Argument getReturnedArgument() {
        return returnedArgument;
    }

    public void setReturnedArgument(Argument _returnedArgument) {
        returnedArgument = Argument.getNullableValue(_returnedArgument);
    }

    public AbstractWrapper getWrapper() {
        return wrapper;
    }

    public void setWrapper(AbstractWrapper _wrapper) {
        wrapper = _wrapper;
    }

    public ExecFormattedRootBlock getGlobalClass() {
        return globalClass;
    }

    public ExecRootBlock getBlockRootType() {
        return globalClass.getRootBlock();
    }

    public boolean stopBreakPoint(ContextEl _context, StackCall _stackCall) {
        if (checkBreakPoint(_stackCall)&&!isVisited()) {
            setVisited(true);
            if (stopExc(_context, _stackCall)) {
                _stackCall.setGlobalOffset(getGlobalOffset());
                return true;
            }
            for (int i : list(_stackCall)) {
                BreakPoint bp_ = _context.getClasses().getDebugMapping().getBreakPointsBlock().getNotNull(getFile(), i);
                if (stopCurrentBp(bp_)) {
                    _stackCall.setGlobalOffset(i);
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    private boolean stopExc(ContextEl _context, StackCall _stackCall) {
        AbstractStask stLast_ = tryGetLastStack();
        ExecBlock bl_ = getBlock();
        if (stLast_ instanceof TryBlockStack && bl_ instanceof ExecAbstractCatchEval) {
            Struct e_ = ((TryBlockStack) stLast_).getException();
            ConditionReturn st_ = _context.getClasses().getDebugMapping().getExceptions().getVal(e_.getClassName(_context));
            if ((st_ == ConditionReturn.YES || st_ == ConditionReturn.CALL_EX) && ExecHelperBlocks.firstMatch(_context, _stackCall, ((ExecAbstractCatchEval)bl_).getContent(), e_, ((ExecAbstractCatchEval)bl_).isCatchAll()) && sizeEl() < 2) {
                return true;
            }
        }
        if (_stackCall.getCallingState() instanceof CustomFoundExc) {
            Struct e_ = ((CustomFoundExc) _stackCall.getCallingState()).getStruct();
            ConditionReturn st_ = _context.getClasses().getDebugMapping().getExceptions().getVal(e_.getClassName(_context));
            return st_ == ConditionReturn.NO || st_ == ConditionReturn.CALL_EX;
        }
        return false;
    }
    private boolean stopCurrentBp(BreakPoint _bp) {
        return _bp.isEnabled() && (!_bp.isEnabledChgtType() || _bp.isInstanceType() && this instanceof AbstractCallingInstancingPageEl || _bp.isStaticType() && this instanceof StaticInitPageEl);
    }

    private int[] list(StackCall _stackCall) {
        if (_stackCall.isCheckingException()) {
            return NumberUtil.wrapIntArray();
        }
        ExecBlock bl_ = getBlock();
        AbstractStask st_ = tryGetLastStack();
        if (st_ instanceof LoopBlockStack && bl_ instanceof ExecAbstractForEachLoop && !(bl_ instanceof ExecForEachIterable) && ((ExecAbstractForEachLoop) bl_).getVariable().getOffset() == getGlobalOffset()) {
            if (!((LoopBlockStack) st_).getContent().hasNext()) {
                return NumberUtil.wrapIntArray(((ExecAbstractForEachLoop) bl_).getSeparator());
            }
            return NumberUtil.wrapIntArray(getGlobalOffset(), ((ExecAbstractForEachLoop) bl_).getSeparator());
        }
        if (getLastLoopIfPossible(bl_) == null){
            if (bl_ instanceof ExecForEachIterable && sizeEl() == 2) {
                return NumberUtil.wrapIntArray(((ExecForEachIterable) bl_).getIteratorOffset());
            }
            if (bl_ instanceof ExecForEachTable && sizeEl() == 2) {
                return NumberUtil.wrapIntArray(((ExecForEachTable) bl_).getOffsets().getIteratorOffset());
            }
        }
        return NumberUtil.wrapIntArray(getGlobalOffset());
    }

    public boolean checkBreakPoint(StackCall _stackCall) {
        if (_stackCall.isCheckingException()) {
            return true;
        }
        if (readWrite == null) {
            return false;
        }
        ExecBlock bl_ = getBlock();
        if (bl_ instanceof ExecDeclareVariable || isEmptyEl()) {
            return false;
        }
        if (bl_ instanceof ExecLine || bl_ instanceof ExecAbstractReturnMethod || bl_ instanceof MethodCallingFinally || bl_ instanceof ExecThrowing || bl_ instanceof ExecDoWhileCondition) {
            return true;
        }
        AbstractStask st_ = tryGetLastStack();
        if (st_ instanceof TryBlockStack && bl_ instanceof ExecAbstractCatchEval) {
            return true;
        }
        if (st_ instanceof EnteredStack) {
            return !((EnteredStack) st_).isEntered();
        }
        if (st_ instanceof LoopBlockStack) {
            return !((LoopBlockStack) st_).getContent().isFinished();
        }
        if (st_ instanceof SwitchBlockStack) {
            return !((SwitchBlockStack) st_).isEntered();
        }
        return true;
    }

}
