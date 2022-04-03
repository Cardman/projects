package code.bean.nat.exec;

import code.bean.nat.exec.blocks.NatRendAbstractForEachLoop;
import code.bean.nat.exec.blocks.NatRendForEachTable;
import code.bean.nat.exec.variables.VariableWrapperNat;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.AbsImportingPage;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendElem;
import code.formathtml.exec.blocks.RendFormInt;
import code.formathtml.exec.blocks.RendParentBlock;
import code.util.*;

public final class NatImportingPage extends AbsImportingPage {

    private NatRendReadWrite rendReadWrite;

    private final CustList<NatAbstractStask> rendBlockStacks = new CustList<NatAbstractStask>();

    private Argument globalArgument = Argument.createVoid();
    private final StringMap<LoopVariable> vars = new StringMap<LoopVariable>();
    private final StringMap<VariableWrapperNat> refParams = new StringMap<VariableWrapperNat>();

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

    public void removeRendLastBlock() {
        NatAbstractStask last_ = getRendBlockStacks().last();
        RendParentBlock cur_ = last_.getCurrentVisitedBlock();
        if (cur_ instanceof NatRendAbstractForEachLoop) {
            ((NatRendAbstractForEachLoop)cur_).removeAllVars(this);
        }
        if (cur_ instanceof NatRendForEachTable) {
            ((NatRendForEachTable)cur_).removeAllVars(this);
        }
        removeRendLastBlockSt();
    }

    public NatRendReadWrite getRendReadWrite() {
        return rendReadWrite;
    }

    public void setNullRendReadWrite() {
        rendReadWrite = null;
    }

    public void setRendReadWrite(NatRendReadWrite _rendReadWrite) {
        rendReadWrite = _rendReadWrite;
    }

    public CustList<NatAbstractStask> getRendBlockStacks() {
        return rendBlockStacks;
    }

    public void addBlock(NatAbstractStask _b) {
        rendBlockStacks.add(_b);
    }
    public void removeRendLastBlockSt() {
        NatAbstractStask last_ = rendBlockStacks.last();
        if (last_ instanceof NatIfStack) {
            if (((NatIfStack)last_).getBlock() instanceof RendElem) {
                rendReadWrite.setWrite(RendBlock.getParentNode(rendReadWrite));
            }
            if (((NatIfStack)last_).getBlock() instanceof RendFormInt) {
                CustList<LongTreeMap<NatNodeContainer>> map_ = rendReadWrite.getConf().getContainersMapStack();
                Longs formsNb_ = rendReadWrite.getConf().getFormsNb();
                long nb_ = formsNb_.last();
                LongTreeMap<NatNodeContainer> containers_ = map_.last();
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

    public NatAbstractStask tryGetRendLastStack() {
        if (hasBlock()) {
            return rendBlockStacks.last();
        }
        return null;
    }

    public boolean hasBlock() {
        return !rendBlockStacks.isEmpty();
    }

    public NatLoopBlockStack getLastLoopIfPossible(RendBlock _bl) {
        NatLoopBlockStack c_ = null;
        NatAbstractStask last_ = tryGetRendLastStack();
        if (last_ instanceof NatLoopBlockStack) {
            c_ = (NatLoopBlockStack) last_;
        }
        if (c_ != null && c_.getCurrentVisitedBlock() == _bl) {
            return c_;
        }
        return null;
    }
    public boolean matchStatement(RendBlock _bl) {
        NatAbstractStask last_ = tryGetRendLastStack();
        if (!(last_ instanceof NatIfStack)) {
            return false;
        }
        return _bl == ((NatIfStack)last_).getBlock();
    }

    public void putValueVar(String _var, VariableWrapperNat _local) {
        refParams.put(_var,_local);
    }

}
