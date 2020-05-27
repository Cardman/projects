package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;

public final class ActionEventStruct extends WithoutParentIdStruct implements Struct {
    private String className;
    public ActionEventStruct(String _className) {
        className = _className;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

}
