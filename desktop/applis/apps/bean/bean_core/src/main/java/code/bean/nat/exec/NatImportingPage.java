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

    private RendReadWrite natReadWrite;

    public RendReadWrite getRendReadWrite() {
        return natReadWrite;
    }

    public void setNullRendReadWrite() {
        natReadWrite = null;
    }

    public void setRendReadWrite(RendReadWrite _rendReadWrite) {
        natReadWrite = _rendReadWrite;
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
                natReadWrite.setWrite(RendBlock.getParentNode(natReadWrite));
            }
            if (((RendIfStack)last_).getBlock() instanceof RendFormInt) {
                CustList<LongTreeMap<NodeContainer>> map_ = natReadWrite.getConf().getContainersMapStack();
                Longs formsNb_ = natReadWrite.getConf().getFormsNb();
                Long nb_ = formsNb_.last();
                LongTreeMap<NodeContainer> containers_ = map_.last();
                natReadWrite.getConf().getContainersMap().put(nb_, containers_);
                CustList<StringList> formatId_ = natReadWrite.getConf().getFormatIdMapStack();
                StringList fid_ = formatId_.last();
                natReadWrite.getConf().getFormatIdMap().put(nb_,fid_);
                natReadWrite.getConf().getInputs().removeLast();
                map_.removeQuicklyLast();
                formatId_.removeQuicklyLast();
                formsNb_.removeQuicklyLast();
            }
        }
    }

    public RendAbstractStask tryGetNatLastStack() {
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
        RendAbstractStask last_ = tryGetNatLastStack();
        if (last_ instanceof RendLoopBlockStack) {
            c_ = (RendLoopBlockStack) last_;
        }
        if (c_ != null && c_.getCurrentVisitedBlock() == _bl) {
            return c_;
        }
        return null;
    }
    public boolean matchStatement(RendBlock _bl) {
        RendAbstractStask last_ = tryGetNatLastStack();
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
