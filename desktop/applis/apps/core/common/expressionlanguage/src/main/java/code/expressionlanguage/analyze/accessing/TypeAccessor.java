package code.expressionlanguage.analyze.accessing;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.exec.blocks.AccessibleBlock;
import code.expressionlanguage.exec.blocks.ExecAccessingImportingBlock;


public final class TypeAccessor implements ExecAccessingImportingBlock {
    private final String fullName;

    public TypeAccessor(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean isTypeHidden(AccessibleBlock _class, ContextEl _analyzable) {
        return !ContextUtil.canAccessType(fullName, _class, _analyzable);
    }

}
