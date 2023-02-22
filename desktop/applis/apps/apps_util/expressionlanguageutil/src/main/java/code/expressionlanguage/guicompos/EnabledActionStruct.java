package code.expressionlanguage.guicompos;

import code.expressionlanguage.WithoutParentIdClassStruct;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.events.AbsEnabledAction;

public final class EnabledActionStruct extends WithoutParentIdClassStruct {
    private final Struct action;
    private final AbsEnabledAction controller;
    public EnabledActionStruct(String _cl, Struct _act, AbsEnabledAction _ctrl) {
        super(_cl);
        action = _act;
        controller = _ctrl;
    }
    public Struct enabled() {
        return BooleanStruct.of(controller.isEnabled());
    }
    public void enabled(Struct _str) {
        controller.setEnabled(BooleanStruct.isTrue(_str));
    }

    public AbsEnabledAction getController() {
        return controller;
    }

    public Struct getAction() {
        return action;
    }
}
