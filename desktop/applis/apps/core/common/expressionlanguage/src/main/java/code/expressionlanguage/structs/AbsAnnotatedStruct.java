package code.expressionlanguage.structs;
import code.expressionlanguage.exec.blocks.ExecRootBlock;

public abstract class AbsAnnotatedStruct extends WithoutParentStruct {
    private ExecRootBlock owner;
    public ExecRootBlock getOwner() {
        return owner;
    }
    public void setOwner(ExecRootBlock _owner) {
        owner = _owner;
    }
}
