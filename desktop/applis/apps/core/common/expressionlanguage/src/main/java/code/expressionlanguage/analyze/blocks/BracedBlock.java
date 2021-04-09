package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.GraphicErrorList;

public abstract class BracedBlock extends AbsBk {

    private AbsBk firstChild;

    private final GraphicErrorList globalErrorsPars = new GraphicErrorList();
    BracedBlock(int _offset) {
        super(_offset);
    }

    public final void appendChild(AbsBk _child) {
        _child.setParent(this);
        FileBlock file_ = getFile();
        if (file_ != null) {
            _child.setFile(file_);
        }
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        AbsBk child_ = firstChild;
        while (true) {
            AbsBk sibling_ = child_.getNextSibling();
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
    public final AbsBk getFirstChild() {
        return firstChild;
    }


    public void removeAllVars(AnalyzedPageEl _ip) {
        removeLocalVars(_ip);
    }

    public final void removeLocalVars(AnalyzedPageEl _ip) {
        for (AbsBk s: ClassesUtil.getDirectChildren(this)) {
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
