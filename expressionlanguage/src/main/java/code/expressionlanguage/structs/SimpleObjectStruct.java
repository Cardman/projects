package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;

public final class SimpleObjectStruct extends WithoutParentIdStruct implements Struct {

    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getAliasObject();
    }

}
