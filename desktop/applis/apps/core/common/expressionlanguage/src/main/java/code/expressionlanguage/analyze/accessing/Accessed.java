package code.expressionlanguage.analyze.accessing;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.AccessEnum;

public final class Accessed {
    private final AccessEnum access;
    private final RootBlock type;
    private final String packageName;
    private final RootBlock parent;

    public Accessed(AccessEnum _access, String _packageName, RootBlock _parent, RootBlock _type) {
        type = _type;
        this.access = _access;
        this.packageName = _packageName;
        parent = _parent;
    }

    public Accessed(AccessEnum _access, String _packageName, RootBlock _type) {
        type = _type;
        this.access = _access;
        this.packageName = _packageName;
        parent = null;
    }

    public RootBlock getParent() {
        return parent;
    }

    public RootBlock outerParent() {
        if (type == null) {
            return null;
        }
        return type.getOuterParent();
    }

    public RootBlock getType() {
        return type;
    }

    public AccessEnum getAccess() {
        return access;
    }

    public String getPackageName() {
        return packageName;
    }

}
