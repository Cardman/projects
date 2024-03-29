package code.bean.nat.exec;

import code.bean.nat.exec.blocks.*;
import code.bean.nat.exec.variables.VariableWrapperNat;
import code.bean.nat.*;
import code.util.*;

public abstract class NatImportingPageAbs {

    private NatRendReadWrite rendReadWrite;

    private final CustList<NatAbstractStask> rendBlockStacks = new CustList<NatAbstractStask>();

    private NaSt globalArgument = NaNu.NULL_VALUE;
    private final StringMap<Integer> vars = new StringMap<Integer>();
    private final StringMap<VariableWrapperNat> refParams = new StringMap<VariableWrapperNat>();
    private NaSt internGlobal;

    private String beanName;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String _beanName) {
        beanName = _beanName;
    }

    public NaSt getInternGlobal() {
        return internGlobal;
    }

    public void setInternGlobal(NaSt _internGlobal) {
        internGlobal = _internGlobal;
    }

    public NaSt getGlobalArgument() {
        return globalArgument;
    }

    public void setGlobalArgumentStruct(NaSt _obj) {
        globalArgument = _obj;
    }

    public StringMap<VariableWrapperNat> getRefParams() {
        return refParams;
    }

    public void removeRefVar(String _key) {
        refParams.removeKey(_key);
    }

    public StringMap<Integer> getVars() {
        return vars;
    }

    public void removeRendLastBlock() {
        NatAbstractStask last_ = getRendBlockStacks().last();
        NatParentBlock cur_ = last_.getCurrentVisitedBlock();
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
    public abstract void removeRendLastBlockSt();

    public NatAbstractStask tryGetRendLastStack() {
        if (hasBlock()) {
            return lastStack();
        }
        return null;
    }

    public NatAbstractStask lastStack() {
        return rendBlockStacks.last();
    }

    public boolean hasBlock() {
        return !rendBlockStacks.isEmpty();
    }

    public NatLoopBlockStack getLastLoopIfPossible(NatBlock _bl) {
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
    public boolean matchStatement(NatBlock _bl) {
        NatAbstractStask last_ = tryGetRendLastStack();
        if (!(last_ instanceof NatIfStack)) {
            return false;
        }
        return _bl == ((NatIfStack)last_).getBlock();
    }

    public void putValueVar(String _var, VariableWrapperNat _local) {
        refParams.put(_var,_local);
    }
    public abstract NatImportingPageAbs fwd();

    public abstract NatRendReadWrite newNatRendReadWrite(NatRendStackCall _rendStackCall);

}
