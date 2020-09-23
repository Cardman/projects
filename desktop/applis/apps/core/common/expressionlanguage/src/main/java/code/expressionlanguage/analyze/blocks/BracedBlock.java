package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.GraphicErrorList;
import code.expressionlanguage.analyze.files.OffsetsBlock;

public abstract class BracedBlock extends Block {

    private Block firstChild;

    private GraphicErrorList globalErrorsPars = new GraphicErrorList();
    BracedBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    public final void appendChild(Block _child) {
        _child.setParent(this);
        FileBlock file_ = getFile();
        if (file_ != null) {
            _child.setFile(file_);
        }
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        Block child_ = firstChild;
        while (true) {
            Block sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                _child.setPreviousSibling(child_);
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    @Override
    public void checkTree(AnalyzingEl _anEl, AnalyzedPageEl _page) {
    }

    @Override
    public final Block getFirstChild() {
        return firstChild;
    }


    public void removeAllVars(AnalyzedPageEl _ip) {
        removeLocalVars(_ip);
    }

    public final void removeLocalVars(AnalyzedPageEl _ip) {
        for (Block s: ClassesUtil.getDirectChildren(this)) {
            if (s instanceof DeclareVariable) {
                for (String v: ((DeclareVariable)s).getVariableNames()) {
                    _ip.getInfosVars().removeKey(v);
                }
            }
        }
    }

    public GraphicErrorList getGlobalErrorsPars() {
        return globalErrorsPars;
    }
}
