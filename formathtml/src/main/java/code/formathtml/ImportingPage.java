package code.formathtml;
import code.bean.Bean;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.stacks.RendIfStack;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendRemovableVars;
import code.formathtml.structs.BeanStruct;
import code.formathtml.structs.StdStruct;
import code.formathtml.util.*;
import code.sml.Element;
import code.sml.Node;
import code.sml.RowCol;
import code.util.CustList;
import code.util.*;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class ImportingPage {

    private static final String EMPTY_STRING = "";

    private static final String EQ = "=";

    private static final String READ_URL = "readUrl";

    private static final String BEAN_NAME = "beanName";

    private static final String SEP_INFO = "\n";

    private static final String SEP_KEY_VAL = ":";

    private PageEl pageEl = new SimplePageEl();
    private Struct internGlobal;

    private String processingAttribute = EMPTY_STRING;

    private CustList<BlockHtml> blockStacks = new CustList<BlockHtml>();

    private CustList<RendRemovableVars> rendBlockStacks = new CustList<RendRemovableVars>();

    private String beanName;

    private StringMap<LocalVariable> returnedValues = new StringMap<LocalVariable>();
    private StringMap<LocalVariable> internVars = new StringMap<LocalVariable>();

    private String readUrl = "";

    private String key;

    private String messageValue;

    private String renderedMessage;

    private String prefix = EMPTY_STRING;

    private ReadWriteHtml readWrite;
    private RendReadWrite rendReadWrite;

    private ProcessingHtml processingHtml;

    private boolean returning;

    private final boolean rendering;

    private int tabWidth;

    private int offset;

    private boolean finallyToProcess;

    private RendText rendText;
    public ImportingPage(boolean _rendering) {
        rendering = _rendering;
        processingHtml = new ProcessingHtml();
    }

    public String getInfos(Configuration _context) {
        StringList list_ = new StringList();
        StringBuilder keyMessage_ = new StringBuilder();
        if (key != null) {
            StringBuilder intro_ = new StringBuilder(key).append(EQ);
            keyMessage_.append(intro_).append(messageValue).append(SEP_INFO);
            keyMessage_.append(processingHtml.getHtml()).append(SEP_INFO);
        }
        StringBuilder page_ = new StringBuilder();
        if (rendering) {
            page_.append(SEP_INFO).append(processingHtml.getHtml()).append(SEP_INFO);
        }
        RowCol rc_ = processingHtml.getRowCol(processingAttribute, offset, tabWidth);
        StringBuilder str_ = new StringBuilder(READ_URL);
        str_.append(SEP_KEY_VAL);
        str_.append(readUrl);
        str_.append(page_);
        str_.append(SEP_INFO);
        str_.append(keyMessage_);
        str_.append(BEAN_NAME);
        str_.append(SEP_KEY_VAL);
        str_.append(beanName);
        str_.append(SEP_INFO);
        str_.append(rc_.display());
        str_.append(SEP_INFO);
        str_.append(list_.display());
        return str_.toString();
    }

    public RowCol getRowCol() {
        return processingHtml.getRowCol(processingAttribute, offset, tabWidth);
    }

    public int getSum() {
        return processingHtml.getSum(processingAttribute, offset);
    }

    public PageEl getPageEl() {
        return pageEl;
    }

    public void addToOffset(int _offset) {
        offset += _offset;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int _offset) {
        offset = _offset;
    }
    public String getNextTempVar() {
        int i_ = CustList.FIRST_INDEX;
        while (true) {
            if (pageEl.getLocalVar(StringList.concatNbs(FormatHtml.TMP_VAR,i_)) == null) {
                break;
            }
            i_++;
        }
        return StringList.concatNbs(FormatHtml.TMP_VAR,i_);
    }

    public boolean isRendering() {
        return rendering;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String _beanName) {
        beanName = _beanName;
    }

    public ReadWriteHtml getReadWrite() {
        return readWrite;
    }

    public void setReadWrite(ReadWriteHtml _readWrite) {
        readWrite = _readWrite;
    }

    public RendReadWrite getRendReadWrite() {
        return rendReadWrite;
    }

    public void setNullRendReadWrite() {
        rendReadWrite = null;
    }

    public void setRendReadWrite(RendReadWrite _rendReadWrite) {
        rendReadWrite = _rendReadWrite;
    }

    public Element getRoot() {
        return processingHtml.getRoot();
    }

    public void setRoot(Element _root) {
        processingHtml.setRoot(_root);
    }

    public String getGlobalClass() {
        return pageEl.getGlobalClass();
    }

    public Argument getGlobalArgument() {
        return pageEl.getGlobalArgument();
    }

    public void setGlobalArgumentStruct(Struct _obj, Configuration _context) {
        LgNames lgNames_ = _context.getStandards();
        pageEl.setGlobalClass(lgNames_.getStructClassName(_obj, _context.getContext()));
        pageEl.setGlobalArgumentStruct(_obj);
    }

    public void setGlobalArgumentObj(Object _obj, String _className, ContextEl _context) {
        pageEl.setGlobalClass(_className);
        pageEl.setGlobalArgumentStruct(StdStruct.wrapStd(_obj, _context));
    }

    public void setGlobalArgumentObj(Bean _obj) {
        pageEl.setGlobalClass(_obj.getClassName());
        pageEl.setGlobalArgumentStruct(new BeanStruct(_obj));
    }

    public void setGlobalClass(String _className) {
        pageEl.setGlobalClass(_className);
    }

    public void setGlobalArgument(Argument _globalArgument, Configuration _context) {
        LgNames lgNames_ = _context.getStandards();
        pageEl.setGlobalClass(lgNames_.getStructClassName(_globalArgument.getStruct(), _context.getContext()));
        pageEl.setGlobalArgument(_globalArgument);
    }

    public StringMap<LoopVariable> getVars() {
        return pageEl.getVars();
    }

    public void setVars(StringMap<LoopVariable> _vars) {
        pageEl.setVars(_vars);
    }


    public void putLocalVar(String _key, LocalVariable _var) {
        pageEl.putLocalVar(_key, _var);
    }

    public void removeLocalVar(String _key) {
        pageEl.removeLocalVar(_key);
    }

    public boolean containsLocalVar(String _key) {
        return pageEl.getLocalVar(_key) != null;
    }

    public LocalVariable getLocalVar(String _key) {
        return pageEl.getLocalVar(_key);
    }

    public StringMap<LocalVariable> getLocalVars() {
        return pageEl.getLocalVars();
    }

    public void setLocalVars(StringMap<LocalVariable> _localVars) {
        pageEl.setLocalVars(_localVars);
    }

    public StringMap<LocalVariable> getCatchVars() {
        return pageEl.getCatchVars();
    }

    public void setCatchVars(StringMap<LocalVariable> _catchVars) {
        pageEl.setCatchVars(_catchVars);
    }

    public StringMap<LocalVariable> getParameters() {
        return pageEl.getParameters();
    }

    public StringMap<LocalVariable> getReturnedValues() {
        return returnedValues;
    }

    public void setReturnedValues(StringMap<LocalVariable> _returnedValues) {
        returnedValues = _returnedValues;
    }

    public BlockHtml getLastStack() {
        return blockStacks.last();
    }

    public boolean noBlock() {
        return blockStacks.isEmpty();
    }

    public void addBlock(BlockHtml _b) {
        blockStacks.add(_b);
    }

    public void addBlock(RendRemovableVars _b) {
        rendBlockStacks.add(_b);
    }

    public void removeLastBlock() {
        blockStacks.removeLast();
    }

    public void removeRendLastBlock() {
        RendRemovableVars last_ = rendBlockStacks.last();
        if (last_ instanceof RendIfStack) {
            if (last_.getBlock() instanceof RendElement) {
                rendReadWrite.setWrite(rendReadWrite.getWrite().getParentNode());
            }
        }
        rendBlockStacks.removeLast();
    }
    public CustList<BlockHtml> getBlockStacks() {
        return blockStacks;
    }

    public void setBlockStacks(CustList<BlockHtml> _blockStacks) {
        blockStacks = _blockStacks;
    }

    public boolean isReturning() {
        return returning;
    }

    public void setReturning(boolean _returning) {
        returning = _returning;
    }

    public String getReadUrl() {
        return readUrl;
    }

    public void setReadUrl(String _readUrl) {
        readUrl = _readUrl;
    }

    public String getHtml() {
        return processingHtml.getHtml();
    }

    public void setHtml(String _html) {
        processingHtml.setHtml(_html);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String _key) {
        key = _key;
    }

    public String getMessageValue() {
        return messageValue;
    }

    public void setMessageValue(String _messageValue) {
        messageValue = _messageValue;
    }

    public String getRenderedMessage() {
        return renderedMessage;
    }

    public void setRenderedMessage(String _renderedMessage) {
        renderedMessage = _renderedMessage;
    }

    public Node getProcessingNode() {
        return processingHtml.getProcessingNode();
    }

    public void setProcessingNode(Node _processingNode) {
        processingHtml.setProcessingNode(_processingNode);
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        tabWidth = _tabWidth;
    }

    public ObjectMap<NodeAttribute,IntTreeMap<Integer>> getEncodedChars() {
        return processingHtml.getEncodedChars();
    }

    public void setEncodedChars(
            ObjectMap<NodeAttribute,IntTreeMap<Integer>> _encodedChars) {
        processingHtml.setEncodedChars(_encodedChars);
    }

    public String getProcessingAttribute() {
        return processingAttribute;
    }

    public void setProcessingAttribute(String _processingAttribute) {
        processingAttribute = _processingAttribute;
    }

    public boolean isLookForAttrValue() {
        return processingHtml.isLookForAttrValue();
    }

    public void setLookForAttrValue(boolean _lookForAttrValue) {
        processingHtml.setLookForAttrValue(_lookForAttrValue);
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String _prefix) {
        prefix = _prefix;
    }

    public Struct getInternGlobal() {
        return internGlobal;
    }

    public void setInternGlobal(Struct _internGlobal) {
        internGlobal = _internGlobal;
    }

    public boolean isFinallyToProcess() {
        return finallyToProcess;
    }

    public void setFinallyToProcess(boolean _finallyToProcess) {
        finallyToProcess = _finallyToProcess;
    }

    public CustList<RendRemovableVars> getRendBlockStacks() {
        return rendBlockStacks;
    }

    public void setRendBlockStacks(CustList<RendRemovableVars> _rendBlockStacks) {
        rendBlockStacks = _rendBlockStacks;
    }

    public RendRemovableVars getRendLastStack() {
        return rendBlockStacks.last();
    }

    public boolean hasBlock() {
        return !rendBlockStacks.isEmpty();
    }

    public RendLoopBlockStack getLastLoopIfPossible() {
        RendLoopBlockStack c_ = null;
        if (hasBlock() && getRendLastStack() instanceof RendLoopBlockStack) {
            c_ = (RendLoopBlockStack) getRendLastStack();
        }
        return c_;
    }

    public StringMap<LocalVariable> getInternVars() {
        return internVars;
    }

    public void setInternVars(StringMap<LocalVariable> _internVars) {
        internVars = _internVars;
    }

    public RendText getRendText() {
        return rendText;
    }

    public void setRendText(RendText _rendText) {
        rendText = _rendText;
    }
}
