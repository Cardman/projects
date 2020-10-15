package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.blocks.RootBlock;

public final class ResultTypeAncestor {
    private final boolean local;
    private final String simpleName;
    private RootBlock resolvedType;

    public ResultTypeAncestor(boolean _local, String _simpleName) {
        this.local = _local;
        this.simpleName = _simpleName;
    }

    public boolean isLocal() {
        return local;
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
