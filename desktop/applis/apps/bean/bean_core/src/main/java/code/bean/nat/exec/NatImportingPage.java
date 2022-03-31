package code.bean.nat.exec;

import code.bean.nat.exec.blocks.NatRendAbstractForEachLoop;
import code.bean.nat.exec.blocks.NatRendForEachTable;
import code.bean.nat.exec.variables.VariableWrapperNat;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.AbsImportingPage;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.exec.stacks.RendAbstractStask;
import code.util.StringMap;

public final class NatImportingPage extends AbsImportingPage {

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
        RendAbstractStask last_ = getRendBlockStacks().last();
        RendParentBlock cur_ = last_.getCurrentVisitedBlock();
        if (cur_ instanceof NatRendAbstractForEachLoop) {
            ((NatRendAbstractForEachLoop)cur_).removeAllVars(this);
        }
        if (cur_ instanceof NatRendForEachTable) {
            ((NatRendForEachTable)cur_).removeAllVars(this);
        }
        removeRendLastBlockSt();
    }

    public void putValueVar(String _var, VariableWrapperNat _local) {
        refParams.put(_var,_local);
    }

}
