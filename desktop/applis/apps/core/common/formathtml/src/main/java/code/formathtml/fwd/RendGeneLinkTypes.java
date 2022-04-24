package code.formathtml.fwd;

import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;
import code.util.StringList;

public final class RendGeneLinkTypes {
    private final CustList<RendDynOperationNode> geneLink;
    private final StringList types;

    public RendGeneLinkTypes(CustList<RendDynOperationNode> _lk, StringList _ty) {
        this.geneLink = _lk;
        this.types = _ty;
    }

    public CustList<RendDynOperationNode> getGeneLink() {
        return geneLink;
    }

    public StringList getTypes() {
        return types;
    }
}
