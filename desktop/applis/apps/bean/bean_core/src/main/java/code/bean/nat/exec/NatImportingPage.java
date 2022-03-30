package code.bean.nat.exec;

import code.bean.nat.exec.variables.VariableWrapperNat;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendElem;
import code.formathtml.exec.blocks.RendFormInt;
import code.formathtml.stacks.*;
import code.formathtml.util.NodeContainer;
import code.util.*;

public final class NatImportingPage {


    private Argument globalArgument = Argument.createVoid();
    private final StringMap<LoopVariable> vars = new StringMap<LoopVariable>();
    private final StringMap<VariableWrapperNat> refParams = new StringMap<VariableWrapperNat>();
    private Struct internGlobal;

    private final CustList<RendAbstractStask> natBlockStacks = new CustList<RendAbstractStask>();

    private String beanName;

    private RendReadWrite rendReadWrite;

    public RendReadWrite getRendReadWrite() {
        return rendReadWrite;
    }

    public void setNullRendReadWrite() {
        rendReadWrite = null;
    }

    public void setRendReadWrite(RendReadWrite _rendReadWrite) {
        rendReadWrite = _rendReadWrite;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String _beanName) {
        beanName = _beanName;
    }

    public Argument getGlobalArgument() {
        return globalArgument;
    }

    public void setGlobalArgumentStruct(Struct _obj) {
        globalArgument = new Argument(_obj);
    }

    public StringMap<VariableWrapperNat> getRefParams() {
        return refParams;
    }

    public void removeRefVar(String _key) {
        refParams.removeKey(_key);
    }

    public StringMap<LoopVariable> getVars() {
        return vars;
    }


    public void addBlock(RendAbstractStask _b) {
        natBlockStacks.add(_b);
    }

    public void removeRendLastBlock() {
        RendAbstractStask last_ = natBlockStacks.last();
//        last_.getCurrentVisitedBlock().removeAllVars(this);
        local(last_);
        natBlockStacks.removeQuicklyLast();
    }

    private void local(RendAbstractStask last_) {
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
    }

    public RendAbstractStask tryGetRendLastStack() {
        if (hasBlock()) {
            return natBlockStacks.last();
        }
        return null;
    }

    public boolean hasBlock() {
        return !natBlockStacks.isEmpty();
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

    public void putValueVar(String _var, VariableWrapperNat _local) {
        refParams.put(_var,_local);
    }

    public Struct getInternGlobal() {
        return internGlobal;
    }

    public void setInternGlobal(Struct _internGlobal) {
        internGlobal = _internGlobal;
    }

}
