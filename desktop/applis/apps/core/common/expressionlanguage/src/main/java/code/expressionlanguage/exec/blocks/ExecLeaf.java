package code.expressionlanguage.exec.blocks;

public abstract class ExecLeaf extends ExecBlock implements WithEl {
    ExecLeaf(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    public ExecBlock getFirstChild() {
        return null;
    }
}
