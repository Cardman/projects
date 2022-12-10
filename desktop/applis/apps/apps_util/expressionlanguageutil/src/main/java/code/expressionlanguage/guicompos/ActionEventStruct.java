package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.gui.AbsCtrlKeyState;

public final class ActionEventStruct extends WithoutParentIdStruct implements Struct {
    private final String className;
    private final AbsCtrlKeyState state;
    private final String command;
    public ActionEventStruct(String _className, AbsCtrlKeyState _state, String _command) {
        className = _className;
        state = _state;
        command = _command;
    }

    public AbsCtrlKeyState getState() {
        return state;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

}
