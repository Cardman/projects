package code.expressionlanguage.exec.blocks;

public abstract class ExecBlock {

    protected static final String EMPTY_STRING = "";

    private ExecBracedBlock parent;

    private ExecBlock nextSibling;

    private ExecBlock previousSibling;

    private ExecAbstractFileBlock file;

    protected ExecBlock() {
    }
    protected final void setParent(ExecBracedBlock _b) {
        parent = _b;
    }

    public final ExecAbstractFileBlock getFile() {
        return file;
    }

    public void setFile(ExecAbstractFileBlock _file) {
        file = _file;
    }


    public final ExecBlock getPreviousSibling() {
        return previousSibling;
    }

    public final ExecBlock getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(ExecBlock _nextSibling) {
        nextSibling = _nextSibling;
    }
    final void setPreviousSibling(ExecBlock _previousSibling) {
        previousSibling = _previousSibling;
    }

    public final ExecBracedBlock getParent() {
        return parent;
    }

}
