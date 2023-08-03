package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.blocks.RootBlock;

public final class ResultTypeAncestor {
    private final boolean local;
    private final String simpleName;
    private final RootBlock owner;
    private RootBlock resolvedType;

    public ResultTypeAncestor(boolean _local, String _simpleName, RootBlock _o) {
        this.local = _local;
        this.simpleName = _simpleName;
        this.owner = _o;
    }

    public boolean isLocal() {
        return local;
    }

    public RootBlock getOwner() {
        return owner;
    }

    public RootBlock getResolvedType() {
        return resolvedType;
    }

    public void setResolvedType(RootBlock _resolvedType) {
        this.resolvedType = _resolvedType;
    }

    public String getSimpleName() {
        return simpleName;
    }
}
