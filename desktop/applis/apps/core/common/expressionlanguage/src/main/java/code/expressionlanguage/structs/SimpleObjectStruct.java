package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public final class SimpleObjectStruct extends WithoutParentIdStruct {

    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getContent().getCoreNames().getAliasObject();
    }

}
