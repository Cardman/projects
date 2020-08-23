package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.blocks.RootBlock;

public final class ResultTypeAncestor {
    private final boolean local;
    private final String simpleName;
    private RootBlock resolvedType;

    public ResultTypeAncestor(boolean local, String simpleName) {
        this.local = local;
        this.simpleName = simpleName;
    }

    public boolean isLocal() {
        return local;
    }

    public RootBlock getResolvedType() {
        return resolvedType;
    }

    public void setResolvedType(RootBlock resolvedType) {
        this.resolvedType = resolvedType;
    }

    public String getSimpleName() {
        return simpleName;
    }
}
