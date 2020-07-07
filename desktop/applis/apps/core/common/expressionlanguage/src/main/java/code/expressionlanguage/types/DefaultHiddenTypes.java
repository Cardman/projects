package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.exec.blocks.ExecAccessingImportingBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;

public final class DefaultHiddenTypes implements AbstractHiddenTypes {
    private final ContextEl context;

    public DefaultHiddenTypes(ContextEl context) {
        this.context = context;
    }

    @Override
    public boolean isHidden(ExecAccessingImportingBlock _global, RootBlock _type) {
        Accessed a_ = new Accessed(_type.getAccess(), _type.getPackageName(), _type.getParentFullName(), _type.getFullName(), _type.getOuterFullName());
        return _global.isTypeHidden(a_, context);
    }
}
