package code.expressionlanguage.analyze.accessing;

import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.analyze.blocks.AccessibleBlock;

public final class Accessed implements AccessibleBlock {
    private final AccessEnum access;
    private final String packageName;
    private final String parentFullName;
    private final String fullName;
    private final String outerFullName;

    public Accessed(AccessEnum access, String packageName, String parentFullName, String fullName, String outerFullName) {
        this.access = access;
        this.packageName = packageName;
        this.parentFullName = parentFullName;
        this.fullName = fullName;
        this.outerFullName = outerFullName;
    }

    public Accessed(AccessEnum access, String packageName, String fullName, String outerFullName) {
        this.access = access;
        this.packageName = packageName;
        this.parentFullName = "";
        this.fullName = fullName;
        this.outerFullName = outerFullName;
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
