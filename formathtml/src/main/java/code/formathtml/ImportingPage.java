package code.formathtml;
import code.expressionlanguage.Argument;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.stacks.RendIfStack;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendRemovableVars;
import code.formathtml.util.*;
import code.util.*;

public final class ImportingPage {

    private static final String EMPTY_STRING = "";

    private PageEl pageEl = new SimplePageEl();
    private Struct internGlobal;

    private String processingAttribute = EMPTY_STRING;

    private CustList<RendRemovableVars> rendBlockStacks = new CustList<RendRemovableVars>();

    private String beanName;

    private StringMap<LocalVariable> internVars = new StringMap<LocalVariable>();

    private String readUrl = "";

    private String key;

    private String prefix = EMPTY_STRING;

    private RendReadWrite rendReadWrite;

    private int tabWidth;

    private int offset;
    private int opOffset;

    private boolean finallyToProcess;

    private String file = "";

    private boolean enabledOp=true;

    public int getRowFile(int _sum) {
        int i_ = 0;
        int r_ = 1;
        while (i_ < Math.min(_sum,file.length())) {
            char ch_ = file.charAt(i_);
            if (ch_ == '\n') {
                r_++;
            }
            i_++;
        }
        return r_;
    }

    public int getColFile(int _sum, int _r) {
        int i_ = 0;
        int r_ = 1;
        while (r_ < _r) {
            char ch_ = file.charAt(i_);
            if (ch_ == '\n') {
                r_++;
            }
            i_++;
        }
        int begin_ = i_;
        int d_ = 0;
        int count_ =  Math.min(_sum,file.length()-1);
        for (int j = begin_; j <= count_; j++) {
            char ch_ = file.charAt(j);
            if (ch_ == '\t') {
                d_ += tabWidth;
                d_ -= d_ % tabWidth;
            } else {
                d_++;
            }
        }
        return d_;
    }
    public int getSum() {
        if (rendReadWrite == null) {
            return 0;
        }
        return AnalyzingDoc.getSum(opOffset,offset,rendReadWrite.getRead(),processingAttribute);
    }

    public void setFile(String _file) {
        file = _file;
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

    public void setOpOffset(int _opOffset) {
        if (!isEnabledOp()) {
            return;
        }
        opOffset = _opOffset;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String _beanName) {
        beanName = _beanName;
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

    public void setGlobalClass(String _className) {
        pageEl.setGlobalClass(_className);
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

    public StringMap<LocalVariable> getLocalVars() {
        return pageEl.getLocalVars();
    }

    public void setLocalVars(StringMap<LocalVariable> _localVars) {
        pageEl.setLocalVars(_localVars);
    }

    public StringMap<LocalVariable> getCatchVars() {
        return pageEl.getCatchVars();
    }

    public StringMap<LocalVariable> getParameters() {
        return pageEl.getParameters();
    }

    public void addBlock(RendRemovableVars _b) {
        rendBlockStacks.add(_b);
    }

    public void removeRendLastBlock() {
        RendRemovableVars last_ = rendBlockStacks.last();
        if (last_ instanceof RendIfStack) {
            if (last_.getBlock() instanceof RendElement) {
                rendReadWrite.setWrite(rendReadWrite.getWrite().getParentNode());
            }
            if (last_.getBlock() instanceof RendForm) {
                CustList<LongTreeMap<NodeContainer>> map_ = rendReadWrite.getContainersMap();
                Longs formsNb_ = rendReadWrite.getFormsNb();
                Long nb_ = formsNb_.last();
                LongTreeMap<NodeContainer> containers_ = map_.last();
                rendReadWrite.getConf().getContainersMap().put(nb_, containers_);
                CustList<StringList> formatId_ = rendReadWrite.getFormatIdMap();
                StringList fid_ = formatId_.last();
                rendReadWrite.getConf().getFormatIdMap().put(nb_,fid_);
                rendReadWrite.getInputs().removeLast();
                map_.removeLast();
                formatId_.removeLast();
                formsNb_.removeLast();
            }
        }
        rendBlockStacks.removeLast();
    }

    public String getReadUrl() {
        return readUrl;
    }

    public void setReadUrl(String _readUrl) {
        readUrl = _readUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String _key) {
        key = _key;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        tabWidth = _tabWidth;
    }

    public String getProcessingAttribute() {
        return processingAttribute;
    }

    public void setProcessingAttribute(String _processingAttribute) {
        processingAttribute = _processingAttribute;
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

    public boolean isEnabledOp() {
        return enabledOp;
    }

    public void setEnabledOp(boolean _enabledOp) {
        enabledOp = _enabledOp;
    }
}
