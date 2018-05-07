package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.sml.Element;
import code.util.NatTreeMap;

public final class StaticBlock extends InitBlock {

    public StaticBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public StaticBlock(ContextEl _importingPage, int _indexChild, BracedBlock _m, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public NatTreeMap<String, String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    @Override
    public NatTreeMap<Integer,String> getClassNamesOffsets(ContextEl _context) {
        NatTreeMap<Integer,String> tr_ = new NatTreeMap<Integer,String>();
        return tr_;
    }

    @Override
    public boolean isStaticContext() {
        return true;
    }

    @Override
    public String getTagName() {
        return TAG_STATIC;
    }
}
