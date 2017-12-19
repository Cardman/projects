package code.expressionlanguage;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.util.CallConstructor;
import code.expressionlanguage.methods.util.CallingClassConstructor;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.sml.DocumentBuilder;
import code.sml.RowCol;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

public final class PageEl {

    private static final String EMPTY_STRING = "";

    private static final String READ_URL = "readUrl";

    private static final String LINE_COL = "line col";

    private static final String BEAN_CLASS = "bean class";

    private static final String PARAMATERS = "parameters";
    private static final String CATCH_VARIABLES = "catch variables";
    private static final String LOCAL_VARIABLES = "local variables";

    private static final String SEP_INFO = "\n";

    private static final String SEP_KEY_VAL = ":";

    private ReadWrite readWrite;
    private Block blockRoot;

    private boolean initializingClass;

    private CallConstructor callingConstr = new CallConstructor();

    private final StringList intializedInterfaces = new StringList();

    /**Only used while throwing exception*/
    private Block currentBlock;

    private CustList<ExpressionLanguage> currentEls = new CustList<ExpressionLanguage>();

    private Argument returnedArgument;

    private Argument rightArgument;

    private String globalClass;

    private Argument globalArgument;

    private StringMap<LoopVariable> vars = new StringMap<LoopVariable>();

    private StringMap<LocalVariable> catchVars = new StringMap<LocalVariable>();

    private StringMap<LocalVariable> localVars = new StringMap<LocalVariable>();

    private StringMap<LocalVariable> parameters = new StringMap<LocalVariable>();

    private CustList<RemovableVars> blockStacks = new CustList<RemovableVars>();

    private boolean finallyToProcess;

    private String readUrl;

    private int tabWidth;

    private int offset;

    private String processingAttribute = EMPTY_STRING;

    public String getInfos(ContextEl _context) {
        return READ_URL+SEP_KEY_VAL+readUrl+SEP_INFO+getCommonInfosAndRc(getTrace(), _context);
    }

    public String getCommonInfosAndRc(RowCol _rc,ContextEl _context) {
        return getCommonInfos(_context)+_rc;
    }

    public RowCol getTrace() {
        RowCol rc_ = new RowCol();
        if (currentBlock != null){
            StringMap<RowCol> a_;
            a_ = currentBlock.getAttributes();
            StringMap<NatTreeMap<Integer,Integer>> e_;
            e_ = currentBlock.getEncoded();
            StringMap<Numbers<Integer>> o_;
            o_ = currentBlock.getOffsets();
            StringMap<Numbers<Integer>> t_;
            t_ = currentBlock.getTabs();
            RowCol endHeader_;
            endHeader_ = currentBlock.getEndHeader();
            rc_ = DocumentBuilder.getOffset(processingAttribute, a_, e_, offset, o_, t_, endHeader_, tabWidth);
        }
        return rc_;
    }

    private String getCommonInfos(ContextEl _context) {
        StringList list_ = new StringList();
        if (globalArgument != null) {
            Object glel_ = globalArgument.getObject();
            if (glel_ != null) {
                list_.add(BEAN_CLASS+SEP_KEY_VAL+globalArgument.getObjectClassName(_context));
            } else {
                list_.add(BEAN_CLASS+SEP_KEY_VAL+null);
            }
        } else {
            list_.add(BEAN_CLASS+SEP_KEY_VAL+null);
        }
        for (EntryCust<String,LoopVariable> e: vars.entryList()) {
            list_.add(e.getKey()+SEP_KEY_VAL+SEP_INFO+e.getValue().getInfos(_context));
        }
        list_.add(LOCAL_VARIABLES);
        for (EntryCust<String,LocalVariable> e: localVars.entryList()) {
            list_.add(e.getKey()+SEP_KEY_VAL+SEP_INFO+e.getValue().getInfos());
        }
        list_.add(CATCH_VARIABLES);
        for (EntryCust<String,LocalVariable> e: catchVars.entryList()) {
            list_.add(e.getKey()+SEP_KEY_VAL+SEP_INFO+e.getValue().getInfos());
        }
        list_.add(PARAMATERS);
        for (EntryCust<String,LocalVariable> e: parameters.entryList()) {
            list_.add(e.getKey()+SEP_KEY_VAL+SEP_INFO+e.getValue().getInfos());
        }
        list_.add(SEP_INFO);
        for (RemovableVars b: blockStacks) {
            list_.add(b.getInfos());
        }
        String keyMessage_ = EMPTY_STRING;
        return keyMessage_+SEP_INFO+list_.toString()+SEP_INFO+LINE_COL+SEP_KEY_VAL;
    }

    public void addToOffset(int _offset) {
        offset += _offset;
    }

    public String getNextTempVar(Classes _classes) {
        StringList resVar_ = _classes.getLocalVariablesNames();
        int i_ = CustList.FIRST_INDEX;
        while (true) {
            if (!resVar_.containsStr(Classes.TEMP_PREFIX+i_)) {
                if (!localVars.contains(Classes.TEMP_PREFIX+i_)) {
                    break;
                }
            }
            i_++;
        }
        return Classes.TEMP_PREFIX+i_;
    }

    public ReadWrite getReadWrite() {
        return readWrite;
    }
    public void setNullReadWrite() {
        readWrite = null;
    }

    public void setReadWrite(ReadWrite _readWrite) {
        readWrite = _readWrite;
    }

    public Block getBlockRoot() {
        return blockRoot;
    }

    public void setBlockRoot(Block _blockRoot) {
        blockRoot = _blockRoot;
    }

    public boolean isInitializingClass() {
        return initializingClass;
    }

    public void setInitializingClass(boolean _initializingClass) {
        initializingClass = _initializingClass;
    }

    public void exitFromConstructor() {
        setArgumentForConstructor();
        setNullReadWrite();
    }

    public void setArgumentForConstructor() {
        CallConstructor caller_ = getCallingConstr();
        if (!caller_.getInstancingStep().isCalling()) {
            setReturnedArgument(getGlobalArgument());
        } else if (getCall() != CallingClassConstructor.SUPER_CLASS){
            setReturnedArgument(getGlobalArgument());
        }
    }

    public CallingClassConstructor getCall() {
        return getCallingConstr().getInstancingStep().getCall();
    }

    public boolean isInstancing() {
        return getCallingConstr().getInstancingStep().isInstancing();
    }

    public ExpressionLanguage getCurrentEl(Block _block, int _index, ExpressionLanguage _default) {
        ExpressionLanguage el_;
        if (_index < currentEls.size()) {
            el_ = currentEls.get(_index);
        } else {
            el_ = _default;
            setCurrentBlock(_block);
            currentEls.add(el_);
        }
        return el_;
    }

    public CallConstructor getCallingConstr() {
        return callingConstr;
    }

    public void setCallingConstr(CallConstructor _callingConstr) {
        callingConstr = _callingConstr;
    }

    public StringList getIntializedInterfaces() {
        return intializedInterfaces;
    }

    public Block getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(Block _currentBlock) {
        currentBlock = _currentBlock;
    }

    public void clearCurrentEls() {
        currentEls.clear();
    }

    public ExpressionLanguage getLastEl() {
        return currentEls.last();
    }

    public void addCurrentEl(ExpressionLanguage _el) {
        currentEls.add(_el);
    }

    public Argument getReturnedArgument() {
        return returnedArgument;
    }

    public void setReturnedArgument(Argument _returnedArgument) {
        returnedArgument = _returnedArgument;
    }

    public Argument getRightArgument() {
        return rightArgument;
    }

    public void setRightArgument(Argument _rightArgument) {
        rightArgument = _rightArgument;
    }

    public String getGlobalClass() {
        return globalClass;
    }

    public void setGlobalClass(String _globalClass) {
        globalClass = _globalClass;
    }

    public String format(String _type, ContextEl _context) {
        boolean static_ = false;
        Argument gl_ = globalArgument;
        if (gl_ == null) {
            static_ = true;
        } else if (gl_.isNull()) {
            static_ = true;
        }
        if (!static_) {
            return Templates.format(globalClass, _type, _context);
        }
        return _type;
    }

    public String formatVarType(String _varType, ContextEl _cont) {
        if (globalArgument == null) {
            return _varType;
        }
        if (globalArgument.isNull()) {
            return _varType;
        }
        String objClass_ = globalArgument.getObjectClassName(_cont);
        String gl_ = globalClass;
        gl_ = StringList.getAllTypes(gl_).first();
        gl_ = Templates.getFullTypeByBases(objClass_, gl_, _cont);
        return Templates.format(gl_, _varType, _cont);
    }

    public Argument getGlobalArgument() {
        return globalArgument;
    }
    public void setGlobalArgumentStruct(Struct _obj) {
        Argument arg_ = new Argument();
        arg_.setStruct(_obj);
        globalArgument = arg_;
    }

    public void setGlobalArgumentObj(Object _obj) {
        Argument arg_ = new Argument();
        arg_.setObject(_obj);
        globalArgument = arg_;
    }

    public void setGlobalArgument(Class<?> _class) {
        Argument arg_ = new Argument();
        globalArgument = arg_;
    }

    public void setGlobalArgument(Argument _globalArgument) {
        globalArgument = _globalArgument;
    }

    public StringMap<LoopVariable> getVars() {
        return vars;
    }

    public void setVars(StringMap<LoopVariable> _vars) {
        vars = _vars;
    }

    public StringMap<LocalVariable> getLocalVars() {
        return localVars;
    }

    public void setLocalVars(StringMap<LocalVariable> _localVars) {
        localVars = _localVars;
    }

    public StringMap<LocalVariable> getCatchVars() {
        return catchVars;
    }

    public void setCatchVars(StringMap<LocalVariable> _catchVars) {
        catchVars = _catchVars;
    }

    public StringMap<LocalVariable> getParameters() {
        return parameters;
    }

    public void setParameters(StringMap<LocalVariable> _parameters) {
        parameters = _parameters;
    }
    public boolean noBlock() {
        return blockStacks.isEmpty();
    }

    public int nbBlocks() {
        return blockStacks.size();
    }

    public LoopBlockStack getLastLoopIfPossible() {
        LoopBlockStack c_ = null;
        if (!noBlock() && getLastStack() instanceof LoopBlockStack) {
            c_ = (LoopBlockStack) getLastStack();
        }
        return c_;
    }

    public RemovableVars getLastStack() {
        return blockStacks.last();
    }

    public void addBlock(RemovableVars _b) {
        blockStacks.add(_b);
    }

    public void removeLastBlock() {
        blockStacks.removeLast();
    }

    public CustList<RemovableVars> getBlockStacks() {
        return blockStacks;
    }

    public void setBlockStacks(CustList<RemovableVars> _blockStacks) {
        blockStacks = _blockStacks;
    }

    public String getReadUrl() {
        return readUrl;
    }

    public void setReadUrl(String _readUrl) {
        readUrl = _readUrl;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        tabWidth = _tabWidth;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int _offset) {
        offset = _offset;
    }

    public String getProcessingAttribute() {
        return processingAttribute;
    }

    public void setProcessingAttribute(String _processingAttribute) {
        processingAttribute = _processingAttribute;
    }

    public boolean isFinallyToProcess() {
        return finallyToProcess;
    }

    public void setFinallyToProcess(boolean _finallyToProcess) {
        finallyToProcess = _finallyToProcess;
    }
}
