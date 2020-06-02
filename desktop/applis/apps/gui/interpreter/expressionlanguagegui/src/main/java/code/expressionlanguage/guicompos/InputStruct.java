package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.Struct;

public abstract class InputStruct extends CustComponentStruct {
    protected InputStruct(String _className) {
        super(_className);
    }
    public abstract Struct isEnabled();
    public abstract void setEnabled(Struct _enabled);
}
