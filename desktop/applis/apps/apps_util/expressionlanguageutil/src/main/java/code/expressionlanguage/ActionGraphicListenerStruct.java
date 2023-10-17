package code.expressionlanguage;

import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.structs.AbsFieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.events.AbsActionListener;
import code.util.CustList;

public final class ActionGraphicListenerStruct extends AbsFieldableStruct implements AbsActionListener {
    private final AbsActionListener actionListener;
    public ActionGraphicListenerStruct(String _className, CustList<ClassFieldStruct> _fields, Struct _parent, String _parentClassName, int _ordinal, String _name, AbsActionListener _ac) {
        super(_className, _fields, _parent, _parentClassName, _ordinal, _name);
        actionListener = _ac;
    }

    @Override
    public void action() {
        actionListener.action();
    }
}
