package code.expressionlanguage.analyze.accessing;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.analyze.blocks.AccessibleBlock;

public final class Accessed implements AccessibleBlock {
    private final AccessEnum access;
    private final String packageName;
    private final String parentFullName;
    private final String fullName;
    private final String outerFullName;

    public Accessed(AccessEnum _access, String _packageName, String _parentFullName, String _fullName, String _outerFullName) {
        this.access = _access;
        this.packageName = _packageName;
        this.parentFullName = _parentFullName;
        this.fullName = _fullName;
        this.outerFullName = _outerFullName;
    }

    public Accessed(AccessEnum _access, String _packageName, String _fullName, String _outerFullName) {
        this.access = _access;
        this.packageName = _packageName;
        this.parentFullName = "";
        this.fullName = _fullName;
        this.outerFullName = _outerFullName;
    }

    @Override
    public String getParentFullName() {
        return parentFullName;
    }

    @Override
    public AccessEnum getAccess() {
        return access;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getOuterFullName() {
        return outerFullName;
    }
}
