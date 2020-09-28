package code.formathtml;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.exec.blocks.*;
import code.formathtml.stacks.*;
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

    private String prefix = EMPTY_STRING;

    private RendReadWrite rendReadWrite;

    private int tabWidth;

    private int offset;
    private int opOffset;

    private String file = "";

    private boolean enabledOp=true;

    private RendBlock root;

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
        pageEl.setGlobalClass(_obj.getClassName(_context.getContext()));
        pageEl.setGlobalArgumentStruct(_obj);
    }

    public StringMap<LocalVariable> getValueVars() {
        return getPageEl().getValueVars();
    }
    public StringMap<LoopVariable> getVars() {
        return getPageEl().getVars();
    }


    public void putLocalVar(String _key, LocalVariable _var) {
        pageEl.putLocalVar(_key, _var);
    }

    public void removeLocalVar(String _key) {
        pageEl.removeLocalVar(_key);
    }

    public void addBlock(RendRemovableVars _b) {
        rendBlockStacks.add(_b);
    }

    public static boolean setRemovedCallingFinallyToProcess(ImportingPage _ip, RendRemovableVars _vars, RendCallingFinally _call, Struct _ex) {
        if (!(_vars instanceof RendTryBlockStack)) {
            _ip.removeRendLastBlock();
            return false;
        }
        RendTryBlockStack try_ = (RendTryBlockStack) _vars;
        if (try_.getCurrentVisitedBlock() instanceof RendFinallyEval) {
            _ip.removeRendLastBlock();
            return false;
        }
        RendParentBlock br_ = try_.getLastBlock();
        if (br_ instanceof RendFinallyEval) {
            _ip.getRendReadWrite().setRead(br_);
            try_.setCalling(_call.newAbruptCallingFinally(_ex));
            return true;
        }
        _ip.removeRendLastBlock();
        return false;
    }
    public void removeRendLastBlock() {
        RendRemovableVars last_ = rendBlockStacks.last();
        last_.getCurrentVisitedBlock().removeAllVars(this);
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

    public void setTabWidth(int _tabWidth) {
        tabWidth = _tabWidth;
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

    public RendRemovableVars getRendLastStack() {
        return rendBlockStacks.last();
    }

    public boolean hasBlock() {
        return !rendBlockStacks.isEmpty();
    }

    public RendLoopBlockStack getLastLoopIfPossible(RendBlock _bl) {
        RendLoopBlockStack c_ = null;
        if (hasBlock() && getRendLastStack() instanceof RendLoopBlockStack) {
            c_ = (RendLoopBlockStack) getRendLastStack();
        }
        if (c_ != null && c_.getBlock() == _bl) {
            return c_;
        }
        return null;
    }
    public boolean matchStatement(RendBlock _bl) {
        if (!hasBlock()) {
            return false;
        }
        return _bl == getRendLastStack().getBlock();
    }

    public StringMap<LocalVariable> getInternVars() {
        return internVars;
    }
    public void clearInternVars() {
        internVars.clear();
    }

    public void putInternVars(String _key, Struct _struct, ContextEl _context) {
        internVars.put(_key,LocalVariable.newLocalVariable(_struct,_context));
    }

    public void putInternVars(String _key, LocalVariable _loc) {
        internVars.put(_key,_loc);
    }
    public boolean isEnabledOp() {
        return enabledOp;
    }

    public void setEnabledOp(boolean _enabledOp) {
        enabledOp = _enabledOp;
    }

    public RendBlock getRoot() {
        return root;
    }

    public void setRoot(RendBlock _root) {
        root = _root;
    }

    public void putValueVar(String _var, LocalVariable _local) {
        pageEl.putValueVar(_var,_local);
    }
}
