package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.syntax.CallerKind;
import code.expressionlanguage.analyze.syntax.FileBlockIndex;
import code.expressionlanguage.analyze.syntax.SrcFileLocation;
import code.gui.MutableTreeNodeNav;
import code.util.CustList;

public final class MetaCaller {
    private final CallerKind kind;
    private final SrcFileLocation call;
    private final CustList<FileBlockIndex> number;
    private boolean recursive;
    private final MutableTreeNodeNav<MetaCaller> meta = new MutableTreeNodeNav<MetaCaller>();

    public MetaCaller(CallerKind _k,SrcFileLocation _c, CustList<FileBlockIndex> _n) {
        this.kind = _k;
        this.call = _c;
        this.number = _n;
        meta.info(this);
    }

    public MutableTreeNodeNav<MetaCaller> getMeta() {
        return meta;
    }

    public boolean isRecursive() {
        return recursive;
    }

    public void recurse() {
        recursive = true;
    }

    public CallerKind getKind() {
        return kind;
    }

    public SrcFileLocation getCall() {
        return call;
    }

    public CustList<FileBlockIndex> getNumber() {
        return number;
    }
}
