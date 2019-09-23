package code.expressionlanguage.guicompos;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

import java.awt.event.ActionEvent;

public final class ActionEventStruct implements Struct {
    private String className;
    public ActionEventStruct(String _className) {
        className = _className;
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }
}
