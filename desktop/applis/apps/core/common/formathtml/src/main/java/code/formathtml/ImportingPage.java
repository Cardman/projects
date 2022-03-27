package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.blocks.*;
import code.formathtml.stacks.*;
import code.formathtml.util.NodeContainer;
import code.util.*;

public final class ImportingPage {

    private final SimplePageEl pageEl = new SimplePageEl();
    private Struct internGlobal;

    private final CustList<RendAbstractStask> rendBlockStacks = new CustList<RendAbstractStask>();

    private String beanName;

    private final StringMap<LocalVariable> internVars = new StringMap<LocalVariable>();

    private RendReadWrite rendReadWrite;

    private int offset;
    private int opOffset;

    private RendDocumentBlock document;
    private ExecFormattedRootBlock globalClass;
    private boolean enabledOp=true;

    public ImportingPage() {
        initGlobal(null, null);
    }

    public int getTrace() {
        return opOffset + offset;
    }

    public RendDocumentBlock getDocument() {
        return document;
    }

    public void doc(RendDocumentBlock _doc) {
        this.document = _doc;
    }

    public SimplePageEl getPageEl() {
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

    public Argument getGlobalArgument() {
        return pageEl.getGlobalArgument();
    }

    public void setGlobalArgumentStruct(Struct _obj, ContextEl _ctx) {
        pageEl.setGlobalArgumentStruct(_obj);
        initGlobal(_obj, _ctx);
    }

    private void initGlobal(Struct _struct, ContextEl _ctx) {
        if (_struct != null) {
            String className_ = _struct.getClassName(_ctx);
            globalClass = new ExecFormattedRootBlock(_ctx.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(className_)),className_);
        } else {
            globalClass = ExecFormattedRootBlock.defValue();
        }
    }
    public StringMap<AbstractWrapper> getRefParams() {
        return getPageEl().getRefParams();
    }

    public void removeRefVar(String _key) {
        getPageEl().removeRefVar(_key);
    }

    public StringMap<LoopVariable> getVars() {
        return getPageEl().getVars();
    }


    public void addBlock(RendAbstractStask _b) {
        rendBlockStacks.add(_b);
    }

    public static boolean setRemovedCallingFinallyToProcessLoop(ImportingPage _ip, RendRemovableVars _vars, RendMethodCallingFinally _call, Struct _ex) {
        return _vars == null || setRemovedCallingFinallyToProcess(_ip, _vars, _call, _ex);
    }
    public static boolean setRemovedCallingFinallyToProcess(ImportingPage _ip, RendRemovableVars _vars, RendMethodCallingFinally _call, Struct _ex) {
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
            try_.setException(_ex);
            try_.setCalling(new RendAbruptCallingFinally(_call));
            return true;
        }
        _ip.removeRendLastBlock();
        return false;
    }
    public void removeRendLastBlock() {
        RendAbstractStask last_ = rendBlockStacks.last();
        last_.getCurrentVisitedBlock().removeAllVars(this);
        if (last_ instanceof RendIfStack) {
            if (((RendIfStack)last_).getBlock() instanceof RendElem) {
                rendReadWrite.setWrite(RendBlock.getParentNode(rendReadWrite));
            }
            if (((RendIfStack)last_).getBlock() instanceof RendFormInt) {
                CustList<LongTreeMap<NodeContainer>> map_ = rendReadWrite.getConf().getContainersMapStack();
                Longs formsNb_ = rendReadWrite.getConf().getFormsNb();
                Long nb_ = formsNb_.last();
                LongTreeMap<NodeContainer> containers_ = map_.last();
                rendReadWrite.getConf().getContainersMap().put(nb_, containers_);
                CustList<StringList> formatId_ = rendReadWrite.getConf().getFormatIdMapStack();
                StringList fid_ = formatId_.last();
                rendReadWrite.getConf().getFormatIdMap().put(nb_,fid_);
                rendReadWrite.getConf().getInputs().removeLast();
                map_.removeQuicklyLast();
                formatId_.removeQuicklyLast();
                formsNb_.removeQuicklyLast();
            }
        }
        rendBlockStacks.removeQuicklyLast();
    }

    public Struct getInternGlobal() {
        return internGlobal;
    }

    public void setInternGlobal(Struct _internGlobal) {
        internGlobal = _internGlobal;
    }

    public RendAbstractStask tryGetRendLastStack() {
        if (hasBlock()) {
            return rendBlockStacks.last();
        }
        return null;
    }

    public boolean hasBlock() {
        return !rendBlockStacks.isEmpty();
    }

    public RendLoopBlockStack getLastLoopIfPossible(RendBlock _bl) {
        RendLoopBlockStack c_ = null;
        RendAbstractStask last_ = tryGetRendLastStack();
        if (last_ instanceof RendLoopBlockStack) {
            c_ = (RendLoopBlockStack) last_;
        }
        if (c_ != null && c_.getCurrentVisitedBlock() == _bl) {
            return c_;
        }
        return null;
    }
    public boolean matchStatement(RendBlock _bl) {
        RendAbstractStask last_ = tryGetRendLastStack();
        if (!(last_ instanceof RendConditionBlockStack)) {
            return false;
        }
        return _bl == ((RendConditionBlockStack)last_).getBlock();
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

    public void putValueVar(String _var, AbstractWrapper _local) {
        pageEl.putValueVar(_var,_local);
    }

    public ExecFormattedRootBlock getGlobalClass() {
        return globalClass;
    }

}
