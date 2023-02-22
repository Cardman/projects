package code.expressionlanguage.guicompos;

import code.expressionlanguage.WithoutParentIdClassStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class CommandStruct extends WithoutParentIdClassStruct {
    private Struct binding = NullStruct.NULL_VALUE;
    private Struct action = NullStruct.NULL_VALUE;
    public CommandStruct(String _cl) {
        super(_cl);
    }

    public Struct getBinding() {
        return binding;
    }

    public void setBinding(Struct _b) {
        this.binding = _b;
    }

    public Struct getAction() {
        return action;
    }

    public void setAction(Struct _a) {
        this.action = _a;
    }
}
