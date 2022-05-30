package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.GraphicErrorList;
import code.expressionlanguage.analyze.files.OffsetStringInfo;

public abstract class BracedBlock extends AbsBk {

    private AbsBk firstChild;

    private final GraphicErrorList globalErrorsPars = new GraphicErrorList();
    private final OffsetStringInfo labelInfo;
    protected BracedBlock(int _offset) {
        this(_offset,new OffsetStringInfo(0,""));
    }
    protected BracedBlock(int _offset, OffsetStringInfo _lab) {
        super(_offset);
        labelInfo = _lab;
    }

    public String getLabel() {
        return getLabelInfo().getInfo();
    }

    public int getLabelOffset() {
        return getLabelInfo().getOffset();
    }

    public OffsetStringInfo getLabelInfo() {
        return labelInfo;
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
